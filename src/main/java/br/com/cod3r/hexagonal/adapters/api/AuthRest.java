package br.com.cod3r.hexagonal.adapters.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cod3r.hexagonal.adapters.dto.LoginSession;
import br.com.cod3r.hexagonal.core.entities.User;
import br.com.cod3r.hexagonal.core.exceptions.AuthException;
import br.com.cod3r.hexagonal.core.ports.PasswordVerifier;
import br.com.cod3r.hexagonal.core.ports.UserByEmailRepository;
import br.com.cod3r.hexagonal.core.usecases.Login;

@RestController
@RequestMapping("/auth")
public class AuthRest {

    @Autowired
    private UserByEmailRepository findRepo;

    @Autowired
    private PasswordVerifier passwordVerifier;

    @PostMapping(path = "login")
    public LoginSession login(String email, String password) {
        Login login = new Login(findRepo, passwordVerifier);
        User user = login.execute(email, password);
        return new LoginSession(user, "fake token...:)");
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<String> userExist(AuthException e) {
        return new ResponseEntity<String>("Usuário/Senha inválidos", HttpStatus.BAD_REQUEST);
    }
}
