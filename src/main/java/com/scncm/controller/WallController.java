package com.scncm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scncm.model.*;
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
import java.util.List;
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

    @RequestMapping(value = "ajax/filterArticles", method = RequestMethod.GET)
    @ResponseBody
    public String filterArticles(
            @RequestParam(value = "news") Boolean news,
            @RequestParam(value = "rating") Boolean rating,
            @RequestParam(value = "barLowerBound") Integer barLowerBound,
            @RequestParam(value = "upperBoundInterval") Integer upperBoundInterval,
            @RequestParam(value = "startingSearchPoint") Integer startingSearchPoint
    ) {
        Map map = new HashMap();
//        Map<Integer,Article> map = new HashMap<Integer,Article>();
        List<Article> articles = articleService.getArticleFiltered(news,rating,barLowerBound,upperBoundInterval,startingSearchPoint);
//        for(int i = 0 ; i < articles.size() ; i++){
//            map.put(i,articles.get(i));
//        }
//        articles.get(0).setOwner(null);
        ObjectMapper objectMapper = new ObjectMapper();
        String articlesAsJson = "";
//        map.put("articol",articles);
//The input argument of the writeValueAsString() function can be a bean, array, list, map or a set.
        try {
            articlesAsJson = objectMapper.writeValueAsString(articles);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        articles.get(0).getOwner().
//        map.put("articol",articles.get(0).setUserArticleVoteSet(null););
        return articlesAsJson;
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
