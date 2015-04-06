package com.scncm.service;

import com.scncm.model.Article;
import com.scncm.model.User;

import java.util.List;
import java.util.Set;

public interface ArticleService {
    Article getArticle(Integer articleId);

    Article getSimpleArticle (Integer articleId);

    List<Article> getArticlesByUser (User user);

    Article addArticle (Article article);

    Set<Article> searchArticles (String searchQuery);

    Boolean updateArticle (Article article);

    Boolean deleteArticle (Article article);

    List<Article> getMostRatedArticle(Integer numberOfArticle);

    List<Article> getArticleFiltered(Boolean news, Boolean rating, Integer startTime, Integer endTime, Integer startingSearchPoint);
}
