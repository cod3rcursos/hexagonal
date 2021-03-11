package br.com.cod3r.hexagonal.adapters.dto;

import java.util.Date;

import br.com.cod3r.hexagonal.domain.entities.User;

public class LoginSession {

    private final String name;
    private final String email;
    private final String token;
    private final Date startsAt;

    public LoginSession(User user, String token) {
        this.name = user.getName().getValue();
        this.email = user.getEmail().getValue();
        this.token = token;
        this.startsAt = new Date();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public Date getStartsAt() {
        return startsAt;
    }
}
