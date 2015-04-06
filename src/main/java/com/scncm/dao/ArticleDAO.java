package com.scncm.dao;

import com.scncm.model.Article;
import com.scncm.model.User;

import java.util.List;
import java.util.Set;

public interface ArticleDAO {

    Article getArticle(Integer articleId);

    Article getSimpleArticle(Integer articleId);

    List<Article> getArticlesByUser(User user);

    Set<Article> searchArticles (String searchQuery);

    List<Article> getArticleFiltered(Boolean news, Boolean rating, Integer startTime, Integer endTime, Integer startingSearchPoint);

    List<Article> getMostRatedArticle(Integer numberOfArticle);

    Article addArticle (Article article);

    Boolean updateArticle (Article article);

    Boolean deleteArticle (Article article);
}
