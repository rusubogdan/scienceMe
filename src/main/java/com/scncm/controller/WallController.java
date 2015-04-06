package com.scncm.controller;

import com.scncm.model.*;
import com.scncm.service.ArticleService;
import com.scncm.service.TagService;
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
import java.util.ArrayList;
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

    @RequestMapping(value = "")
    public ModelAndView wall(HttpServletResponse httpServletResponse) {
        ModelAndView mv;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        mv = new ModelAndView("wall");

        User loggedInUser = userService.getUserByUsername(authentication.getName());

        mv.addObject("loggedInUser", loggedInUser);

        return mv;
    }

    @RequestMapping(value = "ajax/filterArticles", method = RequestMethod.GET)
    @ResponseBody
    public Map filterArticles(
            @RequestParam(value = "news")                   Boolean news,
            @RequestParam(value = "rating")                 Boolean rating,
            @RequestParam(value = "barLowerBound")          Integer barLowerBound,
            @RequestParam(value = "upperBoundInterval")     Integer upperBoundInterval,
            @RequestParam(value = "startingSearchPoint")    Integer startingSearchPoint) {

        Map map = new HashMap();
        List<Article> articles = articleService.getArticleFiltered(news, rating, barLowerBound, upperBoundInterval,
                startingSearchPoint);
       /* ObjectMapper objectMapper = new ObjectMapper();
            String articlesAsJson = "";
            //The input argument of the writeValueAsString() function can be a bean, array, list, map or a set.
            try {
                articlesAsJson = objectMapper.writeValueAsString(articles);
            } catch (IOException e) {
                e.printStackTrace();
        }*/
        map.put("articles", articles);

        return map;
    }

    @RequestMapping(value = "ajax/testArticle", method = RequestMethod.GET)
    @ResponseBody
    public Map testArticle() {
        Map map = new HashMap();

        Article article = articleService.getArticle(1);

//        Article simpleArticle = articleService.getSimpleArticle(10);

        map.put("article", article.getArticleId());
//        map.put("simpleArticle", simpleArticle);

        map.put("message", "message");

        return map;
    }

    @RequestMapping(value = "ajax/getRecommendation", method = RequestMethod.GET)
    @ResponseBody
    public List<Article> getRecommendation() throws TasteException {
        List<Article> articlesList = new ArrayList<Article>();
        List<Integer> recommendedList = new ArrayList<Integer>();
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByUsername(userName);

        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setServerName("ec2-54-163-228-58.compute-1.amazonaws.com");
        dataSource.setDatabaseName("dfvo2f9it4pmk9");
        dataSource.setUser("ucokaucbriaged");
        dataSource.setPassword("MiL-tB6turQdPKbGwjqrBlFLXP");
        dataSource.setPortNumber(5432);
        dataSource.setSsl(true);
        dataSource.setSslfactory("org.postgresql.ssl.NonValidatingFactory");

        PostgreSQLJDBCDataModel postgreSQLJDBCDataModel =  new PostgreSQLJDBCDataModel(dataSource,"user_article","user_id","article_id","rating","timestamp");
        ReloadFromJDBCDataModel model = new ReloadFromJDBCDataModel(postgreSQLJDBCDataModel);
        ItemSimilarity itemSimilarity =  new LogLikelihoodSimilarity(model);
        ItemBasedRecommender recommender = new GenericItemBasedRecommender(model, itemSimilarity);
        int id = currentUser.getUserId();
        List<RecommendedItem> recommendations = recommender.recommend(id, 5);
        for (RecommendedItem recommendation : recommendations) {
            articlesList.add(articleService.getArticle((int) recommendation.getItemID()));
            recommendedList.add((int)recommendation.getItemID());
        }
        if(recommendations.size() != 5) {
            List<Article> ratedArticleList = articleService.getMostRatedArticle( 5-recommendations.size() , currentUser.getUserId(),recommendedList);
            articlesList.addAll(ratedArticleList);
        }
        return articlesList;
    }
}
