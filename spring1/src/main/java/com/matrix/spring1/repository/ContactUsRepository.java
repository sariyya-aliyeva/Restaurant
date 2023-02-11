package com.matrix.spring1.repository;

import com.matrix.spring1.entity.ContactUsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ContactUsRepository extends JpaRepository<ContactUsEntity, Long> {
    @Modifying
    @Transactional
    Long deleteByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ContactUsEntity SET address=:address WHERE id=:id")
    void updateAddressById(Long id, String address);
}
