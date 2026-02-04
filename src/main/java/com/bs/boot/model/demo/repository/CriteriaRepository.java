package com.bs.boot.model.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bs.boot.model.dto.Demo;
import com.bs.boot.model.entity.DemoEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class CriteriaRepository {
	@PersistenceContext
	private EntityManager em;
	
	public List<DemoEntity> findAllCriteria(Map<String, Object> param){
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<DemoEntity> criteriaQuery=cb.createQuery(DemoEntity.class);
		//from
		Root<DemoEntity> demo = criteriaQuery.from(DemoEntity.class);
		
		//select
		criteriaQuery.select(demo);
		
		//where
		List<Predicate> wheres=new ArrayList<>();
		if (param!=null && ((String)param.get("name")).length()>0) {			
			wheres.add(cb.like(demo.get("devName"), "%"+param.get("name")+"%"));
		} 
		if (param.get("age")!=null && param.get("age") instanceof Integer) {
			wheres.add(cb.greaterThan(demo.get("devAge"), (Integer)param.get("age")));
		}
		if (wheres.size()>0) {
			criteriaQuery.where(wheres.toArray(new Predicate[wheres.size()]));
		}
		TypedQuery<DemoEntity> tquery=em.createQuery(criteriaQuery);
		return tquery.getResultList();
		
	}
}
