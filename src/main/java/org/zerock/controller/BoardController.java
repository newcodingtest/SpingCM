package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/*")
@Log4j
public class BoardController {
	
	private final BoardService service;
	
//	@GetMapping("/list")
//	public void list(Model model) {
//		log.info("list.................");
//		//addflashAttribute:잠깐 저장되어 전송되는것<<내부적으로는 httpsession 에 들어가며 한번 사용되면 지워진다.
//		//ex)1회만 사용가능하기때문에 새로고침하면 날라감
//		model.addAttribute("list",service.getList()); //브라우저에 보낼때 url에 붙어서 전송되는 형식 
//	} 
//	
//	@GetMapping("/register")
//	public void register() {
//		
//	}
	
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		log.info("===============================");
		log.info(cri);
		log.info("페이징 리스트 테스트");
		
		model.addAttribute("list",service.getList(cri)); //브라우저에 보낼때 url에 붙어서 전송되는 형식 
		model.addAttribute("pageMaker",new PageDTO(cri, service.getTotalCount(cri)));
	
	} 
	
	@GetMapping("/register")
	public void register() {
		
	}

	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		
		log.info("`: " +  board);
		
		Long bno=service.register(board);
		
		log.info("bno : " + bno);
		
		rttr.addFlashAttribute("result",bno);
		
		return "redirect:/board/list";
		
	}
	
	
	@GetMapping({"/get","/modify"})
	public void get(@RequestParam("bno")long bno, @ModelAttribute("cri") Criteria cri, Model model) {
		model.addAttribute("board",service.get(bno));
		
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board, Criteria cri, RedirectAttributes rttr) {
		log.info("modify: "+board);
		
		if(service.modify(board)==1) {
			rttr.addFlashAttribute("result","success");
			
		}
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		
		//검색조건 유지를 위해서
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/board/list";
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, Criteria cri, RedirectAttributes rttr) {
		log.info("remove..."+bno);
		if(service.remove(bno)==1) {
			rttr.addFlashAttribute("result","success");
		}
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		
		//검색조건 유지를 위해서 >> 쿠키에 저장하는 방법도 있음 추후 추가 예정
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/board/list";
	}
}
