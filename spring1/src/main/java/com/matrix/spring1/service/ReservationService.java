package com.matrix.spring1.service;

import com.matrix.spring1.dto.ReservationDto;
import com.matrix.spring1.dto.TeamDto;
import com.matrix.spring1.jwt.JwtUser;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public interface ReservationService {
    ReservationDto byId(Long id);

    List<ReservationDto> findReservation(String name, String contactNo, LocalDate date, Integer noOfPeople,
                                         String preferredFood, String occasion);

    List<ReservationDto> getAll(JwtUser jwtUser);

    ReservationDto save(ReservationDto dto);

    List<ReservationDto> saveAll(List<ReservationDto> dtoList, JwtUser jwtUser);

    Long deleteByName(String name, JwtUser jwtUser);

    void deleteAll(JwtUser jwtUser);

    List<ReservationDto> getAllReservationsSort(JwtUser jwtUser);

}
