package com.scncm.dao;

import com.scncm.model.HtmlContent;

public interface HtmlContentDAO {

    HtmlContent getHtmlContent(Integer htmlContentId);

    HtmlContent getHtmlContentByArticleId(Integer articleId);

    Integer addHtmlContent (HtmlContent htmlContent);

    Boolean update(HtmlContent htmlContent);
}
