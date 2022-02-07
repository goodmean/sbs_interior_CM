package com.chm.interiorCM.dto.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberSaveForm {
    // 회원가입시 필요한 정보

    @NotBlank(message = "아이디를 입력해 주세요")
    private String loginId;
    @NotBlank(message = "비밀번호를 입력해 주세요")
    private String loginPw;
    @NotBlank(message = "이름을 입력해 주세요")
    private String name;
    @NotBlank(message = "닉네임을 입력해 주세요")
    private String nickname;
    @NotBlank(message = "이메일을 입력해 주세요")
    private String email;

}
