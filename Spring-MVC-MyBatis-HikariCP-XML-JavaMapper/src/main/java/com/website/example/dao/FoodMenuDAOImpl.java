package com.website.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.website.example.mapper.FoodMenuMapper;
import com.website.example.vo.FoodMenuVO;

// XML 환경설정으로 구성했을 때는 필수로 넣어줘야 안 죽음.(서블릿)
// @Component 동작
@Component("foodMenuDAO")
public class FoodMenuDAOImpl implements FoodMenuDAO {

	@Autowired
	@Qualifier("sqlSessionFactory")
	private SqlSessionFactory sqlFactory;
	
	@Override
	public FoodMenuVO select(String id) {
		
		FoodMenuVO vo = null;
			
		/*
		if ( sqlFactory != null ) {
			System.out.println("1참");
			
		}else {
			System.out.println("2거");
		}
		*/
		
		try (SqlSession session = sqlFactory.openSession() ) {
			FoodMenuMapper mapper = session.getMapper(FoodMenuMapper.class);
			vo = mapper.select(id);
			//System.out.println("2참");
		}
		
		return vo;
	}

	@Override
	public List<FoodMenuVO> selectAll() {
		
		List<FoodMenuVO> list = null;
		
		try (SqlSession session = sqlFactory.openSession() ) {
			FoodMenuMapper mapper = session.getMapper(FoodMenuMapper.class);
			list = mapper.selectAll();
			//System.out.println("2참");
		}
		
		return list;
		
	}

	@Override
	public void insert(FoodMenuVO vo) {
		
		// System.out.println("등록DAOImpl");
		
		try (SqlSession session = sqlFactory.openSession() ) {
			FoodMenuMapper mapper = session.getMapper(FoodMenuMapper.class);
			mapper.insert(vo);
			
			// System.out.println("참");
		}
		
	}

	@Override
	public void deleteFoodMenu(String id) {
		
		// System.out.println("등록DAOImpl");
		
		try (SqlSession session = sqlFactory.openSession() ) {
			FoodMenuMapper mapper = session.getMapper(FoodMenuMapper.class);
			mapper.deleteFoodMenu(id);
			
			// System.out.println("참");
		}
		
	}

	@Override
	public void updateFoodMenu(FoodMenuVO vo) {
		// TODO Auto-generated method stub
		// System.out.println("등록DAOImpl");
		
		try (SqlSession session = sqlFactory.openSession() ) {
			FoodMenuMapper mapper = session.getMapper(FoodMenuMapper.class);
			mapper.updateFoodMenu(vo);
			
			// System.out.println("참");
		}
		
	}	
	
}
