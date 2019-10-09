package org.itstep.dto;

import lombok.*;
import org.itstep.model.entity.QuestionType;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDTO {

    private Long id;

    private String definition;

    private QuestionType questionType;

    private List<AnswerDTO> answers;

}
