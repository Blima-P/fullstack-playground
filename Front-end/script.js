const form = document.getElementById("cadastroForm");
const tabela = document.querySelector("#tabela tbody");

form.addEventListener("submit", function (e) {
    e.preventDefault();

    const nome = document.getElementById("nome").value;
    const email = document.getElementById("email").value;
    const telefone = document.getElementById("telefone").value;

    const novaLinha = document.createElement("tr");

    novaLinha.innerHTML = `
    <td>${nome}</td>
    <td>${email}</td>
    <td>${telefone}</td>
    <td><button class="delete-btn">Excluir</button></td>
  `;

    tabela.appendChild(novaLinha);

    // Limpa os campos
    form.reset();

    // Evento para excluir cadastro
    novaLinha.querySelector(".delete-btn").addEventListener("click", () => {
        novaLinha.remove();
    });
});
