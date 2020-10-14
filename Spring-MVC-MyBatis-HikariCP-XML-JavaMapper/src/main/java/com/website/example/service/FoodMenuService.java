package com.website.example.service;


import java.util.List;

import com.website.example.vo.FoodMenuVO;

public interface FoodMenuService {
	
    public FoodMenuVO select(String id);
 
    public List<FoodMenuVO> selectAll();
    
    // 추가 (서비스 영역)
    public void insert(FoodMenuVO vo);

    // 삭제 (서비스 영역)
    public void deleteFoodMenu(String id);
    
    // 수정 (서비스 영역)
    public void updateFoodMenu(FoodMenuVO vo);
    
}
