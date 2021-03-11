package br.com.cod3r.hexagonal.domain.valueobjects;

import br.com.cod3r.hexagonal.domain.exceptions.InvalidNameException;

public class Name implements Comparable<Name> {
    private String value;

    private int minSize;
    private int maxSize;
    private int minNumberOfWords;
    private int maxNumberOfWords;

    Name() {

    }

    public Name(String value) {
        this(value, 5, 2);
    }

    public Name(String value, int minSize, int minNumberOfWords) {
        this(value, minSize, minNumberOfWords, 255, 6);
    }

    public Name(String value, int minSize, int minNumberOfWords, int maxSize, int maxNumberOfWords) {
        this.minSize = minSize;
        this.minNumberOfWords = minNumberOfWords;
        this.maxSize = maxSize;
        this.maxNumberOfWords = maxNumberOfWords;

        if (value != null) {
            this.value = value.trim();
        }

        if (isInvalid()) {
            throw new InvalidNameException();
        }
    }

    public String getValue() {
        return value;
    }

    public boolean isInvalid() {
        if (value == null) {
            return true;
        }

        boolean hasMinSize = value.length() >= minSize;
        boolean hasMaxSize = value.length() <= maxSize;
        boolean hasMinNumberOfWords = value.split(" ").length >= minNumberOfWords;
        boolean hasMaxNumberOfWords = value.split(" ").length <= maxNumberOfWords;
        return !hasMinSize || !hasMaxSize || !hasMinNumberOfWords || !hasMaxNumberOfWords;
    }

    @Override
    public int compareTo(Name o) {
        return value.compareTo(o.value);
    }

    @Override
    public String toString() {
        return value;
    }
}
