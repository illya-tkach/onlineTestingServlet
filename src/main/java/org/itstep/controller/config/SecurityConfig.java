package org.itstep.controller.config;

import java.util.*;

public class SecurityConfig {
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_CLIENT = "CLIENT";

    // String: Role
    // List<String>: urlPatterns.
    private static final Map<String, List<String>> mapConfig = new HashMap<String, List<String>>();

    static {
        init();
    }

    private static void init() {

        // Configure For "USER" Role.
        List<String> urlPatterns1 = new ArrayList<String>();

        urlPatterns1.add("/getQuestion");
        urlPatterns1.add("/radioAnswered");
        urlPatterns1.add("/results");
        urlPatterns1.add("/resetAnswers");
        urlPatterns1.add("/tests");
        urlPatterns1.add("/getRandQuestions");

        mapConfig.put(ROLE_CLIENT, urlPatterns1);

        // Configure For "ADMIN" Role.
        List<String> urlPatterns2 = new ArrayList<String>();
        urlPatterns2.add("/getRating");

        mapConfig.put(ROLE_ADMIN, urlPatterns2);
    }

    public static Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }
}
