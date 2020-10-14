package com.website.example.dao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.website.example.vo.AccountVO;

@Repository("accountDAO")
public class AccountDAOImpl implements AccountDAO {

	@Autowired
	@Qualifier("sqlSession")
    private SqlSession sqlSession;    

	//PlatformTransactionManager 이용 수동으로 트랜잭션을 다루는 방법(MyBatis 지원)
	@Autowired
	@Qualifier("txManager")
	private PlatformTransactionManager txManager;
	DefaultTransactionDefinition def = null;
	TransactionStatus status = null;
	
	private static final String Namespace = "com.website.example.mapper.AccountMapper";

    @Override
	public AccountVO select(String idx) {
		return sqlSession.selectOne(Namespace + ".select", idx);
	}

	@Override
	public List<AccountVO> selectAll() {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("start", 1);
		paramMap.put("end", 10);
		
		return sqlSession.selectList(Namespace + ".selectAll", paramMap);
		
	}

	@Override
	public void createAccount(AccountVO vo) {

		System.out.println("추가 결과" + sqlSession.insert(Namespace + ".insertAccount", vo) );
		
	}

	@Override
	public void deleteAccount(String idx) {

		sqlSession.delete(Namespace + ".deleteAccount", idx);
	}

	@Override
	public void minus(String sender, long money) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", sender);
		paramMap.put("money", money);
		
		sqlSession.update(Namespace + ".updateMinus", paramMap);
	}

	@Override
	public void plus(String receiver, long money) {
		
		AccountVO vo = new AccountVO();

		vo.setName(receiver);
		vo.setBalance(money);
		
		sqlSession.update(Namespace + ".updatePlus", vo);
		
	}
	
	// @Transactional 권장하진 않음. (어노테이션 관련 문제)
	// 먹통일 때도 생김.(MyBatis는 MyBatis 방식의 Transaction 방법이 있음.)
	@Override
	public void transfer(String sender, String receiver, long money) throws Exception {
		
		AccountVO paramvo2 = new AccountVO();
		
		Map<String, Object> paramMap1 = new HashMap<String, Object>();
		
		paramMap1.put("name", sender);
		paramMap1.put("money", money);
		
		paramvo2.setName(receiver);
		paramvo2.setBalance(money);

		try {

			def = new DefaultTransactionDefinition();
			def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

			status = txManager.getTransaction(def);	
			
			sqlSession.update(Namespace + ".updateMinus", paramMap1);
			// 의도적 예외 발생
			//sqlSession.update(Namespace + ".updatePlus", paramvo2);
			sqlSession.update(Namespace + ".updatePlus", null);
			txManager.commit(status);
			
		} catch (Exception e) {
			txManager.rollback(status);
		}
		
	}

}