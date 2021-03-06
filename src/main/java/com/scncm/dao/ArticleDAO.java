package com.scncm.dao;

import com.scncm.model.Article;
import com.scncm.model.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ArticleDAO {

    Article getArticle(Integer articleId);

    Article getSimpleArticle(Integer articleId);

    Article getArticleByToken(String token);

    List<Article> getArticlesByUser(User user);

    List<Map> searchArticles (String searchQuery);

    List<Map> getArticleFiltered(Boolean news, Boolean rating, Integer startTime, Integer endTime, Integer startingSearchPoint);

    List<Map> getMostRatedArticle(Integer numberOfArticle, Integer userId, List<Integer> recommendedList);

    List<Map> getArticleAndRating(List<Integer> recommendedList);

    // List<Map> getArticleSearch(List<Integer> resultList);

    Integer addArticle (Article article);

    Boolean updateArticle (Article article);

    Boolean deleteArticle (Article article);

    Integer verifyIfUserVoteArticle(Integer id_user, Integer id_article);

    void insertOrUpdeteVoteArtcile(Integer user_id, Integer article_id, Integer rating);
}
