package org.itstep.model.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Answer {

    private Long id;

    private String definition;

    private boolean isCorrect;

    private Question question;

}
