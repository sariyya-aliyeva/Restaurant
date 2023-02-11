package com.matrix.spring1.mapper;

import com.matrix.spring1.dto.BlogDto;
import com.matrix.spring1.dto.ContactUsDto;
import com.matrix.spring1.entity.BlogEntity;
import com.matrix.spring1.entity.ContactUsEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface ContactUsMapper {
    ContactUsDto toDTO(ContactUsEntity entity);
    List<ContactUsDto> toDTO(List<ContactUsEntity> entityList);
    ContactUsEntity fromDTO(ContactUsDto dto);
    List<ContactUsEntity> fromDTO(List<ContactUsDto> dtoList);
}
