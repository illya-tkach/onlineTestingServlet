package org.itstep.service;

import org.itstep.dto.QuestionDTO;
import org.itstep.model.entity.Question;

import java.util.List;

public interface QuestionService {
    List<QuestionDTO> toDtoQuestions();
    List<Question> getAllQuestionsByTestID (long testID);
    public QuestionDTO getNextQuestion(long questionID, List<QuestionDTO> questionDTOS);
}
