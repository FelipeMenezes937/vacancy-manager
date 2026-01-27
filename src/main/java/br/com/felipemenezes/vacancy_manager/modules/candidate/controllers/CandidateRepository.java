package br.com.felipemenezes.vacancy_manager.modules.candidate.controllers;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.felipemenezes.vacancy_manager.modules.candidate.CandidateEntity;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID>{
    
    
} 