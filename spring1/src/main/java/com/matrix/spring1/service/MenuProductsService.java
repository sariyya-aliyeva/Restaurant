package com.matrix.spring1.service;

import com.matrix.spring1.dto.HomeDto;
import com.matrix.spring1.dto.MenuProductsDto;
import com.matrix.spring1.dto.TeamDto;
import com.matrix.spring1.jwt.JwtUser;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public interface MenuProductsService {
    MenuProductsDto getById(Long id);

    List<MenuProductsDto> findByProductName(String productName);

    List<MenuProductsDto> getAll();

    MenuProductsDto save(MenuProductsDto dto, JwtUser jwtUser);

    List<MenuProductsDto> saveAll(List<MenuProductsDto> dtoList, JwtUser jwtUser);

    void deleteById(Long id, JwtUser jwtUser);

    void deleteAll(JwtUser jwtUser);

    void updateById(Long id, String productName, JwtUser jwtUser);

    List<MenuProductsDto> getAllSortQuery();

    List<MenuProductsDto> getAllProductsPage(Pageable page);
}
