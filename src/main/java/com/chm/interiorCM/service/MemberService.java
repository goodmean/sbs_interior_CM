package com.chm.interiorCM.service;

import com.chm.interiorCM.config.Role;
import com.chm.interiorCM.dao.MemberRepository;
import com.chm.interiorCM.domain.Article;
import com.chm.interiorCM.domain.Member;
import com.chm.interiorCM.dto.article.ArticleDTO;
import com.chm.interiorCM.dto.member.MemberModifyForm;
import com.chm.interiorCM.dto.member.MemberSaveForm;
import com.chm.interiorCM.dto.member.MyPageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final ArticleService articleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByLoginId(username).get();
    }

    public void isDuplicateMember(String loginId, String nickname, String email){

        if(memberRepository.existsByLoginId(loginId)){
            throw new IllegalStateException("이미 존재하는 아이디 입니다");
        }else if(memberRepository.existsByNickname(nickname)){
            throw new IllegalStateException("이미 존재하는 아이디 입니다");
        }else if(memberRepository.existsByEmail(email)){
            throw new IllegalStateException("이미 존재하는 이메일 입니다.");
        }
    }

    /**
     * 회원가입
     * @param memberSaveForm
     */

    @Transactional
    public void save(MemberSaveForm memberSaveForm) throws IllegalStateException{

        isDuplicateMember(
                memberSaveForm.getLoginId(),
                memberSaveForm.getNickname(),
                memberSaveForm.getEmail()
        );

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        Member member = Member.createMember(
                memberSaveForm.getLoginId(),
                bCryptPasswordEncoder.encode(memberSaveForm.getLoginPw()),
                memberSaveForm.getName(),
                memberSaveForm.getNickname(),
                memberSaveForm.getEmail(),
                Role.MEMBER
        );

        memberRepository.save(member);
    }

    public Member findById( Long id ){

        Optional<Member> memberOptional = memberRepository.findById(id);

        memberOptional.orElseThrow(
                () -> new IllegalStateException("존재하지 않는 회원입니다.")
        );

        return memberOptional.get();
    }

    public Member findByLoginId(String loginId) throws IllegalStateException{

        Optional<Member> memberOptional = memberRepository.findByLoginId(loginId);

        // 만약 로그인 아이디로 찾는다고 했는데 존재하지 않는 회원일때 대처
        memberOptional.orElseThrow(
                () -> new IllegalStateException("존재하지 않는 아이디입니다.")
        );

        return memberOptional.get();
    }

    // 회원 정보 수정
    @Transactional
    public Long modifyMember(MemberModifyForm memberModifyForm, String loginId){

        Member findMember = findByLoginId(loginId);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        findMember.modifyMember(
                bCryptPasswordEncoder.encode(memberModifyForm.getLoginPw()),
                memberModifyForm.getNickname(),
                memberModifyForm.getEmail()
        );
        
        return findMember.getId();
    }

    public MyPageDTO getMyArticles(String loginId) {

        List<ArticleDTO> articleDTOList = new ArrayList<>();

        Member findMember = findByLoginId(loginId);

        List<Article> articles = findMember.getArticles();

        for( Article article : articles ){
            ArticleDTO findArticle = articleService.getArticle(article.getId());
            articleDTOList.add(findArticle);
        }

        return new MyPageDTO(findMember, articleDTOList);

    }

    public boolean isDupleLoginId(String loginId){
        return memberRepository.existsByLoginId(loginId);
    }

    public boolean idDupleNickname(String nickname){
        return memberRepository.existsByNickname(nickname);
    }

}
