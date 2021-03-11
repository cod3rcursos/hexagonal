package br.com.cod3r.hexagonal.adapters.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cod3r.hexagonal.adapters.dto.LoginSession;
import br.com.cod3r.hexagonal.adapters.dto.UserDTO;
import br.com.cod3r.hexagonal.domain.entities.User;
import br.com.cod3r.hexagonal.domain.exceptions.EmailNotFoundException;
import br.com.cod3r.hexagonal.domain.exceptions.WrongPasswordException;
import br.com.cod3r.hexagonal.domain.ports.UserRepository;
import br.com.cod3r.hexagonal.domain.services.Login;

@RestController
@RequestMapping("/auth")
public class AuthRest {

    @Autowired
    private UserRepository userRepo;

    @PostMapping(path = "login")
    public LoginSession login(UserDTO userDTO) {
        Login login = new Login(userRepo);
        User user = login.execute(userDTO.toUser());
        return new LoginSession(user, "fake token...:)");
    }

    @ExceptionHandler({ EmailNotFoundException.class, WrongPasswordException.class })
    public ResponseEntity<String> wrongEmailAndPassword(RuntimeException e) {
        return new ResponseEntity<String>("Usuário/Senha inválidos", HttpStatus.BAD_REQUEST);
    }
}
