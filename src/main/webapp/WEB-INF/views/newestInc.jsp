<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="newest-articles" id="newest-article-container">
    <div id="fixed-loader-newest" class="fixed-loader-newest">
        <img src="<c:url value="/resources/img/ajax-loader.gif"/>"/>
    </div>

    <div id="fixed-loader-newest-end" class="fixed-loader-newest">
        <img src="<c:url value="/resources/img/ajax-loader.gif"/>"/>
    </div>
</div>

<div id="sample-article-preview" class="article-preview-container" style="display: none;">
    <div class="article-title"></div>
    <div class="article-text"></div>
    <div class="article-information">
        <div class="article-author"></div>
        <a href="" class="article-reference">Read the full article</a>
    </div>
</div>