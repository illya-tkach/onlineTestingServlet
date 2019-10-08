package org.itstep.model.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestRating {

    private UserAccount user;

    private Test test;

    private int rating;

}
