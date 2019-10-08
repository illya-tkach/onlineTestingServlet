package org.itstep.controller.command.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.itstep.controller.command.Command;
import org.itstep.dto.QuestionDTO;
import org.itstep.service.QuestionService;
import org.itstep.service.impl.QuestionServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class QuestionCommand implements Command {

    private static final Logger log = Logger.getLogger(QuestionCommand.class);

    QuestionService questionService = QuestionServiceImpl.getInstance();

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
        HttpSession session = request.getSession();
        List<QuestionDTO> questionDTOS = (List<QuestionDTO>) session.getAttribute("questionList");
        long questionID = Long.parseLong(request.getParameter("question"));
        QuestionDTO nextQuestion = questionService.getNextQuestion(questionID, questionDTOS);

        try {
            response.setContentType("application/json");
            String json = new ObjectMapper().writeValueAsString(nextQuestion);
            response.getWriter().write(json);
            return "response:";
        } catch (IOException e) {
            log.info("Exception when retrieve data");
            e.printStackTrace();
        }

        request.setAttribute("error", "Error in QuestionCommand");
        return "/WEB-INF/error.jsp";

    }

    private String doPost(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
