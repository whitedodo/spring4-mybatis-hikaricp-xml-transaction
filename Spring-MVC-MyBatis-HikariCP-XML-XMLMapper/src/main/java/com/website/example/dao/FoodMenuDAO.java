package com.website.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.website.example.vo.FoodMenuVO;

public interface FoodMenuDAO {
	
	// 1개만 select문
	public FoodMenuVO select(@Param("id")String id);
	
	// 여러 개 select문
	public List<FoodMenuVO> selectAll();
	
	// 추가 insert문
	public void insert(FoodMenuVO vo);
	
	// 수정 update문
	public void updateFoodMenu(FoodMenuVO vo);
	
	// 삭제 delete문
	public void deleteFoodMenu(String id);  
	
}
