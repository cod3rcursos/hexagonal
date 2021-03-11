package br.com.cod3r.hexagonal.domain.valueobjects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.cod3r.hexagonal.domain.exceptions.InvalidEmailException;

public class Email {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);

    private String value;

    Email() {

    }

    public Email(String value) {
        this.value = value != null ? value : "";
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(value);
        if (!matcher.find()) {
            throw new InvalidEmailException();
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getValue();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
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
        Email other = (Email) obj;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }
}
