package com.chm.interiorCM.dto.adm;

import com.chm.interiorCM.domain.Member;
import com.chm.interiorCM.dto.article.ArticleListDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class AdmMemberDetailDTO {

	private Long memberId;

	private String loginId;
	private String nickname;
	private String name;

	private LocalDateTime regDate;

	private List<ArticleListDTO> articles;

	public AdmMemberDetailDTO(Member member, List<ArticleListDTO> articles){

		this.memberId = member.getId();

		this.loginId = member.getLoginId();
		this.nickname = member.getNickname();
		this.name = member.getName();

		this.regDate = member.getRegDate();

		this.articles = articles;

	}

}
