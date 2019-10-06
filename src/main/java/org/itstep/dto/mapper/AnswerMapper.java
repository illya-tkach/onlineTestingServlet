package org.itstep.dto.mapper;

import org.itstep.dto.AnswerDTO;
import org.itstep.model.entity.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface AnswerMapper {

    AnswerMapper INSTANCE = Mappers.getMapper( AnswerMapper.class );

    @Mapping(target = "question", ignore = true)
    Answer toAnswer(AnswerDTO answerDTO);

    @Mapping(target = "answered", ignore = true)
    AnswerDTO fromAnswer(Answer answer);

}
