package com.bs.boot.model.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bs.boot.model.entity.DemoEntity;

@Repository
public interface DemoRepository extends JpaRepository<DemoEntity,Integer>{
}
