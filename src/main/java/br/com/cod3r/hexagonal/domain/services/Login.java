package br.com.cod3r.hexagonal.domain.services;

import br.com.cod3r.hexagonal.domain.entities.User;
import br.com.cod3r.hexagonal.domain.exceptions.EmailNotFoundException;
import br.com.cod3r.hexagonal.domain.exceptions.WrongPasswordException;
import br.com.cod3r.hexagonal.domain.ports.UserRepository;
import br.com.cod3r.hexagonal.domain.valueobjects.Password;

public class Login {

    private UserRepository userRepo;

    public Login(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User execute(User user) {
        User userFromDB = userRepo.findByEmail(user.getEmail());
        if (userFromDB == null) {
            throw new EmailNotFoundException();
        }

        Password informed = user.getPassword();
        Password expected = userFromDB.getPassword();

        if(!informed.same(expected)) {
            throw new WrongPasswordException();
        }

        return userFromDB;
    }
}
