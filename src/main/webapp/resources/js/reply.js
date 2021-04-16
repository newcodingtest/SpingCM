/**
 ajax 호출을 담당한다.
 */
console.log("Reply Module");

var replyService = (function(){
	
	// 1. 댓글 등록 처리
	function add(reply, callback, error){
		console.log("add reply.......");
	
	
	$.ajax({
		type : 'post',
		url : '/replies/new',
		data : JSON.stringify(reply),
		contentType : "application/json; charset=utf-8",
		success : function(result, status, xhr){
			if(callback){
				callback(result);
			}
		},
		error : function(xhr, status, er){
			if(error){
				error(er);
			}
		}
	});
	}
	

	
	 //2.댓글 목록 페이징 버튼 처리
	function getList(param, callback, error){
		
		var bno = param.bno;
		var page = param.page || 1;
		
		$.getJSON("/replies/pages/" + bno + "/" + page + ".json",
			function(data){
				if(callback){
					// 댓글 목록만 가져오는 경우 callback(data);
					callback(data.replyCnt, data.list); //댓글 숫자와 목록을 가져오는 경우
				}
			}).fail(function(xhr, status, err){ 
			if(error){
				error();
			}	
			});
			}
	
	// 3. 댓글 삭제와 갱신
	function remove(rno, callback, error){
		$.ajax({
			type : 'delete',
			url : '/replies/'+ rno,
			success : function(deleteResult, status, xhr){
				if(callback){
					callback(deleteResult);
				}
			},
			
			error : function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		});
		
	}
	
	//댓글 수정
	function update(reply, callback, error){
		
		console.log("rno: " +reply.rno);
		
		$.ajax({
			type : 'put',
			url : '/replies/' +reply.rno,
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error : function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		});
		
		
	}
	
	//댓글 조회 처리
	function get(rno, callback, error){
                          	  	$.get("/replies/" + rno + ".json", function(result){
                          	  		if(callback){
                          	  			callback(result);
                          	  		}
                          	  	}).fail(function(result){
                          	  		if(error){
                          	  			error();
                          	  		}
                          	  	});
}

//xml이나 json 형태로 데이터를 받을 때는 순수하게 숫자로 표현되는 시간 값이 나와, 화면에서는 이를 변환해서 사용하는 것이 좋음
	function displayTime(timeValue){
		var today = new Date();
		var gap = today.getTime() - timeValue;
		
		var dateObj = new Date(timeValue);
		var str = "";
		
		if(gap < (1000 * 60 * 60 * 24)){
			
			var hh = dateObj.getHours();
			var mi = dateObj.getMinutes();
			var ss = dateObj.getSeconds();
			
			return [ (hh > 9 ? '' : '0') + hh, ':', (mi > 9 ? '' : '0') + mi,':',
					  (ss > 9 ? '' : '0') + ss ].join('');
		} else {
			var yy = dateObj.getFullYear();
			var mm = dateObj.getMonth()+1;
			var dd = dateObj.getDate();
			
			return [ yy, '/', (mm > 9 ? '' : '0') + mm, '/',
					(dd > 9 ? '' : '0') + dd ].join('');
		}
	}; 
  
  
 
	
	
	
	return {
		add : add,
		get : get,
		getList : getList,
		remove : remove,
		update : update,
		displayTime : displayTime
	};
	
})();

