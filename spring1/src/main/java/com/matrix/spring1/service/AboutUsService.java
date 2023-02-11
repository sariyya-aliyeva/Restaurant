package com.matrix.spring1.service;

import com.matrix.spring1.dto.AboutUsDto;
import com.matrix.spring1.dto.HomeDto;
import com.matrix.spring1.jwt.JwtUser;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Service
public interface AboutUsService {
    AboutUsDto get(Long id);

    List<AboutUsDto> findByHeadingAndText1AndText2AndText3AndText4(String heading, String text1, String text2,
                                                                   String text3, String text4);

    List<AboutUsDto> getAll();

    AboutUsDto save(AboutUsDto dto, JwtUser jwtUser);

    List<AboutUsDto> saveAll(List<AboutUsDto> dtoList, JwtUser jwtUser);

    Long deleteByText(String text1, JwtUser jwtUser);

    void deleteAll(JwtUser jwtUser);

    void updateById(Long id, String heading, JwtUser jwtUser);


}
