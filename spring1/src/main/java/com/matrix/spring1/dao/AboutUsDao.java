package com.matrix.spring1.dao;

import com.matrix.spring1.entity.AboutUsEntity;
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
public class AboutUsDao {
    @PersistenceContext
    EntityManager em;

    public List<AboutUsEntity> findAboutUsTexts(String heading, String text1, String text2, String text3, String text4) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        log.info("heading {} text1 {} text2 {} text3 {} text4 {}", heading, text1, text2, text3, text4);
        CriteriaQuery<AboutUsEntity> criteriaQuery = criteriaBuilder.createQuery(AboutUsEntity.class);
        Root<AboutUsEntity> aboutUsEntityRoot = criteriaQuery.from(AboutUsEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        if (heading != null) {
            predicates.add(criteriaBuilder.equal(aboutUsEntityRoot.get("heading"), heading));
        }
        if (text1 != null) {
            predicates.add(criteriaBuilder.equal(aboutUsEntityRoot.get("text1"), text1));
        }
        if (text2 != null) {
            predicates.add(criteriaBuilder.equal(aboutUsEntityRoot.get("text2"), text2));
        }
        if (text3 != null) {
            predicates.add(criteriaBuilder.equal(aboutUsEntityRoot.get("text3"), text3));
        }
        if (text4 != null) {
            predicates.add(criteriaBuilder.equal(aboutUsEntityRoot.get("text4"), text4));
        }
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        TypedQuery<AboutUsEntity> query = em.createQuery(criteriaQuery);

        return query.getResultList();
    }
}
