package com.chm.interiorCM.controller;

import com.chm.interiorCM.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/adm")
public class AdmBoardController {

	private final BoardService boardService;

	// 게시판 리스트
	@GetMapping("/boards")
	public String showManageBoard( Model model ){

		model.addAttribute("boards", boardService.getBoardsList());

		return "adm/board/main";
	}

	// 게시판 저장
	// 게시판 수정
	// 게시판 삭제

}
