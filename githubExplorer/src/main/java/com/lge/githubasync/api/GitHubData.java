package com.lge.githubasync.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

/**
 * Test-purpose GitHubApi Data Source. Each GitHubApi methods work as blocking call.
 * 
 * DO NOT MODIFY THIS!
 */
public class GitHubData {
    public Map<String, Map<String, Integer>> projectContributors = new HashMap<String, Map<String, Integer>>();
    public Map<String, List<String>> userStarred = new HashMap<String, List<String>>();

    String[] users = "hanjooyung chisunjoung wisang yskang daniel wonyoung hanil jyp starbear birm psh phg moon jjun bang facil hwa nature jj jy gu ok mj smith hwang byul euna hwan jenny"
            .split(" ");
    String[] projects = "lge/g3 lge/g2 lge/g lge/optimus lge/roboking lge/whisen lge/tromm lge/dios lge/gwatch lge/webos google/nexus google/android google/wear google/glass google/polymer google/chrome google/gmail google/esspresso motorola/360"
            .split(" ");
    {
        Random rand = new Random(0);
        for (int i = 0; i < 400; i++) {
            String user = users[rand.nextInt(users.length)];
            String project = projects[rand.nextInt(projects.length)];
            if (rand.nextInt(10) > 5) {
                contribute(user, project, rand.nextInt(10) + 1);
            } else {
                star(user, project);
            }
        }
    }

    private void star(String user, String project) {
        appendToMap(userStarred, user, project);
    }

    private void contribute(String user, String project, int contributions) {
        if (!projectContributors.containsKey(project)) {
            projectContributors.put(project, new HashMap<String, Integer>());
        }
        if (!projectContributors.get(project).containsKey(user)) {
            projectContributors.get(project).put(user, contributions);
        } else {
            projectContributors.get(project).put(user,
                    projectContributors.get(project).get(user) + contributions);
        }
    }

    private void appendToMap(Map<String, List<String>> map, String key, String value) {
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<String>());
        }
        if (!map.get(key).contains(value)) {
            map.get(key).add(value);
        }
    }

    public List<Contributor> getContributors(String projectName) {
        delay();
        Map<String, Integer> contributors = projectContributors.get(projectName);
        if (contributors == null)
            return Collections.<Contributor> emptyList();
        else
            return map(contributors.entrySet(), toContributor());
    }

    public List<Project> getStarredProjects(String userName) {
        delay();
        List<String> starred = userStarred.get(userName);
        if (starred == null)
            return Collections.<Project> emptyList();
        else
            return map(starred, toProject());
    }

    public List<Project> getProjects() {
        delay();
        return map(Arrays.asList(projects), toProject());
    }

    interface Func1<A, B> {
        B call(A a);
    }

    private Func1<Entry<String, Integer>, Contributor> toContributor() {
        return new Func1<Entry<String, Integer>, Contributor>() {
            @Override
            public Contributor call(Entry<String, Integer> a) {
                return new Contributor(a.getKey(), a.getValue());
            }
        };
    }

    private Func1<String, Project> toProject() {
        return new Func1<String, Project>() {
            @Override
            public Project call(String a) {
                return new Project(a);
            }
        };
    }

    private <A, B> List<B> map(Collection<A> values, Func1<A, B> f) {
        List<B> result = new ArrayList<B>();
        for (A s : values) {
            try {
                result.add(f.call(s));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private static final long API_DELAY = 100L;
    private static final long API_DELAY_RANGE = 10L;
    private Random rand = new Random();

    private long randomDelay() {
        return API_DELAY + rand.nextLong() % API_DELAY_RANGE;
    }

    private void delay() {
        try {
            Thread.sleep(randomDelay());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
