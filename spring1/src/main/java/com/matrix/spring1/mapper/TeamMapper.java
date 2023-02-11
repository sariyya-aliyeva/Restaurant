package com.matrix.spring1.mapper;

import com.matrix.spring1.dto.TeamDto;
import com.matrix.spring1.entity.TeamEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface TeamMapper {
    TeamDto toDTO(TeamEntity entity);
    List<TeamDto> toDTO(List<TeamEntity> entityList);
    TeamEntity fromDTO(TeamDto dto);
    List<TeamEntity> fromDTO(List<TeamDto> dtoList);
}
