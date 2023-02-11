package com.matrix.spring1.service.impl;

import com.matrix.spring1.dao.ReservationDao;
import com.matrix.spring1.dto.ReservationDto;
import com.matrix.spring1.entity.ReservationEntity;
import com.matrix.spring1.exception.BadRequestException;
import com.matrix.spring1.exception.NotFoundException;
import com.matrix.spring1.jwt.JwtUser;
import com.matrix.spring1.mapper.ReservationMapper;
import com.matrix.spring1.repository.ReservationRepository;
import com.matrix.spring1.service.ReservationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ReservationServiceImp implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final ReservationDao reservationDao;

    @Override
    public ReservationDto byId(Long id) {
        ReservationEntity reservationEntity = getEntity(id);
        return reservationMapper.toDTO(reservationEntity);
    }

    @Override
    public List<ReservationDto> findReservation(String name, String contactNo, LocalDate date, Integer noOfPeople, String preferredFood, String occasion) {
        log.info("reservationDao====");
        var reservation = reservationDao.findReservation(name, contactNo, date, noOfPeople, preferredFood, occasion);
        return reservationMapper.toDTO(reservation);
    }

    @Override
    public List<ReservationDto> getAll(JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream().filter(u -> u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, 400, "Only admin");
        }

        List<ReservationEntity> all = reservationRepository.findAll();
        return reservationMapper.toDTO(all);
    }

    @Override
    public ReservationDto save(ReservationDto dto) {
        ReservationEntity entity = reservationMapper.fromDTO(dto);
        ReservationEntity save = reservationRepository.save(entity);
        return reservationMapper.toDTO(save);
    }

    @Override
    public List<ReservationDto> saveAll(List<ReservationDto> dtoList, JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream().filter(u -> u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, 400, "Only admin");
        }
        log.info("dto   {}", dtoList);

        List<ReservationEntity> entityList = reservationMapper.fromDTO(dtoList);
        List<ReservationEntity> entities = reservationRepository.saveAll(entityList);
        return reservationMapper.toDTO(entities);
    }

    @Override
    public Long deleteByName(String name, JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream().filter(u -> u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, 400, "Only admin");
        }
        log.info("name   {}", name);

        return reservationRepository.deleteByName(name);
    }

    @Override
    public void deleteAll(JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream().filter(u -> u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, 400, "Only admin");
        }

        reservationRepository.deleteAll();
    }

    @Override
    public List<ReservationDto> getAllReservationsSort(JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream().filter(u -> u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, 400, "Only admin");
        }

        List<ReservationEntity> noOfPeople = reservationRepository.findAll(Sort.by(Sort.Direction.DESC, "noOfPeople"));
        return reservationMapper.toDTO(noOfPeople);
    }

    private ReservationEntity getEntity(Long id) {
        return reservationRepository.findById(id).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND, 2, String.format("Information was not found for ID %s", id)));
    }
}
