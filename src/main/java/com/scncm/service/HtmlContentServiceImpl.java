package com.scncm.service;

import com.scncm.dao.HtmlContentDAO;
import com.scncm.model.HtmlContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HtmlContentServiceImpl implements HtmlContentService{

    @Autowired
    private HtmlContentDAO htmlContentDAO;

    public HtmlContent getHtmlContent (Integer htmlContentId) {
        return htmlContentDAO.getHtmlContent(htmlContentId);
    }

    public HtmlContent getHtmlContentByArticleId (Integer articleId) {
        return htmlContentDAO.getHtmlContentByArticleId(articleId);
    }

    @Override
    public Integer addHtmlContent(HtmlContent htmlContent) {
        return htmlContentDAO.addHtmlContent(htmlContent);
    }

}
