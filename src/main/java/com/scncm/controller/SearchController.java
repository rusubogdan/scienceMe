package com.scncm.controller;

import com.scncm.model.Article;
import com.scncm.model.User;
import com.scncm.service.ArticleService;
import com.scncm.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping(value = "/search")
public class SearchController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    /**
     * On search from wall page it gets me here, on search page, with the search bar from this page
     * containing the search query from wall page search bar.
     * Via AJAX, the request will be made from this page to bring me the articles I've search for, at page load.
     * In the left side, there will be some tags for a more filtered search result.
     */


    /**
     * @param searchQuery
     * @return
     *
     * Search page is allowed only on a POST method with some search query
     * */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView searchPage(
            @RequestParam(value = "searchQuery") String searchQuery) {
        ModelAndView mv = new ModelAndView("searchPage");

        Set<Article> articles = articleService.searchArticles(searchQuery);

        List<Article> articleList = new ArrayList<Article>(articles);

        mv.addObject("searchQuery", searchQuery);
        mv.addObject("articleList", articleList);

        return mv;
    }

    @RequestMapping(value = "ajax/filterArticles", method = RequestMethod.POST)
    @ResponseBody
    public Map search(
            @RequestParam String searchQuery) {
        Map response = new HashMap();

        response.put("msg", "bla");

        return response;
    }

}
