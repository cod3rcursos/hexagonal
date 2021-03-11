package br.com.cod3r.hexagonal.domain.entities;

import br.com.cod3r.hexagonal.domain.valueobjects.Email;
import br.com.cod3r.hexagonal.domain.valueobjects.Name;
import br.com.cod3r.hexagonal.domain.valueobjects.Password;

public class User {
    private Long id;
    private Name name;
    private Email email;
    private Password password;

    User() {

    }

    public User(Long id, String name, String email, String password) {
        this(id, new Name(name), new Email(email), new Password(password));
    }

    public User(Long id, Name name, Email email, Password password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
