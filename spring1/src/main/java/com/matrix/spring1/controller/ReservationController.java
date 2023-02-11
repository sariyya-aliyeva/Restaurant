package com.matrix.spring1.controller;

import com.matrix.spring1.dto.ReservationDto;
import com.matrix.spring1.jwt.JwtUser;
import com.matrix.spring1.service.ReservationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "reservation")
@Api(description = "Reservation info")
@RequiredArgsConstructor
@Validated
public class ReservationController {
    private final ReservationService reservationService;

    @ApiOperation(value = "Get reservations by id", response = ReservationDto.class)
    @GetMapping(value = "/get/by-id/{id}")
    public ReservationDto getById(@PathVariable Long id) {
        return reservationService.byId(id);
    }

    @ApiOperation(value = "Get reservations by query", response = ReservationDto.class)
    @GetMapping(value = "/get/query")
    public List<ReservationDto> findReservation(@RequestParam(required = false) String name,
                                                @RequestParam(required = false) String contactNo,
                                                @RequestParam(required = false) LocalDate date,
                                                @RequestParam(required = false) Integer noOfPeople,
                                                @RequestParam(required = false) String preferredFood,
                                                @RequestParam(required = false) String occasion) {
        return reservationService.findReservation(name, contactNo, date, noOfPeople, preferredFood, occasion);
    }

    @ApiOperation(value = "Get all reservations", response = ReservationDto.class)
    @GetMapping(value = "/get/all")
    public List<ReservationDto> getAll(JwtUser jwtUser) {
        return reservationService.getAll(jwtUser);
    }

    @ApiOperation(value = "Save reservation", response = ReservationDto.class)
    @PostMapping(value = "/save")
    public ReservationDto save(@RequestBody ReservationDto dto) {
        return reservationService.save(dto);
    }

    @ApiOperation(value = "Save all reservations", response = ReservationDto.class)
    @PostMapping(value = "/save-all")
    public List<ReservationDto> saveAll(@RequestBody List<ReservationDto> dto, JwtUser jwtUser) {
        return reservationService.saveAll(dto, jwtUser);
    }

    @ApiOperation(value = "Delete reservation by name", response = ReservationDto.class)
    @DeleteMapping(value = "/delete/{name}")
    public Long delete(@PathVariable String name, JwtUser jwtUser) {
        return reservationService.deleteByName(name, jwtUser);
    }

    @ApiOperation(value = "Delete all", response = ReservationDto.class)
    @DeleteMapping(value = "/delete-all")
    public void deleteAll(JwtUser jwtUser) {
        reservationService.deleteAll(jwtUser);
    }


    @ApiOperation(value = "Get reservations with sorting", response = ReservationDto.class)
    @GetMapping(value = "/get/all/sort")
    public List<ReservationDto> getReservationsSort(JwtUser jwtUser) {
        return reservationService.getAllReservationsSort(jwtUser);
    }
}
