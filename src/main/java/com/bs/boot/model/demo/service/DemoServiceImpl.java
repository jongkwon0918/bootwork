package com.bs.boot.model.demo.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bs.boot.model.demo.repository.DemoRepository;
import com.bs.boot.model.dto.Demo;
import com.bs.boot.model.entity.DemoEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DemoServiceImpl implements DemoService {

	private final DemoRepository demoRepository;

	@Override
	public List<Demo> searchDemoAll() {
//		return demoRepository.findAll(Sort.by("devAge").descending()).stream().map(DemoEntity::convert).toList();

		/*
		 * Pageable pageable=PageRequest.of(0, 5); return
		 * demoRepository.findAll(pageable).stream().map(DemoEntity::convert).toList();
		 */

		
		Pageable pageable=PageRequest.of(0, 5, Sort.by("devAge").descending()); 
		return demoRepository.findAll(pageable).stream().map(DemoEntity::convert).toList();
		 
	}

	@Override
	public Demo searchDemoById(Integer devNo) {
//		DemoEntity demoEntity=demoRepository.findById(devNo);
//		if(demoEntity==null) throw new NullPointerException();
//		return demoEntity.convert();
		return demoRepository.findById(devNo).orElseThrow().convert();
	}

	@Override
	public boolean insertDemo(Demo demo) {
		try {
			demoRepository.save(demo.convert());
		} catch (RuntimeException e) {
			return false;
		}
		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Demo> searchDemoByName(String name) {
		// TODO Auto-generated method stub
		return demoRepository.findByDevName(name).map(DemoEntity::convert).toList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Demo> searchDemoByAgeGreater(Integer age) {
		// TODO Auto-generated method stub
		return demoRepository.findByDevAgeGreaterThanEqual(age).map(DemoEntity::convert).toList();
	}

}
