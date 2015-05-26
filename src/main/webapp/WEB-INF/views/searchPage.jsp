<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
    <title>wall</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link href="<c:url value="/resources/css/bootstrap.min.css"/> " rel="stylesheet"/>
    <link href="<c:url value="/resources/css/bootstrap-theme.min.css"/> " rel="stylesheet"/>
    <link href="<c:url value="/resources/css/jquery.range.css"/> " rel="stylesheet">
    <!-- for carousel -->
    <link href="<c:url value="/resources/css/slick.css"/> " rel="stylesheet"/>
    <link href="<c:url value="/resources/css/slick-theme.css"/> " rel="stylesheet"/>

    <%--always the last included style !!!--%>
    <link href="<c:url value="/resources/css/scienceMe.css"/> " rel="stylesheet"/>

    <script src="<c:url value="/resources/js/jquery-2.1.3.min.js"/> " rel="stylesheet"></script>
    <script src="<c:url value="/resources/js/jquery.range.js"/>" rel="stylesheet"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/> " rel="stylesheet"></script>
    <script src="<c:url value="/resources/js/slick.min.js"/>" rel="stylesheet"></script>

    <%--always the last included script !!!--%>
    <script src="<c:url value="/resources/js/search.js"/> " rel="stylesheet"></script>


</head>

<body>
    <%-----------header-----------%>
    <jsp:include page="header.jsp"/>
    <%----------------------------%>
    <div class="container">
        <div class="row row-centered">
            <div class="col-md-3 col-md-pull-1 ">
            </div>
            <div class="col-md-9 col-centered wall-content-container col-md-pull-1 container">
                <div class="jumbotron">
<div class="newest-articles" id="newest-article-container">
                        <c:forEach items="${articleList}" var="article">
                        <div id="sample-article-preview" class="article-preview-container">
                            <div class="image-holder" style="background: url(${article.imageLink}) 50% 50% no-repeat;">
                            </div>
                            <div class="content-holder">
                                <div class="article-title">${article.title}</div>
                                <div class="bar"></div>
                                <div class="article-text">${article.description}</div>
                                <div class="article-information">
                                    <div class="article-rating">
                                        <span>${article.userArticleVoteSet}/5</span>
                                    </div>
                                    <div class="article-author">
                                        <a href="/user/${article.owner.username}" class="author-reference">${article.owner.username}</a>
                                    </div>
                                    <div class="article-link">
                                        <a href="/article/view/${article.token}" class="article-reference">Read the full article</a>
                                    </div>
                                </div>
                            </div>

                        </div>
                        </c:forEach>
</div>
                </div>
            </div>
        </div>
    </div>


    <%-----------footer-----------%>
    <jsp:include page="footer.jsp"/>
    <%----------------------------%>
</body>