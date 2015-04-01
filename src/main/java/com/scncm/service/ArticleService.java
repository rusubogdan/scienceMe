package com.scncm.service;

import com.scncm.model.Article;
import com.scncm.model.User;

import java.util.List;

public interface ArticleService {
    Article getArticle(Integer articleId);

    List<Article> getArticlesByUser (User user);

    Article addArticle (Article article);

    Boolean updateArticle (Article article);

    Boolean deleteArticle (Article article);

    List<Article> getArticleFiltered(Boolean news, Boolean rating, Integer startTime, Integer endTime, Integer startingSearchPoint);
}
