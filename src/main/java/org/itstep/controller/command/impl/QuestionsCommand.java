package org.itstep.controller.command.impl;

import org.itstep.controller.command.Command;
import org.itstep.dto.QuestionDTO;
import org.itstep.dto.mapper.QuestionMapper;
import org.itstep.model.entity.Question;
import org.itstep.service.QuestionService;
import org.itstep.service.impl.QuestionServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class QuestionsCommand implements Command {

    QuestionService questionService = new QuestionServiceImpl();

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

        long testID = Long.parseLong(request.getParameter("test"));

        List<Question> questions = questionService.getAllQuestionsByTestID(testID);
        List<QuestionDTO> questionDTOS = QuestionMapper.INSTANCE.fromQuestions(questions);

        HttpSession session = request.getSession();
        session.setAttribute("questionList", questionDTOS);
        session.setAttribute("currentTestID", testID);
        request.setAttribute("questionList", questionDTOS);

        return "/WEB-INF/views/test.jsp";
    }

    private String doPost(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
