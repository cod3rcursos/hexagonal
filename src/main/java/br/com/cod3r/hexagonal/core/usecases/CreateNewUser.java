package br.com.cod3r.hexagonal.core.usecases;

import br.com.cod3r.hexagonal.core.entities.User;
import br.com.cod3r.hexagonal.core.exceptions.UserExistsException;
import br.com.cod3r.hexagonal.core.ports.PasswordEncrypter;
import br.com.cod3r.hexagonal.core.ports.SaveUserRepository;
import br.com.cod3r.hexagonal.core.ports.UserByEmailRepository;

public class CreateNewUser {

    private SaveUserRepository saveRepo;
    private UserByEmailRepository findRepo;
    private PasswordEncrypter encrypter;

    public CreateNewUser(SaveUserRepository saveRepo, 
        UserByEmailRepository findRepo,
        PasswordEncrypter encrypter) {
        this.saveRepo = saveRepo;
        this.findRepo = findRepo;
        this.encrypter = encrypter;
    }

    public User execute(User user) {
        User userFromDB = findRepo.findByEmail(user.getEmail());

        if (userFromDB != null) {
            throw new UserExistsException();
        }

        // Validar atributos...

        String encryptedPassword = encrypter.encrypt(user.getPassword());
        user.setPassword(encryptedPassword);

        return saveRepo.save(user);
    }
}
