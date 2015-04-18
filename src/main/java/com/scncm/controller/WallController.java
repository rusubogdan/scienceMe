package com.scncm.controller;

import com.scncm.model.Article;
import com.scncm.service.ArticleService;
import com.scncm.service.UserService;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.PostgreSQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.ReloadFromJDBCDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.recommender.ItemBasedRecommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.postgresql.ds.PGSimpleDataSource;
import org.slf4j.Logger;
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
import java.util.*;

@Controller
@RequestMapping(value = "/wall")
public class WallController {

    Logger logger = org.slf4j.LoggerFactory.getLogger(WallController.class);

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "")
    public ModelAndView wall(HttpServletResponse httpServletResponse) {
        ModelAndView mv;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        mv = new ModelAndView("wall");
        String name = authentication.getName();
        mv.addObject("userName", name);

        return mv;
    }

    @RequestMapping(value = "ajax/filterArticles", method = RequestMethod.GET)
    @ResponseBody
    public List<Map> filterArticles(
            @RequestParam(value = "news") Boolean news,
            @RequestParam(value = "rating") Boolean rating,
            @RequestParam(value = "barLowerBound") Integer barLowerBound,
            @RequestParam(value = "upperBoundInterval") Integer upperBoundInterval,
            @RequestParam(value = "startingSearchPoint") Integer startingSearchPoint) {
        List<Map> articles = articleService.getArticleFiltered(news, rating, barLowerBound, upperBoundInterval,
                startingSearchPoint);
       /* ObjectMapper objectMapper = new ObjectMapper();
            String articlesAsJson = "";
            //The input argument of the writeValueAsString() function can be a bean, array, list, map or a set.
            try {
                articlesAsJson = objectMapper.writeValueAsString(articles);
            } catch (IOException e) {
                e.printStackTrace();
        }*/

            return articles;
    }

    @RequestMapping(value = "ajax/testArticle", method = RequestMethod.GET)
    @ResponseBody
    public Map testArticle() {
        Map map = new HashMap();

        Article article = articleService.getArticle(9);

//        Article simpleArticle = articleService.getSimpleArticle(10);

        map.put("article", article);
//        map.put("simpleArticle", simpleArticle);

        map.put("message", "message");

        return map;
    }

//    public Comparator<Map> mapComparator = new Comparator<Map>() {
//        public int compare(Map m1, Map m2) {
//            if (((Double) m1.get("rating")) < ((Double) m2.get("rating"))) {
//                return 1;
//            } else {
//                return -1;
//            }
//        }
//    };


    @RequestMapping(value = "ajax/getRecommendation", method = RequestMethod.GET)
    @ResponseBody
    public List<Map> getRecommendation() {
        List<Map> articlesList = new ArrayList<Map>();
        List<Integer> recommendedList;
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer userId = userService.getUserIdByUsername(userName);
        recommendedList = userService.getRecommendationByUsername(userName);

        if(recommendedList.size() != 0) {
            articlesList.addAll(articleService.getArticleAndRating(recommendedList));
//            Collections.sort(articlesList, mapComparator);
        }
        else{
            articlesList.addAll( articleService.getMostRatedArticle(10,userId, recommendedList));
        }

        return articlesList;
    }
}
