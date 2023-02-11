package com.matrix.spring1.repository;

import com.matrix.spring1.entity.AboutUsEntity;
import com.matrix.spring1.entity.BlogEntity;
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
public interface BlogRepository extends JpaRepository<BlogEntity, Long> {
    @Modifying
    @Transactional
    Long deleteByText(String text);

    @Modifying
    @Transactional
    @Query(value = "UPDATE BlogEntity SET text=:text WHERE id=:id")
    void updateTextById(Long id, String text);

    List<BlogEntity> findAll(Sort sort);
}
