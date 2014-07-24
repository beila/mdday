package com.lge.markov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Represents a prefix word list. When updating a prefix it keeps length the same.
 *
 */
public class Prefix {

    final List<String> prefix;

    /**
     * Creates a new prefix instance with n-repeated words.
     * 
     * @param n
     * @param word
     */
    public Prefix(int n, String word) {
        prefix = Collections.nCopies(n, word);
    }

    /**
     * Creates a copy from other Prefix instance.
     * 
     * @param other
     */
    public Prefix(Prefix other) {
        prefix = other.prefix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Prefix prefix1 = (Prefix) o;

        return Arrays.deepEquals(prefix.toArray(), prefix1.prefix.toArray());

    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(prefix.toArray());
    }

    public Prefix(Prefix other, String word) {
        List<String> before = new ArrayList<>(other.prefix);
        before.remove(0);
        before.add(word);
        prefix = Collections.unmodifiableList(before);
    }

    @Override
    public String toString() {
        return Arrays.deepToString(prefix.toArray());
    }
}
