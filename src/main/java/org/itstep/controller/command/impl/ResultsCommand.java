package org.itstep.controller.command.impl;

import org.itstep.controller.command.Command;
import org.itstep.dto.QuestionDTO;
import org.itstep.model.entity.UserAccount;
import org.itstep.service.TestService;
import org.itstep.service.impl.TestServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ResultsCommand implements Command {

    TestService testService = new TestServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<QuestionDTO> questionDTOS = (List<QuestionDTO>) request.getSession().getAttribute("questionList");
        int totalPoints = testService.calculatePoints(questionDTOS);
        long testID = (Long) request.getSession().getAttribute("currentTestID");
        UserAccount user = (UserAccount) request.getSession().getAttribute("loginedUser");

        testService.saveRating(testID, user.getId(), totalPoints);

        request.setAttribute("totalPoints", totalPoints);

        request.setAttribute("questionList", questionDTOS);

        return "/WEB-INF/views/resultPage.jsp";
    }
}
