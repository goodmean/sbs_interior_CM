package com.chm.interiorCM.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.parameters.P;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //무분별한 객체 생성을 막기 위해서
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // db의 auto increment 같은의미
    private Long id;

    private String title;
    private String body;

    private LocalDateTime regDate = LocalDateTime.now();
    private LocalDateTime updateDate = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    // 생성 메소드
    public static Article createArticle(String title, String body){
        Article article = new Article();

        article.title = title;
        article.body = body;

        return article;
    }

    public  void modifyArticle(String title, String body){

        this.title = title;
        this.body = body;

        this.updateDate = LocalDateTime.now();

    }

    // 연관관계 메소드
    public void setMember(Member member){
        this.member = member;
        member.getArticles().add(this);
    }

    public void setBoard(Board board) {
        this.board = board;
        member.getArticles().add(this);
    }
}
