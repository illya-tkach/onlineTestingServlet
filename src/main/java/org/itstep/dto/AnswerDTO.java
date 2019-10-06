package org.itstep.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO {

    private Long id;

    private String definition;

    private boolean isCorrect;

    private boolean answered;

}
