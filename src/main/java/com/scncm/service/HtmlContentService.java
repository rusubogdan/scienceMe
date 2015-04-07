package com.scncm.service;

import com.scncm.model.HtmlContent;

public interface HtmlContentService {

    HtmlContent getHtmlContent (Integer htmlContentId);

    HtmlContent getHtmlContentByArticleId (Integer articleId);

    Integer addHtmlContent(HtmlContent htmlContent);
}
