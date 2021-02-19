package br.com.cod3r.hexagonal.core.ports;

public interface PasswordVerifier {

    public boolean verify(
        String plainPassword, String encryptedPassword);
}
