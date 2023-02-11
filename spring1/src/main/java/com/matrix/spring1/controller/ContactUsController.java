package com.matrix.spring1.controller;

import com.matrix.spring1.dto.ContactUsDto;
import com.matrix.spring1.jwt.JwtUser;
import com.matrix.spring1.service.ContactUsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "contact-us")
@Api(description = "Contact Us info")
@RequiredArgsConstructor
@Validated
public class ContactUsController {
    private final ContactUsService contactUsService;

    @ApiOperation(value = "Get contact by id", response = ContactUsDto.class)
    @GetMapping(value = "/get/by-id/{id}")
    public ContactUsDto getById(@PathVariable Long id) {
        return contactUsService.get(id);
    }

    @ApiOperation(value = "Get contact by query", response = ContactUsDto.class)
    @GetMapping(value = "/get/query")
    public List<ContactUsDto> findContactInfo(@RequestParam(required = false) String phoneNumber,
                                              @RequestParam(required = false) String email,
                                              @RequestParam(required = false) String address,
                                              JwtUser jwtUser) {
        return contactUsService.findContactInfo(phoneNumber, email, address, jwtUser);
    }

    @ApiOperation(value = "Get all contacts", response = ContactUsDto.class)
    @GetMapping(value = "/get/all")
    public List<ContactUsDto> getAll() {
        return contactUsService.getAll();
    }

    @ApiOperation(value = "Save one contact info", response = ContactUsDto.class)
    @PostMapping(value = "/save")
    public ContactUsDto save(@RequestBody ContactUsDto dto, JwtUser jwtUser) {
        return contactUsService.save(dto, jwtUser);
    }

    @ApiOperation(value = "Save all contact info", response = ContactUsDto.class)
    @PostMapping(value = "/save-all")
    public List<ContactUsDto> saveAll(@RequestBody List<ContactUsDto> dto, JwtUser jwtUser) {
        return contactUsService.saveAll(dto, jwtUser);
    }

    @ApiOperation(value = "Delete contact info by email", response = ContactUsDto.class)
    @DeleteMapping(value = "/delete/{email}")
    public Long delete(@PathVariable String email, JwtUser jwtUser) {
        return contactUsService.deleteByEmail(email, jwtUser);
    }

    @ApiOperation(value = "Delete all contact info", response = ContactUsDto.class)
    @DeleteMapping(value = "/delete-all")
    public void deleteAll(JwtUser jwtUser) {
        contactUsService.deleteAll(jwtUser);
    }

    @ApiOperation(value = "Update contact address by id", response = ContactUsDto.class)
    @PutMapping(value = "/update-by-id-query/{id}/{address}")
    public void updateQuery(@PathVariable Long id, @PathVariable String address, JwtUser jwtUser) {
        contactUsService.updateById(id, address, jwtUser);
    }
}
