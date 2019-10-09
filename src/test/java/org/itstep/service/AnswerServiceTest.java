package org.itstep.service;

import org.itstep.dto.AnswerDTO;
import org.itstep.dto.QuestionDTO;
import org.itstep.service.impl.AnswerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class AnswerServiceTest {

    private AnswerService answerService;
    private ArrayList<QuestionDTO> questionDTOS;

    @Before
    public void setUp() throws Exception {

        answerService = AnswerServiceImpl.getInstance();

        populateQuestions();

    }

    @Test
    public void setAnswerToQuestionTest() {

        answerService.setAnswerToQuestion(1l, 3l, questionDTOS);

        answerService.setAnswerToQuestion(2l, 5l, questionDTOS);

        assertEquals(true, questionDTOS.get(0).getAnswers().get(2).isAnswered());

        assertEquals(true, questionDTOS.get(1).getAnswers().get(1).isAnswered());

        assertEquals(false, questionDTOS.get(1).getAnswers().get(2).isAnswered());
    }

    private void populateQuestions() {
        ArrayList<AnswerDTO> answerDTOS = new ArrayList<>() {
            {
                add(new AnswerDTO(1l, "Answer1", false, false));
                add(new AnswerDTO(2l, "Answer2", false, false));
                add(new AnswerDTO(3l, "Answer3", false, false));
            }
        };

        ArrayList<AnswerDTO> answerDTOS2 = new ArrayList<>() {
            {
                add(new AnswerDTO(4l, "Answer4", false, false));
                add(new AnswerDTO(5l, "Answer5", false, false));
                add(new AnswerDTO(6l, "Answer6", false, false));
            }
        };
        questionDTOS = new ArrayList<>() {
            {
                add(QuestionDTO.builder().id(1l).definition("Question1").answers(answerDTOS).build());
                add(QuestionDTO.builder().id(2l).definition("Question2").answers(answerDTOS2).build());
            }
        };
    }
}
