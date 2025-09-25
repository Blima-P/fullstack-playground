package com.pedrobraga.cv.controller;

import com.pedrobraga.cv.model.Resume;
import com.pedrobraga.cv.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    @Autowired
    private ResumeRepository resumeRepository;

    @GetMapping
    public Resume getResume() {
        // Por enquanto, vamos retornar um objeto estático
        Resume resume = new Resume();
        resume.setName("Pedro Braga de Lima");
        resume.setEmail("Pbl0812@gmail.com");
        resume.setGithub("https://github.com/Blima-P");
        resume.setLinkedin("https://www.linkedin.com/in/pedro-braga-de-lima-633717303/");
        resume.setSummary("Estudante de Engenharia de Software na Universidade Católica de Brasília (UCB), atualmente atuando como Estagiário de TI na CAESB. Tenho foco em aprender e aplicar tecnologias como Java, SQL, e outras ferramentas de desenvolvimento web e backend. Futuramente, tenho interesse em trabalhar com Inteligência Artificial.");
        return resume;
    }
}