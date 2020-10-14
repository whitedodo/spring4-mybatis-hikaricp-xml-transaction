package com.website.example.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.website.example.mapper.AccountMapper;
import com.website.example.mapper.FoodMenuMapper;
import com.website.example.vo.AccountVO;
import com.website.example.vo.FoodMenuVO;

@Repository("accountDAO")
public class AccountDAOImpl implements AccountDAO {

	@Autowired
	@Qualifier("sqlSessionFactory")
    private SqlSessionFactory sqlFactory;

	//PlatformTransactionManager 이용 수동으로 트랜잭션을 다루는 방법(MyBatis 지원)
	@Autowired
	@Qualifier("txManager")
	private PlatformTransactionManager txManager;
	DefaultTransactionDefinition def = null;
	TransactionStatus status = null;
	    
	@Override
	public AccountVO select(String idx) {

		AccountVO vo = null;
		
		try (SqlSession session = sqlFactory.openSession() ) {
			AccountMapper mapper = session.getMapper(AccountMapper.class);
			vo = mapper.select(idx);
			//System.out.println("2참");
		}
		
		return vo;
	}

	@Override
	public List<AccountVO> selectAll() {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("start", 1);
		paramMap.put("end", 10);
		
		List<AccountVO> list = null;
		
		try (SqlSession session = sqlFactory.openSession() ) {
			
			AccountMapper mapper = session.getMapper(AccountMapper.class);
			
			list = mapper.selectAll();
			//System.out.println("2참");
		}
		
		return list;
		
	}

	@Override
	public void createAccount(AccountVO vo) {

		try (SqlSession session = sqlFactory.openSession() ) {
			AccountMapper mapper = session.getMapper(AccountMapper.class);
			mapper.insert(vo);
			
			// System.out.println("참");
		}
		
		//System.out.println("추가 결과" + sqlSession.insert(Namespace + ".insertAccount", vo) );
		
	}

	@Override
	public void deleteAccount(String idx) {

		// System.out.println("등록DAOImpl");
		
		try (SqlSession session = sqlFactory.openSession() ) {
			AccountMapper mapper = session.getMapper(AccountMapper.class);
			mapper.deleteAccount(idx);
			
			// System.out.println("참");
		}
		
	}

	@Override
	public void minus(String sender, long money) {

		AccountVO vo = new AccountVO();
		vo.setName(sender);
		vo.setBalance(money);
		
		try (SqlSession session = sqlFactory.openSession() ) {
			AccountMapper mapper = session.getMapper(AccountMapper.class);
			mapper.updateMinus(vo);
			
			// System.out.println("참");
		}
		
	}

	@Override
	public void plus(String receiver, long money) throws Exception {
		
		AccountVO vo = new AccountVO();
		vo.setName(receiver);
		vo.setBalance(money);
    	
    	try (SqlSession session = sqlFactory.openSession() ) {
			AccountMapper mapper = session.getMapper(AccountMapper.class);
			mapper.updatePlus(vo);
			
			// System.out.println("참");
		}
		
	}
	

	@Override
	public void transfer(String sender, String receiver, long money) throws Exception{
		
		AccountVO vo1 = new AccountVO();
		vo1.setName(sender);
		vo1.setBalance(money);
		
		AccountVO vo2 = new AccountVO();
		vo2.setName(receiver);
		vo2.setBalance(money);
		
		System.out.println("recv:" + receiver + "/money:" + money);

		try (SqlSession session = sqlFactory.openSession() ){

			def = new DefaultTransactionDefinition();
			def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

			status = txManager.getTransaction(def);	
			
			AccountMapper mapper = session.getMapper(AccountMapper.class);
			mapper.updateMinus(vo1);
			
			// System.out.println("참");
			// 의도적 예외 발생
			mapper.updatePlus(vo2);
			
			txManager.commit(status);
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
			txManager.rollback(status);
		}
		
	}


}
