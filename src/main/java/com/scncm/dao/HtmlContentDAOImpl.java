package com.scncm.dao;

import com.scncm.model.Article;
import com.scncm.model.HtmlContent;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class HtmlContentDAOImpl implements HtmlContentDAO {

    private static final Logger logger = LoggerFactory.getLogger(HtmlContentDAO.class);

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public HtmlContent getHtmlContent(Integer htmlContentId) {
        return (HtmlContent) getCurrentSession().load(HtmlContent.class, htmlContentId);
    }

    @Override
    public HtmlContent getHtmlContentByArticleId(Integer articleId) {
        List<HtmlContent> htmlList = new ArrayList<HtmlContent>();
        Query query;
        try {
            query = getCurrentSession().createQuery("from HtmlContent h where h.articleId = :articleId");
            query.setParameter("articleId", articleId);
            htmlList = query.list();
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }

        if (htmlList.size() > 0) {
            return htmlList.get(0);
        } else {
            return null;
        }
    }

    public Integer addHtmlContent (HtmlContent htmlContent) {
        return (Integer) getCurrentSession().save(htmlContent);
    }

    public Boolean update(HtmlContent htmlContent) {
        try {
            getCurrentSession().getTransaction().begin();
            getCurrentSession().update(htmlContent);
            getCurrentSession().getTransaction().commit();
            getCurrentSession().flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
