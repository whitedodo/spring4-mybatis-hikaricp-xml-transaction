package com.website.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.website.example.vo.FoodMenuVO;

@Mapper
public interface FoodMenuMapper {

	@Select("SELECT * from Foodmenu_tbl where id = #{id}")
	FoodMenuVO select(@Param("id") String id);
	
	@Select("Select * from Foodmenu_tbl where id > 0 and id < 10")
	List<FoodMenuVO> selectAll();
	
	@Insert("Insert into Foodmenu_tbl(name, price, store_id, cnt, regidate) " + 
			"values(#{item.name}, #{item.price}, #{item.store_id}, #{item.cnt}, #{item.regidate})")
	void insert(@Param("item") FoodMenuVO vo);
	
	@Delete("delete from foodmenu_tbl where id = #{id}")
	void deleteFoodMenu(@Param("id") String id);
	
	@Update("update foodmenu_tbl set name = #{item.name}, " +
			"price = #{item.price}, store_id=#{item.store_id}, cnt=#{item.cnt}, " +
			"regidate = #{item.regidate} where id = #{item.id}")
	void updateFoodMenu(@Param("item") FoodMenuVO vo);
}
