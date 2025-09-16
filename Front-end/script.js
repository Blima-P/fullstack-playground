// Alternância de tema (claro/escuro)
const toggleBtn = document.getElementById("toggleTheme");
const body = document.body;
body.classList.add("light");

toggleBtn.addEventListener("click", () => {
  body.classList.toggle("dark");
  body.classList.toggle("light");
  toggleBtn.textContent = body.classList.contains("dark") ? "☀️ Mudar Tema" : "🌙 Mudar Tema";
});

// Dados do gráfico
const canvas = document.getElementById("chart");
const ctx = canvas.getContext("2d");

const dados = [12, 48, 23, 35, 15];
const cores = ["#0077ff", "#00cc88", "#ffaa00", "#ff5555", "#9933ff"];

function desenharGrafico() {
  const total = dados.reduce((a, b) => a + b, 0);
  let anguloInicial = 0;

  dados.forEach((valor, i) => {
    const angulo = (valor / total) * 2 * Math.PI;
    ctx.beginPath();
    ctx.moveTo(150, 150);
    ctx.arc(150, 150, 120, anguloInicial, anguloInicial + angulo);
    ctx.fillStyle = cores[i];
    ctx.fill();
    anguloInicial += angulo;
  });
}

canvas.width = 300;
canvas.height = 300;
desenharGrafico();
