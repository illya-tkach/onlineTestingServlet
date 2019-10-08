package org.itstep.controller.command.impl;

import org.apache.log4j.Logger;
import org.itstep.controller.command.Command;
import org.itstep.model.entity.TestRating;
import org.itstep.service.TestRatingService;
import org.itstep.service.impl.TestRatingServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class RatingCommand implements Command {

    private static final Logger log = Logger.getLogger(QuestionCommand.class);

    private TestRatingService ratingService = TestRatingServiceImpl.getInstance();

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

        List<TestRating> testRatings = ratingService.getAllRatings();

        request.setAttribute("testRatings", testRatings);

        return "/WEB-INF/views/studentsResults.jsp";
    }

    private String doPost(HttpServletRequest request, HttpServletResponse response) {
        return "/WEB-INF/error.jsp";
    }
}
