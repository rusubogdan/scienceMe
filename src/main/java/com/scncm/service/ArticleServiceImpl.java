package com.scncm.service;

import com.scncm.dao.ArticleDAO;
import com.scncm.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDAO articleDAO;

    @Override
    public Article getArticle(Integer articleId) {
        return articleDAO.getArticle(articleId);
    }
}
