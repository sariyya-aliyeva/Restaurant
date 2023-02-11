package com.matrix.spring1.service.impl;

import com.matrix.spring1.dao.TeamDao;
import com.matrix.spring1.dto.TeamDto;
import com.matrix.spring1.entity.TeamEntity;
import com.matrix.spring1.exception.BadRequestException;
import com.matrix.spring1.exception.NotFoundException;
import com.matrix.spring1.jwt.JwtUser;
import com.matrix.spring1.mapper.TeamMapper;
import com.matrix.spring1.repository.TeamRepository;
import com.matrix.spring1.service.TeamService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class TeamServiceImp implements TeamService {
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
    private final TeamDao teamDao;


    @Override
    public TeamDto getMemberById(Long id) {
        TeamEntity teamEntity = getEntity(id);
        return teamMapper.toDTO(teamEntity);
    }

    @Override
    public List<TeamDto> getMemberByNameAndText(String memberName, String memberText) {
        log.info("teamDao====");
        var teamEntity = teamDao.findTeamMembers(memberName, memberText);
        return teamMapper.toDTO(teamEntity);
    }

    @Override
    public List<TeamDto> getAllMembers() {
        List<TeamEntity> all = teamRepository.findAll();
        return teamMapper.toDTO(all);
    }

    @Override
    public TeamDto saveMember(TeamDto dto, JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream().filter(u -> u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, 400, "Only admin");
        }
        log.info("dto   {}", dto);

        TeamEntity entity = teamMapper.fromDTO(dto);
        TeamEntity save = teamRepository.save(entity);
        return teamMapper.toDTO(save);
    }

    @Override
    public List<TeamDto> saveAllMembers(List<TeamDto> dtoList, JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream().filter(u -> u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, 400, "Only admin");
        }
        log.info("dto   {}", dtoList);

        List<TeamEntity> entityList = teamMapper.fromDTO(dtoList);
        List<TeamEntity> membersEntities = teamRepository.saveAll(entityList);
        return teamMapper.toDTO(membersEntities);
    }

    @Override
    public Long deleteByMemberName(String memberName, JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream().filter(u -> u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, 400, "Only admin");
        }
        log.info("memberName   {}", memberName);

        return teamRepository.deleteByMemberName(memberName);
    }

    @Override
    public void deleteAll(JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream().filter(u -> u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, 400, "Only admin");
        }

        teamRepository.deleteAll();
    }

    @Override
    public void updateById(Long id, String memberName, JwtUser jwtUser) {
        log.info("jwtUser= {}", jwtUser);

        var userRole = jwtUser.getRoles().stream().filter(u -> u.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList());

        if (userRole.isEmpty()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, 400, "Only admin");
        }
        log.info("id   {}", id);
        log.info("memberName   {}", memberName);

        TeamDto membersById = getMemberById(id);
        if (membersById == null) {
            throw new NotFoundException(HttpStatus.NOT_FOUND, 1, String.format("Information was not found for ID %s", id));
        } else {
            teamRepository.updateMemberById(id, memberName, LocalDateTime.now());
        }
    }

    @Override
    public List<TeamDto> getAllMembersSort() {
        List<TeamEntity> id = teamRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return teamMapper.toDTO(id);
    }

    @Override
    public List<TeamDto> getAllMembersSortQuery() {
        List<TeamEntity> allSort = teamRepository.findAllSort(JpaSort.unsafe(Sort.Direction.DESC, "Length(memberName)"));
        return teamMapper.toDTO(allSort);
    }

    @Override
    public List<TeamDto> getAllMembersPage(Pageable page) {
        List<TeamEntity> allPage = teamRepository.findAllPage(page);
        return teamMapper.toDTO(allPage);
    }

    private TeamEntity getEntity(Long id) {
        return teamRepository.findById(id).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND, 2, String.format("Information was not found for ID %s", id)));
    }
}
