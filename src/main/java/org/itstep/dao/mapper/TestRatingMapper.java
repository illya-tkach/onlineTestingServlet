package org.itstep.dao.mapper;

import org.itstep.model.entity.Test;
import org.itstep.model.entity.TestRating;
import org.itstep.model.entity.UserAccount;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestRatingMapper implements ObjectMapper<TestRating> {
    @Override
    public TestRating extractFromResultSet(ResultSet rs) throws SQLException {
        TestRating testRating = new TestRating();

        Test test = Test.builder()
                .id(rs.getLong("test_id"))
                .topicName(rs.getString("topic_name"))
                .build();
        UserAccount userAccount = UserAccount.builder()
                .id(rs.getInt("useraccount_id"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .build();

        return testRating.builder()
                .test(test)
                .user(userAccount)
                .rating(rs.getInt("rating"))
                .build();
    }
}
