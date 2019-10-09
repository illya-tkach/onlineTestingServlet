package org.itstep.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerDTO {

    private Long id;

    private String definition;

    private boolean isCorrect;

    private boolean answered;

    public boolean getIsCorrect() {
        return isCorrect;
    }
}
