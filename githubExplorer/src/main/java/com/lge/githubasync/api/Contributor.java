package com.lge.githubasync.api;

/**
 * Represents a contributor to a project in GitHub.
 * 
 * DO NOT MODIFY THIS!
 */
public class Contributor {
    public final String name;
    public final int contributions;

    public Contributor(String name, int contributions) {
        this.name = name;
        this.contributions = contributions;
    }

    @Override
    public String toString() {
        return name;
    }
}
