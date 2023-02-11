package com.matrix.spring1.repository;

import com.matrix.spring1.entity.ReservationEntity;
import com.matrix.spring1.entity.TeamEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
    @Modifying
    @Transactional
    Long deleteByName(String name);

    List<ReservationEntity> findAll(Sort sort);

}
