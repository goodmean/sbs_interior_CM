package com.chm.interiorCM.config;

import com.chm.interiorCM.dao.ArticleRepository;
import com.chm.interiorCM.dao.BoardRepository;
import com.chm.interiorCM.dao.MemberRepository;
import com.chm.interiorCM.domain.Article;
import com.chm.interiorCM.domain.Board;
import com.chm.interiorCM.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.initAdmin();
        initService.initMember();
    }

    @Component
    @Service
    @RequiredArgsConstructor
    static class InitService{

        private final MemberRepository memberRepository;
        private final BoardRepository boardRepository;
        private final ArticleRepository articleRepository;

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

            for( int i =  1; i <= 3; i++ ){

                Board board = Board.createBoard(
                        "게시판" + i,
                        "게시판" + i,
                        admin
                );

                boardRepository.save(board);

            }

        }

        public void initMember(){

            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

            List<Board> boardList = boardRepository.findAll();

            for( int i = 1; i <= 5; i++ ){

                Member member = Member.createMember(
                        "user" + i,
                        bCryptPasswordEncoder.encode("user" + i),
                        "유저1",
                        "유저1",
                        "user" + i + "@user.com",
                        Role.MEMBER
                );

                memberRepository.save(member);

                for( int j = 1; j <= 3; j++) {

                    for (int k = 1; k <= 3; k++) {

                        Article article = Article.createArticle(
                                "제목" + k,
                                "내용" + k
                        );

                        article.setMember(member);
                        article.setBoard(boardList.get( j - 1 ));

                        articleRepository.save(article);

                    }
                }
            }

        }

    }

}
