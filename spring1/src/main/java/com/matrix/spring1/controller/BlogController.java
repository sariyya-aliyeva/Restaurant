package com.matrix.spring1.controller;

import com.matrix.spring1.dto.BlogDto;
import com.matrix.spring1.jwt.JwtUser;
import com.matrix.spring1.service.BlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "blog")
@Api(description = "Blog info")
@RequiredArgsConstructor
@Validated
public class BlogController {
    private final BlogService blogService;

    @ApiOperation(value = "Get blog by id", response = BlogDto.class)
    @GetMapping(value = "/get/by-id/{id}")
    public BlogDto getById(@PathVariable Long id) {
        return blogService.get(id);
    }

    @ApiOperation(value = "Get blogs by query", response = BlogDto.class)
    @GetMapping(value = "/get/query")
    public List<BlogDto> findByDateAndTextAndAuthor(@RequestParam(required = false) String date, @RequestParam(required = false) String text, @RequestParam(required = false) String author) {
        return blogService.findByDateAndTextAndAuthor(date, text, author);
    }

    @ApiOperation(value = "Get all blogs", response = BlogDto.class)
    @GetMapping(value = "/get/all")
    public List<BlogDto> getAll() {
        return blogService.getAll();
    }

    @ApiOperation(value = "Save one blog", response = BlogDto.class)
    @PostMapping(value = "/save")
    public BlogDto save(@RequestBody BlogDto dto, JwtUser jwtUser) {
        System.out.println("jwtuser======" + jwtUser);
        return blogService.save(dto, jwtUser);
    }

    @ApiOperation(value = "Save all blogs", response = BlogDto.class)
    @PostMapping(value = "/save-all")
    public List<BlogDto> saveAll(@RequestBody List<BlogDto> dto, JwtUser jwtUser) {
        return blogService.saveAll(dto, jwtUser);
    }

    @ApiOperation(value = "Delete blogs by text", response = BlogDto.class)
    @DeleteMapping(value = "/delete/{text}")
    public Long delete(@PathVariable String text, JwtUser jwtUser) {
        return blogService.deleteByText(text, jwtUser);
    }

    @ApiOperation(value = "Delete all", response = BlogDto.class)
    @DeleteMapping(value = "/delete-all")
    public void deleteAll(JwtUser jwtUser) {
        blogService.deleteAll(jwtUser);
    }

    @ApiOperation(value = "Update blog text by query", response = BlogDto.class)
    @PutMapping(value = "/update-by-id-query/{id}/{text}")
    public void updateQuery(@PathVariable Long id, @PathVariable String text, JwtUser jwtUser) {
        blogService.updateById(id, text, jwtUser);
    }
}
