package com.matrix.spring1.dao;

import com.matrix.spring1.entity.TeamEntity;
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
public class TeamDao {
    @PersistenceContext
    EntityManager em;

    public List<TeamEntity> findTeamMembers(String memberName, String memberText) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        log.info("memberName {} memberText {}", memberName, memberText);
        CriteriaQuery<TeamEntity> criteriaQuery = criteriaBuilder.createQuery(TeamEntity.class);
        Root<TeamEntity> teamEntityRoot = criteriaQuery.from(TeamEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        if (memberName != null) {
            predicates.add(criteriaBuilder.equal(teamEntityRoot.get("memberName"), memberName));
        }
        if (memberText != null) {
            predicates.add(criteriaBuilder.equal(teamEntityRoot.get("memberText"), memberText));
        }
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        TypedQuery<TeamEntity> query = em.createQuery(criteriaQuery);

        return query.getResultList();
    }
}
