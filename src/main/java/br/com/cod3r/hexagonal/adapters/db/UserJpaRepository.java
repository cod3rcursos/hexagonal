package br.com.cod3r.hexagonal.adapters.db;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.cod3r.hexagonal.domain.entities.User;
import br.com.cod3r.hexagonal.domain.valueobjects.Email;

public interface UserJpaRepository extends JpaRepository<User, Long> {

    public User findByEmail(Email email);

    public Page<User> findAll(Pageable pageable);
}
