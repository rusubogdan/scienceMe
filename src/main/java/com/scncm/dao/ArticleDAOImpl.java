package com.scncm.dao;

import com.scncm.model.Article;
import com.scncm.model.User;

import org.hibernate.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ArticleDAOImpl implements ArticleDAO {

    private Logger logger = LoggerFactory.getLogger(ArticleDAOImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Article getArticle(Integer articleId) {
        return (Article) getCurrentSession().get(Article.class, articleId);
    }

    public Article getSimpleArticle(Integer articleId) {
        List<Article> simpleArticles = new ArrayList<Article>();
        Query query;

        try {
            query = getCurrentSession().createQuery(
                    "select new Article(a.articleId, a.title, a.description, a.owner, a.readingTime, " +
                            "a.link, a.createdDate)" +
                            "from Article a " +
                            "where a.articleId = :articleId"
            );
            query.setParameter("articleId", articleId);
            simpleArticles = query.list();
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }

        if (simpleArticles.size() > 0) {
            return simpleArticles.get(0);
        } else {
            return null;
        }
    }

    public Article getArticleByToken (String token) {
        List<Article> articles = new ArrayList<Article>();
        Query query;
        try {
            query = getCurrentSession().createQuery("from Article a where a.token = :token");
            query.setParameter("token", token);
            articles = query.list();
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }

        if (articles.size() > 0) {
            return articles.get(0);
        } else {
            return null;
        }
    }

    public List<Article> getArticlesByUser(User user) {
        List<Article> articles = new ArrayList<Article>();
        Query query;
        try {
            query = getCurrentSession().createQuery("from Article a where a.user = :user");
            query.setParameter("user", user);
            articles = query.list();
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }

        if (articles.size() > 0) {
            return articles;
        } else {
            return null;
        }
    }

    public Set<Article> searchArticles(String searchQuery) {
        List<Article> articles = new ArrayList<Article>();
        Query query = null;

        try {
            query = getCurrentSession().createQuery("from Article a where a.title like :searchQuery " +
                    "or a.description like :searchQuery");
            query.setParameter("searchQuery", '%' + searchQuery + '%');
            articles = query.list();
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }

        Set<Article> articleSet = new HashSet<Article>(articles);

        if (articleSet.size() > 0)
            return articleSet;
        else
            return null;
    }

    public List<Map> getArticleFiltered(Boolean news, Boolean rating, Integer barLowerBound,
                                            Integer barUpperBound, Integer startingSearchPoint) {
        List<Map> articles = new ArrayList<Map>();
        List<Object[]> temporaryArticles;
        Query query = null;
        if (!news && rating) {
                query = getCurrentSession().createQuery("SELECT (select A from Article A where A.articleId = " +
                        "UAV.article.articleId), sum(UAV.rating)/count(UAV.article) from UserArticleVote UAV  " +
                        "where UAV.article.readingTime between :barLowerBound and :barUpperBound " +
                        "group by 1 order by 2 desc");
                query.setParameter("barLowerBound", barLowerBound);
                query.setParameter("barUpperBound", barUpperBound);
                query.setFirstResult(startingSearchPoint);
                query.setMaxResults(10);
                temporaryArticles = query.list();
                for(int i = 0 ; i < temporaryArticles.size() ; i++) {
                    Map temporaryMap = new HashMap<>();
                    temporaryMap.put("article",(Article) temporaryArticles.get(i)[0]);
                    Double articleRating = (Double) temporaryArticles.get(i)[1];
                    if(articleRating == null) {
                        temporaryMap.put("rating",0);
                    }
                    else{
                        temporaryMap.put("rating",articleRating);
                    }
                    articles.add(temporaryMap);
                }
            }
            else {
                if (news && !rating) {
                    query = getCurrentSession().createQuery("select A,(SELECT avg(UAV.rating) from UserArticleVote UAV " +
                            "where UAV.article.articleId = A.articleId) from Article A where A.readingTime between" +
                            " :barLowerBound and :barUpperBound order by A.createdDate");
                    query.setParameter("barLowerBound", barLowerBound);
                    query.setParameter("barUpperBound", barUpperBound);
                    query.setFirstResult(startingSearchPoint);
                    query.setMaxResults(10);
                    temporaryArticles = query.list();
                    for(int i = 0 ; i < temporaryArticles.size() ; i++) {
                        Map temporaryMap = new HashMap<>();
                        temporaryMap.put("article",(Article) temporaryArticles.get(i)[0]);
                        Double articleRating = (Double) temporaryArticles.get(i)[1];
                        if(articleRating == null) {
                            temporaryMap.put("rating",0);
                        }
                        else{
                            temporaryMap.put("rating",articleRating);
                        }
                        articles.add(temporaryMap);
                    }
                }
            }

        if (articles.size() > 0) {
            return articles;
        } else {
            return null;
        }

    }

    public List<Map> getMostRatedArticle(Integer numberOfArticle, Integer userId, List<Integer> recommendedList){
        List<Map> articles = new ArrayList<Map>();
        List<Object[]> temporaryArticles;
        Query query;
        String hibernateQuery = "SELECT (select A from Article A where A.articleId = " +
                "UAV.article.articleId) as article, sum(UAV.rating)/count(UAV.article) as rating from " +
                "UserArticleVote UAV where UAV.article.owner.userId != :userId and UAV.article.articleId not in " +
                "(SELECT UAV1.article.articleId from UserArticleVote UAV1 where UAV1.user.userId = :userId)";
        if(recommendedList.size() != 0){
            hibernateQuery +=" and UAV.article.articleId not in (:recommendedList) group by 1 order by 2 desc";
            query = getCurrentSession().createQuery(hibernateQuery);
            query.setParameterList("recommendedList",recommendedList);
        }
        else{
            hibernateQuery += "group by 1 order by 2 desc";
            query = getCurrentSession().createQuery(hibernateQuery);
        }
        query.setMaxResults(numberOfArticle);
        query.setParameter("userId", userId);
        temporaryArticles = query.list();
        for(int i = 0 ; i < temporaryArticles.size() ; i++) {
            Map temporaryMap = new HashMap<>();
            temporaryMap.put("article", temporaryArticles.get(i)[0]);
            temporaryMap.put("rating", temporaryArticles.get(i)[1]);
            articles.add(temporaryMap);
        }

        return articles;
    }

    public Map getArticleAndRating(Integer articleId){
        Map articleAndRating = new HashMap<>();
        Query query;
        List<Object[]> resultQuery;

        query = getCurrentSession().createQuery("Select A,(select avg(UAV.rating) from UserArticleVote UAV where " +
                "UAV.article.articleId = :articleId) from Article A where A.articleId = :articleId");
        query.setParameter("articleId",articleId);
        resultQuery = query.list();
        if(resultQuery.size() == 1) {
            articleAndRating.put("article", resultQuery.get(0)[0]);
            articleAndRating.put("rating",resultQuery.get(0)[1]);
        }
        return articleAndRating;
    }

    public Integer addArticle(Article article) {
        Integer articleId = (Integer) getCurrentSession().save(article);

        return articleId;
    }

    public Boolean updateArticle(Article article) {
        try {
            getCurrentSession().update(article);
//            getCurrentSession().getTransaction().commit();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean deleteArticle(Article article) {
        try {
            getCurrentSession().delete(article);
//            getCurrentSession().getTransaction().commit();

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
