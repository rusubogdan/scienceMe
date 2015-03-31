package com.scncm.dao;

import com.scncm.model.Article;
import com.scncm.model.User;

import java.util.List;

public interface ArticleDAO {

    Article getArticle(Integer articleId);

    List<Article> getArticlesByUser(User user);

    List<Article> getArticleFiltered(boolean news, boolean rating, int startTime, int endTime);

    Article addArticle (Article article);

    Boolean updateArticle (Article article);

    Boolean deleteArticle (Article article);
}
