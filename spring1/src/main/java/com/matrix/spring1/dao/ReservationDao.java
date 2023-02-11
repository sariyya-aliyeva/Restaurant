package com.matrix.spring1.dao;

import com.matrix.spring1.entity.ReservationEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class ReservationDao {
    @PersistenceContext
    EntityManager em;

    public List<ReservationEntity> findReservation(String name, String contactNo, LocalDate date, Integer noOfPeople,
                                                   String preferredFood, String occasion) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        log.info("name {} contactNo {} date {} noOfPeople {} preferredFood {} occasion {}", name, contactNo, date,
                noOfPeople, preferredFood, occasion);
        CriteriaQuery<ReservationEntity> criteriaQuery = criteriaBuilder.createQuery(ReservationEntity.class);
        Root<ReservationEntity> reservationEntityRoot = criteriaQuery.from(ReservationEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        if (name != null) {
            predicates.add(criteriaBuilder.equal(reservationEntityRoot.get("name"), name));
        }
        if (contactNo != null) {
            predicates.add(criteriaBuilder.equal(reservationEntityRoot.get("contactNo"), contactNo));
        }
        if (date != null) {
            predicates.add(criteriaBuilder.equal(reservationEntityRoot.get("date"), date));
        }
        if (noOfPeople != null) {
            predicates.add(criteriaBuilder.equal(reservationEntityRoot.get("noOfPeople"), noOfPeople));
        }
        if (preferredFood != null) {
            predicates.add(criteriaBuilder.equal(reservationEntityRoot.get("preferredFood"), preferredFood));
        }
        if (occasion != null) {
            predicates.add(criteriaBuilder.equal(reservationEntityRoot.get("occasion"), occasion));
        }
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        TypedQuery<ReservationEntity> query = em.createQuery(criteriaQuery);

        return query.getResultList();
    }
}
