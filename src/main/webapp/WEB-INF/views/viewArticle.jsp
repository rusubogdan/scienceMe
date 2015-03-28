<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>
        ${title_view}
    </title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <%--main stylesheet--%>
    <link href="<c:url value="/resources/css/bootstrap.min.css"/> " rel="stylesheet"/>
    <link href="<c:url value="/resources/css/bootstrap-theme.min.css"/> " rel="stylesheet"/>
    <link href="<c:url value="/resources/css/scienceMe.css"/> " rel="stylesheet"/>
    <link href="<c:url value="/resources/css/article.css"/> " rel="stylesheet"/>

    <script src="<c:url value="/resources/js/jquery-2.1.3.min.js"/> " rel="stylesheet"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/> " rel="stylesheet"></script>
    <script src="<c:url value="/resources/js/wall.js"/> " rel="stylesheet"></script>
    <

</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>

    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <div class="container-view-article">
                <br><br>

                <h1 class="text-center">
                    ${title}
                </h1>

                <br> <br>
                ${description}
            </div>
        </div>
        <div class="col-md-2"></div>

    </div>
</body>
</html>