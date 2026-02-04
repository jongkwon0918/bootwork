package com.bs.boot.model.demo.repository;

import java.util.List;
import java.util.stream.Stream;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bs.boot.model.entity.DemoEntity;

@Repository
public interface DemoRepository extends JpaRepository<DemoEntity,Integer>{
	
	//쿼리 메소드만들기
	//이름에 매개변수값이 포함된 demo 조회하기
	//방식: findBy 필드명[연산자][논리연산자][필드명][연산자]....[orderby필드명정렬방식]
	//이름에 매개변수값이 일치하는 demo조회하기
	Stream<DemoEntity> findByDevName(String devName);
	
	//나이가 매개변수값보다 많은 demo조회
	Stream<DemoEntity> findByDevAgeGreaterThanEqual(Integer age);
	
	//jpql구문 이용하기
	//@Query어노테이션 이용
	@Query("select d from DemoEntity d")
	public List<DemoEntity> selectDemoAll();
	
	//매개변수 처리하기
	//인덱스. key방식
	@Query("select d from DemoEntity d where d.devAge >= ?1 and d.devName like ?2")
	public List<DemoEntity> selectDemoByAgeAndName(Integer age, String name);
	
	@Query("select d from DemoEntity d where d.devGender = :gender")
	public List<DemoEntity> selectGender(@Param(value = "gender") String gender);
	
	@Query(value = "select * from dev", nativeQuery = true)
	public List<DemoEntity> selectNativeAll();
	
}
