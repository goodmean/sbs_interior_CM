package com.chm.interiorCM.controller;

import com.chm.interiorCM.service.AdmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adm")
@RequiredArgsConstructor
public class AdmController {

	private final AdmService admService;

	@GetMapping("/page")
	public String showAdminPage(Model model){

		model.addAttribute("memberStatData", admService.getMemberStatDto());
		model.addAttribute("boardStatData", admService.getBoardStatDto());
		model.addAttribute("articleStatData", admService.getArticleStatDto());

		return "adm/general/main";
	}

}
