package com.scncm.controller;

import com.scncm.model.Article;
import com.scncm.model.ArticleTag;
import com.scncm.model.Tag;
import com.scncm.model.User;
import com.scncm.service.ArticleService;
import com.scncm.service.TagService;
import com.scncm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/wall")
public class WallController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "")
    public ModelAndView wall(HttpServletResponse httpServletResponse) {
        ModelAndView mv;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // todo redirect the infidel from the wall page to login page
        // only vip are allowed
        if (authentication.getAuthorities().toString().contains("ROLE_ANONYMOUS")) {
            try {
                httpServletResponse.sendRedirect("/login?forbidden");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        mv = new ModelAndView("wall");

        User loggedInUser = userService.getUserByUsername(authentication.getName());

        mv.addObject("loggedInUser", loggedInUser);

        return mv;
    }

    @RequestMapping(value = "ajax/testArticle", method = RequestMethod.GET)
    @ResponseBody
    public Map testArticle() {
        Map map = new HashMap();
        map.put("object", "bla");

        Article articleFromDb = articleService.getArticle(1);
        Tag tagFromDb = tagService.getTag(4);

        ArticleTag articleTag = new ArticleTag();
        articleTag.setArticle(articleFromDb);
        articleTag.setTag(tagFromDb);

        articleFromDb.getArticleTags().add(articleTag);

        articleService.updateArticle(articleFromDb);

        return map;
    }
}
