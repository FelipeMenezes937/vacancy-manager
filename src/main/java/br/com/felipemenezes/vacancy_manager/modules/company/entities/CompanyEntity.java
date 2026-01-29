package br.com.felipemenezes.vacancy_manager.modules.company.entities;


import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity(name = "company")// nome da coluna no banco
@Data //setei getters/setters
public class CompanyEntity {
    @Id // unique id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    @NotBlank()
    @Pattern(regexp = "\\S+", message = "o campo [username] não deve conter espacos")
    private String username;

    @Email(message = "o campo Email deve conter um [email] válido")
    private String email;

    @Length(min = 10, max= 100)
    private String password;
    private String website;
    private String description;
    
    // grava a hora a cada registro
    @CreationTimestamp
    private LocalDateTime createdAt;
}
