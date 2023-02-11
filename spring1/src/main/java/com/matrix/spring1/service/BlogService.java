package com.matrix.spring1.service;

import com.matrix.spring1.dto.AboutUsDto;
import com.matrix.spring1.dto.BlogDto;
import com.matrix.spring1.jwt.JwtUser;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public interface BlogService {
    BlogDto get(Long id);

    List<BlogDto> findByDateAndTextAndAuthor(String date, String text, String author);

    List<BlogDto> getAll();

    BlogDto save(BlogDto dto, JwtUser jwtUser);

    List<BlogDto> saveAll(List<BlogDto> dtoList, JwtUser jwtUser);

    Long deleteByText(String text, JwtUser jwtUser);

    void deleteAll(JwtUser jwtUser);

    void updateById(Long id, String text, JwtUser jwtUser);
}
