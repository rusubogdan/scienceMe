package com.scncm.controller;

import com.scncm.dao.ArticleDAO;
import com.scncm.model.Article;
import com.scncm.model.User;
import com.scncm.service.ArticleService;
import com.scncm.service.UserService;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.PostgreSQLJDBCDataModel;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("user")
public class UserProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;


    @RequestMapping("/{username}")
    public ModelAndView userProfile(@PathVariable(value = "username") String username) {
        ModelAndView mv = new ModelAndView("userProfile");

        User loggedInUser = userService.getUserByUsername(username);
        Set <Article> userArticles = articleService.searchArticles("is");

        /*
            List <Article> userArticles = articleService.getArticlesByUser(loggedInUser);
            # the articleService.getArticlesByUser returns no data.
        */


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (loggedInUser == null) {
            return new ModelAndView("redirect:/404");
        }

        mv.addObject("userName", authentication.getName());
        mv.addObject("loggedInUser", loggedInUser);
        mv.addObject("userArticles", userArticles);

        return mv;
    }



}
