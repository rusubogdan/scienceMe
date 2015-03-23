package com.scncm.dao;

import com.scncm.model.Article;
import com.scncm.model.User;

import java.util.List;

public interface ArticleDAO {

    Article getArticle(Integer articleId);

    List<Article> getArticlesByUser(User user);

    Article addArticle (Article article);

    Boolean updateArticle (Article article);

    Boolean deleteArticle (Article article);
}
