<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    

<%@ include file="../includes/header.jsp" %>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header"> board modify/delete</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                           board modify/delete
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        <form>
                        <input type='hidden' name='pageNum' value='${cri.pageNum}'>
                        <input type='hidden' name='amount' value='${cri.pageNum}'>
                        <input type='hidden' name='amount' value='${board.bno}'>
                        	<div class="form-group">
                                            <label>BNO</label>
                                            <input class="form-control" name="bno" readonly="readonly" value='<c:out value="${board.bno}"/>'>
                            </div>
                          
                          	<div class="form-group">
                                            <label>Title</label>
                                            <input class="form-control" name="title"  value='<c:out value="${board.title}"/>'>
                            </div>
                          	
                          	<div class="form-group">
                                            <label>Content</label>
                                            <textarea class="form-control" rows="5" cols="50" name="content"><c:out value="${board.content}"/></textarea>
                            </div>
                            
                            <div class="form-group">
                                            <label>Writer</label>
                                            <input class="form-control" name="writer" value='<c:out value="${board.writer}"/>'>
                            </div>
                          	<!-- data-oper='modify' html5 이상에서 지원하는 커스텀 데이터 속성 -->
                          	  <button type="button" class="btn btn-default" data-oper='modify'>수정확인</button>
                              <button type="button" class="btn btn-danger" data-oper='remove'>삭제</button>
                              <button type="button" class="btn btn-info listBtn" data-oper='list'>글 목록</button>
                         </form>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
<script type="text/javascript">

$(document).ready(function(){
	
	var formObj=$("form");
	
	$('.btn').click(function(e){
	
	//버튼만의 동작만 원하기 때문에 버튼은 애초에 form태크 안에 있어서 버튼을 누를시 폼태그가 동작하지만 기본동작들을 막아주는 명령어로 버튼만의 행위만 동작하게 해준다
	e.preventDefault();
	
	//this>>버튼 data 속성을 쓰면 해당 버튼의 값이 먼지 알수있음
	var operation=$(this).data("oper");
	
	console.log(operation);
	
	if(operation=='list'){
		self.location="/board/list";
		
	}else if(operation=='remove'){
		formObj.attr("action","/board/remove")
		.attr("method","post");
		
		formObj.submit();
	}else if(operation=='modify'){
		formObj.attr("action","/board/modify")
		.attr("method","post");
		
		formObj.submit();
	}
		
	})
	

	
})


</script>           
           
 <%@ include file="../includes/footer.jsp" %>