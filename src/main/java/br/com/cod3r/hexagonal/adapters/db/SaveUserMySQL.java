package br.com.cod3r.hexagonal.adapters.db;

import br.com.cod3r.hexagonal.core.entities.User;
import br.com.cod3r.hexagonal.core.ports.SaveUserRepository;

import org.springframework.data.repository.Repository;

public interface SaveUserMySQL 
    extends Repository<User, Long>, SaveUserRepository {

}
