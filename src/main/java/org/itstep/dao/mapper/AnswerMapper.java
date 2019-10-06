package org.itstep.dao.mapper;

import org.itstep.model.entity.Answer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerMapper implements ObjectMapper<Answer> {
    @Override
    public Answer extractFromResultSet(ResultSet rs) throws SQLException {
        Answer answer = Answer.builder()
                .id(rs.getLong("id"))
                .definition(rs.getString("definitions"))
                .isCorrect(rs.getBoolean("is_correct"))
                .build();
        return answer;
    }
}
