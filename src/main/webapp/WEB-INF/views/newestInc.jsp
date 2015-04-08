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
    <div class="image-holder">
        <img src="<c:url value="/resources/img/atom.png"/> "/>
    </div>
    <div class="content-holder">
        <div class="article-title"></div>
        <div class="bar"></div>
        <div class="article-text"></div>
        <div class="article-information">
            <div class="article-rating">
                <span></span>
            </div>
            <div class="article-author">
                <a href="" class="author-reference"></a>
            </div>
            <div class="article-link">
                <a href="" class="article-reference">Read the full article</a>
            </div>
        </div>
    </div>

</div>