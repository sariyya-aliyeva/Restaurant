package com.matrix.spring1.mapper;

import com.matrix.spring1.dto.ReservationDto;
import com.matrix.spring1.dto.TeamDto;
import com.matrix.spring1.entity.ReservationEntity;
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
public interface ReservationMapper {
    ReservationDto toDTO(ReservationEntity entity);
    List<ReservationDto> toDTO(List<ReservationEntity> entityList);
    ReservationEntity fromDTO(ReservationDto dto);
    List<ReservationEntity> fromDTO(List<ReservationDto> dtoList);
}
