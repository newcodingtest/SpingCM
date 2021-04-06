<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    

<%@ include file="../includes/header.jsp" %>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header"> board read</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                           board read
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        
                        	<div class="form-group">
                                            <label>BNO</label>
                                            <input class="form-control" name="bno" readonly="readonly" value='<c:out value="${board.bno}"/>'>
                            </div>
                          
                          	<div class="form-group">
                                            <label>Title</label>
                                            <input class="form-control" name="title" readonly="readonly" value='<c:out value="${board.title}"/>'>
                            </div>
                          	
                          	<div class="form-group">
                                            <label>Content</label>
                                            <textarea class="form-control" rows="5" cols="50" name="content"><c:out value="${board.content}"/></textarea>
                            </div>
                            
                            <div class="form-group">
                                            <label>Writer</label>
                                            <input class="form-control" name="writer" value='<c:out value="${board.writer}"/>'>
                            </div>
                          	
                          <form id="actionForm" action="/board/list" method='get'>
                           	<input type='hidden' name='pageNum' value='${cri.pageNum }'>
                           	<input type='hidden' name='amount' value='${cri.amount }'>
                           	<input type='hidden' name='bno' value='${board.bno}'>
                           </form>
                          	
                          	
                          	
                          	
                          	  <button type="button" class="btn btn-default listBtn"><a href='/board/list'>목록</a></button>
                              <button type="button" class="btn btn-default modBtn"><a href='/board/modify?bno=<c:out value="${board.bno}"/>'>수정</a></button>
                          	  <script>
                          	  // 상세보기란에서 다시 전체목록리스트로 버튼을 누르면 해당 url 에서 더이상 bno는 필요없으니까
                          	  // remove로 url에서 떼어내준다.
                          	  var actionForm = $("#actionForm");
                          	  
                          	  $(".listBtn").click(function(e){
                          		  e.preventDefault();
                          		  actionForm.find("input[name='bno']").remove();
                          		  actionForm.submit();
                          	  });
                          	  
                          	  $(".modBtn").click(function(e){
                          		  e.preventDefault();
                          		  actionForm.attr("action","/board/modify");
                          		  actionForm.submit();
                          	  });
                          	  
                          	  
                          	  </script>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
           
           
 <%@ include file="../includes/footer.jsp" %>