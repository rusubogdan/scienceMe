package com.scncm.dao;

import com.scncm.model.Article;
import com.scncm.model.User;

import org.hibernate.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public List<Article> getArticleFiltered(Boolean news, Boolean rating, Integer barLowerBound,
                                            Integer barUpperBound, Integer startingSearchPoint) {
        List<Article> articles = new ArrayList<Article>();
        Query query = null;
        if (!news && !rating) {
            query = getCurrentSession().createQuery(
                    "SELECT new Article(A.articleId, A.title, A.description, A.owner, " +
                            "A.readingTime, A.link, A.createdDate, 1) " +
                    "from Article A " +
                    "where A.readingTime between :barLowerBound and :barUpperBound");
            query.setParameter("barLowerBound", barLowerBound);
            query.setParameter("barUpperBound", barUpperBound);
            query.setFirstResult(startingSearchPoint);
            query.setMaxResults(10);
        } else {
            if (!news && rating) {
                query = getCurrentSession().createQuery("SELECT A ,(SELECT sum(UAV.rating)/count" +
                        "(UAV.articleId) from UserArticleVote UAV where A.articleId = UAV.article.articleId" +
                        " group by UAV.article.articleId)from Article A where A.readingTime between" +
                        " :barLowerBound and :barUpperBound order by 2 desc");
                query.setParameter("barLowerBound", barLowerBound);
                query.setParameter("barUpperBound", barUpperBound);
                query.setFirstResult(startingSearchPoint);
                query.setMaxResults(10);
            } else {
                if (news && !rating) {
                    query = getCurrentSession().createQuery(
                            "from Article a " +
                                    "where a.readingTime between :barLowerBound and :barUpperBound " +
                                    "order by a.createdDate"
                    );
                    query.setParameter("barLowerBound", barLowerBound);
                    query.setParameter("barUpperBound", barUpperBound);
                    query.setFirstResult(startingSearchPoint);
                    query.setMaxResults(10);
                } else {
                    query = null;
                }
            }

        }
        try {
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

    public List<Article> getMostRatedArticle(Integer numberOfArticle){
        List<Article> articles = new ArrayList<Article>();
        List<Article> temporaryArticles = new ArrayList<Article>();
        Query query;
        query = getCurrentSession().createQuery("SELECT A ,(SELECT sum(UAV.rating)/count" +
                "(UAV.articleId) from UserArticleVote UAV where A.articleId = UAV.article.articleId" +
                " group by UAV.article.articleId)from Article A order by 2 desc");
        query.setMaxResults(numberOfArticle);
        temporaryArticles = query.list();
        return articles;
    }

    public Article addArticle(Article article) {
        Integer addedArcticle = (Integer) getCurrentSession().save(article);
//        getCurrentSession().getTransaction().commit();

        return article;
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
