# Spring Framework 4.2.4 Releases에서 MyBatis, HikariCP, XML(Java and XML Mapper) Transaction 설정하기
(Setting MyBatis, HikariCP, XML(Java and XML Mapper) Transaction in Spring Framework 4.2.4 Releases)

### 기본 정보(Information)
##### 제작일자(Create date): 2020-10-14
##### 작성언어(Write language): Java
##### IDE: Eclipse IDE with Spring Tool Suite 4-4.7.2.
##### 제작자(Author): 도도(Dodo) / rabbit.white at daum dot net
##### 프레임워크(Framework): 
##### 라이브러리(Library): 
##### 1. Apache-Maven 3.6.3/1.16.0.2.20200610-1735 (https://maven.apache.org/)
##### (소프트웨어 프로젝트 관리 및 이해 도구)
##### 2. Spring Framework 4.2.4 RELEASES
##### 3. Spring-test 4.2.4 RELEASES
##### 4. https://mvnrepository.com/artifact/com.zaxxer/HikariCP
##### - HikariCP 3.4.5
##### 5. https://mvnrepository.com/artifact/org.mybatis/mybatis
##### - MyBatis 3.5.6
##### 6. https://mvnrepository.com/artifact/org.mybatis/mybatis-spring
##### - myBatis-spring
##### 7. https://mvnrepository.com/artifact/javax.servlet/jstl
##### 자바 버전(Java-Version): OpenJDK-14.0.2 (https://openjdk.java.net/) // Version 1.8
##### 8. amazon-corretto-8.265.01.1-windows-x64-jdk.zip
##### 9. Spring-TX
##### - https://mvnrepository.com/artifact/org.springframework/spring-tx

### 1. 소개(Description)
##### 1. 해당 프로젝트는 Spring Framework 4.2.4 Releases, MyBatis, HikariCP, MyBatis-Spring을 적용하여 트랜젝션 처리를 구현하였다.
#####    (The project implemented transaction processing by applying Spring Framework 4.2.4 Releases, MyBatis, HikariCP, and MyBatis-Spring.)
##### 2. 어노테이션 방식으로 해버리면, MyBatis에서 트랜젝션이 잘 동작하지 않는 문제가 있다. 메뉴얼 참고하여 보완함.
#####    (If you do it with annotation method, there is a problem that transactions do not work well in MyBatis. Supplemented by referring to the manual.)

### 2. 시연(Practice)


### 3. 참고자료(References)
##### 1. MyBatis - 마이바티스 3 | 매퍼 XML 파일, https://mybatis.org/mybatis-3/ko/sqlmap-xml.html, Accessed by 2020-10-12, Last Modified 2020-07-10.
