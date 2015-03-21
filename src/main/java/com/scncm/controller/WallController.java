package com.scncm.controller;

import com.scncm.model.Article;
import com.scncm.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "wall")
public class WallController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "")
    public ModelAndView wall() {
        return new ModelAndView("wall");
    }

    @RequestMapping(value = "ajax/testArticle", method = RequestMethod.GET)
    public Article testArticle(@RequestParam(value = "id") Integer id) {
        return articleService.getArticle(1);
    }
}
