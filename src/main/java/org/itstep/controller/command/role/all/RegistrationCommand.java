package org.itstep.controller.command.role.all;

import org.itstep.controller.command.Command;
import org.itstep.controller.utils.AppUtils;
import org.itstep.dao.exception.NotUniqueUsernameException;
import org.itstep.model.entity.UserAccount;
import org.itstep.model.entity.validator.UserValidator;
import org.itstep.service.UserService;
import org.itstep.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand implements Command {

    UserService userService = UserServiceImpl.getInstance();

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
        return "/WEB-INF/views/registration.jsp";
    }

    private String doPost(HttpServletRequest request, HttpServletResponse response) {
        UserValidator userValidator = new UserValidator();

        UserAccount user = UserAccount.builder()
                .firstName(request.getParameter("firstName"))
                .lastName(request.getParameter("lastName"))
                .email(request.getParameter("email"))
                .password(request.getParameter("password"))
                .build();

        userValidator.validate(user);

        if (userValidator.hasErrors()) {
            userValidator.setErrorAttributes(request);
            return "/WEB-INF/views/registration.jsp";
        } else {
            try {
                userService.save(user);
                AppUtils.storeLoginedUser(request.getSession(), user);
                return "/index.jsp";
            } catch (NotUniqueUsernameException e) {
                String errorMessage = e.getMessage();

                request.setAttribute("errorMessage", errorMessage);

                return "/WEB-INF/views/registration.jsp";
            }
        }
    }
}
