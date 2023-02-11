package com.matrix.spring1.controller;

import com.matrix.spring1.dto.MenuCategoriesDto;
import com.matrix.spring1.jwt.JwtUser;
import com.matrix.spring1.service.MenuCategoriesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "categories")
@Api(description = "Categories info")
public class CategoriesController {
    private MenuCategoriesService categoriesService;

    @ApiOperation(value = "Save one category", response = MenuCategoriesDto.class)
    @PostMapping("/save")
    public MenuCategoriesDto save(@RequestBody MenuCategoriesDto dto, JwtUser jwtUser) {
        return categoriesService.save(dto, jwtUser);
    }

    @ApiOperation(value = "Save all categories", response = MenuCategoriesDto.class)
    @PostMapping(value = "/save-all")
    public List<MenuCategoriesDto> saveAll(@RequestBody List<MenuCategoriesDto> dto, JwtUser jwtUser) {
        return categoriesService.saveAll(dto, jwtUser);
    }

    @ApiOperation(value = "Get all categories", response = MenuCategoriesDto.class)
    @GetMapping("/get/all")
    public List<MenuCategoriesDto> getAll() {
        return categoriesService.getAll();
    }

    @ApiOperation(value = "Delete by category name", response = MenuCategoriesDto.class)
    @DeleteMapping(value = "/delete/{categoryName}")
    public Long delete(@PathVariable String categoryName, JwtUser jwtUser) {
        return categoriesService.deleteByCategoryName(categoryName, jwtUser);
    }

    @ApiOperation(value = "Delete all products", response = MenuCategoriesDto.class)
    @DeleteMapping(value = "/delete-all")
    public void deleteAll(JwtUser jwtUser) {
        categoriesService.deleteAll(jwtUser);
    }

    @ApiOperation(value = "Update category name by id", response = MenuCategoriesDto.class)
    @PutMapping(value = "/update-by-id-query/{id}/{categoryName}")
    public void updateByQuery(@PathVariable Long id, @PathVariable String categoryName, JwtUser jwtUser) {
        categoriesService.updateById(id, categoryName, jwtUser);
    }
}
