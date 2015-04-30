<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>
        ${articleTitle}
    </title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <%--main stylesheet--%>
    <link href="<c:url value="/resources/css/bootstrap.min.css"/> " rel="stylesheet"/>
    <link href="<c:url value="/resources/css/bootstrap-theme.min.css"/> " rel="stylesheet"/>
    <link href="<c:url value="/resources/css/scienceMe.css"/> " rel="stylesheet"/>
    <link href="<c:url value="/resources/css/article.css"/> " rel="stylesheet"/>

    <script src="<c:url value="/resources/js/jquery-2.1.3.min.js"/> " rel="stylesheet"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/> " rel="stylesheet"></script>

    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="/js/addArticle.js" type="text/javascript"></script>

    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/resources/css/star-rating.css" media="all" rel="stylesheet" type="text/css"/>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="/resources/js/star-rating.js" type="text/javascript"></script>
</head>
<body class="wallpaper">
    <jsp:include page="header.jsp"/>
    <br><br>

    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">

            <div class="container-view-article">
                <input id="rating-input" type="number" value="${my_vote}"/>
                <span class="pull-right"> Time to read: ${articleTimeRead} </span>
                <hr>

                <h1 class="text-center">
                    ${articleTitle}
                </h1>
                <div>
                    ${articleHtml}
                </div>
            </div>
        </div>
        <div class="col-md-2"></div>

    </div>

    <script>
        jQuery(document).ready(function () {
            $('#rating-input').rating({
                min: 0,
                max: 5,
                step: 1,
                size: 'xs'
            });

            $('#btn-rating-input').on('click', function() {
                var $a = self.$element.closest('.star-rating');
                var chk = !$a.hasClass('rating-disabled');
                $('#rating-input').rating('refresh', {showClear:!chk, disabled:chk});
            });


            $('.btn-danger').on('click', function() {
                $("#kartik").rating('destroy');
            });

            $('.btn-success').on('click', function() {
                $("#kartik").rating('create');
            });

            $('#rating-input').on('rating.change', function() {
                alert($('#rating-input').val());

                $.post('/article/ajax/vote', {"vote": $('#rating-input').val(),"id_user": "${id_user}", "id_article": "${id_article}"},
                        function (response) {
//                list of objects of type article and integer
                            var articlesMap = response;
                            console.log("ajunge" + response);
                        }
                );

            });


            $('.rb-rating').rating({'showCaption':true, 'stars':'3', 'min':'0', 'max':'3', 'step':'1', 'size':'xs', 'starCaptions': {0:'status:nix', 1:'status:wackelt', 2:'status:geht', 3:'status:laeuft'}});
        });
    </script>
</body>
</html>




