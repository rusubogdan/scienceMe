package com.scncm.controller;

import com.scncm.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
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

        return map;
    }

    @RequestMapping(value = "ajax/getRecommendation", method = RequestMethod.GET)
    @ResponseBody
    public List<Article> getRecommendation() throws TasteException {
            List<Article> articlesList = new ArrayList<Article>();

        PGSimpleDataSource dataSource = new PGSimpleDataSource();
//        dataSource.setServerName("ec2-54-163-228-58.compute-1.amazonaws.com");
//        dataSource.setDatabaseName("dfvo2f9it4pmk9");
//        dataSource.setUser("ucokaucbriaged");
//        dataSource.setPassword("MiL-tB6turQdPKbGwjqrBlFLXP");
//        dataSource.setPortNumber(5432);
//        dataSource.setSsl(true);
//        dataSource.setSslfactory("org.postgresql.ssl.NonValidatingFactory");

        dataSource.setServerName("ec2-50-17-202-29.compute-1.amazonaws.com");
        dataSource.setDatabaseName("d6ef4r6bet13qq");
        dataSource.setUser("ezsnwwozpbvurl");
        dataSource.setPassword("ANZJCP71_D8hLT0hAZDhcZvteg");
        dataSource.setPortNumber(5432);
        dataSource.setSslfactory("org.postgresql.ssl.NonValidatingFactory");
        dataSource.setSsl(true);

        PostgreSQLJDBCDataModel postgreSQLJDBCDataModel =  new PostgreSQLJDBCDataModel(dataSource,"user_article","user_id","article_id","rating","timestamp");
        ReloadFromJDBCDataModel model = new ReloadFromJDBCDataModel(postgreSQLJDBCDataModel);
        ItemSimilarity itemSimilarity =  new LogLikelihoodSimilarity(model);
        ItemBasedRecommender recommender = new GenericItemBasedRecommender(model, itemSimilarity);
        List<RecommendedItem> recommendations = recommender.recommend(4, 5);
        if(recommendations.size() != 0) {
            for (RecommendedItem recommendation : recommendations) {
                System.out.println(recommendation);
                articlesList.add(articleService.getArticle((int) recommendation.getItemID()));
            }
        }
        else{
            articlesList = articleService.getMostRatedArticle(5);
        }
        return articlesList;
    }
}
