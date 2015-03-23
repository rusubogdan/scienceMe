package com.scncm.dao;

import com.scncm.model.Article;
import com.scncm.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleDAOImpl implements ArticleDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Article getArticle(Integer articleId) {
        return (Article) getCurrentSession().load(Article.class, articleId);
    }

    public List<Article> getArticlesByUser (User user) {
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

    public Article addArticle (Article article) {
        Article addedArcticle = (Article) getCurrentSession().save(article);
        getCurrentSession().getTransaction().commit();

        return addedArcticle;
    }

    public Boolean updateArticle (Article article) {
        try {
            getCurrentSession().update(article);
            getCurrentSession().getTransaction().commit();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean deleteArticle (Article article) {
        try {
            getCurrentSession().delete(article);
            getCurrentSession().getTransaction().commit();

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
