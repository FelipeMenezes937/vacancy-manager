package br.com.felipemenezes.vacancy_manager.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

/* com o Data o Spring gerou todos os getters/setters necessários, o segundo cria um construtor */
@Data
@AllArgsConstructor 
public class ErrorMessageDTO {
    private String message;
    private String field;

}
