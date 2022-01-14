package com.chm.interiorCM.dto.Member;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberSaveForm {
    // 회원가입시 필요한 정보

    @NotBlank
    private String loginId;
    @NotBlank
    private String loginPw;
    @NotBlank
    private String name;
    @NotBlank
    private String nickname;
    @NotBlank
    private String email;

}
