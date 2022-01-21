package com.chm.interiorCM.config;

import com.chm.interiorCM.dao.MemberRepository;
import com.chm.interiorCM.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.initAdmin();
    }

    @Component
    @Service
    @RequiredArgsConstructor
    static class InitService{

        private final MemberRepository memberRepository;

        public void initAdmin(){

            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

            Member admin = Member.createMember(
                    "admin",
                    bCryptPasswordEncoder.encode("admin"),
                    "관리자",
                    "관리자",
                    "admin@admin.com",
                    Role.ADMIN
            );

            memberRepository.save(admin);

        }

    }

}
