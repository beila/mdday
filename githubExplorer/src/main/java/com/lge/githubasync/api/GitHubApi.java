package com.lge.githubasync.api;

import java.util.List;

/**
 * Interface for GitHub access APIs. Each method requires a Callback.
 * 
 * DO NOT MODIFY THIS!
 */
public interface GitHubApi {
    /**
     * Retrieves the list of contributors of a GitHub project.
     * @param projectName
     * @param callback
     */
    void getContributors(String projectName, Callback<List<Contributor>> callback);

    /**
     * Retrieves the list of projects starred by a user.
     * @param userName
     * @param callback
     */
    void getStarredProjects(String userName, Callback<List<Project>> callback);

    /**
     * Retrieves the list of all projects hosted on GitHub.
     * @param callback
     */
    void getProjects(Callback<List<Project>> callback);
}
