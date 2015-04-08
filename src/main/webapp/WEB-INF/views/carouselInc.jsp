<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="articles-carousel" class="articles-carousel">
    <div id="fixed-loader-carousel" class="article-preview-container">
        <div class="article-title"></div>
        <div class="article-text fixed-loader-carousel">
            <img src="<c:url value="/resources/img/ajax-loader.gif"/>"/>
        </div>
        <div class="article-information">
            <div class="article-author"></div>
            <a href="" class="article-reference"></a>
        </div>
    </div>
</div>

<%--this is the sample; when used, the id will be change and the div will be shown !!! --%>
<%--<div id="sample-article-container" class="article-preview-container" style="display: none;">--%>
    <%--<div class="article-title"></div>--%>
    <%--<div class="article-text"></div>--%>
    <%--<div class="article-information">--%>
        <%--<div class="article-rating">--%>
            <%--<span></span>--%>
        <%--</div>--%>
        <%--<div class="article-author">--%>
            <%--<a href="" class="author-reference"></a>--%>
        <%--</div>--%>
        <%--<a href="" class="article-reference">Read the full article</a>--%>
    <%--</div>--%>
<%--</div>--%>

<div id="sample-article-container" class="article-preview-container" style="display: none;">
        <div class="image-holder">
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
