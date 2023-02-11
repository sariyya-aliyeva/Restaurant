package com.matrix.spring1.repository;

import com.matrix.spring1.entity.HomeEntity;
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
public interface HomeRepository extends JpaRepository<HomeEntity, Long> {
    @Modifying
    @Transactional
    Long deleteByHeading(String heading);

    @Modifying
    @Transactional
    @Query(value = "UPDATE HomeEntity SET heading=:heading, updatedAt =:updateTime WHERE id=:id")
    void updateHeadingById(Long id, String heading, LocalDateTime updateTime);


}
