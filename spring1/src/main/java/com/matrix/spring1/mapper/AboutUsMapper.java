package com.matrix.spring1.mapper;

import com.matrix.spring1.dto.AboutUsDto;
import com.matrix.spring1.dto.HomeDto;
import com.matrix.spring1.entity.AboutUsEntity;
import com.matrix.spring1.entity.HomeEntity;
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
public interface AboutUsMapper {
    AboutUsDto toDTO(AboutUsEntity entity);
    List<AboutUsDto> toDTO(List<AboutUsEntity> entityList);
    AboutUsEntity fromDTO(AboutUsDto dto);
    List<AboutUsEntity> fromDTO(List<AboutUsDto> dtoList);
}
