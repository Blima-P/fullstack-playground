const express = require('express');
const app = express();
const port = 3000;

// Dados do seu currículo
const resumeData = {
    personalInfo: {
        name: 'Pedro Braga de Lima',
        email: 'Pbl0812@gmail.com',
        github: 'https://github.com/Blima-P',
        linkedin: 'https://www.linkedin.com/in/pedro-braga-de-lima-633717303/'
    },
    summary: 'Estudante de Engenharia de Software na Universidade Católica de Brasília (UCB), atualmente atuando como Estagiário de TI na CAESB. Tenho foco em aprender e aplicar tecnologias como Java, SQL, e outras ferramentas de desenvolvimento web e backend. Futuramente, tenho interesse em trabalhar com Inteligência Artificial.'
};

// Rota para pegar os dados do currículo
app.get('/api/resume', (req, res) => {
    res.json(resumeData);
});

app.listen(port, () => {
    console.log(`Servidor rodando em http://localhost:${port}`);
});