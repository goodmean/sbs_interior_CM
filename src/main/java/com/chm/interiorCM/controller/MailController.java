package com.chm.interiorCM.controller;

import com.chm.interiorCM.dto.member.FindPasswordForm;
import com.chm.interiorCM.service.MailService;
import com.chm.interiorCM.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MailController {

	private final MailService mailService;
	private final MemberService memberService;

	@ResponseBody
	@PostMapping("mails/find/pw")
	public boolean getForgotPassword(@RequestBody FindPasswordForm findPasswordForm) {

		if (memberService.isDupleLoginId(findPasswordForm.getLoginId())) {
			return false;
		}

		try{
			mailService.sendMail(findPasswordForm);
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
