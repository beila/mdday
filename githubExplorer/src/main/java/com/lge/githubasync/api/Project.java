package com.lge.githubasync.api;

/**
 * Represents a project in GitHub.
 * 
 * DO NOT MODIFY THIS!
 */
public class Project {
    public final String name;

    public Project(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
