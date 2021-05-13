# 스프링 레거시 게시판 만들기
+ JDK 1.8
 
+ ORACLE 11G
 
+ Eclipse
 
+ Ojdbc 8 + Mybatis
 
+ Spring + Java + Window 10.
 
 
 
 ## 기능
+ 로그인 및 접근권한(<a href="https://github.com/newcodingtest/SpingCM/blob/Spring/src/main/webapp/WEB-INF/spring/security-context.xml">스프링 시큐리티 적용</a>)
    <blockquote>zzz</blockquote>
	<!-- 로그인 세션유지 -->
		<security:remember-me
			data-source-ref="dataSource" token-validity-seconds="604800" />
		<!-- 로그아웃시 쿠기 및 세션 삭제 -->
		<security:logout logout-url="/customLogout"
			invalidate-session="true" delete-cookies="remember-me,JSESSION_ID" />
<security:authentication-manager>
		<!-- 로그인 인증 절차확인 -->
		<security:authentication-provider
			user-service-ref="customUserDetailsService">
			<!-- 비밀번호 단방향 암호화 등록 -->
			<security:password-encoder
				ref="bcryptPasswordEncoder" />
		</security:authentication-provider>
	</security:authentication-manager>
	

1.단방향 암호화 2.로그인시 세션유지 3.로그아웃시 세션삭제 3.로그인 기억하기(remember-me)

+ 게시판 CRUD
+ 댓글 CRUD(JSON 전송)
+ 파일첨부(파일삭제->스프링 배치적용)
 
 
 
 
 
 
 
 
 
 

## 브렌치 릴리즈.
 ##### 1  test01 ----> 게시판 CRUD 구현
 ##### 2. test02 ----> 게시판 CRUD + 댓글처리 기능 구현
 ##### 3. REPLY10 ----> test02 + 댓글처리 페이징
 ##### 4. Attach_12 ----> REPLY10 + 첨부파일 처리 완료(2021.04.26)
 ##### 5. PERFECT ----> 게시판 구현 완료(2021.05.04)
 
 
 
 
 
