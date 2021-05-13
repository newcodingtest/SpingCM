# 스프링 레거시 게시판 만들기
+ JDK 1.8
 
+ ORACLE 11G
 
+ Eclipse
 
+ Ojdbc 8 + Mybatis
 
+ Spring + Java + Window 10.
 
 
 
 ## 기능
+ 로그인 및 접근권한(<a href="https://github.com/newcodingtest/SpingCM/blob/Spring/src/main/webapp/WEB-INF/spring/security-context.xml">스프링 시큐리티 적용</a>)
'''	
<div class="colorscripter-code" style="color:#f0f0f0;font-family:Consolas, 'Liberation Mono', Menlo, Courier, monospace !important; position:relative !important;overflow:auto"><table class="colorscripter-code-table" style="margin:0;padding:0;border:none;background-color:#272727;border-radius:4px;" cellspacing="0" cellpadding="0"><tr><td style="padding:6px;border-right:2px solid #4f4f4f"><div style="margin:0;padding:0;word-break:normal;text-align:right;color:#aaa;font-family:Consolas, 'Liberation Mono', Menlo, Courier, monospace !important;line-height:130%"><div style="line-height:130%">1</div><div style="line-height:130%">2</div><div style="line-height:130%">3</div><div style="line-height:130%">4</div><div style="line-height:130%">5</div><div style="line-height:130%">6</div><div style="line-height:130%">7</div><div style="line-height:130%">8</div><div style="line-height:130%">9</div><div style="line-height:130%">10</div><div style="line-height:130%">11</div></div></td><td style="padding:6px 0;text-align:left"><div style="margin:0;padding:0;color:#f0f0f0;font-family:Consolas, 'Liberation Mono', Menlo, Courier, monospace !important;line-height:130%"><div style="padding:0 6px; white-space:pre; line-height:130%"><span style="color:#f0f0f0">&lt;</span><span style="color:#ff3399">security:authentication-manager</span><span style="color:#f0f0f0">&gt;</span></div><div style="padding:0 6px; white-space:pre; line-height:130%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#999999">&lt;!--&nbsp;로그인&nbsp;인증&nbsp;절차확인&nbsp;--&gt;</span></div><div style="padding:0 6px; white-space:pre; line-height:130%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#f0f0f0">&lt;</span><span style="color:#ff3399">security:authentication-provider</span></div><div style="padding:0 6px; white-space:pre; line-height:130%"><span style="color:#a8ff58"></span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#a8ff58"></span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#a8ff58"></span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#a8ff58">user-service-ref</span>=<span style="color:#ffd500">"customUserDetailsService"</span><span style="color:#a8ff58"></span><span style="color:#f0f0f0">&gt;</span></div><div style="padding:0 6px; white-space:pre; line-height:130%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#999999">&lt;!--&nbsp;비밀번호&nbsp;단방향&nbsp;암호화&nbsp;등록&nbsp;--&gt;</span></div><div style="padding:0 6px; white-space:pre; line-height:130%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#f0f0f0">&lt;</span><span style="color:#ff3399">security:password-encoder</span></div><div style="padding:0 6px; white-space:pre; line-height:130%"><span style="color:#a8ff58"></span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#a8ff58"></span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#a8ff58"></span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#a8ff58"></span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#a8ff58">ref</span>=<span style="color:#ffd500">"bcryptPasswordEncoder"</span><span style="color:#a8ff58"></span>&nbsp;<span style="color:#a8ff58">/</span><span style="color:#f0f0f0">&gt;</span></div><div style="padding:0 6px; white-space:pre; line-height:130%">&nbsp;</div><div style="padding:0 6px; white-space:pre; line-height:130%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#f0f0f0">&lt;</span><span style="color:#f0f0f0">/</span><span style="color:#ff3399">security:authentication-provider</span><span style="color:#f0f0f0">&gt;</span></div><div style="padding:0 6px; white-space:pre; line-height:130%">&nbsp;</div><div style="padding:0 6px; white-space:pre; line-height:130%"><span style="color:#f0f0f0">&lt;</span><span style="color:#f0f0f0">/</span><span style="color:#ff3399">security:authentication-manager</span><span style="color:#f0f0f0">&gt;</span></div></div></td><td style="vertical-align:bottom;padding:0 2px 4px 0"><a href="http://colorscripter.com/info#e" target="_blank" style="text-decoration:none;color:white"><span style="font-size:9px;word-break:normal;background-color:#4f4f4f;color:white;border-radius:10px;padding:1px">cs</span></a></td></tr></table></div>
	
'''
	
	

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
 
 
 
 
 
