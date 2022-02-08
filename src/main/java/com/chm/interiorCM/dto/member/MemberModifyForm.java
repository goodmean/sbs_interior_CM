package com.chm.interiorCM.dto.member;

import com.chm.interiorCM.domain.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class MemberModifyForm {

    private Long id;

    private String loginId;

    @NotBlank(message = "비밀번호를 입력해 주세요")
    private String loginPw;

    private String name;

    @NotBlank(message = "닉네임을 입력해 주세요")
    private String nickname;

    @NotBlank(message = "이메일을 입력해 주세요")
    private String email;

    public MemberModifyForm(Member findMember){

        this.id = findMember.getId();

        this.loginId = findMember.getLoginId();
        this.loginPw = findMember.getLoginPw();

        this.name = findMember.getName();
        this.nickname = findMember.getNickname();
        this.email = findMember.getEmail();

    }

}
