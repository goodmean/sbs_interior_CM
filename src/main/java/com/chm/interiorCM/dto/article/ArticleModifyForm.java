package com.chm.interiorCM.dto.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor // 어떤 인자든 받는 생성자를 만듦
@NoArgsConstructor
public class ArticleModifyForm {

	@NotBlank(message = "제목을 입력해 주세요")
    private String title;

	@NotBlank(message = "내용을 입력해 주세요")
    private String body;

    private Long board_id;

	public ArticleModifyForm(ArticleDTO articleDTO) {

		this.title = articleDTO.getTitle();
		this.body = articleDTO.getBody();

		this.board_id = articleDTO.getBoardId();

	}
}
