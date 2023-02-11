package com.matrix.spring1.controller;

import com.matrix.spring1.dto.TeamDto;
import com.matrix.spring1.jwt.JwtUser;
import com.matrix.spring1.service.TeamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "team")
@Api(description = "Members info")
@RequiredArgsConstructor
@Validated
public class TeamController {
    private final TeamService teamService;

    @ApiOperation(value = "Get member by id", response = TeamDto.class)
    @GetMapping(value = "/get/by-id/{id}")
    public TeamDto getMembers(@PathVariable Long id) {
        return teamService.getMemberById(id);
    }

    @ApiOperation(value = "Get members with query", response = TeamDto.class)
    @GetMapping(value = "/get/query")
    public List<TeamDto> getMemberByNameAndText(@RequestParam(required = false) String memberName,
                                                @RequestParam(required = false) String memberText) {
        return teamService.getMemberByNameAndText(memberName, memberText);
    }

    @ApiOperation(value = "Get all members", response = TeamDto.class)
    @GetMapping(value = "/get/all")
    public List<TeamDto> getAllMembers() {
        return teamService.getAllMembers();
    }

    @ApiOperation(value = "Save one member", response = TeamDto.class)
    @PostMapping(value = "/save")
    public TeamDto saveMember(@RequestBody TeamDto dto, JwtUser jwtUser) {
        return teamService.saveMember(dto, jwtUser);
    }

    @ApiOperation(value = "Save all members", response = TeamDto.class)
    @PostMapping(value = "/save-all")
    public List<TeamDto> saveAllMembers(@RequestBody List<TeamDto> dto, JwtUser jwtUser) {
        return teamService.saveAllMembers(dto, jwtUser);
    }

    @ApiOperation(value = "Delete member by name", response = TeamDto.class)
    @DeleteMapping(value = "/by-member-name/{memberName}")
    public Long deleteMember(@PathVariable String memberName, JwtUser jwtUser) {
        return teamService.deleteByMemberName(memberName, jwtUser);
    }

    @ApiOperation(value = "Delete all members", response = TeamDto.class)
    @DeleteMapping(value = "/all")
    public void deleteAllMembers(JwtUser jwtUser) {
        teamService.deleteAll(jwtUser);
    }

    @ApiOperation(value = "Update member name by id", response = TeamDto.class)
    @PutMapping(value = "/update-member-name/by-id-query/{id}/{memberName}")
    public void updateMemberQuery(@PathVariable Long id, @PathVariable String memberName, JwtUser jwtUser) {
        teamService.updateById(id, memberName, jwtUser);
    }

    @ApiOperation(value = "Get members with sorting", response = TeamDto.class)
    @GetMapping(value = "/get/all/sort")
    public List<TeamDto> getMembersSort() {
        return teamService.getAllMembersSort();
    }

    @ApiOperation(value = "Get members with sort query", response = TeamDto.class)
    @GetMapping(value = "/get/all/sort/query")
    public List<TeamDto> getMembersSortQuery() {
        return teamService.getAllMembersSortQuery();
    }

    @ApiOperation(value = "Get members with page", response = TeamDto.class)
    @GetMapping(value = "/get/all/page")
    public List<TeamDto> getAllMembersPage(Pageable page) {
        return teamService.getAllMembersPage(page);
    }

}
