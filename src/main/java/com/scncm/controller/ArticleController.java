package com.scncm.controller;

import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.scncm.model.Article;
import com.scncm.model.HtmlContent;
import com.scncm.model.User;
import com.scncm.service.ArticleService;
import com.scncm.service.HtmlContentService;
import com.scncm.service.UserService;
import com.syncthemall.diffbot.Diffbot;
import com.syncthemall.diffbot.exception.DiffbotException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;
import java.sql.Timestamp;
import java.util.*;

@Controller
@RequestMapping(value = "/article", method = RequestMethod.GET)
public class ArticleController {
    Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Autowired
    private HtmlContentService htmlContentService;

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView addArticle() {
        ModelAndView mv = new ModelAndView("addArticle");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        mv.addObject("userName", authentication.getName());

        return mv;
    }

    @RequestMapping(value = "add.do", method = RequestMethod.POST)
    public ModelAndView addArticleInDataBase(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("addArticle");
        com.scncm.model.Article article = null;

        if (request != null) {
            String new_link = request.getParameter("link");
            String new_description = request.getParameter("description");
            String new_time = request.getParameter("time");
            String new_tags = request.getParameter("tags");

            if (new_link != null) {
                Diffbot diffbot = null;

                try {
                    diffbot = new Diffbot(new ApacheHttpTransport(), new JacksonFactory(), "25831bb0c62f549dab3e1807bef2ff5f");
                    /*in the diffbotArticle we keep all the data received from the diffBoot api*/
                    com.syncthemall.diffbot.model.article.Article diffbotArticle = diffbot.article().analyze(new_link).execute();

                    mv.addObject("description", diffbotArticle.getHtml());

                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                    User loggedInUser = userService.getUserByUsername(authentication.getName());

                    article = new com.scncm.model.Article();
                    article.setDescription(new_description);
                    article.setTitle(diffbotArticle.getTitle());
                    article.setLink(diffbotArticle.getUrl());
                    article.setOwner(loggedInUser);
                    article.setCreatedDate(new Timestamp(new Date().getTime()));
                    article.setReadingTime(Integer.parseInt(new_time));

                    // create new html content
                    HtmlContent htmlContent = new HtmlContent();
                    htmlContent.setHtml(diffbotArticle.getHtml());

                    // Nu intrebati de ce  :)
                    htmlContent.setArticleId(9999);

                    // save html content to db
                    Integer htmlContentId = htmlContentService.addHtmlContent(htmlContent);

                    // set html content id to the article
                    article.setHtmlContentId(htmlContentId);

                    Integer articleId = articleService.addArticle(article);

                    htmlContent.setArticleId(articleId);

                    // update html content
                    Boolean success = htmlContentService.update(htmlContent);

                    // some error occurs
                    if (articleId < 1 || !success) {
                        logger.warn("article creation failed");
                        return new ModelAndView("redirect:/article/add");
                    }

                    mv.addObject("loggedInUser", loggedInUser);

                } catch (DiffbotException | JAXBException e) {
                    e.printStackTrace();
                }
            }
        }

        mv = new ModelAndView("redirect:/article/view/" + (article != null ? article.getToken() : "00000000"));

        return mv;
    }

    @RequestMapping(value = "view", method = RequestMethod.GET)
    public ModelAndView viewArticle() {
        return new ModelAndView("redirect:/wall");
    }

    @RequestMapping(value = "view/{token}", method = RequestMethod.GET)
    public ModelAndView viewArticle(@PathVariable(value = "token") String token) {
        ModelAndView mv = new ModelAndView("viewArticle");
        Article article = articleService.getArticleByToken(token);

        if (article == null) {
            return new ModelAndView("redirect:/404");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        HtmlContent htmlContent = htmlContentService.getHtmlContentByArticleId(article.getArticleId());

        mv.addObject("userName", authentication.getName());
        mv.addObject("articleTitle", article.getTitle());
        mv.addObject("articleDescription", article.getDescription());
        mv.addObject("articleHtml", htmlContent.getHtml());

        return mv;
    }
}
