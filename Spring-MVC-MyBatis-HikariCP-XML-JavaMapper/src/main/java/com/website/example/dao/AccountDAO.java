package com.website.example.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.website.example.vo.AccountVO;

public interface AccountDAO {
	
	// 1개만 select문
	public AccountVO select(@Param("idx")String idx);
	
	// 여러 개 select문
	public List<AccountVO> selectAll();
	
	// 추가 insert문
	public void createAccount(AccountVO vo);
	
	// 수정 update문(minus)
	public void minus(String sender, long money);
	
	// 수정 update문(plus)
	public void plus(String receiver, long moeny) throws Exception;
	
	// 삭제 delete문
	public void deleteAccount(String idx);
	
	// 트렌젝션 MyBatis 스타일 Transfer (Update)
	void transfer(String sender, String receiver, long money) throws Exception;
	
}
