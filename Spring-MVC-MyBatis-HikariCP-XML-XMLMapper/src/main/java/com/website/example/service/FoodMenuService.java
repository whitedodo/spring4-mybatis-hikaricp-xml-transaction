package com.website.example.service;


import java.util.List;

import com.website.example.vo.FoodMenuVO;

public interface FoodMenuService {
	
    public FoodMenuVO select(String id);
    
    public List<FoodMenuVO> selectAll();
 
    public void insert(FoodMenuVO vo);
    
    public void updateFoodMenu(FoodMenuVO vo);
    
    public void deleteFoodMenu(String id);
    
}
