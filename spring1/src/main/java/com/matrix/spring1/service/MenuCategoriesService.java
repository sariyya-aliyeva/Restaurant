package com.matrix.spring1.service;

import com.matrix.spring1.dto.MenuCategoriesDto;
import com.matrix.spring1.dto.MenuProductsDto;
import com.matrix.spring1.jwt.JwtUser;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public interface MenuCategoriesService {
    MenuCategoriesDto getById(Long id);

    List<MenuCategoriesDto> findByCategoryName(String categoryName);

    List<MenuCategoriesDto> getAll();

    MenuCategoriesDto save(MenuCategoriesDto dto, JwtUser jwtUser);

    List<MenuCategoriesDto> saveAll(List<MenuCategoriesDto> dtoList, JwtUser jwtUser);

    Long deleteByCategoryName(String categoryName, JwtUser jwtUser);

    void deleteAll(JwtUser jwtUser);

    void updateById(Long id, String categoryName, JwtUser jwtUser);
}
