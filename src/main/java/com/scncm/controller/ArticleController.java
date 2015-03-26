package com.scncm.controller;

import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.scncm.dao.ArticleDAO;
import com.scncm.dao.ArticleDAOImpl;
import com.scncm.model.User;
import com.scncm.service.ArticleService;
import com.scncm.service.UserService;
import com.syncthemall.diffbot.Diffbot;
import com.syncthemall.diffbot.exception.DiffbotException;
import com.syncthemall.diffbot.model.article.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Created by marcubeniamin on 25/03/15.
 */

@Controller
@RequestMapping(value = "/article", method = RequestMethod.GET)
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;


//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public ModelAndView addArticle() {
//        return new ModelAndView("addArticle");
//    }

    @RequestMapping(value = "add-article", method = RequestMethod.GET)
    public ModelAndView addArticle(
            @RequestParam(value = "article", required = false) com.scncm.model.Article article) throws JAXBException {
        ModelAndView mv = new ModelAndView("addArticle");

        Diffbot diffbot = new Diffbot(new ApacheHttpTransport(), new JacksonFactory(), "25831bb0c62f549dab3e1807bef2ff5f");
        try {
            Article article_diff = diffbot.article().analyze("http://ro.wikipedia.org/wiki/Rom%C3%A2nia").execute();
            mv.addObject("autor", article_diff.getAuthor());
            mv.addObject("date", article_diff.getDate());
            mv.addObject("title", article_diff.getTitle());


            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            // todo redirect the infidel from the wall page to login page
            // only vip are allowed
            if (authentication.getAuthorities().toString().contains("ROLE_ANONYMOUS")) {
                HttpServletResponse httpServletResponse = null;
                httpServletResponse.sendRedirect("/");
                return null;
            }

            User loggedInUser = userService.getUserByUsername(authentication.getName());

            article.setDescription(article_diff.getText());
            article.setTitle(article_diff.getTitle());
            article.setLink(article_diff.getUrl());
            article.setOwner(loggedInUser);
            article.setReadingTime(10);

            ArticleDAOImpl newArticle = new ArticleDAOImpl();
            newArticle.addArticle(article);

        } catch (DiffbotException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mv;
    }


    @RequestMapping(value = "view-article", method = RequestMethod.GET)
    public ModelAndView viewArticle(
            @RequestParam(value = "article", required = false) com.scncm.model.Article article) throws JAXBException {
        ModelAndView mv = new ModelAndView("viewArticle");

        Diffbot diffbot = new Diffbot(new ApacheHttpTransport(), new JacksonFactory(), "25831bb0c62f549dab3e1807bef2ff5f");
        try {
            Article article_diff = diffbot.article().analyze("http://ro.wikipedia.org/wiki/Rom%C3%A2nia").execute();
            mv.addObject("autor", article_diff.getAuthor());
            mv.addObject("date", article_diff.getDate());
            mv.addObject("title", article_diff.getTitle());
            mv.addObject("title_view","View Article");
            mv.addObject("description", article_diff.getHtml());
        } catch (DiffbotException e) {
            e.printStackTrace();
        }

        return mv;
    }


    public void testRequest(){

    }


}
