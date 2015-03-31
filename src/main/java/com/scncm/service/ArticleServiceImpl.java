package com.scncm.service;

import com.scncm.dao.ArticleDAO;
import com.scncm.model.Article;
import com.scncm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDAO articleDAO;
    @Transactional
    public Article getArticle(Integer articleId) {
        return articleDAO.getArticle(articleId);
    }

    // useless, get them directly from user object !!!
    public List<Article> getArticlesByUser (User user) {
        return articleDAO.getArticlesByUser(user);
    }

    public Article addArticle (Article article) {
        return articleDAO.addArticle(article);
    }

    public Boolean updateArticle (Article article) {
        return articleDAO.updateArticle(article);
    }

    public Boolean deleteArticle (Article article) {
        return articleDAO.deleteArticle(article);
    }

    public List<Article> getArticleFiltered(boolean news, boolean rating, int startTime, int endTime) {
        return articleDAO.getArticleFiltered(news, rating, startTime, endTime);
    }
}
