ㅡ<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    

<%@ include file="../includes/header.jsp" %>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Tables</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            DataTables Advanced Tables
                            <button id='regBtn' type="button" class="btn btn-xs pull-right">글 쓰기</button>	
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" >
                                <thead>
                                    <tr>
                                        <th>BNO</th>
                                        <th>TITLE</th>
                                        <th>WRITER</th>
                                        <th>REGDATE</th>
                                        <th>UPDATEDATE</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${list }" var="board">
                                    <tr class="odd gradeX">
                                        <td>${board.bno}</td>
                                        <td><a class='move' href='<c:out value="${board.bno}"/>'><c:out value="${board.title}"/></a></td>
                                        <td>${board.writer}</td>
                                        <td class="center"><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regdate}"/></td>
                                        <td class="center"><fmt:formatDate pattern="yyyy-MM-dd" value="${board.updateDate}"/></td>
                                    </tr>
                                 </c:forEach>
                                </tbody>
                            </table>
                            
                            <h3>${pageMaker}</h3>
                            <div class='pull-right'>
                            	<ul class="pagination">
<!-- 이전 버튼 https://getbootstrap.com/docs/4.0/components/pagination/ 참고-->
                            	<c:if test="${pageMaker.prev}">
									<li class="page-item">
										<a class="page-link" href="${pageMaker.startPage -1 }" tabindex="-1">Previous</a>
									</li>
								</c:if>	
									<c:forEach begin="${pageMaker.startPage}" 
                            	  	           end="${pageMaker.endPage}" var="num">
                         			<li class="page-item ${pageMaker.cri.pageNum == num? "active":"" }">
                         			<a class="page-link" href="${num}">${num}</a></li>
                        			</c:forEach>	
                        			
<!-- 다음 버튼 https://getbootstrap.com/docs/4.0/components/pagination/ 참고-->
                        			<c:if test="${pageMaker.next}">
									<li class="page-item">
										<a class="page-link" href="${pageMaker.endPage+1}" tabindex="-1">Next</a>
									</li>	
									</c:if>	
			                     </ul>
                            </div>
                           
                           <form id="actionForm" action="/board/list" method='get'>
                           	<input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum }'>
                           	<input type='hidden' name='amount' value='${pageMaker.cri.amount }'>
                           </form>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            
<!--  -----------------------modal 알람 창 디자인 ------------------------------ -->            
<div id="myModal" class="modal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p>Modal body text goes here.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">Save changes</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>            
      
<!--   글 번호 등록 알람창 >> [?번째 글이 등록되었습니다] ------------------------->            
 <script type="text/javascript">
 $(document).ready(function(){
	// 1. 글등록 알람창
	 var result='<c:out value="${result}"/>';
	 
	 
	 checkModal(result);
	 
	 history.replaceState({},null,null);
	 
	 function checkModal(result){
		 
	
		 
		 
		 if(result==''|| history.state){ //히스토리는 이벤트를 담아두는 문서객체
			 return;
		 }
		 
		 if(result=='success'){ 
			 $(".modal-body").html(
					 "정상적으로 처리되었습니다.");
			 
		 }else if(parseInt(result)>0){
			 $(".modal-body").html(
					 "게시글 "+parseInt(result)+" 번이 등록되었습니다.");
		 }
		 $("#myModal").modal("show");
	 }
	 
	 
	 // 2. list 페이지에서 글쓰기 누르면 글쓰기 폼으로 이동, a 태그로 쓰는 방법도 있음
	 $("#regBtn").on("click",function(){
		 self.location="/board/register";
	 });
	 
	 // 3. 페이징
	 var actionForm=$("#actionForm");
	 
	 $(".page-link").on("click", function(e){
		 
		 e.preventDefault(); //a태그의 기본 동작을 막고
		 
		var targetPage = $(this).attr("href");
		
		 console.log(targetPage);
		 
		 actionForm.find("input[name='pageNum']").val(targetPage);
		 actionForm.submit();
	 });
	 
	 // 4. 글 누르면 상세보기 이동란
	 $(".move").on("click", function(e){
		 e.preventDefault();
		 
		 var targetBno = $(this).attr("href");
		 
		 console.log(targetBno);
		 
		 actionForm.append("<input type='hidden' name='bno' value='"+targetBno+"'>'");
		 actionForm.attr("action","/board/get").submit();
		 
	 })
 });
 
 </script>          
           
 <%@ include file="../includes/footer.jsp" %>