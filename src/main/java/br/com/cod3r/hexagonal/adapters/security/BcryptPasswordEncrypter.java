package br.com.cod3r.hexagonal.adapters.security;

import org.springframework.stereotype.Component;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.cod3r.hexagonal.core.ports.PasswordEncrypter;

@Component
public class BcryptPasswordEncrypter implements PasswordEncrypter {

    @Override
    public String encrypt(String plainPassword) {
        char[] encryptedPassword = BCrypt
            .withDefaults()
            .hashToChar(12, plainPassword.toCharArray());
        return String.valueOf(encryptedPassword);
    }
}
