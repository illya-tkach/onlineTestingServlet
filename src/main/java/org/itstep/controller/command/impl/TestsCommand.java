package org.itstep.controller.command.impl;

import org.itstep.controller.command.Command;
import org.itstep.model.entity.Test;
import org.itstep.service.TestService;
import org.itstep.service.impl.TestServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class TestsCommand implements Command {

    TestService testService = new TestServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String requestMethod = request.getMethod();

        if (requestMethod.equals("GET"))
            return doGet(request, response);
        else if (requestMethod.equals("POST"))
            return doPost(request, response);
        else
            return "/WEB-INF/error/405.jsp";
    }
    private String doGet(HttpServletRequest request, HttpServletResponse response) {

        List<Test> tests = testService.getAllTests();

        request.setAttribute("testList", tests);

        return "/WEB-INF/views/testsMenu.jsp";
    }

    private String doPost(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}