package com.matrix.spring1.dao;

import com.matrix.spring1.entity.BlogEntity;
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
public class BlogDao {
    @PersistenceContext
    EntityManager em;

    public List<BlogEntity> findBlogTexts(String date, String text, String author) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        log.info("date {} text {} author {}", date, text, author);
        CriteriaQuery<BlogEntity> criteriaQuery = criteriaBuilder.createQuery(BlogEntity.class);
        Root<BlogEntity> blogEntityRoot = criteriaQuery.from(BlogEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        if (date != null) {
            predicates.add(criteriaBuilder.equal(blogEntityRoot.get("date"), date));
        }
        if (text != null) {
            predicates.add(criteriaBuilder.equal(blogEntityRoot.get("text"), text));
        }
        if (author != null) {
            predicates.add(criteriaBuilder.equal(blogEntityRoot.get("author"), author));
        }
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        TypedQuery<BlogEntity> query = em.createQuery(criteriaQuery);

        return query.getResultList();
    }
}
