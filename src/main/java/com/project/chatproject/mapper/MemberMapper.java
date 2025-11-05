package com.project.chatproject.mapper;

import com.project.chatproject.domain.dto.user.MemberDto;
import com.project.chatproject.domain.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    MemberDto toDto(Member e);
    Member toEntity(MemberDto d);
}