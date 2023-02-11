package com.matrix.spring1.service.impl;

import com.matrix.spring1.dao.HomeDao;
import com.matrix.spring1.dto.HomeDto;
import com.matrix.spring1.entity.HomeEntity;
import com.matrix.spring1.exception.BadRequestException;
import com.matrix.spring1.exception.NotFoundException;
import com.matrix.spring1.jwt.JwtUser;
import com.matrix.spring1.mapper.HomeMapper;
import com.matrix.spring1.repository.HomeRepository;
import com.matrix.spring1.service.HomeService;
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
public class HomeServiceImp implements HomeService {
    private final HomeRepository homeRepository;
    private final HomeMapper homeMapper;
    private final HomeDao homeDao;

    @Override
    public HomeDto getById(Long id) {
        HomeEntity homeEntity = getEntity(id);
        return homeMapper.toDTO(homeEntity);
    }

    @Override
    public List<HomeDto> findByHeadingAndText1AndText2(String heading, String text1, String text2) {
        log.info("aboutUsDao====");
        var homeEntity = homeDao.findHomeTexts(heading, text1, text2);
        return homeMapper.toDTO(homeEntity);
    }

    @Override
    public List<HomeDto> getAll() {
        List<HomeEntity> all = homeRepository.findAll();
        return homeMapper.toDTO(all);
    }

    @Override
    public HomeDto save(HomeDto dto, JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream().filter(u -> u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, 400, "Only admin");
        }
        log.info("dto   {}", dto);

        HomeEntity entity = homeMapper.fromDTO(dto);
        HomeEntity save = homeRepository.save(entity);
        return homeMapper.toDTO(save);
    }

    @Override
    public List<HomeDto> saveAll(List<HomeDto> dtoList, JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream().filter(u -> u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, 400, "Only admin");
        }
        log.info("dto   {}", dtoList);

        List<HomeEntity> entityList = homeMapper.fromDTO(dtoList);
        List<HomeEntity> entities = homeRepository.saveAll(entityList);
        return homeMapper.toDTO(entities);
    }

    @Override
    public Long deleteByHeading(String heading, JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream().filter(u -> u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, 400, "Only admin");
        }
        log.info("heading   {}", heading);

        return homeRepository.deleteByHeading(heading);
    }

    @Override
    public void deleteAll(JwtUser jwtUser) {
        homeRepository.deleteAll();
    }

    @Override
    public void updateById(Long id, String heading, JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream().filter(u -> u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, 400, "Only admin");
        }
        log.info("id   {}", id);
        log.info("heading   {}", heading);

        HomeDto byId = getById(id);
        if (byId == null) {
            throw new NotFoundException(HttpStatus.NOT_FOUND, 1, String.format("Information was not found for ID %s", id));
        } else {
            homeRepository.updateHeadingById(id, heading, LocalDateTime.now());
        }
    }

    private HomeEntity getEntity(Long id) {
        return homeRepository.findById(id).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND, 2, String.format("Information was not found for ID %s", id)));
    }
}
