package br.com.felipemenezes.vacancy_manager.exceptions;

public class UserFoundException extends RuntimeException{
    public UserFoundException(){
        super("User alredy exists");
    }
    
}
