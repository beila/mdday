package com.lge.githubasync;

import java.util.List;

import com.lge.githubasync.api.Callback;
import com.lge.githubasync.api.GitHubApi;
import com.lge.githubasync.api.Project;

public class MainApp {
    private GitHubApi api;
    private Logger log;

    public MainApp(GitHubApi api, Logger log) {
        this.api = api;
        this.log = log;
        // TODO Delete following line when submit
        log.setMode(Logger.VERBOSE);
    }

    // TODO Complete this method
    public void printAllProjects() throws Exception {
        // TODO Delete following example code [START]
        api.getProjects(new Callback<List<Project>>() {
            @Override
            public void success(List<Project> projects) {
                log.info("project list:" + projects);
            }
        });
        // [END]

        log.print("===== All projects in GitHub");
        // use log.print() to print result
    }

    // TODO Complete this method
    public void printContributorsOfProject(final String projectName) throws Exception {

        log.print("===== Contributors of " + projectName);
        log.print("#COMMIT\tNAME");
        // use log.print() to print result
        // result should be sorted by contributions(DESCENDING) and name(ASENDING)
    }

    // TODO Complete this method
    public void printFavoriteProjectsOfContributorsOfProject(final String projectName)
            throws Exception {

        log.print("===== Favorite projects of contributors of " + projectName);
        log.print("#STAR\tNAME");
        // use log.print() to print result
        // result should be sorted by stars(DESCENDING) and name(ASENDING)
    }

}
