package com.matrix.spring1.controller;

import com.matrix.spring1.dto.HomeDto;
import com.matrix.spring1.jwt.JwtUser;
import com.matrix.spring1.service.HomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "home")
@Api(description = "Home info")
@RequiredArgsConstructor
@Validated
public class HomeController {
    private final HomeService homeService;

    @ApiOperation(value = "Get by id", response = HomeDto.class)
    @GetMapping(value = "/get/by-id/{id}")
    public HomeDto byId(@PathVariable Long id) {
        return homeService.getById(id);
    }

    @ApiOperation(value = "Get by query", response = HomeDto.class)
    @GetMapping(value = "/get/query")
    public List<HomeDto> findByHeadingAndText1AndText2(@RequestParam(required = false) String heading, @RequestParam(required = false) String text1, @RequestParam(required = false) String text2) {
        return homeService.findByHeadingAndText1AndText2(heading, text1, text2);
    }

    @ApiOperation(value = "Get all", response = HomeDto.class)
    @GetMapping(value = "/get/all")
    public List<HomeDto> getAll() {
        return homeService.getAll();
    }


    @ApiOperation(value = "Save one", response = HomeDto.class)
    @PostMapping(value = "/save")
    public HomeDto save(@RequestBody HomeDto dto, JwtUser jwtUser) {
        return homeService.save(dto, jwtUser);
    }

    @ApiOperation(value = "Save all", response = HomeDto.class)
    @PostMapping(value = "/save-all")
    public List<HomeDto> saveAll(@RequestBody List<HomeDto> dto, JwtUser jwtUser) {
        return homeService.saveAll(dto, jwtUser);
    }

    @ApiOperation(value = "Delete by heading", response = HomeDto.class)
    @DeleteMapping(value = "/delete/{heading}")
    public Long delete(@PathVariable String heading, JwtUser jwtUser) {
        return homeService.deleteByHeading(heading, jwtUser);
    }

    @ApiOperation(value = "Delete all", response = HomeDto.class)
    @DeleteMapping(value = "/delete-all")
    public void deleteAll(JwtUser jwtUser) {
        homeService.deleteAll(jwtUser);
    }

    @ApiOperation(value = "Update heading by id", response = HomeDto.class)
    @PutMapping(value = "/update-by-id-query/{id}/{heading}")
    public void updateByQuery(@PathVariable Long id, @PathVariable String heading, JwtUser jwtUser) {
        homeService.updateById(id, heading, jwtUser);
    }

}
