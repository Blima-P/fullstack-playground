class Usuario {
  constructor(nome, email, idade) {
    this.nome = nome;
    this.email = email;
    this.idade = idade;
  }
}

class Cadastro {
  constructor() {
    this.usuarios = JSON.parse(localStorage.getItem("usuarios")) || [];
    this.tabela = document.querySelector("#tabelaUsuarios tbody");
    this.form = document.querySelector("#cadastroForm");
    this.modoEscuroBtn = document.querySelector("#toggleTheme");

    this.renderizar();
    this.eventos();
  }

  eventos() {
    this.form.addEventListener("submit", (e) => {
      e.preventDefault();
      this.adicionarUsuario();
    });

    this.modoEscuroBtn.addEventListener("click", () => {
      document.body.classList.toggle("dark");
    });
  }

  adicionarUsuario() {
    const nome = document.querySelector("#nome").value.trim();
    const email = document.querySelector("#email").value.trim();
    const idade = document.querySelector("#idade").value.trim();

    if (!nome || !email || !idade) {
      alert("Preencha todos os campos!");
      return;
    }

    const novo = new Usuario(nome, email, idade);
    this.usuarios.push(novo);
    this.salvar();
    this.renderizar();

    this.form.reset();
  }

  editarUsuario(index) {
    const usuario = this.usuarios[index];
    document.querySelector("#nome").value = usuario.nome;
    document.querySelector("#email").value = usuario.email;
    document.querySelector("#idade").value = usuario.idade;

    this.usuarios.splice(index, 1);
    this.salvar();
    this.renderizar();
  }

  excluirUsuario(index) {
    if (confirm("Tem certeza que deseja excluir este usuário?")) {
      this.usuarios.splice(index, 1);
      this.salvar();
      this.renderizar();
    }
  }

  salvar() {
    localStorage.setItem("usuarios", JSON.stringify(this.usuarios));
  }

  renderizar() {
    this.tabela.innerHTML = "";

    this.usuarios.forEach((usuario, index) => {
      const row = document.createElement("tr");

      row.innerHTML = `
        <td>${usuario.nome}</td>
        <td>${usuario.email}</td>
        <td>${usuario.idade}</td>
        <td>
          <button class="action-btn edit">Editar</button>
          <button class="action-btn delete">Excluir</button>
        </td>
      `;

      row.querySelector(".edit").addEventListener("click", () => this.editarUsuario(index));
      row.querySelector(".delete").addEventListener("click", () => this.excluirUsuario(index));

      this.tabela.appendChild(row);
    });
  }
}

document.addEventListener("DOMContentLoaded", () => new Cadastro());
