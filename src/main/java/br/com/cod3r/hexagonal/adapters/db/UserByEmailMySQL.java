package br.com.cod3r.hexagonal.adapters.db;

import org.springframework.data.repository.Repository;

import br.com.cod3r.hexagonal.core.entities.User;
import br.com.cod3r.hexagonal.core.ports.UserByEmailRepository;

public interface UserByEmailMySQL 
    extends Repository<User, Long>, UserByEmailRepository {

}
