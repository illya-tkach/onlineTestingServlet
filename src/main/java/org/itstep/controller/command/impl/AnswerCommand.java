package org.itstep.controller.command.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.itstep.controller.command.Command;
import org.itstep.dto.QuestionDTO;
import org.itstep.service.AnswerService;
import org.itstep.service.QuestionService;
import org.itstep.service.impl.AnswerServiceImpl;
import org.itstep.service.impl.QuestionServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AnswerCommand implements Command {

    private static final Logger log = Logger.getLogger(QuestionCommand.class);

    AnswerService answerService = AnswerServiceImpl.getInstance();

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
        return null;
    }

    private String doPost(HttpServletRequest request, HttpServletResponse response) {
        List<QuestionDTO> questionDTOS = (List<QuestionDTO>) request.getSession().getAttribute("questionList");
        String answerID = request.getParameter("answerID");
        long questionID = Long.parseLong(request.getParameter("questionID"));
        long nextQuestionID = Long.parseLong(request.getParameter("nextQuestionID"));
        if (!answerID.equals("undefined")) {
            answerService.setAnswerToQuestion(questionID, Long.parseLong(answerID), questionDTOS);
        }
        QuestionDTO question = questionService.getNextQuestion(nextQuestionID,questionDTOS);

        try {
            response.setContentType("application/json");
            String json = new ObjectMapper().writeValueAsString(question);
            response.getWriter().write(json);
            return "response:";
        } catch (IOException e) {
            log.info("Exception when retrieve data");
            e.printStackTrace();
        }

        request.setAttribute("error", "Error in QuestionCommand");
        return "/WEB-INF/error.jsp";
    }
}
