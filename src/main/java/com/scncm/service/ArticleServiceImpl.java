package com.scncm.service;

import com.scncm.dao.ArticleDAO;
import com.scncm.model.Article;
import com.scncm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDAO articleDAO;
    @Transactional
    public Article getArticle(Integer articleId) {
        return articleDAO.getArticle(articleId);
    }

    public Article getSimpleArticle(Integer articleId) {
        return articleDAO.getSimpleArticle(articleId);
    }

    // useless, get them directly from user object !!!
    public List<Article> getArticlesByUser (User user) {
        return articleDAO.getArticlesByUser(user);
    }

    public Article addArticle (Article article) {
        return articleDAO.addArticle(article);
    }

    @Override
    public Set<Article> searchArticles(String searchQuery) {
        return articleDAO.searchArticles(searchQuery);
    }

    public Boolean updateArticle (Article article) {
        return articleDAO.updateArticle(article);
    }

    public Boolean deleteArticle (Article article) {
        return articleDAO.deleteArticle(article);
    }

    public List<Article> getArticleFiltered(Boolean news, Boolean rating, Integer startTime, Integer endTime, Integer startingSearchPoint) {
        return articleDAO.getArticleFiltered(news, rating, startTime, endTime, startingSearchPoint);
    }
}
