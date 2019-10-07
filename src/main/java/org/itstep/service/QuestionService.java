package org.itstep.service;

import org.itstep.dto.QuestionDTO;
import org.itstep.model.entity.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getAllQuestionsByTestID (long testID);
    QuestionDTO getNextQuestion(long questionID, List<QuestionDTO> questionDTOS);
}
