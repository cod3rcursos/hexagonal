package br.com.cod3r.hexagonal.adapters.security;

import org.springframework.stereotype.Component;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.cod3r.hexagonal.core.ports.PasswordVerifier;

@Component
public class BcryptPasswordVerifier implements PasswordVerifier {

    @Override
    public boolean verify(String plainPassword, String encryptedPassword) {
        BCrypt.Result result = BCrypt.verifyer()
            .verify(plainPassword.toCharArray(), encryptedPassword);
        return result.verified;
    }
}
