package org.itstep.model.entity;

import lombok.*;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {

    private Long id;

    private String definition;

    private QuestionType questionType;

    private List<Answer> answers;

    private Test test;

}
