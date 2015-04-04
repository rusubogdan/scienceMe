package com.scncm.controller;

import com.scncm.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scncm.service.ArticleService;
import com.scncm.service.TagService;
import com.scncm.service.UserService;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
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
        List<Article> articles = articleService.getArticleFiltered(news, rating, barLowerBound, upperBoundInterval, startingSearchPoint);
        ObjectMapper objectMapper = new ObjectMapper();
        String articlesAsJson = "";
        //The input argument of the writeValueAsString() function can be a bean, array, list, map or a set.
        try {
            articlesAsJson = objectMapper.writeValueAsString(articles);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return articlesAsJson;
    }

    @RequestMapping(value = "ajax/testArticle", method = RequestMethod.GET)
    @ResponseBody
    public Map testArticle() throws TasteException {
        Map map = new HashMap();

        map.put("message", "message");


        DataModel model = new FileDataModel(new File("/path/to/dataset.csv"));
        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
        UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
        UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
        List<RecommendedItem> recommendations = recommender.recommend(2, 3);
        for (RecommendedItem recommendation : recommendations) {
            System.out.println(recommendation);
        }

        return map;
    }
}
