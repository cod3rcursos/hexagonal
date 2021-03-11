package br.com.cod3r.hexagonal.adapters.db;

import org.springframework.data.repository.Repository;

import br.com.cod3r.hexagonal.domain.entities.User;
import br.com.cod3r.hexagonal.domain.ports.UserRepository;

public interface UserRepositoryImpl extends Repository<User, Long>, UserRepository {

}
