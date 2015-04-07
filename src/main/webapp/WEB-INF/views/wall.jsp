<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>wall</title>

    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/img/atom.png"/> " />

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
    <script src="<c:url value="/resources/js/jquery.range.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/> " rel="stylesheet"></script>
    <script src="<c:url value="/resources/js/slick.min.js"/>" type="text/javascript" rel="stylesheet"></script>

    <%--always the last included script !!!--%>
    <script src="<c:url value="/resources/js/wall.js"/> " rel="stylesheet"></script>

</head>

<body class="wallpaper">
<%-----------header-----------%>
<jsp:include page="header.jsp"/>
<%----------------------------%>

<div class="container">
    <div class="row">
        <%-----------leftSideBar-----------%>
        <jsp:include page="leftSideBar.jsp"/>
        <%---------------------------------%>
        <div class="col-md-9 wall-content-container col-md-pull-1">
            <div class="jumbotron">
                <jsp:include page="carouselInc.jsp"/>
                <jsp:include page="newestInc.jsp"/>

                <p id="learn-more-button" class="btn btn-primary">Learn more »</p>
            </div>
        </div>
    </div>
</div>

<%-----------footer-----------%>
<jsp:include page="footer.jsp"/>
<%----------------------------%>
</body>

