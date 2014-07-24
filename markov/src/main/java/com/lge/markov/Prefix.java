package com.lge.markov;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a prefix word list. When updating a prefix it keeps length the same.
 *
 */
public class Prefix {

    private List<String> prefix = new ArrayList<String>();

    /**
     * Creates a new prefix instance with n-repeated words.
     * 
     * @param n
     * @param word
     */
    public Prefix(int n, String word) {
        for (int i = 0; i < n; i++) {
            prefix.add(word);
        }
    }

    /**
     * Creates a copy from other Prefix instance.
     * 
     * @param other
     */
    public Prefix(Prefix other) {
        prefix.addAll(other.prefix);
    }

    // TODO Add methods here if necessary
}
