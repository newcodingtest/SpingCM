package org.zerock.domain;

import lombok.Data;
import lombok.ToString;

@Data
public class PageDTO {
	
	private int startPage;
	private int endPage;
	
	private boolean prev;
	private boolean next;
	
	private int total;
	private Criteria cri;  //페이지 번호랑 한페이지에 표현해줄 글 제한수 포함
	
	public PageDTO(Criteria cri, int total) {
		this.cri = cri;
		this.total = total;
		
		this.endPage =  (int)Math.ceil(cri.getPageNum() / 10.0) * 10;
		
		this.startPage = endPage - 9;
		
		this.prev = this.startPage > 1;
		
										//71.0 / 10 => 7.1 => 8
		int realEnd =(int) (Math.ceil( (total * 1.0) / cri.getAmount()) );
		
		this.endPage=realEnd <= endPage? realEnd : endPage;
		
		this.next = this.endPage < realEnd;
			
	}
}
