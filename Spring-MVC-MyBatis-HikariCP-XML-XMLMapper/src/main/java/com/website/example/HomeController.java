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


/**
 * Handles requests for the application home page.
 */
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
	

    // @Inject - 동작은 함. 단, 특정 ID를 지정할 수가 없음.
	// (Inject의 한계)
	
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
		for ( int id = CHOOSE_SESSION_FACTORY; 
				id <= CHOOSE_FOODMENU_DELETE; 
				id++ ) {
			
			sampleHelloWorld(id);
			
		}
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
		
		switch ( choose )
		{
			case CHOOSE_SESSION_FACTORY:

				try {
					System.out.println("----- sqlFactory 확인 ------");
					SqlSession session = sqlFactory.openSession();
					System.out.println("성공1 : " + session);
						
				} catch (Exception ex){
					System.out.println("실패1..!");
					ex.printStackTrace();
				}
				
				break;
				
				
			case CHOOSE_FOODMENU_SERVICE:
				
				System.out.println("----- 2. service 확인 ------");
				/*
				// 1개만 검색
		        System.out.println(service.select("1"));
				*/
				
				/*
				try {

					FoodMenuVO vo = dao.select("1");
					System.out.println("성공 : " + vo.getName());
						
				} catch (Exception ex){
					System.out.println("실패..!");
					ex.printStackTrace();
				}
				
				*/
				
				break;
				
			case CHOOSE_FOODMENU_SELECTALL:

				System.out.println("----- 3. foodMenu_selectAll ------");
				
				// 단일 조회
				try {
					
					if ( foodMenuService != null )
						System.out.println("참2");
					else
						System.out.println("거짓2");
					
					FoodMenuVO vo = foodMenuService.select("1");
					System.out.println("성공2 : " + vo.getName());
						
				} catch (Exception ex){
					System.out.println("실패2..!");
					ex.printStackTrace();
				}
				
				break;
				
			case CHOOSE_FOODMENU_SELECT:

				System.out.println("----- 4. foodMenu_select ------");
				
				// 다중 조회
				try {
					
					if ( foodMenuService != null )
						System.out.println("참3");
					else
						System.out.println("거짓3");
					
					List<FoodMenuVO> list = foodMenuService.selectAll();
					
					for ( FoodMenuVO vo : list) {
						System.out.println("성공 : " + vo.getName());	
					}
						
				} catch (Exception ex){
					System.out.println("실패..!");
					ex.printStackTrace();
				}
				
				break;
				
			case CHOOSE_FOODMENU_INSERT:

				System.out.println("----- 5. foodMenu_insert ------");
				
				// 추가
				try {
					
					if ( foodMenuService != null )
						System.out.println("참4");
					else
						System.out.println("거짓4");
					
					FoodMenuVO vo = new FoodMenuVO();
					vo.setName("홍길녀");
					vo.setPrice(20000);
					vo.setStore_id(2);
					vo.setCnt(0);
					vo.setRegidate(java.sql.Date.valueOf("2020-03-04"));
					
					foodMenuService.insert(vo);
					
						
				} catch (Exception ex){
					System.out.println("실패..!");
					ex.printStackTrace();
				}
				
				break;
				
			case CHOOSE_FOODMENU_UPDATE:

				System.out.println("----- 6. foodMenu_update ------");
				
				// 수정
				try {
					
					if ( foodMenuService != null )
						System.out.println("참5");
					else
						System.out.println("거짓5");
					
					FoodMenuVO vo = new FoodMenuVO();
					vo.setId(3);
					vo.setName("홍길영");
					vo.setPrice(15000);
					vo.setStore_id(1);
					vo.setCnt(3);
					vo.setRegidate(java.sql.Date.valueOf("2020-02-04"));
					
					foodMenuService.updateFoodMenu(vo);
					
				} catch (Exception ex){
					System.out.println("실패..!");
					ex.printStackTrace();
				}
				
				break;
				
			case CHOOSE_FOODMENU_DELETE:

				System.out.println("----- 7. foodMenu_delete ------");
				
				// 삭제
				try {
					
					if ( foodMenuService != null )
						System.out.println("참6");
					else
						System.out.println("거짓6");
					
					String id = "45";
					
					foodMenuService.deleteFoodMenu(id);
					
						
				} catch (Exception ex){
					System.out.println("실패..!");
					ex.printStackTrace();
				}
				
				//System.out.println(vo.getName());
				break;
		
		} // end of switch
		
		
	}
	
}
