package com.lge.githubasync.api;

/**
 * Used by GitHubApi interface.
 *
 * DO NOT MODIFY THIS!
 * 
 * @param <T>
 */
public interface Callback<T> {
    /**
     * Called when an asynchronous call succeeds.
     * 
     * @param t
     */
    void success(T t);
}
