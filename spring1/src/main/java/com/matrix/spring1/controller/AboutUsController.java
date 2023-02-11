package com.matrix.spring1.controller;

import com.matrix.spring1.dto.AboutUsDto;
import com.matrix.spring1.jwt.JwtUser;
import com.matrix.spring1.service.AboutUsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "about-us")
@Api(description = "About Us info")
@RequiredArgsConstructor
@Validated
public class AboutUsController {
    private final AboutUsService aboutUsService;

    @ApiOperation(value = "Get by id", response = AboutUsDto.class)
    @GetMapping(value = "/get/by-id/{id}")
    public AboutUsDto getById(@PathVariable Long id) {
        return aboutUsService.get(id);
    }

    @ApiOperation(value = "Get by query", response = AboutUsDto.class)
    @GetMapping(value = "/get/query")
    public List<AboutUsDto> findByHeadingAndText1AndText2AndText3AndText4(@RequestParam String heading,
                                                                          @RequestParam(required = false) String text1,
                                                                          @RequestParam(required = false) String text2,
                                                                          @RequestParam(required = false) String text3,
                                                                          @RequestParam(required = false) String text4) {
        return aboutUsService.findByHeadingAndText1AndText2AndText3AndText4(heading, text1, text2, text3, text4);
    }

    @ApiOperation(value = "Get all", response = AboutUsDto.class)
    @GetMapping(value = "/get/all")
    public List<AboutUsDto> getAll() {
        return aboutUsService.getAll();
    }

    @ApiOperation(value = "Save one", response = AboutUsDto.class)
    @PostMapping(value = "/save")
    public AboutUsDto save(@RequestBody AboutUsDto dto, JwtUser jwtUser) {
        return aboutUsService.save(dto, jwtUser);
    }

    @ApiOperation(value = "Save all", response = AboutUsDto.class)
    @PostMapping(value = "/save-all")
    public List<AboutUsDto> saveAll(@RequestBody List<AboutUsDto> dto, JwtUser jwtUser) {
        return aboutUsService.saveAll(dto, jwtUser);
    }

    @ApiOperation(value = "Delete by text", response = AboutUsDto.class)
    @DeleteMapping(value = "/delete")
    public Long delete(@RequestParam String text1, JwtUser jwtUser) {
        return aboutUsService.deleteByText(text1, jwtUser);
    }

    @ApiOperation(value = "Delete all", response = AboutUsDto.class)
    @DeleteMapping(value = "/delete-all")
    public void deleteAll(JwtUser jwtUser) {
        aboutUsService.deleteAll(jwtUser);
    }

    @ApiOperation(value = "Update heading by id", response = AboutUsDto.class)
    @PutMapping(value = "update-heading-by-id/{id}/{heading}")
    public void updateQuery(@PathVariable Long id, @PathVariable String heading, JwtUser jwtUser) {
        aboutUsService.updateById(id, heading, jwtUser);
    }

}
