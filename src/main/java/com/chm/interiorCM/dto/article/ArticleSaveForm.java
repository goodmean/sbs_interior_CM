package com.chm.interiorCM.dto.article;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ArticleSaveForm {

    @NotBlank(message = "제목을 입력해 주세요")
    private String title;
    @NotBlank(message = "내용을 입력해 주세요")
    private String body;

    private Long board_id;

}
