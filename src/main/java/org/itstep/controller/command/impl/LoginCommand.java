package org.itstep.controller.command.impl;

import org.apache.log4j.Logger;
import org.itstep.controller.command.Command;
import org.itstep.controller.utils.AppUtils;
import org.itstep.model.entity.UserAccount;
import org.itstep.model.entity.validator.UserValidator;
import org.itstep.service.UserService;
import org.itstep.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LoginCommand implements Command {

    private static final Logger log = Logger.getLogger(LoginCommand.class);

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
        return "/WEB-INF/views/login.jsp";
    }

    private String doPost(HttpServletRequest request, HttpServletResponse response) {

        UserValidator userValidator = new UserValidator();

        UserAccount user = UserAccount.builder()
                .email(request.getParameter("email"))
                .password(request.getParameter("password"))
                .build();

        userValidator.validate(user);

        if (userValidator.hasErrors()) {
            userValidator.setErrorAttributes(request);
            return "/WEB-INF/views/login.jsp";
        }

        if (AppUtils.checkUserIsLogged(request, user.getEmail())) {
            request.setAttribute("error", "User " + user.getEmail() + " Already logged in");
            log.info("Try login when already logged");
            return "/WEB-INF/error.jsp";
        }

        Optional<UserAccount> userAccount = userService.findUserByLgnAndPswrd(user.getEmail(), user.getPassword());

        if (!userAccount.isPresent()) {
            String errorMessage = "Invalid userName or Password";

            request.setAttribute("errorMessage", errorMessage);

            return "/WEB-INF/views/login.jsp";
        }

        AppUtils.storeLoginedUser(request.getSession(), userAccount.get());
        log.info("User: " + userAccount.get().getEmail() + " successfully logged in");

        int redirectId = -1;
        try {
            redirectId = Integer.parseInt(request.getParameter("redirectId"));
        } catch (Exception e) {
//            e.printStackTrace();
        }
        String requestUri = AppUtils.getRedirectAfterLoginUrl(request.getSession(), redirectId);
        if (requestUri != null) {
            return "redirect:" + requestUri;
        } else {
            // Default after successful login
            // redirect to /userInfo page
            return "redirect:/";
        }
    }

}
