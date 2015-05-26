package com.scncm.controller;

import com.scncm.model.Article;
import com.scncm.model.User;
import com.scncm.service.ArticleService;
import com.scncm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.List;

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

        if (loggedInUser == null) {
            return new ModelAndView("redirect:/404");
        }

//        Set <Article> userArticles = articleService.searchArticles("is");

        List<Article> userArticles = articleService.getArticlesByUser(loggedInUser);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        mv.addObject("userName", authentication.getName());
        mv.addObject("loggedInUser", loggedInUser);
        mv.addObject("registeredDate",
                new SimpleDateFormat("MM-dd-yyyy").format(loggedInUser.getRegisterDate()));
        mv.addObject("userArticles", userArticles);

        if (userArticles == null || userArticles.size() == 0) {
            mv.addObject("noContribution", true);
        } else {
            mv.addObject("noContribution", false);
        }

        return mv;
    }



}
