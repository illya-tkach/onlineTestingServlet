package org.itstep.dto.mapper;

import org.itstep.dto.QuestionDTO;
import org.itstep.model.entity.Question;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(uses = AnswerMapper.class)
public interface QuestionMapper {

    QuestionMapper INSTANCE = Mappers.getMapper( QuestionMapper.class );

    @Mapping(target = "test", ignore = true)
    Question toQuestion(QuestionDTO customerDto);

    @InheritInverseConfiguration
    QuestionDTO fromQuestion(Question customer);

    List<Question> toQuestions(List<QuestionDTO> questions);

    List<QuestionDTO> fromQuestions(List<Question> questions);
}
