package com.matrix.spring1.mapper;

import com.matrix.spring1.dto.HomeDto;
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
public interface HomeMapper {
    HomeDto toDTO(HomeEntity entity);
    List<HomeDto> toDTO(List<HomeEntity> entityList);
    HomeEntity fromDTO(HomeDto dto);
    List<HomeEntity> fromDTO(List<HomeDto> dtoList);
}
