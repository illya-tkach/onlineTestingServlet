package org.itstep.service;


import org.itstep.dto.QuestionDTO;
import org.itstep.model.entity.Test;

import java.util.List;

public interface TestService {
    List<Test> getAllTests();

//    Test getById(long id);
//
    int calculatePoints(List<QuestionDTO> questionDTOS);
//
    void saveRating(long testID, long userID, int totalPoints);
}
