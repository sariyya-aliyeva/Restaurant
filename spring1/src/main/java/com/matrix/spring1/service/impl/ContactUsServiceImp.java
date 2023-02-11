package com.matrix.spring1.service.impl;

import com.matrix.spring1.dao.ContactUsDao;
import com.matrix.spring1.dto.ContactUsDto;
import com.matrix.spring1.entity.ContactUsEntity;
import com.matrix.spring1.exception.BadRequestException;
import com.matrix.spring1.exception.NotFoundException;
import com.matrix.spring1.jwt.JwtUser;
import com.matrix.spring1.mapper.ContactUsMapper;
import com.matrix.spring1.repository.ContactUsRepository;
import com.matrix.spring1.service.ContactUsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ContactUsServiceImp implements ContactUsService {
    private final ContactUsRepository contactUsRepository;
    private final ContactUsMapper contactUsMapper;
    private final ContactUsDao contactUsDao;

    @Override
    public ContactUsDto get(Long id) {
        ContactUsEntity contactUsEntity = getEntity(id);
        return contactUsMapper.toDTO(contactUsEntity);
    }

    @Override
    public List<ContactUsDto> findContactInfo(String phoneNumber, String email, String address, JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream()
                .filter(u -> u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, 400, "Only admin");
        }

        log.info("contactUsDao====");
        var entity = contactUsDao.findContactInfo(phoneNumber, email, address);
        return contactUsMapper.toDTO(entity);
    }

    @Override
    public List<ContactUsDto> getAll() {
        List<ContactUsEntity> all = contactUsRepository.findAll();
        return contactUsMapper.toDTO(all);
    }

    @Override
    public ContactUsDto save(ContactUsDto dto, JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream()
                .filter(u -> u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, 400, "Only admin");
        }
        log.info("dto   {}", dto);

        ContactUsEntity entity = contactUsMapper.fromDTO(dto);
        ContactUsEntity save = contactUsRepository.save(entity);
        return contactUsMapper.toDTO(save);
    }

    @Override
    public List<ContactUsDto> saveAll(List<ContactUsDto> dtoList, JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream()
                .filter(u -> u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, 400, "Only admin");
        }
        log.info("dto   {}", dtoList);

        List<ContactUsEntity> entityList = contactUsMapper.fromDTO(dtoList);
        List<ContactUsEntity> entities = contactUsRepository.saveAll(entityList);
        return contactUsMapper.toDTO(entities);
    }

    @Override
    public Long deleteByEmail(String email, JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream()
                .filter(u -> u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, 400, "Only admin");
        }
        log.info("email   {}", email);

        return contactUsRepository.deleteByEmail(email);
    }

    @Override
    public void deleteAll(JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream()
                .filter(u -> u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, 400, "Only admin");
        }

        contactUsRepository.deleteAll();
    }

    @Override
    public void updateById(Long id, String address, JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream()
                .filter(u -> u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, 400, "Only admin");
        }
        log.info("id   {}", id);
        log.info("address   {}", address);

        ContactUsDto byId = get(id);
        if (byId == null) {
            throw new NotFoundException(HttpStatus.NOT_FOUND, 1, String.format("Information was not found for ID %s", id));
        } else {
            contactUsRepository.updateAddressById(id, address);
        }
    }

    private ContactUsEntity getEntity(Long id) {
        return contactUsRepository.findById(id).orElseThrow(() ->
                new NotFoundException(HttpStatus.NOT_FOUND, 2, String.format("Information was not found for ID %s", id)));
    }
}
