package com.matrix.spring1.service.impl;

import com.matrix.spring1.dto.MenuProductsDto;
import com.matrix.spring1.entity.MenuProductsEntity;
import com.matrix.spring1.exception.BadRequestException;
import com.matrix.spring1.exception.NotFoundException;
import com.matrix.spring1.jwt.JwtUser;
import com.matrix.spring1.mapper.MenuProductsMapper;
import com.matrix.spring1.repository.MenuProductsRepository;
import com.matrix.spring1.service.MenuProductsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class MenuProductsServiceImp implements MenuProductsService {
    private final MenuProductsRepository productsRepository;
    private final MenuProductsMapper productsMapper;

    @Override
    public MenuProductsDto getById(Long id) {
        MenuProductsEntity entity = getEntity(id);
        return productsMapper.toDTO(entity);
    }

    @Override
    public List<MenuProductsDto> findByProductName(String productName) {
        log.info("aboutUsDao====");
        var entity = productsRepository.findByProductName(productName);
        return productsMapper.toDTO(entity);
    }

    @Override
    public List<MenuProductsDto> getAll() {
        List<MenuProductsEntity> all = productsRepository.findAll();
        return productsMapper.toDTO(all);
    }

    @Override
    public MenuProductsDto save(MenuProductsDto dto, JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream()
                .filter(u -> u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, 400, "Only admin");
        }
        log.info("dto   {}", dto);

        MenuProductsEntity entity = productsMapper.fromDTO(dto);
        MenuProductsEntity save = productsRepository.save(entity);
        return productsMapper.toDTO(save);
    }

    @Override
    public List<MenuProductsDto> saveAll(List<MenuProductsDto> dtoList, JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream()
                .filter(u -> u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, 400, "Only admin");
        }
        log.info("dto   {}", dtoList);

        List<MenuProductsEntity> entityList = productsMapper.fromDTO(dtoList);
        List<MenuProductsEntity> entities = productsRepository.saveAll(entityList);
        return productsMapper.toDTO(entities);
    }

    @Override
    public void deleteById(Long id, JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream()
                .filter(u -> u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, 400, "Only admin");
        }
        log.info("id   {}", id);

        productsRepository.deleteById(id);
    }

    @Override
    public void deleteAll(JwtUser jwtUser) {
        productsRepository.deleteAll();
    }

    @Override
    public void updateById(Long id, String productName, JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream()
                .filter(u -> u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, 400, "Only admin");
        }
        log.info("id   {}", id);
        log.info("product   {}", productName);

        MenuProductsDto byId = getById(id);
        if (byId == null) {
            throw new NotFoundException(HttpStatus.NOT_FOUND, 1, String.format("Information was not found for ID %s", id));
        } else {
            productsRepository.updateProductNameById(id, productName);
        }
    }

    @Override
    public List<MenuProductsDto> getAllSortQuery() {
        List<MenuProductsEntity> allSort = productsRepository.findAllSort(JpaSort.unsafe(Sort.Direction.DESC, "price"));
        return productsMapper.toDTO(allSort);
    }

    @Override
    public List<MenuProductsDto> getAllProductsPage(Pageable page) {
        List<MenuProductsEntity> allPage = productsRepository.findAllPage(page);
        return productsMapper.toDTO(allPage);
    }

    private MenuProductsEntity getEntity(Long id) {
        return productsRepository.findById(id).orElseThrow(() ->
                new NotFoundException(HttpStatus.NOT_FOUND, 2, String.format("Information was not found for ID %s", id)));
    }
}
