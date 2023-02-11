package com.matrix.spring1.controller;

import com.matrix.spring1.dto.HomeDto;
import com.matrix.spring1.dto.MenuProductsDto;
import com.matrix.spring1.dto.TeamDto;
import com.matrix.spring1.jwt.JwtUser;
import com.matrix.spring1.service.MenuProductsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "products")
@Api(description = "Products info")
public class ProductsController {
    private final MenuProductsService productsService;

    @ApiOperation(value = "Get by id", response = HomeDto.class)
    @GetMapping(value = "/get/by-id/{id}")
    public MenuProductsDto byId(@PathVariable Long id) {
        return productsService.getById(id);
    }

    @ApiOperation(value = "Get by name", response = HomeDto.class)
    @GetMapping(value = "/get/query")
    public List<MenuProductsDto> findByProductName(String productName) {
        return productsService.findByProductName(productName);
    }

    @ApiOperation(value = "Get all products", response = MenuProductsDto.class)
    @GetMapping("/get/all")
    public List<MenuProductsDto> getAll() {
        return productsService.getAll();
    }

    @ApiOperation(value = "Save one product", response = MenuProductsDto.class)
    @PostMapping("/save")
    public MenuProductsDto save(@RequestBody MenuProductsDto dto, JwtUser jwtUser) {
        return productsService.save(dto, jwtUser);
    }

    @ApiOperation(value = "Save all products", response = HomeDto.class)
    @PostMapping(value = "/save-all")
    public List<MenuProductsDto> saveAll(@RequestBody List<MenuProductsDto> dto, JwtUser jwtUser) {
        return productsService.saveAll(dto, jwtUser);
    }

    @ApiOperation(value = "Delete by product id", response = MenuProductsDto.class)
    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable Long id, JwtUser jwtUser) {
        productsService.deleteById(id, jwtUser);
    }

    @ApiOperation(value = "Delete all products", response = MenuProductsDto.class)
    @DeleteMapping(value = "/delete-all")
    public void deleteAll(JwtUser jwtUser) {
        productsService.deleteAll(jwtUser);
    }

    @ApiOperation(value = "Update product name by id", response = MenuProductsDto.class)
    @PutMapping(value = "/update-by-id-query/{id}/{productName}")
    public void updateByQuery(@PathVariable Long id, @PathVariable String productName, JwtUser jwtUser) {
        productsService.updateById(id, productName, jwtUser);
    }

    @ApiOperation(value = "Get all products by sort query", response = MenuProductsDto.class)
    @GetMapping(value = "/get/all/sort/query")
    public List<MenuProductsDto> getBySortQuery() {
        return productsService.getAllSortQuery();
    }

    @ApiOperation(value = "Get products with page", response = TeamDto.class)
    @GetMapping(value = "/get/all/page")
    public List<MenuProductsDto> getAllProductsPage(Pageable page) {
        return productsService.getAllProductsPage(page);
    }

}
