package br.com.cod3r.hexagonal.domain.valueobjects;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import br.com.cod3r.hexagonal.domain.exceptions.InvalidPasswordException;

public class Password {

    private static final String ID = "$31$";
    private static final String ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final int SIZE = 128;
    private static final Pattern hashPattern = Pattern.compile("\\$31\\$(\\d\\d?)\\$(.{43})");
    private static final int COST = 16;
    private static final SecureRandom random = new SecureRandom();

    private char[] raw;
    private String encoded;

    Password() {
    }

    public Password(CharSequence raw) {
        this(raw.toString());
    }

    public Password(String raw) {
        this.raw = raw.toCharArray();
        this.encoded = encodePassword(this.raw);

        if (isInvalid()) {
            throw new InvalidPasswordException();
        }
    }

    public static Password fromRaw(CharSequence raw) {
        return new Password(raw);
    }

    public static Password fromRaw(String raw) {
        return new Password(raw);
    }

    public static Password fromEncoded(String encoded) {
        Password password = new Password();
        password.encoded = encoded;
        return password;
    }

    public String getEncoded() {
        return encoded;
    }

    private boolean isInvalid() {
        return raw == null || raw.length < 6;
    }

    public boolean same(Password other) {
        if (raw != null) {
            return authenticate(raw, other.encoded);
        } else if (other.raw != null) {
            return authenticate(other.raw, encoded);
        } else {
            return false;
        }
    }

    private String encodePassword(char[] limpa) {
        byte[] salt = new byte[SIZE / 8];
        random.nextBytes(salt);
        byte[] dk = pbkdf2(limpa, salt, 1 << COST);
        byte[] hash = new byte[salt.length + dk.length];
        System.arraycopy(salt, 0, hash, 0, salt.length);
        System.arraycopy(dk, 0, hash, salt.length, dk.length);
        Base64.Encoder enc = Base64.getUrlEncoder().withoutPadding();
        return ID + COST + '$' + enc.encodeToString(hash);
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations) {
        KeySpec spec = new PBEKeySpec(password, salt, iterations, SIZE);
        try {
            SecretKeyFactory f = SecretKeyFactory.getInstance(ALGORITHM);
            return f.generateSecret(spec).getEncoded();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    private boolean authenticate(char[] raw, String encoded) {
        Matcher m = hashPattern.matcher(encoded);
        if (!m.matches()) {
            return false;
        }
        int iterations = iterations(Integer.parseInt(m.group(1)));
        byte[] hash = Base64.getUrlDecoder().decode(m.group(2));
        byte[] salt = Arrays.copyOfRange(hash, 0, SIZE / 8);
        byte[] check = pbkdf2(raw, salt, iterations);
        int zero = 0;
        for (int idx = 0; idx < check.length; ++idx) {
            zero |= hash[salt.length + idx] ^ check[idx];
        }
        return zero == 0;
    }

    private static int iterations(int cost) {
        return 1 << cost;
    }

    @Override
    public String toString() {
        return encoded;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Password other = (Password) obj;
        if (encoded == null) {
            if (other.encoded != null)
                return false;
        }
        return same(other);
    }
}
