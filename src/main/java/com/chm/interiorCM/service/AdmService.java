package com.chm.interiorCM.service;

import com.chm.interiorCM.config.Role;
import com.chm.interiorCM.dao.MemberRepository;
import com.chm.interiorCM.dto.adm.MemberStatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdmService {

	private final MemberRepository memberRepository;

	public MemberStatDto getMemberStatDto(){

		return new MemberStatDto(
				memberRepository.count(),
				memberRepository.countTodayMember(),
				memberRepository.countByAuthorityLike(Role.ADMIN),
				memberRepository.countByAuthorityLike(Role.MEMBER)
		);
	}

}
