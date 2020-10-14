package com.website.example.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.website.example.vo.FoodMenuVO;

@Repository("foodMenuDAO")
public class FoodMenuDAOImpl implements FoodMenuDAO {

    @Inject
    private SqlSession sqlSession;

    private static final String Namespace = "com.website.example.mapper.FoodMenuMapper";

	@Override
	public FoodMenuVO select(String id) {
		
		return sqlSession.selectOne(Namespace + ".select", id);
	}

	@Override
	public List<FoodMenuVO> selectAll() {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("start", 1);
		paramMap.put("end", 10);
		
		return sqlSession.selectList(Namespace + ".selectAll", paramMap);
		
	}

	@Override
	public void insert(FoodMenuVO vo) {
		
		System.out.println("추가 결과" + sqlSession.insert(Namespace + ".insertFoodMenu", vo) );
		
	}

	@Override
	public void deleteFoodMenu(String id) {
		
		sqlSession.delete(Namespace + ".deleteFoodMenu", id);
	}

	@Override
	public void updateFoodMenu(FoodMenuVO vo) {
		
		sqlSession.update(Namespace + ".updateFoodMenu", vo);
		
	}


}
