package org.itstep.service;

import org.itstep.dto.AnswerDTO;
import org.itstep.dto.QuestionDTO;
import org.itstep.service.impl.TestServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestServiceTest {

    private TestService testService;
    private ArrayList<QuestionDTO> questionDTOS;

    @Before
    public void setUp() throws Exception {

        testService = TestServiceImpl.getInstance();

        populateQuestions();

    }

    @Test
    public void calculatePointsTest(){

        int points = testService.calculatePoints(questionDTOS);

        assertEquals(2, points);

    }


    private void populateQuestions() {
        ArrayList<AnswerDTO> answerDTOS = new ArrayList<>() {
            {
                add(new AnswerDTO(1l, "Answer1", false, false));
                add(new AnswerDTO(2l, "Answer2", false, false));
                add(new AnswerDTO(3l, "Answer3", true, true));
            }
        };

        ArrayList<AnswerDTO> answerDTOS2 = new ArrayList<>() {
            {
                add(new AnswerDTO(4l, "Answer4", false, false));
                add(new AnswerDTO(5l, "Answer5", false, false));
                add(new AnswerDTO(6l, "Answer6", true, true));
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
