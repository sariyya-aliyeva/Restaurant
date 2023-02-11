package com.matrix.spring1.service;

import com.matrix.spring1.dto.HomeDto;
import com.matrix.spring1.jwt.JwtUser;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public interface HomeService {
    HomeDto getById(Long id);

    List<HomeDto> findByHeadingAndText1AndText2(String heading, String text1, String text2);

    List<HomeDto> getAll();

    HomeDto save(HomeDto dto, JwtUser jwtUser);

    List<HomeDto> saveAll(List<HomeDto> dtoList, JwtUser jwtUser);

    Long deleteByHeading(String heading, JwtUser jwtUser);

    void deleteAll(JwtUser jwtUser);

    void updateById(Long id, String heading, JwtUser jwtUser);

}
