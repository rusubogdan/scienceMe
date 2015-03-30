package com.scncm.controller;

import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
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

import javax.servlet.http.HttpServletRequest;
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


    @RequestMapping(value = "add-article", method = RequestMethod.GET)
    public ModelAndView addArticle(
            @RequestParam(value = "article", required = false) com.scncm.model.Article article, HttpServletRequest request) throws JAXBException {

        ModelAndView mv = new ModelAndView("addArticle");

        String new_link = request.getParameter("link");
        String new_description = request.getParameter("description");
        String new_time = request.getParameter("time");
        String new_tags = request.getParameter("tags");

        if(new_link != null) {

            Diffbot diffbot = new Diffbot(new ApacheHttpTransport(), new JacksonFactory(), "25831bb0c62f549dab3e1807bef2ff5f");
            try {
//                aici tin informatiile de la api
                Article article_diff = diffbot.article().analyze(new_link).execute();

                System.out.println("aici");
                mv.addObject("description", article_diff.getHtml());


            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

                // todo redirect the infidel from the wall page to login page
                // only vip are allowed
            if (authentication.getAuthorities().toString().contains("ROLE_ANONYMOUS")) {
                HttpServletResponse httpServletResponse = null;
                httpServletResponse.sendRedirect("/");
                return null;
            }

            User loggedInUser = userService.getUserByUsername(authentication.getName());

                com.scncm.model.Article art = new com.scncm.model.Article();

            art.setArticleId(100);
            art.setDescription(new_description);  // descrierea din formular
            art.setTitle(article_diff.getTitle());  // titlul returnat de api
            art.setLink(article_diff.getUrl()); // url-ul returnat de api catre articol
            art.setOwner(loggedInUser); // userul logat
            art.setReadingTime(Integer.parseInt(new_time)); // timpul din formular
            art.setContent(article_diff.getHtml()); // content-ul returnat de api

            articleService.addArticle(art);

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

        com.scncm.model.Article articol =  articleService.getArticle(0);

//        in view folosesc doar title si description deocamdata
        mv.addObject("title", articol.getTitle());
        mv.addObject("title_view","View Article");
        mv.addObject("description", articol.getDescription());

        return mv;
    }


    public void testRequest(){

    }


}
