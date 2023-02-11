package com.matrix.spring1.dao;

import com.matrix.spring1.entity.ContactUsEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class ContactUsDao {
    @PersistenceContext
    EntityManager em;

    public List<ContactUsEntity> findContactInfo(String phoneNumber, String email, String address) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        log.info("phoneNumber {} email {} address {}", phoneNumber, email, address);
        CriteriaQuery<ContactUsEntity> criteriaQuery = criteriaBuilder.createQuery(ContactUsEntity.class);
        Root<ContactUsEntity> contactUsEntityRoot = criteriaQuery.from(ContactUsEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        if (phoneNumber != null) {
            predicates.add(criteriaBuilder.equal(contactUsEntityRoot.get("phoneNumber"), phoneNumber));
        }
        if (email != null) {
            predicates.add(criteriaBuilder.equal(contactUsEntityRoot.get("email"), email));
        }
        if (address != null) {
            predicates.add(criteriaBuilder.equal(contactUsEntityRoot.get("address"), address));
        }
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        TypedQuery<ContactUsEntity> query = em.createQuery(criteriaQuery);

        return query.getResultList();
    }
}
