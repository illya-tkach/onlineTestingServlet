package org.itstep.dao;

import org.itstep.model.entity.Test;
import java.util.List;

public interface TestDao extends GenericDao<Test> {
    List<Test> findAll();
    void saveRating(long testID, long userID, int totalPoints);
}
