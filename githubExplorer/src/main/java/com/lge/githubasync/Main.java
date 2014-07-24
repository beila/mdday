package com.lge.githubasync;

import java.util.concurrent.TimeoutException;

import com.lge.githubasync.api.GitHubApiService;
import com.lge.githubasync.api.GitHubData;

/**
 * Driver class for this program.
 * 
 * DO NOT MODIFY THIS!
 */
public class Main {
    public static void main(String[] args) throws Exception {
        GitHubApiService api = new GitHubApiService(new GitHubData());
        Logger log = new Logger(Logger.NORMAL);
        MainApp app = new MainApp(api, log);

        long start = System.currentTimeMillis();

        app.printAllProjects();
        app.printContributorsOfProject("lge/g3");
        app.printFavoriteProjectsOfContributorsOfProject("lge/g3");

        long elapsed = System.currentTimeMillis() - start;
        if (elapsed > 500) {
            throw new TimeoutException("500ms timelimit exceeded!");
        }
    }
}
