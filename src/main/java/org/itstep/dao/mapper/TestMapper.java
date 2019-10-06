package org.itstep.dao.mapper;

import org.itstep.model.entity.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestMapper implements ObjectMapper<Test> {
    @Override
    public Test extractFromResultSet(ResultSet rs) throws SQLException {
        Test test = Test.builder()
                .id(rs.getLong("id"))
                .topicName(rs.getString("topic_name"))
                .build();
        return test;
    }
}
