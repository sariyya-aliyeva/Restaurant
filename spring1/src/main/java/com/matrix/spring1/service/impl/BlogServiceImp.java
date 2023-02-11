package com.matrix.spring1.service.impl;

import com.matrix.spring1.dao.BlogDao;
import com.matrix.spring1.dto.BlogDto;
import com.matrix.spring1.entity.BlogEntity;
import com.matrix.spring1.exception.BadRequestException;
import com.matrix.spring1.exception.NotFoundException;
import com.matrix.spring1.jwt.JwtFilter;
import com.matrix.spring1.jwt.JwtUser;
import com.matrix.spring1.mapper.BlogMapper;
import com.matrix.spring1.property.JwtProperty;
import com.matrix.spring1.repository.BlogRepository;
import com.matrix.spring1.repository.ConfirmationTokenRepository;
import com.matrix.spring1.service.BlogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class BlogServiceImp implements BlogService {
    private final BlogRepository blogRepository;
    private final BlogMapper blogMapper;
    private final BlogDao blogDao;

    @Override
    public BlogDto get(Long id) {
        BlogEntity blogEntity = getEntity(id);
        return blogMapper.toDTO(blogEntity);
    }

    @Override
    public List<BlogDto> findByDateAndTextAndAuthor(String date, String text, String author) {
        log.info("blogDao====");
        var entity = blogDao.findBlogTexts(date, text, author);
        return blogMapper.toDTO(entity);
    }

    @Override
    public List<BlogDto> getAll() {
        List<BlogEntity> all = blogRepository.findAll();
        return blogMapper.toDTO(all);
    }

    @Override
    public BlogDto save(BlogDto dto, JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream()
                .filter(u -> u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, 400, "Only admin");
        }
        log.info("dto   {}", dto);

        BlogEntity entity = blogMapper.fromDTO(dto);
        BlogEntity save = blogRepository.save(entity);
        return blogMapper.toDTO(save);
    }

    @Override
    public List<BlogDto> saveAll(List<BlogDto> dtoList, JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream()
                .filter(u -> u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, 400, "Only admin");
        }
        log.info("dto   {}", dtoList);

        List<BlogEntity> entityList = blogMapper.fromDTO(dtoList);
        List<BlogEntity> entities = blogRepository.saveAll(entityList);
        return blogMapper.toDTO(entities);
    }

    @Override
    public Long deleteByText(String text, JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream()
                .filter(u -> u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, 400, "Only admin");
        }
        log.info("text   {}", text);

        return blogRepository.deleteByText(text);
    }

    @Override
    public void deleteAll(JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream()
                .filter(u -> u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, 400, "Only admin");
        }
        blogRepository.deleteAll();
    }

    @Override
    public void updateById(Long id, String text, JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream()
                .filter(u -> u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, 400, "Only admin");
        }
        log.info("id   {}", id);
        log.info("text   {}", text);

        BlogDto byId = get(id);
        if (byId == null) {
            throw new NotFoundException(HttpStatus.NOT_FOUND, 1, String.format("Information was not found for ID %s", id));
        } else {
            blogRepository.updateTextById(id, text);
        }
    }

    private BlogEntity getEntity(Long id) {
        return blogRepository.findById(id).orElseThrow(() ->
                new NotFoundException(HttpStatus.NOT_FOUND, 2, String.format("Information was not found for ID %s", id)));
    }
}
