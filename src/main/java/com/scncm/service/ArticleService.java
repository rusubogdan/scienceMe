package com.scncm.service;

import com.scncm.model.Article;
import com.scncm.model.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ArticleService {
    Article getArticle(Integer articleId);

    Article getSimpleArticle (Integer articleId);

    List<Article> getArticlesByUser (User user);

    Integer addArticle (Article article);

    Set<Article> searchArticles (String searchQuery);

    Boolean updateArticle (Article article);

    Boolean deleteArticle (Article article);

    List<Map> getMostRatedArticle(Integer numberOfArticle, Integer userId, List<Integer> recommendedList);

    Map getArticleAndRating(Integer articleId);

    List<Map> getArticleFiltered(Boolean news, Boolean rating, Integer startTime, Integer endTime, Integer startingSearchPoint);

    Article getArticleByToken(String token);
}
