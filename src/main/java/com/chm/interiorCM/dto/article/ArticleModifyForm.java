package com.chm.interiorCM.dto.article;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // 어떤 인자든 받는 생성자를 만듦
public class ArticleModifyForm {

    private String title;

    private String body;

    private Long board_id;

}
