package com.website.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.website.example.vo.FoodMenuVO;

public interface FoodMenuDAO {
	
	// 1개만 select
	public FoodMenuVO select(String id);
	
	// 여러 개 select
	public List<FoodMenuVO> selectAll();
	
	// 삽입 @INSERT 어노테이션 방식 구현
	public void insert(FoodMenuVO vo);
	
	// 삭제 @DELETE 어노테이션 방식 구현
	public void deleteFoodMenu(String id);
	
	// 수정 @UPDATE 어노테이션 방식 구현
	public void updateFoodMenu(FoodMenuVO vo);
	
}
