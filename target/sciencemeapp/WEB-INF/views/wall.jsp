<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>wall</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <%--main stylesheet--%>
    <link href="<c:url value="/resources/css/bootstrap.min.css"/> " rel="stylesheet"/>
    <link href="<c:url value="/resources/css/bootstrap-theme.min.css"/> " rel="stylesheet"/>
    <link href="<c:url value="/resources/css/scienceMe.css"/> " rel="stylesheet"/>

    <script src="<c:url value="/resources/js/jquery-2.1.3.min.js"/> " rel="stylesheet"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/> " rel="stylesheet"></script>
    <script src="<c:url value="/resources/js/wall.js"/> " rel="stylesheet"></script>
    <!-- pentru carousel -->
    <script type="text/javascript" src="//cdn.jsdelivr.net/jquery.slick/1.4.1/slick.min.js"></script>
    <link href="<c:url value="/resources/css/slick.css"/> " rel="stylesheet"/>
    <link href="<c:url value="/resources/css/slick-theme.css"/> " rel="stylesheet"/>


</head>

<body class="wallpaper">
<%-----------header-----------%>
<jsp:include page="header.jsp"/>
<%----------------------------%>

<div class="container">
    <div class="row">
        <div class="col-md-1">
            <div class="sidebar-nav-fixed affix wall-left-sidebar">
                <div class="well transparent-sidebar">
                    <ul class="nav ">
                        <li class="nav-header">Sidebar</li>
                        <li><a href="#">Link</a></li>
                        <li><a href="#">Link</a></li>
                    </ul>
                </div>
                <!--/.well -->
            </div>
            <!--/sidebar-nav-fixed -->
        </div>
        <!--/span-->
        <div class="col-md-9 wall-content-container">
            <div class="jumbotron">
                <jsp:include page="articles/carousel.inc.jsp"/>
                <jsp:include page="articles/newest.inc.jsp"/>

                <h1>Hello, world!</h1>


                <p>This is a template for a simple marketing or informational website. It
                    includes a large callout called the hero unit and three supporting pieces
                    of content. Use it<br/><br/><br/><br/><br/><br/><br/>
                    <br/><br/><br/><br/><br/><br/><br/> as a starting point to create something more unique.
                    <br/><br/><br/><br/>
                    <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
                    <br/><br/><br/><br/>
                    <br/><br/><br/><br/>
                    <br/><br/><br/><br/>
                </p>

                <p id="test1Button"><a class="btn btn-primary btn-lg">Learn more »</a></p>
            </div>
        </div>
        <!--/span-->
        <div class="col-md-2">
            <div class="sidebar-nav-fixed pull-right affix wall-right-sidebar">
                <div class="well transparent-sidebar">
                    <ul class="nav ">
                        <li class="nav-header">Sidebar</li>
                        <li class="active"><a href="#">Link</a>
                        </li>
                        <li><a href="#">Link</a>
                        </li>
                    </ul>
                </div>
                <!--/.well -->
            </div>
            <!--/sidebar-nav-fixed -->
        </div>
        <!--/span-->
    </div>
    <!--/row-->
</div>
<!--/.fluid-container-->


<%-----------footer-----------%>
<jsp:include page="footer.jsp"/>
<%----------------------------%>
</body>
