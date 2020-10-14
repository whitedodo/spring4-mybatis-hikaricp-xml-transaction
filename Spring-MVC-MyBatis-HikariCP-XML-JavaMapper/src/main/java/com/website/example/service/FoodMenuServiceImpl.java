package com.website.example.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.website.example.dao.FoodMenuDAO;
import com.website.example.vo.FoodMenuVO;

@Repository("foodMenuService")
public class FoodMenuServiceImpl implements FoodMenuService {
	
	// 주입(Inject - @Repository 표기가 있어야 가능함.)
	// 어노테이션 연결된 구성
	@Autowired
	@Qualifier("foodMenuDAO")
	private FoodMenuDAO foodMenuDao;

	@Override
	public FoodMenuVO select(String id) {
		
		return foodMenuDao.select(id);
	}
	
	@Override
	public List<FoodMenuVO> selectAll() {
		
		return foodMenuDao.selectAll();
	}

	@Override
	public void insert(FoodMenuVO vo) {
		foodMenuDao.insert(vo);
	}

	@Override
	public void deleteFoodMenu(String id) {
		foodMenuDao.deleteFoodMenu(id);
	}

	@Override
	public void updateFoodMenu(FoodMenuVO vo) {
		foodMenuDao.updateFoodMenu(vo);
	}


}
