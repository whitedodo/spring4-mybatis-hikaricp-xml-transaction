package com.website.example;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.website.example.dao.FoodMenuDAO;
import com.website.example.service.AccountService;
import com.website.example.service.FoodMenuService;
import com.website.example.vo.FoodMenuVO;


@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	private final int CHOOSE_SESSION_FACTORY = 1;
	private final int CHOOSE_FOODMENU_SERVICE = 2;
	private final int CHOOSE_FOODMENU_SELECTALL = 3;
	private final int CHOOSE_FOODMENU_SELECT = 4;
	private final int CHOOSE_FOODMENU_INSERT = 5;
	private final int CHOOSE_FOODMENU_UPDATE = 6;
	private final int CHOOSE_FOODMENU_DELETE = 7;
	
	private final int CHOOSE_TRANSACTION_MYBATIS = 8;
	
	// 루트컨테이너에서 빈(Bean)을 받아와서 자동으로 넣어줌
	@Autowired
	@Qualifier("sqlSessionFactory")
	private SqlSessionFactory sqlFactory;
	
	// 방식1: @Inject
    // @Inject
	@Autowired
	@Qualifier("foodMenuService")
    private FoodMenuService foodMenuService;

	@Autowired
	@Qualifier("accountService")
    private AccountService accountService;
	
	//@Inject
	//private FoodMenuDAO dao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws Exception {
		
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
	
		/*
		// 셈플 코드
		for (int id = CHOOSE_SESSION_FACTORY;
				id <= CHOOSE_FOODMENU_DELETE;
				id++ ) {
			
			sampleHelloWorld(id);
 						
		} // end of for
		*/
		
		// 2단계
		try {
			transactionSample(CHOOSE_TRANSACTION_MYBATIS);
			
		} catch (SQLException e) {
			System.out.println("트랜젝션 체크(오류)");
			
			e.printStackTrace();
		}
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}

	private void transactionSample( int choose ) throws Exception {
		
		switch ( choose ) {
	
			case CHOOSE_TRANSACTION_MYBATIS:
				
				accountService.accountTransfer("홍길동", "홍길자", 500);
				break;
		
		} // end of switch
		
		
	}
	
	private void sampleHelloWorld(int choose) {
		
		switch ( choose ) {
		
			case CHOOSE_SESSION_FACTORY:
			
				// 1단계: SqlSession 실험
				System.out.println("----- 1단계: SqlSession ----");
				
				try {

					SqlSession session = sqlFactory.openSession();
					System.out.println("성공1 : " + session);
						
				} catch (Exception ex){
					System.out.println("실패1..!");
					ex.printStackTrace();
				}
				
				break;
				
			case CHOOSE_FOODMENU_SERVICE:
				
				/*
				// 1개만 검색
		        System.out.println(service.select("1"));
				*/
				
				/*
				try {

					FoodMenuVO vo = dao.select("1");
					System.out.println("성공2 : " + vo.getName());
						
				} catch (Exception ex){
					System.out.println("실패2..!");
					ex.printStackTrace();
				}
				*/
				
				break;
				
			case CHOOSE_FOODMENU_SELECTALL:
				
				System.out.println("----- 3단계: SelectAll ----");
				// 3단계 어노테이션 실험 (전체 불러오기) - SelectAll
				try {
					
					if ( foodMenuService != null )
						System.out.println("참3");
					else
						System.out.println("거짓3");
					
					List<FoodMenuVO> list = foodMenuService.selectAll();
					
					for (FoodMenuVO vo : list) {
						System.out.println("불러오기 : " + vo.getName());
					}
					
				} catch (Exception ex){
					System.out.println("실패..!");
					ex.printStackTrace();
				}
				
				break;
				
			case CHOOSE_FOODMENU_SELECT:
				
				System.out.println("----- 4단계: Select ----");
				// 4단계 어노테이션 실험 - 단일 쿼리 조회
				try {
					
					if ( foodMenuService != null )
						System.out.println("참4");
					else
						System.out.println("거짓4");
					
					FoodMenuVO vo = foodMenuService.select("1");
					System.out.println("서비스 연결 성공 : " + vo.getName());
						
				} catch (Exception ex){
					System.out.println("실패..!");
					ex.printStackTrace();
				}
				
				break;
				
			case CHOOSE_FOODMENU_INSERT:

				System.out.println("----- 5단계: insert ----");
				// 5단계 어노테이션 실험(등록) - Insert
				try {
					
					if ( foodMenuService != null )
						System.out.println("참5");
					else
						System.out.println("거짓5");
					
					FoodMenuVO vo = new FoodMenuVO();
					
					vo.setName("홍길자");
					vo.setPrice(10000);
					vo.setStore_id(2);
					vo.setCnt(0);
					vo.setRegidate(java.sql.Date.valueOf("2020-04-02"));
					
					foodMenuService.insert(vo);
					
					System.out.println("등록 성공 : " + vo.getName());
						
				} catch (Exception ex){
					System.out.println("실패..!");
					ex.printStackTrace();
				}

				break;
				
			case CHOOSE_FOODMENU_UPDATE:
				
				System.out.println("----- 6단계: Update ----");
				// 6단계 어노테이션 실험 (수정 구현) - UPDATE
				try {
					
					if ( foodMenuService != null )
						System.out.println("참6");
					else
						System.out.println("거짓6");

					FoodMenuVO vo = new FoodMenuVO();
					
					vo.setId(1);
					vo.setName("홍길자");
					vo.setPrice(10000);
					vo.setStore_id(2);
					vo.setCnt(0);
					vo.setRegidate(java.sql.Date.valueOf("2020-04-02"));
					
					foodMenuService.updateFoodMenu(vo);
					
				} catch (Exception ex){
					System.out.println("실패..!");
					ex.printStackTrace();
				}
				
				break;
				
			case CHOOSE_FOODMENU_DELETE:
				
				System.out.println("----- 7단계: delete ----");
				// 7단계 어노테이션 실험 (삭제 구현) - DELETE
				try {
					
					if ( foodMenuService != null )
						System.out.println("참7");
					else
						System.out.println("거짓7");

					FoodMenuVO vo = new FoodMenuVO();
								
					foodMenuService.deleteFoodMenu("4");
					
				} catch (Exception ex){
					System.out.println("실패..!");
					ex.printStackTrace();
				}
				
				break;
	
		
		} // end of switch
		
	}
	
}
