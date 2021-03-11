package br.com.cod3r.hexagonal.domain.ports;

import br.com.cod3r.hexagonal.domain.entities.User;
import br.com.cod3r.hexagonal.domain.valueobjects.Email;

public interface UserRepository {

    public User save(User user);

    public User findByEmail(Email email);
}
