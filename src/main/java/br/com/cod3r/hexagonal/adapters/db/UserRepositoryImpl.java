package br.com.cod3r.hexagonal.adapters.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import br.com.cod3r.hexagonal.domain.entities.User;
import br.com.cod3r.hexagonal.domain.ports.UserRepository;
import br.com.cod3r.hexagonal.domain.valueobjects.Email;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserJpaRepository repo;

    @Override
    public User save(User user) {
        return repo.save(user);
    }

    @Override
    public User findByEmail(Email email) {
        return repo.findByEmail(email);
    }

    @Override
    public List<User> findAll(int page, int size) {
        Page<User> users = repo.findAll(PageRequest.of(page, size));
        return users.toList();
    }
}
