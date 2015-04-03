package com.scncm.dao;

import com.scncm.model.Article;
import com.scncm.model.ArticleTag;
import com.scncm.model.User;
import org.hibernate.*;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class ArticleDAOImpl implements ArticleDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Article getArticle(Integer articleId) {
        return (Article) getCurrentSession().get(Article.class, articleId);
    }

    public List<Article> getArticlesByUser(User user) {
        List<Article> articles = new ArrayList<Article>();
        Query query;
        try {
            query = getCurrentSession().createQuery("from Article a where a.user = :user");
            query.setParameter("user", user);
            articles = query.list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
        }

        Set<Article> articleSet = new HashSet<Article>(articles);

        if (articleSet.size() > 0)
            return articleSet;
        else
            return null;
    }

    public List<Article> getArticleFiltered(Boolean news, Boolean rating, Integer barLowerBound, Integer barUpperBound, Integer startingSearchPoint) {
            List<Article> articles = new ArrayList<Article>();
        Query query;
        if (!news && !rating) {
            query = getCurrentSession().createQuery("SELECT A.articleId,A.title,A.description,A.owner.username,A.readingTime,A.createdDate,A.articleTags from Article A where A.readingTime between :barLowerBound and :barUpperBound");
            query.setParameter("barLowerBound", barLowerBound);
            query.setParameter("barUpperBound", barUpperBound);
            query.setFirstResult(startingSearchPoint);
            query.setMaxResults(10);
        } else {
            if (!news && rating) {
                query = getCurrentSession().createQuery("SELECT A.articleId,A.title,A.description,A.owner.username,A.readingTime,A.createdDate,A.articleTags ,coalesce((SELECT sum (case UAV.vote.voteName " +
                        "when 'LIKE' then 1 when 'DISLIKE' then -1 else 0 end)" +
                        " from UserArticleVote UAV where A.articleId = UAV.article.articleId" +
                        " group by UAV.article.articleId),0)from Article A where A.readingTime between" +
                        " :barLowerBound and :barUpperBound order by 2 desc");
                query.setParameter("barLowerBound", barLowerBound);
                query.setParameter("barUpperBound", barUpperBound);
                query.setFirstResult(startingSearchPoint);
                query.setMaxResults(10);
            } else {
                if (news && !rating) {
                    query = getCurrentSession().createQuery("SELECT new Article(A.articleId,A.title,A.description,A.owner,A.userArticleVoteSet," +
                            "A.articleTags,A.readingTime,A.link,A.htmlContent,A.createdDate) from Article A where A.readingTime between" +
                            " :barLowerBound and :barUpperBound");
                    query.setParameter("barLowerBound", barLowerBound);
                    query.setParameter("barUpperBound", barUpperBound);
                    query.setFirstResult(startingSearchPoint);
                    query.setMaxResults(10);
//                    Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Article.class);
//                    ProjectionList projList = Projections.projectionList();
//                    projList.add(Projections.property("articleId"));
//                    projList.add(Projections.property("title"));
//                    projList.add(Projections.property("description"));
//                    projList.add(Projections.property("owner"));
//                    projList.add(Projections.property("readingTime"));
//                    projList.add(Projections.property("createdDate"));
//                    projList.add(Projections.property("articleTags"));
//                    criteria.setProjection(projList);
////                    criteria.setFetchMode("articleTags", FetchMode.SELECT);
////                    criteria.setResultTransformer(Transformers.aliasToBean(Article.class));
//                    articles = criteria.list();
                } else {
                    query = null;
                }
            }

        }
        try {
            articles = query.list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (articles.size() > 0) {
            return articles;
        } else {
            return null;
        }

    }

    public Article addArticle(Article article) {
        Integer addedArcticle = (Integer) getCurrentSession().save(article);
//        getCurrentSession().getTransaction().commit();

        return article;
    }

    public Boolean updateArticle(Article article) {
        try {
            getCurrentSession().update(article);
            getCurrentSession().getTransaction().commit();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean deleteArticle(Article article) {
        try {
            getCurrentSession().delete(article);
            getCurrentSession().getTransaction().commit();

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
