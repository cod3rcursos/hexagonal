package br.com.cod3r.hexagonal.core.usecases;

import br.com.cod3r.hexagonal.core.entities.User;
import br.com.cod3r.hexagonal.core.exceptions.AuthException;
import br.com.cod3r.hexagonal.core.ports.PasswordVerifier;
import br.com.cod3r.hexagonal.core.ports.UserByEmailRepository;

public class Login {

    private UserByEmailRepository findRepo;
    private PasswordVerifier passwordVerifier;

    public Login(UserByEmailRepository findRepo, PasswordVerifier passwordVerifier) {
        this.findRepo = findRepo;
        this.passwordVerifier = passwordVerifier;
    }

    public User execute(String email, String plainPassword) {
        User userFromDB = findRepo.findByEmail(email);
        if (userFromDB == null) {
            throw new AuthException();
        }

        String encryptedPassword = userFromDB.getPassword();
        boolean passwordMatches = passwordVerifier.verify(plainPassword, encryptedPassword);

        if (!passwordMatches) {
            throw new AuthException();
        }

        return userFromDB;
    }
}
