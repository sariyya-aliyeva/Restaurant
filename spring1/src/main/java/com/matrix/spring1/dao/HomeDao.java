package com.matrix.spring1.dao;

import com.matrix.spring1.entity.HomeEntity;
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
public class HomeDao {
    @PersistenceContext
    EntityManager em;

    public List<HomeEntity> findHomeTexts(String heading, String text1, String text2) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        log.info("heading {} text1 {} text2 {}", heading, text1, text2);
        CriteriaQuery<HomeEntity> criteriaQuery = criteriaBuilder.createQuery(HomeEntity.class);
        Root<HomeEntity> homeEntityRoot = criteriaQuery.from(HomeEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        if (heading != null) {
            predicates.add(criteriaBuilder.equal(homeEntityRoot.get("heading"), heading));
        }
        if (text1 != null) {
            predicates.add(criteriaBuilder.equal(homeEntityRoot.get("text1"), text1));
        }
        if (text2 != null) {
            predicates.add(criteriaBuilder.equal(homeEntityRoot.get("text2"), text2));
        }
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        TypedQuery<HomeEntity> query = em.createQuery(criteriaQuery);

        return query.getResultList();
    }
}
