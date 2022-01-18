package com.chm.interiorCM.dao;

import com.chm.interiorCM.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
