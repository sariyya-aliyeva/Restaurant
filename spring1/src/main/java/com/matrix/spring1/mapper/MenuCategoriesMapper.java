package com.matrix.spring1.mapper;

import com.matrix.spring1.dto.MenuCategoriesDto;
import com.matrix.spring1.entity.MenuCategoriesEntity;
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
public interface MenuCategoriesMapper {
    MenuCategoriesDto toDTO(MenuCategoriesEntity entity);
    List<MenuCategoriesDto> toDTO(List<MenuCategoriesEntity> entityList);
    MenuCategoriesEntity fromDTO(MenuCategoriesDto dto);
    List<MenuCategoriesEntity> fromDTO(List<MenuCategoriesDto> dtoList);
}
