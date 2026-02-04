package com.bs.boot.model.demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bs.boot.model.entity.DemoEntity;
import com.bs.boot.model.entity.QDemoEntity;
import com.querydsl.jpa.impl.JPAQuery;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class QueryDslrepository {
	private final EntityManager em;
	
	public List<DemoEntity> findAllDemoQueryDsl(String name){
		JPAQuery<DemoEntity> query = new JPAQuery<>(em);
		//Root<>
		QDemoEntity qdemo=QDemoEntity.demoEntity;
		
		query.from(qdemo);
		query.where(qdemo.devName.like("%"+name+"%"));
		query.orderBy(qdemo.devAge.desc());
		
		return query.fetch();
	}

}
