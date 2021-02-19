package br.com.cod3r.hexagonal.core.ports;

public interface PasswordEncrypter {
    
    public String encrypt(String plainPassword);
}
