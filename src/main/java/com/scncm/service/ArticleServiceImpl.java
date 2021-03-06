package com.scncm.service;

import com.scncm.dao.ArticleDAO;
import com.scncm.helpers.AppUtil;
import com.scncm.helpers.Constants;
import com.scncm.model.Article;
import com.scncm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
public  class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDAO articleDAO;
    @Transactional
    public Article getArticle(Integer articleId) {
        return articleDAO.getArticle(articleId);
    }

    public Article getSimpleArticle(Integer articleId) {
        return articleDAO.getSimpleArticle(articleId);
    }

    public Article getArticleByToken(String token) {
        return articleDAO.getArticleByToken(token);
    }

    // useless, get them directly from user object !!!
    public List<Article> getArticlesByUser (User user) {
        return articleDAO.getArticlesByUser(user);
    }

    public Integer addArticle (Article article) {
        String token = AppUtil.generateRandomString(Constants.tokenValidChars, Constants.tokenLength);
        while(this.getArticleByToken(token) != null) {
            token = AppUtil.generateRandomString(Constants.tokenValidChars, Constants.tokenLength);
        }
        article.setToken(token);
        return articleDAO.addArticle(article);
    }

    @Override
    public List<Map> searchArticles(String searchQuery) {
        return articleDAO.searchArticles(searchQuery);
    }

    public Boolean updateArticle (Article article) {
        return articleDAO.updateArticle(article);
    }

    public Boolean deleteArticle (Article article) {
        return articleDAO.deleteArticle(article);
    }

    public List<Map> getArticleFiltered(Boolean news, Boolean rating, Integer startTime, Integer endTime, Integer startingSearchPoint) {
        return articleDAO.getArticleFiltered(news, rating, startTime, endTime, startingSearchPoint);
    }

    public List<Map> getMostRatedArticle(Integer numberOfArticle, Integer userId, List<Integer> recommendedList){
        return articleDAO.getMostRatedArticle(numberOfArticle,userId,recommendedList);
    }

    public List<Map> getArticleAndRating(List<Integer> recommendedList){
        return articleDAO.getArticleAndRating(recommendedList);
    }

    public Integer verifyIfUserVoteArticle(Integer user_id, Integer article_id){
        return articleDAO.verifyIfUserVoteArticle(user_id, article_id);
    }

    public void insertOrUpdeteVoteArtcile(Integer user_id, Integer article_id, Integer rating){
        articleDAO.insertOrUpdeteVoteArtcile(user_id, article_id,rating);
    }
}
