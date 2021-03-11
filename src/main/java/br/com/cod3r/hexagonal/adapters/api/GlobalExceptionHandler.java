package br.com.cod3r.hexagonal.adapters.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.cod3r.hexagonal.domain.exceptions.EmailNotFoundException;
import br.com.cod3r.hexagonal.domain.exceptions.InvalidEmailException;
import br.com.cod3r.hexagonal.domain.exceptions.InvalidNameException;
import br.com.cod3r.hexagonal.domain.exceptions.InvalidPasswordException;
import br.com.cod3r.hexagonal.domain.exceptions.UserExistsException;
import br.com.cod3r.hexagonal.domain.exceptions.WrongPasswordException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ EmailNotFoundException.class, WrongPasswordException.class })
    public ResponseEntity<String> wrongEmailAndPassword(RuntimeException e) {
        return new ResponseEntity<String>("Usuário/Senha inválidos", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<String> userExists(UserExistsException e) {
        return new ResponseEntity<String>("Usuário já cadastrado.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<String> invalidEmail(InvalidEmailException e) {
        return new ResponseEntity<String>("E-mail com formato inválido.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<String> invalidPassword(InvalidPasswordException e) {
        return new ResponseEntity<String>("A senha deve ter no mínimo 6 caracteres.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidNameException.class)
    public ResponseEntity<String> invalidName(InvalidNameException e) {
        return new ResponseEntity<String>("O nome deve ter sobrenome e com no mínimo 5 letras.",
                HttpStatus.BAD_REQUEST);
    }
}
