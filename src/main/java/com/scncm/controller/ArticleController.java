package com.scncm.controller;

import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.scncm.model.User;
import com.scncm.service.ArticleService;
import com.scncm.service.UserService;
import com.syncthemall.diffbot.Diffbot;
import com.syncthemall.diffbot.exception.DiffbotException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;


@Controller
@RequestMapping(value = "/article", method = RequestMethod.GET)
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "add-article", method = RequestMethod.GET)
    public ModelAndView addArticle(
            @RequestParam(value = "article", required = false) com.scncm.model.Article article, HttpServletRequest request) throws JAXBException {

        ModelAndView mv = new ModelAndView("addArticle");

        String new_link = request.getParameter("link");
        String new_description = request.getParameter("description");
        String new_time = request.getParameter("time");
        String new_tags = request.getParameter("tags");

        if (new_link != null) {

            Diffbot diffbot = new Diffbot(new ApacheHttpTransport(), new JacksonFactory(), "25831bb0c62f549dab3e1807bef2ff5f");
            try {
                /*in the article_diff we keep all the data received from the diffBoot api*/
                com.syncthemall.diffbot.model.article.Article article_diff = diffbot.article().analyze(new_link).execute();

                System.out.println("aici");
                mv.addObject("description", article_diff.getHtml());

                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

                if (authentication.getAuthorities().toString().contains("ROLE_ANONYMOUS")) {
                    HttpServletResponse httpServletResponse = null;
                    httpServletResponse.sendRedirect("/");
                    return null;
                }

                User loggedInUser = userService.getUserByUsername(authentication.getName());

                com.scncm.model.Article art = new com.scncm.model.Article();

                art.setArticleId(100);
                art.setDescription(new_description);
                art.setTitle(article_diff.getTitle());
                art.setLink(article_diff.getUrl());
                art.setOwner(loggedInUser);
                art.setReadingTime(Integer.parseInt(new_time));
                art.setHtmlContent(article_diff.getHtml());

                articleService.addArticle(art);

                mv.addObject("loggedInUser", loggedInUser);

            } catch (DiffbotException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        mv.addObject("description", "description");
        return mv;
    }


    @RequestMapping(value = "view-article", method = RequestMethod.GET)
    public ModelAndView viewArticle(
            @RequestParam(value = "article", required = false) com.scncm.model.Article article) throws JAXBException {

        ModelAndView mv = new ModelAndView("viewArticle");

        System.out.println("ajunge");

        com.scncm.model.Article articol = articleService.getArticle(0);

//        in view folosesc doar title si description deocamdata
        mv.addObject("title", articol.getTitle());
        mv.addObject("title_view", "View Article");
        mv.addObject("description", articol.getDescription());

        return mv;
    }


    public void testRequest() {

    }


}
