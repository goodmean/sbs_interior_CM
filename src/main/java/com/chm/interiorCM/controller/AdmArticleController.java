package com.chm.interiorCM.controller;

import com.chm.interiorCM.service.AdmArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/adm")
public class AdmArticleController {

	private final AdmArticleService admArticleService;

	// 게시글 리스트
	@GetMapping("/articles")
	public String showManageArticle(Model model){

		model.addAttribute("articles", admArticleService.getArticleList());

		return "adm/article/main";
	}

	// 게시글 삭제

}
