package br.com.cod3r.hexagonal.adapters.api;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cod3r.hexagonal.adapters.dto.UserDTO;
import br.com.cod3r.hexagonal.domain.entities.User;
import br.com.cod3r.hexagonal.domain.ports.UserRepository;
import br.com.cod3r.hexagonal.domain.services.AllUsers;
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

    @GetMapping({ "", "/{page}", "/{page}/{size}" })
    public List<UserDTO> all(@PathVariable("page") Optional<Integer> page,
            @PathVariable("size") Optional<Integer> size) {
        AllUsers allUsers = new AllUsers(userRepo);
        List<User> users = allUsers.execute(page, size);
        Stream<UserDTO> usersDTO = users.stream().map(user -> new UserDTO(user));
        return usersDTO.collect(Collectors.toList());
    }
}
