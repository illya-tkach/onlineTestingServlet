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

        // Configure For "CLIENT" Role.
        List<String> urlPatterns1 = new ArrayList<String>();

        urlPatterns1.add("/userInfo");
        urlPatterns1.add("/employeeTask");
        urlPatterns1.add("/personal");
        urlPatterns1.add("/booking");


        mapConfig.put(ROLE_CLIENT, urlPatterns1);

        // Configure For "ADMIN" Role.
        List<String> urlPatterns2 = new ArrayList<String>();
        urlPatterns2.add("/recordList");
        urlPatterns2.add("/userInfo");
        urlPatterns2.add("/managerTask");
        urlPatterns2.add("/newRecord");

        mapConfig.put(ROLE_ADMIN, urlPatterns2);
    }

    public static Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }
}
