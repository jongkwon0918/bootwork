package com.bs.boot.model.demo.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

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
		return demoRepository.findAll().stream()
				.map(DemoEntity::convert).toList();
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
		}catch(RuntimeException e) {
			return false;
		}
		return true;
	}

}
