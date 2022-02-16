package com.chm.interiorCM.dto.member;

import com.chm.interiorCM.domain.Member;
import com.chm.interiorCM.dto.article.ArticleDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MyPageDTO {

	private String nickname;

	private List<ArticleDTO> articles;

	public MyPageDTO(Member member, List<ArticleDTO> articles) {

		this.nickname = member.getNickname();

		this.articles = articles;

	}

}
