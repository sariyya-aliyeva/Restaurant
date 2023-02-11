package com.matrix.spring1.repository;

import com.matrix.spring1.entity.TeamEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Long> {
    @Modifying
    @Transactional
    Long deleteByMemberName(String memberName);

    @Modifying
    @Transactional
    @Query(value = "UPDATE TeamEntity SET memberName=:memberName, updatedAt =:updateTime WHERE id=:id")
    void updateMemberById(Long id, String memberName, LocalDateTime updateTime);

    List<TeamEntity> findAll(Sort sort);

    @Query(value = "Select p from TeamEntity p")
    List<TeamEntity> findAllSort(Sort sort);

    @Query(value = "Select p from TeamEntity p")
    List<TeamEntity> findAllPage(Pageable page);

}
