package org.itstep.controller;

import org.itstep.controller.command.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;

public class Servlet extends HttpServlet {


    CommandHolder commandHolder;

    public Servlet() {
        super();
        commandHolder = new CommandHolder();
    }

    @Override
    public void init(ServletConfig servletConfig) {
        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        path = path.replaceAll(".*/" , "");
        Command command = commandHolder.getCommand(path);
        String page = command.execute(request, response);
        if (page.contains("redirect:")){
            page = page.replaceAll("redirect:" , "");
            response.sendRedirect(request.getContextPath() + page);
            return;
        }
        if (page.contains("response:")){
            return;
        }

        request.getRequestDispatcher(page).forward(request,response);
    }
}
