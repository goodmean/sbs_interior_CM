package com.chm.interiorCM.service;

import com.chm.interiorCM.dao.ArticleRepository;
import com.chm.interiorCM.domain.Article;
import com.chm.interiorCM.domain.Member;
import com.chm.interiorCM.dto.article.ArticleSaveForm;
import com.chm.interiorCM.dto.member.MemberModifyForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional
    public void save(ArticleSaveForm articleSaveForm, Member member){

        Article article = Article.createArticle(
                articleSaveForm.getTitle(),
                articleSaveForm.getBody()
        );

        article.setMember(member);

        articleRepository.save(article);

    }

}
