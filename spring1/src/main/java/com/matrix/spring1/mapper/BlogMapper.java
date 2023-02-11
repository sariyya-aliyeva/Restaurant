package com.matrix.spring1.mapper;

import com.matrix.spring1.dto.AboutUsDto;
import com.matrix.spring1.dto.BlogDto;
import com.matrix.spring1.entity.AboutUsEntity;
import com.matrix.spring1.entity.BlogEntity;
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
public interface BlogMapper {
    BlogDto toDTO(BlogEntity entity);
    List<BlogDto> toDTO(List<BlogEntity> entityList);
    BlogEntity fromDTO(BlogDto dto);
    List<BlogEntity> fromDTO(List<BlogDto> dtoList);
}
