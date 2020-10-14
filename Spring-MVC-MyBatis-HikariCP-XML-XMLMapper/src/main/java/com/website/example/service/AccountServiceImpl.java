package com.website.example.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.website.example.dao.AccountDAO;
import com.website.example.dao.FoodMenuDAO;
import com.website.example.vo.AccountVO;

@Repository("accountService")
public class AccountServiceImpl implements AccountService {

	@Autowired
	@Qualifier("accountDAO")
	private AccountDAO accountDAO;
	
	@Override
	public void accountCreate(AccountVO vo) throws SQLException {
		accountDAO.createAccount(vo);
	}

	@Override
	public void accountTransfer(String sender, String receiver, int money) throws Exception  {
		accountDAO.transfer(sender, receiver, money);
	}

}
