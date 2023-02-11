package com.matrix.spring1.mapper;

import com.matrix.spring1.dto.MenuProductsDto;
import com.matrix.spring1.entity.MenuCategoriesEntity;
import com.matrix.spring1.entity.MenuProductsEntity;
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
public interface MenuProductsMapper {
    @Mapping(source = "categories.id", target = "categoryId")
    MenuProductsDto toDTO(MenuProductsEntity entity);
    List<MenuProductsDto> toDTO(List<MenuProductsEntity> entityList);
    @Mapping(source = "categoryId", target = "categories.id")
    MenuProductsEntity fromDTO(MenuProductsDto dto);
    List<MenuProductsEntity> fromDTO(List<MenuProductsDto> dtoList);
}
