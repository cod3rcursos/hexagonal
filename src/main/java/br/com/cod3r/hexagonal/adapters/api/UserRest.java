package br.com.cod3r.hexagonal.adapters.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cod3r.hexagonal.core.entities.User;
import br.com.cod3r.hexagonal.core.exceptions.UserExistsException;
import br.com.cod3r.hexagonal.core.ports.PasswordEncrypter;
import br.com.cod3r.hexagonal.core.ports.SaveUserRepository;
import br.com.cod3r.hexagonal.core.ports.UserByEmailRepository;
import br.com.cod3r.hexagonal.core.usecases.CreateNewUser;

@RestController
@RequestMapping("/api/users")
public class UserRest {

    @Autowired
    private SaveUserRepository saveRepo;

    @Autowired
    private UserByEmailRepository findRepo;

    @Autowired
    private PasswordEncrypter encrypter;

    @PostMapping
    public User save(User user) {
        CreateNewUser createNewUser = new CreateNewUser(
            saveRepo, findRepo, encrypter);
        return createNewUser.execute(user);
    }

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<String> userExist(UserExistsException e) {
        return new ResponseEntity<String>("Usuário já cadastrado.",
            HttpStatus.BAD_REQUEST);
    }
}
