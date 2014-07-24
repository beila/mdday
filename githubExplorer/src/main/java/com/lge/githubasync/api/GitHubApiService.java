package com.lge.githubasync.api;

import java.util.List;

/**
 * Test-purpose GitHubApi implementation. Every requests runs on a separate thread and callback is
 * called on that thread.
 * 
 * DO NOT MODIFY THIS!
 */
public class GitHubApiService implements GitHubApi {
    private GitHubData data;

    public GitHubApiService(GitHubData data) {
        this.data = data;
    }

    @Override
    public void getContributors(final String projectName, final Callback<List<Contributor>> callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                callback.success(data.getContributors(projectName));
            }
        }).start();
    }

    @Override
    public void getStarredProjects(final String userName, final Callback<List<Project>> callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                callback.success(data.getStarredProjects(userName));
            }
        }).start();
    }

    @Override
    public void getProjects(final Callback<List<Project>> callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                callback.success(data.getProjects());
            }
        }).start();
    }

}
