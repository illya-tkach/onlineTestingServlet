package org.itstep.model.entity;

import lombok.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Test {

    private Long id;

    private String topicName;

    private Set<Question> questions;

    private Set<TestRating> testRatings;

}
