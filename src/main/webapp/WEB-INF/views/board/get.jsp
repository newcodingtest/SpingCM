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
                           	<input type='hidden' name='type' value='${cri.type}'>
                           	<input type='hidden' name='keyword' value='${cri.keyword}'>
                           </form>
<!-- ==================================댓글처리 구간================================== -->                          	
                          	<div class='row'>
                          	
                          		<div class="col-lg-12">
                          		
                          			<div class="panel panel-default">
                          				<div class="panel-heading">
                          					<i class="fa fa-comments fa-fw"></i> Reply
<!-- ===========================댓글 등록 버튼 ========================= -->                          					
                          						<button id='addReplyBtn' class='btn btn-primary btn-xs pull-right'>New Reply</button>
                          				</div>
                          				
                          				<div class="panel-body">
                          				
                          					<ul class="chat">
                          					
                          						<li class="left clearfix" data-rno='12'>
                          							<div>
                          								<strong class="primary-font">user00</strong>
                          								<small class="pull-right text-muted">2018-01-01 13:13</small>
                          							</div>
                          							<p>good job!</p>
                          							</div>
                          						</li>
                          					</ul>
                          				</div>
<!-- panel-footer 부분에 js 이용하여 페이징 버튼 생성  -->                          				
                          			  <div class="panel-footer">
                          			  
                          			  </div>	 
                          			</div>
                          		</div>
                          	</div>
                          	
                          	
                          	  <button type="button" class="btn btn-default listBtn"><a href='/board/list'>목록</a></button>
                              <button type="button" class="btn btn-default modBtn"><a href='/board/modify?bno=<c:out value="${board.bno}"/>'>수정</a></button>
                          	  
