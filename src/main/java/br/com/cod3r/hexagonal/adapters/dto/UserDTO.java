package br.com.cod3r.hexagonal.adapters.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.cod3r.hexagonal.domain.entities.User;

public class UserDTO {
    private Long id;
    private String name;
    private String email;
    @JsonIgnore
    private String password;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this(user.getId(), user.getName().getValue(), user.getEmail().getValue(), user.getPassword().getEncoded());
    }

    public UserDTO(String name, String email, String password) {
        this(null, name, email, password);
    }

    public UserDTO(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User toUser() {
        return new User(id, name, email, password);
    }
}
