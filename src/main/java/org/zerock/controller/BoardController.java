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
//		//addflashAttribute:��� ����Ǿ� ���۵Ǵ°�<<���������δ� httpsession �� ���� �ѹ� ���Ǹ� ��������.
//		//ex)1ȸ�� ��밡���ϱ⶧���� ���ΰ�ħ�ϸ� ����
//		model.addAttribute("list",service.getList()); //�������� ������ url�� �پ ���۵Ǵ� ���� 
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
		log.info("����¡ ����Ʈ �׽�Ʈ");
		
		model.addAttribute("list",service.getList(cri)); //�������� ������ url�� �پ ���۵Ǵ� ���� 
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
		
		//�˻����� ������ ���ؼ�
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
		
		//�˻����� ������ ���ؼ� >> ��Ű�� �����ϴ� ����� ���� ���� �߰� ����
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/board/list";
	}
}
