package com.chm.interiorCM.service;

import com.chm.interiorCM.dao.ArticleRepository;
import com.chm.interiorCM.domain.Article;
import com.chm.interiorCM.dto.article.ArticleListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdmArticleService {

	private final ArticleRepository articleRepository;

	public List<ArticleListDTO> getArticleList(){

		List<ArticleListDTO> articleListDTOList = new ArrayList<>();

		List<Article> articleList = articleRepository.findAll();

		for( Article article : articleList){

			ArticleListDTO articleListDTO = new ArticleListDTO(article);
			articleListDTOList.add(articleListDTO);
		}

		return articleListDTOList;
	}

}
