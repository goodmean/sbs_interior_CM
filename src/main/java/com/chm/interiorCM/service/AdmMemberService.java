package com.chm.interiorCM.service;

import com.chm.interiorCM.dao.MemberRepository;
import com.chm.interiorCM.domain.Member;
import com.chm.interiorCM.dto.member.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdmMemberService {

	private final MemberRepository memberRepository;

	public List<MemberDTO> getMemberList() {

		List<MemberDTO> memberDTOList = new ArrayList<>();

		List<Member> memberList = memberRepository.findAll();

		for (Member member : memberList) {
			MemberDTO memberDTO = new MemberDTO(member);
			memberDTOList.add(memberDTO);
		}

		return memberDTOList;
	}

}
