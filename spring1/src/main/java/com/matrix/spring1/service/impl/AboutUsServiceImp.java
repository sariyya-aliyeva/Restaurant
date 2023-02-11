package com.matrix.spring1.service.impl;

import com.matrix.spring1.dao.AboutUsDao;
import com.matrix.spring1.dto.AboutUsDto;
import com.matrix.spring1.entity.AboutUsEntity;
import com.matrix.spring1.exception.BadRequestException;
import com.matrix.spring1.exception.NotFoundException;
import com.matrix.spring1.jwt.JwtUser;
import com.matrix.spring1.mapper.AboutUsMapper;
import com.matrix.spring1.repository.AboutUsRepository;
import com.matrix.spring1.service.AboutUsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class AboutUsServiceImp implements AboutUsService {
    private final AboutUsRepository aboutUsRepository;
    private final AboutUsMapper aboutUsMapper;
    private final AboutUsDao aboutUsDao;

    @Override
    public AboutUsDto get(Long id) {
        AboutUsEntity aboutUsEntity = getEntity(id);
        return aboutUsMapper.toDTO(aboutUsEntity);
    }

    @Override
    public List<AboutUsDto> findByHeadingAndText1AndText2AndText3AndText4(String heading, String text1, String text2,
                                                                          String text3, String text4) {
        log.info("aboutUsDao====");
        var aboutEntity = aboutUsDao.findAboutUsTexts(heading, text1, text2, text3, text4);
        return aboutUsMapper.toDTO(aboutEntity);
    }

    @Override
    public List<AboutUsDto> getAll() {
        List<AboutUsEntity> all = aboutUsRepository.findAll();
        return aboutUsMapper.toDTO(all);
    }

    @Override
    public AboutUsDto save(AboutUsDto dto, JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream()
                .filter(u->u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()){
            throw new BadRequestException(HttpStatus.BAD_REQUEST,400,"Only admin");
        }
        log.info("dto   {}", dto);

        AboutUsEntity entity = aboutUsMapper.fromDTO(dto);
        AboutUsEntity save = aboutUsRepository.save(entity);
        return aboutUsMapper.toDTO(save);
    }

    @Override
    public List<AboutUsDto> saveAll(List<AboutUsDto> dtoList, JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream()
                .filter(u->u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()){
            throw new BadRequestException(HttpStatus.BAD_REQUEST,400,"Only admin");
        }
        log.info("dto   {}", dtoList);

        List<AboutUsEntity> entityList = aboutUsMapper.fromDTO(dtoList);
        List<AboutUsEntity> entities = aboutUsRepository.saveAll(entityList);
        return aboutUsMapper.toDTO(entities);
    }

    @Override
    public Long deleteByText(String text1, JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream()
                .filter(u->u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()){
            throw new BadRequestException(HttpStatus.BAD_REQUEST,400,"Only admin");
        }
        log.info("text   {}", text1);

        return aboutUsRepository.deleteByText1(text1);
    }

    @Override
    public void deleteAll(JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream()
                .filter(u->u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()){
            throw new BadRequestException(HttpStatus.BAD_REQUEST,400,"Only admin");
        }

        aboutUsRepository.deleteAll();
    }

    @Override
    public void updateById(Long id, String heading, JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream()
                .filter(u->u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()){
            throw new BadRequestException(HttpStatus.BAD_REQUEST,400,"Only admin");
        }
        log.info("id   {}", id);
        log.info("heading   {}", heading);

        AboutUsDto byId = get(id);
        if (byId == null) {
            throw new NotFoundException(HttpStatus.NOT_FOUND, 1, String.format("Information was not found for ID %s", id));
        } else {
            aboutUsRepository.updateHeadingById(id, heading, LocalDateTime.now());
        }
    }

    private AboutUsEntity getEntity(Long id) {
        return aboutUsRepository.findById(id).orElseThrow(() ->
                new NotFoundException(HttpStatus.NOT_FOUND, 2, String.format("Information was not found for ID %s", id)));
    }
}
