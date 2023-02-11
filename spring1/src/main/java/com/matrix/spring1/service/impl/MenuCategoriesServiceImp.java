package com.matrix.spring1.service.impl;

import com.matrix.spring1.dto.MenuCategoriesDto;
import com.matrix.spring1.entity.MenuCategoriesEntity;
import com.matrix.spring1.exception.BadRequestException;
import com.matrix.spring1.exception.NotFoundException;
import com.matrix.spring1.jwt.JwtFilter;
import com.matrix.spring1.jwt.JwtUser;
import com.matrix.spring1.mapper.MenuCategoriesMapper;
import com.matrix.spring1.property.JwtProperty;
import com.matrix.spring1.repository.ConfirmationTokenRepository;
import com.matrix.spring1.repository.MenuCategoriesRepository;
import com.matrix.spring1.service.MenuCategoriesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class MenuCategoriesServiceImp implements MenuCategoriesService {
    private final MenuCategoriesRepository categoriesRepository;
    private final MenuCategoriesMapper categoriesMapper;

    @Override
    public MenuCategoriesDto getById(Long id) {
        MenuCategoriesEntity entity = getEntity(id);
        return categoriesMapper.toDTO(entity);
    }

    @Override
    public List<MenuCategoriesDto> findByCategoryName(String categoryName) {
        log.info("aboutUsDao====");
        var entity = categoriesRepository.findByCategoryName(categoryName);
        return categoriesMapper.toDTO(entity);
    }

    @Override
    public List<MenuCategoriesDto> getAll() {
        List<MenuCategoriesEntity> all = categoriesRepository.findAll();
        return categoriesMapper.toDTO(all);
    }

    @Override
    public MenuCategoriesDto save(MenuCategoriesDto dto, JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream()
                .filter(u -> u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, 400, "Only admin");
        }
        log.info("dto   {}", dto);

        MenuCategoriesEntity entity = categoriesMapper.fromDTO(dto);
        MenuCategoriesEntity save = categoriesRepository.save(entity);
        return categoriesMapper.toDTO(save);
    }

    @Override
    public List<MenuCategoriesDto> saveAll(List<MenuCategoriesDto> dtoList, JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream()
                .filter(u -> u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, 400, "Only admin");
        }
        log.info("dto   {}", dtoList);

        List<MenuCategoriesEntity> entityList = categoriesMapper.fromDTO(dtoList);
        List<MenuCategoriesEntity> entities = categoriesRepository.saveAll(entityList);
        return categoriesMapper.toDTO(entities);
    }

    @Override
    public Long deleteByCategoryName(String categoryName, JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream()
                .filter(u -> u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, 400, "Only admin");
        }
        log.info("category   {}", categoryName);

        return categoriesRepository.deleteByCategoryName(categoryName);
    }

    @Override
    public void deleteAll(JwtUser jwtUser) {
        categoriesRepository.deleteAll();
    }

    @Override
    public void updateById(Long id, String categoryName, JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream()
                .filter(u -> u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, 400, "Only admin");
        }
        log.info("id   {}", id);
        log.info("category   {}", categoryName);

        MenuCategoriesDto byId = getById(id);
        if (byId == null) {
            throw new NotFoundException(HttpStatus.NOT_FOUND, 1, String.format("Information was not found for ID %s", id));
        } else {
            categoriesRepository.updateCategoryNameById(id, categoryName);
        }
    }

    private MenuCategoriesEntity getEntity(Long id) {
        return categoriesRepository.findById(id).orElseThrow(() ->
                new NotFoundException(HttpStatus.NOT_FOUND, 2, String.format("Information was not found for ID %s", id)));
    }
}
