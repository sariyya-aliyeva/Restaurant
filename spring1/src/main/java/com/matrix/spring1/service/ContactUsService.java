package com.matrix.spring1.service;

import com.matrix.spring1.dto.ContactUsDto;
import com.matrix.spring1.jwt.JwtUser;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public interface ContactUsService {
    ContactUsDto get(Long id);

    List<ContactUsDto> findContactInfo(String phoneNumber, String email, String address, JwtUser jwtUser);
    List<ContactUsDto> getAll();

    ContactUsDto save(ContactUsDto dto, JwtUser jwtUser);

    List<ContactUsDto> saveAll(List<ContactUsDto> dtoList, JwtUser jwtUser);

    Long deleteByEmail(String email, JwtUser jwtUser);

    void deleteAll(JwtUser jwtUser);

    void updateById(Long id, String address, JwtUser jwtUser);
}
