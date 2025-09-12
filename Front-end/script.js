// Elementos
const btnAdd = document.getElementById('btnAdd');
const modal = document.getElementById('modal');
const closeModal = document.getElementById('closeModal');
const cancel = document.getElementById('cancel');
const form = document.getElementById('form');
const tableBody = document.querySelector('#table tbody');
const emptyText = document.getElementById('emptyText');
const searchInput = document.getElementById('search');
const modalTitle = document.getElementById('modalTitle');

let editingId = null; // id do item sendo editado

// KEY no localStorage
const STORAGE_KEY = 'cadastros_v2';

// carregar lista do localStorage
function loadCadastros() {
  const raw = localStorage.getItem(STORAGE_KEY);
  try {
    return raw ? JSON.parse(raw) : [];
  } catch {
    return [];
  }
}

// salvar lista
function saveCadastros(list) {
  localStorage.setItem(STORAGE_KEY, JSON.stringify(list));
}

// gerar id simples
function newId() {
  return Date.now().toString(36) + Math.random().toString(36).slice(2,6);
}

// renderizar tabela
function render(filter = '') {
  const list = loadCadastros();
  const filtered = list.filter(item => {
    const q = filter.trim().toLowerCase();
    if (!q) return true;
    return item.name.toLowerCase().includes(q) || item.email.toLowerCase().includes(q) || (item.phone||'').toLowerCase().includes(q);
  });

  tableBody.innerHTML = '';
  if (filtered.length === 0) {
    emptyText.style.display = 'block';
  } else {
    emptyText.style.display = 'none';
    filtered.forEach(item => {
      const tr = document.createElement('tr');
      tr.innerHTML = `
        <td>${escapeHtml(item.name)}</td>
        <td>${escapeHtml(item.email)}</td>
        <td>${escapeHtml(item.phone || '')}</td>
        <td>
          <div class="btn-row">
            <button class="edit" data-id="${item.id}">Editar</button>
            <button class="del" data-id="${item.id}">Excluir</button>
          </div>
        </td>
      `;
      tableBody.appendChild(tr);
    });
  }
}

// evitar HTML injection simples
function escapeHtml(s = '') {
  return String(s)
    .replaceAll('&','&amp;')
    .replaceAll('<','&lt;')
    .replaceAll('>','&gt;')
    .replaceAll('"','&quot;')
    .replaceAll("'",'&#39;');
}

// abrir modal para novo
btnAdd.addEventListener('click', () => {
  editingId = null;
  modalTitle.textContent = 'Novo cadastro';
  form.name.value = '';
  form.email.value = '';
  form.phone.value = '';
  openModal();
});

// abrir / fechar
function openModal(){ modal.classList.remove('hidden'); form.name.focus(); }
function close(){ modal.classList.add('hidden'); }

// fechar eventos
closeModal.addEventListener('click', close);
cancel.addEventListener('click', close);
modal.addEventListener('click', (e) => {
  if (e.target === modal) close();
});

// submit do formulario
form.addEventListener('submit', (e) => {
  e.preventDefault();
  const name = form.name.value.trim();
  const email = form.email.value.trim();
  const phone = form.phone.value.trim();

  if (!name || !email) {
    alert('Nome e email são obrigatórios.');
    return;
  }

  let list = loadCadastros();

  if (editingId) {
    // editar
    list = list.map(item => item.id === editingId ? { ...item, name, email, phone } : item);
    editingId = null;
  } else {
    // adicionar
    const item = { id: newId(), name, email, phone, createdAt: Date.now() };
    list.unshift(item); // adiciona no topo
  }

  saveCadastros(list);
  render(searchInput.value);
  form.reset();
  close();
});

// delegação de eventos para editar/excluir
tableBody.addEventListener('click', (e) => {
  const btn = e.target.closest('button');
  if (!btn) return;
  const id = btn.dataset.id;
  if (btn.classList.contains('del')) {
    if (!confirm('Excluir esse cadastro?')) return;
    let list = loadCadastros();
    list = list.filter(x => x.id !== id);
    saveCadastros(list);
    render(searchInput.value);
    return;
  }
  if (btn.classList.contains('edit')) {
    const list = loadCadastros();
    const item = list.find(x => x.id === id);
    if (!item) return;
    editingId = id;
    modalTitle.textContent = 'Editar cadastro';
    form.name.value = item.name;
    form.email.value = item.email;
    form.phone.value = item.phone || '';
    openModal();
    return;
  }
});

// busca em tempo real
searchInput.addEventListener('input', (e) => render(e.target.value));

// init
(function init(){
  // criar campos do form com nomes acessíveis (para ease)
  form.name = document.getElementById('name');
  form.email = document.getElementById('email');
  form.phone = document.getElementById('phone');

  render();
})();
