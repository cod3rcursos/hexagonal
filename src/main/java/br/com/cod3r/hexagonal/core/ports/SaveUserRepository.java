package br.com.cod3r.hexagonal.core.ports;

import br.com.cod3r.hexagonal.core.entities.User;

public interface SaveUserRepository {
    
    public User save(User user);
}
