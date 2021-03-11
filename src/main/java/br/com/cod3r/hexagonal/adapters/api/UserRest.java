package br.com.cod3r.hexagonal.adapters.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cod3r.hexagonal.adapters.dto.UserDTO;
import br.com.cod3r.hexagonal.domain.entities.User;
import br.com.cod3r.hexagonal.domain.ports.UserRepository;
import br.com.cod3r.hexagonal.domain.services.CreateNewUser;

@RestController
@RequestMapping("/api/users")
public class UserRest {

    @Autowired
    private UserRepository userRepo;

    @PostMapping
    public UserDTO save(UserDTO userDTO) {
        CreateNewUser createNewUser = new CreateNewUser(userRepo);
        User savedUser = createNewUser.execute(userDTO.toUser());
        return new UserDTO(savedUser);
    }
}
