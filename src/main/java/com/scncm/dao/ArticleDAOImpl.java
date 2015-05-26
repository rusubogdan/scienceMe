package com.scncm.dao;

import com.scncm.model.Article;
import com.scncm.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.security.Timestamp;
import java.util.*;

@Repository
public  class ArticleDAOImpl implements ArticleDAO {

    private static final Logger logger = LoggerFactory.getLogger(ArticleDAOImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Article getArticle(Integer articleId) {
        return (Article) getCurrentSession().load(Article.class, articleId);
    }

    public Article getSimpleArticle(Integer articleId) {
        List<Article> simpleArticles = new ArrayList<Article>();
        Query query;

        try {
            query = getCurrentSession().createQuery(
                    "select new Article(a.articleId, a.title, a.description, a.owner, a.readingTime, " +
                            "a.link, a.createdDate)" +
                            "from Article a " +
                            "where a.articleId = :articleId"
            );
            query.setParameter("articleId", articleId);
            simpleArticles = query.list();
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }

        if (simpleArticles.size() > 0) {
            return simpleArticles.get(0);
        } else {
            return null;
        }
    }

    public Article getArticleByToken(String token) {
        List<Article> articles = new ArrayList<Article>();
        Query query;
        try {
            query = getCurrentSession().createQuery("from Article a where a.token = :token");
            query.setParameter("token", token);
            articles = query.list();
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }

        if (articles.size() > 0) {
            return articles.get(0);
        } else {
            return null;
        }
    }

    public List<Article> getArticlesByUser(User user) {
        List<Article> articles = new ArrayList<Article>();
        Query query;
        try {
            query = getCurrentSession().createQuery("from Article a where a.owner = :user");
            query.setParameter("user", user);
            articles = query.list();
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }

        if (articles.size() > 0) {
            return articles;
        } else {
            return null;
        }
    }

    public Set<Article> searchArticles(String searchQuery) {
        List<Article> articles = new ArrayList<Article>();
        Query query = null;

        try {
            query = getCurrentSession().createQuery("from Article a where a.title like :searchQuery " +
                    "or a.description like :searchQuery");
            query.setParameter("searchQuery", '%' + searchQuery + '%');
            articles = query.list();
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }

        Set<Article> articleSet = new HashSet<Article>(articles);

        if (articleSet.size() > 0)
            return articleSet;
        else
            return null;
    }

    public List<Map> getArticleFiltered(Boolean news, Boolean rating, Integer barLowerBound,
                                        Integer barUpperBound, Integer startingSearchPoint) {
        List<Map> articles = new ArrayList<Map>();
        List<Object[]> temporaryArticles;
        Query query = null;
        if (!news && rating) {
            query = getCurrentSession().createSQLQuery(
                    "select A.title, A.description, A.owner_id, " +
                            "(Select U.username " +
                            "from users U " +
                            "where U.id = A.owner_id)," +
                            "A.reading_time, A.created_date, A.token," +
                                "(select coalesce(avg(cast(NULLIF(UA.rating, 0) AS BIGINT)), 0)" +
                                "from user_article UA " +
                                "where UA.article_id = A.article_id) as rating," +
                            "coalesce (nullif (A.image_link, ''), 'http://www.mbari.org/earth/images/atom.png') " +
                    "from article A " +
                    "where A.reading_time between :barLowerBound and :barUpperBound" +
                    " ORDER BY rating desc");
            query.setParameter("barLowerBound", barLowerBound);
            query.setParameter("barUpperBound", barUpperBound);
            query.setFirstResult(startingSearchPoint);
            query.setMaxResults(10);
            temporaryArticles = query.list();
            for (int i = 0; i < temporaryArticles.size(); i++) {
                Map temporaryMap = new HashMap<>();
                temporaryMap.put("title",temporaryArticles.get(i)[0]);
                temporaryMap.put("description",temporaryArticles.get(i)[1]);
                temporaryMap.put("ownerId",temporaryArticles.get(i)[2]);
                temporaryMap.put("ownerUsername",temporaryArticles.get(i)[3]);
                temporaryMap.put("readingTime",temporaryArticles.get(i)[4]);
                temporaryMap.put("createdDate",temporaryArticles.get(i)[5]);
                temporaryMap.put("token",temporaryArticles.get(i)[6]);
                if (temporaryArticles.get(i)[7] == null) {
                    temporaryMap.put("rating", 0);
                } else {
                    temporaryMap.put("rating", temporaryArticles.get(i)[7]);
                }
                temporaryMap.put("imageLink", temporaryArticles.get(i)[8]);
                articles.add(temporaryMap);
            }
        } else {
            if (news && !rating) {
                query = getCurrentSession().createSQLQuery(
                        "select A.title, A.description, A.owner_id, " +
                                "(select U.username " +
                                "from users U " +
                                "where U.id = A.owner_id), " +
                                "A.reading_time, A.created_date, A.token, " +
                                "(select coalesce(avg(cast(NULLIF(UA.rating, 0) AS BIGINT)), 0) " +
                                "from user_article UA " +
                                "where UA.article_id = A.article_id) as rating, " +
                                "coalesce (nullif (A.image_link, ''), 'http://www.mbari.org/earth/images/atom.png') " +
                        "from article A " +
                        "where A.reading_time between :barLowerBound and :barUpperBound " +
                        "ORDER BY A.created_date desc");
                query.setParameter("barLowerBound", barLowerBound);
                query.setParameter("barUpperBound", barUpperBound);
                query.setFirstResult(startingSearchPoint);
                query.setMaxResults(10);
                temporaryArticles = query.list();
                for (int i = 0; i < temporaryArticles.size(); i++) {
                    Map temporaryMap = new HashMap<>();
                    temporaryMap.put("title",temporaryArticles.get(i)[0]);
                    temporaryMap.put("description",temporaryArticles.get(i)[1]);
                    temporaryMap.put("ownerId",temporaryArticles.get(i)[2]);
                    temporaryMap.put("ownerUsername",temporaryArticles.get(i)[3]);
                    temporaryMap.put("readingTime",temporaryArticles.get(i)[4]);
                    temporaryMap.put("createdDate",temporaryArticles.get(i)[5]);
                    temporaryMap.put("token",temporaryArticles.get(i)[6]);
                    if (temporaryArticles.get(i)[7] == null) {
                        temporaryMap.put("rating", 0);
                    } else {
                        temporaryMap.put("rating", temporaryArticles.get(i)[7]);
                    }
                    temporaryMap.put("imageLink", temporaryArticles.get(i)[8]);
                    articles.add(temporaryMap);
                }
            }
        }

        if (articles.size() > 0) {
            return articles;
        } else {
            return null;
        }

    }

    public List<Map> getMostRatedArticle(Integer numberOfArticle, Integer userId, List<Integer> recommendedList) {
        List<Map> articles = new ArrayList<Map>();
        List<Object[]> temporaryArticles;
        Query query;
        String hibernateQuery =
                "select A.title,A.description,A.owner_id," +
                        "(Select U.username " +
                        "from users U " +
                        "where U.id = A.owner_id)," +
                        "A.reading_time,A.created_date,A.token," +
                        "(select coalesce(avg(cast(NULLIF(UA.rating, 0) AS BIGINT)), 0)" +
                        "from user_article UA " +
                        "where UA.article_id = A.article_id) as rating," +
                        "coalesce (nullif (A.image_link, ''), 'http://www.mbari.org/earth/images/atom.png') " +
                "from article A " +
                "where A.owner_id != :userId and A.article_id not in (select UA.article_id " +
                                                                     "from user_article UA " +
                                                                     "where UA.user_id = :userId)";
        if (recommendedList.size() != 0) {
            hibernateQuery += " and A.article_id not in (:recommendedList) order by rating desc";
            query = getCurrentSession().createQuery(hibernateQuery);
            query.setParameterList("recommendedList", recommendedList);
        } else {
            hibernateQuery += "order by rating desc";
            query = getCurrentSession().createSQLQuery(hibernateQuery);
        }
        query.setMaxResults(numberOfArticle);
        query.setParameter("userId", userId);
        temporaryArticles = query.list();

        for (int i = 0; i < temporaryArticles.size(); i++) {
            Map temporaryMap = new HashMap<>();
            temporaryMap.put("title",temporaryArticles.get(i)[0]);
            temporaryMap.put("description",temporaryArticles.get(i)[1]);
            temporaryMap.put("ownerId",temporaryArticles.get(i)[2]);
            temporaryMap.put("ownerUsername",temporaryArticles.get(i)[3]);
            temporaryMap.put("readingTime",temporaryArticles.get(i)[4]);
            temporaryMap.put("createdDate",temporaryArticles.get(i)[5]);
            temporaryMap.put("token",temporaryArticles.get(i)[6]);
            if (temporaryArticles.get(i)[7] == null) {
                temporaryMap.put("rating", 0);
            } else {
                temporaryMap.put("rating", temporaryArticles.get(i)[7]);
            }
            temporaryMap.put("imageLink", temporaryArticles.get(i)[8]);
            articles.add(temporaryMap);
        }
        return articles;
    }

    public List<Map> getArticleAndRating(List<Integer> recommendedList) {
        List<Map> articleAndRating = new ArrayList<>();
        Query query;
        List<Object[]> temporaryArticles;

        query = getCurrentSession().createSQLQuery(
                "select A.title,A.description,A.owner_id," +
                        "(Select U.username " +
                        "from users U" +
                        " where U.id = A.owner_id)" +
                        ",A.reading_time,A.created_date,A.token," +
                        "(select coalesce(avg(cast(NULLIF(UA.rating, 0) AS BIGINT)), 0)" +
                        "from user_article UA " +
                        "where UA.article_id = A.article_id) as rating," +
                        "coalesce (nullif (A.image_link, ''), 'http://www.mbari.org/earth/images/atom.png') " +
                        "from article A where " +
                                        "A.article_id in (:recommendedList) " +
                        "order by rating desc");
        query.setParameterList ("recommendedList", recommendedList);
        temporaryArticles = query.list();
        for (int i = 0; i < temporaryArticles.size(); i++) {
            Map temporaryMap = new HashMap<>();
            temporaryMap.put("title",temporaryArticles.get(i)[0]);
            temporaryMap.put("description",temporaryArticles.get(i)[1]);
            temporaryMap.put("ownerId",temporaryArticles.get(i)[2]);
            temporaryMap.put("ownerUsername",temporaryArticles.get(i)[3]);
            temporaryMap.put("readingTime",temporaryArticles.get(i)[4]);
            temporaryMap.put("createdDate",temporaryArticles.get(i)[5]);
            temporaryMap.put("token",temporaryArticles.get(i)[6]);
            if (temporaryArticles.get(i)[7] == null) {
                temporaryMap.put("rating", 0);
            } else {
                temporaryMap.put("rating", temporaryArticles.get(i)[7]);
            }
            temporaryMap.put("imageLink", temporaryArticles.get(i)[8]);
            articleAndRating.add(temporaryMap);
        }
        return articleAndRating;
    }

    public Integer addArticle(Article article) {
        Integer articleId = (Integer) getCurrentSession().save(article);

        return articleId;
    }

    public Boolean updateArticle(Article article) {
        try {
            getCurrentSession().update(article);
//            getCurrentSession().getTransaction().commit();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean deleteArticle(Article article) {
        try {
            getCurrentSession().delete(article);
//            getCurrentSession().getTransaction().commit();

            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public Integer verifyIfUserVoteArticle(Integer user_id, Integer article_id){
      List<Integer> rating = new ArrayList<Integer>();
        Query query;

        try {
            query = getCurrentSession().createSQLQuery(
                    "select rating " +
                            "from user_article as a " +
                            "where a.article_id = (:article_id) " +
                            "and a.user_id = (:user_id) ");

            query.setParameter ("user_id", user_id);
            query.setParameter ("article_id", article_id);

            rating = query.list();
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }

        if (rating.size() > 0) {
            return rating.get(0);
        } else {
            return -1;
        }

    }

    public void insertOrUpdeteVoteArtcile(Integer user_id, Integer article_id, Integer rating){
        List<Integer> id = new ArrayList<Integer>();
        Query query;

        try {
            query = getCurrentSession().createSQLQuery(
                    "select id " +
                            "from user_article as a " +
                            "where a.article_id = (:article_id) " +
                            "and a.user_id = (:user_id) ");

            query.setParameter ("user_id", user_id);
            query.setParameter ("article_id", article_id);

            id = query.list();
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }

        if (id.size() > 0) {
            query = getCurrentSession().createSQLQuery(
              "update user_article " +
                      "set rating = :rating " +
                      "where user_id = :user_id " +
                      "and article_id = :article_id"
            );
        } else {
            Date insert_date = new java.sql.Timestamp(new Date().getTime());
            query = getCurrentSession().createSQLQuery(
                    "insert into user_article (user_id,article_id, rating, timestamp) " +
                            " values(:user_id, :article_id, :rating, :insert_date) "
            );

            query.setParameter ("insert_date", insert_date);
        }

        query.setParameter ("user_id", user_id);
        query.setParameter ("article_id", article_id);
        query.setParameter ("rating", rating);

        query.executeUpdate();


    }
}
