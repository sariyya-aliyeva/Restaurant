package com.matrix.spring1.repository;

import com.matrix.spring1.entity.MenuProductsEntity;
import com.matrix.spring1.entity.TeamEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface MenuProductsRepository extends JpaRepository<MenuProductsEntity, Long> {
    List<MenuProductsEntity> findByProductName(String productName);

    @Modifying
    @Transactional
    @Query(value = "UPDATE MenuProductsEntity SET productName=:productName WHERE id=:id")
    void updateProductNameById(Long id, String productName);

    List<MenuProductsEntity> findAll(Sort sort);

    @Query(value = "Select p from MenuProductsEntity p")
    List<MenuProductsEntity> findAllSort(Sort sort);

    @Query(value = "Select p from MenuProductsEntity p")
    List<MenuProductsEntity> findAllPage(Pageable page);
}
