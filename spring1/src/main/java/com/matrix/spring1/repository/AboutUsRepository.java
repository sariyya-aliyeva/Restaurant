package com.matrix.spring1.repository;

import com.matrix.spring1.entity.AboutUsEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AboutUsRepository extends JpaRepository<AboutUsEntity, Long> {
    @Modifying
    @Transactional
    Long deleteByText1(String text1);

    @Modifying
    @Transactional
    @Query(value = "UPDATE AboutUsEntity SET heading=:heading, updatedAt =:updateTime WHERE id=:id")
    void updateHeadingById(Long id, String heading, LocalDateTime updateTime);

}