<!-- =========================== 댓글 CRUD를 할수있는 모달창 =========================== -->                          	  
                          	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
                          		aria-labelledby="myModalLabel" aria-hidden="true">
                          		<div class="modal-dialog">
                          		  <div class="modal-content">
                          		  	<div class="modal-header">
                          		  	  <button type="button" class="close" data-dismiss="modal"
                          		  	  	aria-hidden="true">&times;</button>
                          		  	  <h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>		
                          		  	</div>
                          		  	<div class="modal-body">
                          		  	  <div class="form-group">
                          		  	  	<label>Reply</label>
                          		  	  	<input class="form-control" name='reply' value='New Reply!!!'>
                          		  	  </div>
                          		  	  <div class="form-group">
                          		  	  	<label>Replayer</label>
                          		  	  	<input class="form-control" name='replayer' value='replayer'>
                          		  	  </div>
                          		  	  <div class="form-group">
                          		  	  	<label>Reply Date</label>
                          		  	  	<input class="form-control" name='replyDate' value=''>
                          		  	  </div>
                          		  	</div>
                          		  <div class="modal-footer">
                          		  	<button id='modalModBtn' type="button" class="btn btn-warning">Modify</button>
                          		  	<button id='modalRemoveBtn' type="button" class="btn btn-danger">Remove</button>
                          		  	<button id='modalCloseBtn' type="button" class="btn btn-default">Close</button>
                          		  	<button id='modalRegisterBtn' type="button" class="btn btn-default">Register</button>
                          		  </div>	
                          		  </div>	
                          		</div>  
                          	  
                          	  
                          	  
                          	  <script type="text/javascript" src="/resources/js/reply.js"></script>
                          	  <script>
                          	  
 //REST 댓글 처리 테스트 시작=================================================
	 						// $(document).ready 는 body 태그의 모든 태그들이 출력된 다음에 호출이 되는 코드
                          	  $(document).ready(function(){
                          		  
                          		  var bnoValue = '<c:out value="${board.bno}"/>';
                          		  var replyUL = $(".chat");
                          		  
                          		  showList(1);
                          		  
                          		  function showList(page){
                          			  
                          			  console.log("show list "+page);
                          			  replyService.getList({bno:bnoValue, page: page || 1}, function(replyCnt,list){
                          				  
                          				  console.log("replyCnt: " +replyCnt);
                          				  console.log("list: " + list);
                          				  console.log(list);
   //reply.js에서 댓글페이징 버튼 처리 부분 가져옴                      				  
										  if(page == -1){ //만약 페이지 번호가 -1로 전달되면 
											  pageNum = Math.ceil(replyCnt/10.0); //마지막 페이지를 찾아서
											  showList(pageNum); //다시 호출
											  return;
										  }				                          				  
                          				  
                          				  var str="";
                          				  if(list == null || list.length == 0){
                          					  return;
                          				  }
                          				  for(var i=0, len=list.length || 0; i < len; i++){
                          					  str+="<li class='left clearfix' data-rno='"+list[i].rno+"'>";
                          					  str+=" <div><div class='header'><strong class='primary-font'>["
                          					  		+list[i].rno+"] "+list[i].replayer+"</strong>";
                          					  str+=" <small class='pull-right text-muted'>"+replyService.displayTime(list[i].replyDate)+"</small></div>";
                          					  str+="  <p>"+list[i].reply+"</p></div></li>";
                          				  }
                          				  
                          				  replyUL.html(str);
                          				  
                          				  showReplyPage(replyCnt);
                          			  }); // end function
                          		  }// end showList
                          		  
                          //모달창 활성화에 필요한 변수들  
                          	var modal = $(".modal");
                          	var modalInputReply = modal.find("input[name='reply']"); 	  
                          	var modalInputReplayer = modal.find("input[name='replayer']"); 	  
                          	var modalInputReplyDate = modal.find("input[name='replyDate']"); 	  
                          	
                          	var modalModBtn = $("#modalModBtn");
                          	var modalRemoveBtn = $("#modalRemoveBtn");
                          	var modalRegisterBtn = $("#modalRegisterBtn");
                          	
                          //댓글 추가 버튼을 클릭시, 댓글 등록하는 모달 창이 등장함	
                          	$("#addReplyBtn").on("click", function(e){
                          		
                          		modal.find("input").val("");
                          		modalInputReplyDate.closest("div").hide();
                          		modal.find("button[id !='modalCloseBtn']").hide();
                          		
                          		modalRegisterBtn.show();
                          		
                          		$(".modal").modal("show");
                          	});
                          		  console.log(replyService);
                          	  
//===================================댓글 삭제 ===================================
 							modalRemoveBtn.on("click", function(e){
 								
 								var rno = modal.data("rno");
 								
 								replyService.remove(rno, function(result){
 									
 									alert(result);
 									modal.modal("hide");
 									showList(pageNum);
 								});//remove
 							});//modalRemoveBtn
		
//===================================댓글 수정 =================================== 
 							modalModBtn.on("click", function(e){
 								
 								var reply = {rno:modal.data("rno"), reply: modalInputReply.val()};
 								
 								replyService.update(reply, function(result){
 									
 									alert(result);
 									modal.modal("hide");
 									showList(pageNum);
 								});
 							});//modalModBtn
 
 							
//===================================댓글 추가 저리 ===================================
 							modalRegisterBtn.on("click", function(e){
 								
 								var reply = {
 										reply : modalInputReply.val(),
 										replayer : modalInputReplayer.val(),
 										bno : bnoValue
 								};
 								replyService.add(reply, function(result){
 									
 									alert(result);
 									
 									modal.find("input").val("");
 									modal.modal("hide");
 									
 									//사용자가 새 댓글 추가시 -1 호출하여 전체 댓글의 숫자를 파악
 									
 									showList(-1);
 								});
 							}); //modalRegisterBtn
 							
//=========================댓글 클릭시 상세보기===================================
 						$(".chat").on("click", "li", function(e){
 							
 							var rno = $(this).data("rno");
 							
 							replyService.get(rno, function(reply){
 								
 								modalInputReply.val(reply.reply);
 								modal
 							});
 						}); // chat
                          	  
                          	  console.log("===============");
                          	  console.log("JS TEST");
                          	  
                          	/*   var bnoValue = '<c:out value="${board.bno}"/>';
                          	  
                          	  //replyService add 테스트
                          	  replyService.add(
                          		{reply : "JS Test", replayer : "tester", bno : bnoValue},
                          		function(result){
                          			alert("result: "+result);
                          		}
                          	  ); //replyService.add
                          	  
                          	  */
                          	  
                          	  //해당 게시물의 모든 댓글 가져오기
                          	  replyService.getList({bno:bnoValue, page : 1}, function(list){
                          		  
                          		  for(var i =0, len = list.length||0; i < len; i++){
                          			  console.log(list[i]);
                          		  }
                          	  }); //replyService.getList
                          	  
                          	  /* 
                          	  //2번 댓글 삭제 테스트
                          	  replyService.remove(2, function(count){
                          		  
                          		  console.log(count);
                          		  
                          		  if(count== "success"){
                          			  alert("removed")
                          		  }
                          	  }, function(err){
                          		  alert('error...');
                          	  }); //replyService.remove
                          	   */
                          	
                          	 /*  
                          	  //댓글 15번 수정 테스트
                          	  replyService.update({
                          		  rno : 3,
                          		  bno : bnoValue,
                          		  reply : "Modified Reply..."
                          	  }, function(result){
                          		  alert("수정완료...");
                          	  }); //replyService.update
                          	  
                          	  //댓글조회처리
                          	  replyService.get(10, function(data){
                          		  console.log(data);
                          	  });
                          	  */
                         
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
                          	  
 // 위의 panel-footer 부분에 댓글 페이징 버튼 생성                         	  
                          	  var pageNum = 1; //기본값은 1페이지
                          	  var replyPageFooter = $(".panel-footer");
                          	  
                          	  function showReplyPage(replyCnt){
                          		  var endNum = Math.ceil(pageNum / 10.0) * 10;
                          		  var startNum = endNum - 9;
                          		  
                          		  var prev = startNum !=1; //prev 이전 버튼은 처음페이지가 1이 아니면 true로 활성화
                          		  var next = false;
                          		  
                          		  //댓글 총량이 정해두었던 기본 끝페이지량 보다 적으면
                          		  //댓글 총량 *10.0 에서 반올림 값을 끝페이지번호로 지정
                          		  if(endNum * 10 >= replyCnt){
                          			  endNum = Math.ceil(replyCnt/10.0);
                          		  }
                          		  
                          		  //위와는 반대로 댓글량이 초과되면 next 를 활성화한다.
                          		  if(endNum * 10 < replyCnt){
                          			  next = true;
                          		  }
                          		  
                          		  var str = "<ul class-'pagination pull-right'>";
                          		  
                          		  if(prev){
                          			  str+= "<li class='page-item'><a class='page-link' href='"+(startNum-1)+"'>이전</a></li>";
                          		  }
                          		  
                          		  
                          		  for(var i = startNum; i <= endNum; i++){
                          			  
                          			  var active = pageNum == i? "active":"";
                          			  
                          			  str+= "<li class='page-item "+active+" '><a class='page-link' href='"+i+"'>"+i+"</a></li>";
                          		  }
                          		  
                          		  if(next){
                          			  str+= "<li class='page-item'><a class='page-link' href='"+(endNum+1)+"'>다음</a></li>";
                          		  }
                          		  
                          		  str+= "</ul></div>";
                          		  
                          		  console.log(str);
                          		  
                          		  replyPageFooter.html(str);
                          	  
                          	  
                          	  }//showReplyPage
                          	  
                          	replyPageFooter.on("click","li a", function(e){
                          		  e.preventDefault();
                          		  console.log("page click");
                          		  
                          		  var targetPageNum = $(this).attr("href");
                          		  
                          		  console.log("targetPageNum: "+ targetPageNum);
                          		  
                          		  pageNum = targetPageNum;
                          		  
                          		  showList(pageNum);
                          	  }); //replypageFooter
                          	  
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