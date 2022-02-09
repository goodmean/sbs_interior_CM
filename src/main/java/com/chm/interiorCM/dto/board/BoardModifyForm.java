package com.chm.interiorCM.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class BoardModifyForm {

    private Long id;

    @NotBlank(message = "게시판 이름을 입력해 주세요")
    private String name;

    private String detail;

}
