package br.com.felipemenezes.vacancy_manager.modules.candidate.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipemenezes.vacancy_manager.modules.candidate.CandidateEntity;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @PostMapping("/")
    public String create(@Valid @RequestBody CandidateEntity candidateEntity){
        System.out.println("candidato:" + candidateEntity.getNome());
        return "usuario: " + candidateEntity.getNome();
    }
}
