package com.website.example.service;


import java.sql.SQLException;

import com.website.example.vo.AccountVO;

public interface AccountService {
	
    public void accountCreate(AccountVO vo) throws SQLException;
    public void accountTransfer(String sender, String receiver, int money) throws Exception;
    
}
