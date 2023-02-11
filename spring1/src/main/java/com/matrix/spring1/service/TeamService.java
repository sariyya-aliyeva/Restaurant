package com.matrix.spring1.service;

import com.matrix.spring1.dto.TeamDto;
import com.matrix.spring1.jwt.JwtUser;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public interface TeamService {
    TeamDto getMemberById(Long id);

    List<TeamDto> getMemberByNameAndText(String memberName, String memberText);

    List<TeamDto> getAllMembers();

    TeamDto saveMember(TeamDto dto, JwtUser jwtUser);

    List<TeamDto> saveAllMembers(List<TeamDto> dtoList, JwtUser jwtUser);

    Long deleteByMemberName(String memberName,  JwtUser jwtUser);

    void deleteAll(JwtUser jwtUser);

    void updateById(Long id, String memberName, JwtUser jwtUser);

    List<TeamDto> getAllMembersSort();

    List<TeamDto> getAllMembersSortQuery();
    List<TeamDto> getAllMembersPage(Pageable page);
}




