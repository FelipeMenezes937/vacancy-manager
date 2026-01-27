package br.com.felipemenezes.vacancy_manager.modules.candidate;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data // Data diz pro lombok fazer getters e setters pra todos os dados da classe
public class CandidateEntity {
    private UUID id;
    private String nome;

    @NotBlank()
    @Pattern(regexp = "\\S+", message = "o campo [username] não deve conter espacos")
    private String username;

    @Email(message = "o campo Email deve conter um [email] válido")
    private String email;

    @Length(min = 10, max= 100)
    private String password;

    private String description;
    private String curriculum;


}
