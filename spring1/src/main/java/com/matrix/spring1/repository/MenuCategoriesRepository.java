package com.matrix.spring1.repository;

import com.matrix.spring1.dto.MenuCategoriesDto;
import com.matrix.spring1.entity.HomeEntity;
import com.matrix.spring1.entity.MenuCategoriesEntity;
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
public interface MenuCategoriesRepository extends JpaRepository<MenuCategoriesEntity, Long> {
    List<MenuCategoriesEntity> findByCategoryName(String categoryName);

    @Modifying
    @Transactional
    Long deleteByCategoryName(String categoryName);

    @Modifying
    @Transactional
    @Query(value = "UPDATE MenuCategoriesEntity SET categoryName=:categoryName WHERE id=:id")
    void updateCategoryNameById(Long id, String categoryName);

    List<MenuCategoriesEntity> findAll(Sort sort);


}
