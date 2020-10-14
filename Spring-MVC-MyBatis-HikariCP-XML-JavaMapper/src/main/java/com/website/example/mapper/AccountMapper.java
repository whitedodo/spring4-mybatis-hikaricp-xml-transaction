package com.website.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.website.example.vo.AccountVO;

@Mapper
public interface AccountMapper {

	@Select("SELECT * from account_tbl where idx = #{idx}")
	AccountVO select(@Param("id") String id);
	
	@Select("Select * from account_tbl where idx > 0 and idx < 10")
	List<AccountVO> selectAll();
	
	@Insert("Insert into account_tbl(name, balance, regidate) " + 
			"values(#{item.name}, #{item.balance}, #{item.regidate})")
	void insert(@Param("item") AccountVO vo);
	
	@Delete("delete from account_tbl where idx = #{idx}")
	void deleteAccount(@Param("idx") String idx);
	
	@Update("update account_tbl set balance = " +
			" (select balance from account_tbl " + 
			"where name = #{item.name}) - #{item.balance} where name=#{item.name}")
	void updateMinus(@Param("item") AccountVO vo);
	
	@Update("update account_tbl set balance = " +
			" (select balance from account_tbl " + 
			"where name = #{item.name}) + #{item.balance} where name=#{item.name}")
	void updatePlus(@Param("item") AccountVO vo);
}
