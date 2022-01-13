package com.chm.interiorCM.dto.Member;

import lombok.Data;

@Data
public class MemberSaveForm {
    // 회원가입시 필요한 정보

    private String loginId;
    private String loginPw;
    private String name;
    private String nickname;
    private String email;
}
