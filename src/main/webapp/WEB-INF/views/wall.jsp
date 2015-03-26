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

    </head>

<body class="wallpaper">
<%-----------header-----------%>
<jsp:include page="header.jsp"/>
<%----------------------------%>

<%--header--%>
<%--
<div class="navbar navbar-fixed-top navbar-default">
    <div class="container">
        <div class="navbar-header header-search" style="width: 80%">
            <a class="navbar-brand" href="/">home</a>

            <div class="col-sm-9 col-md-9 pull-right">
                <form class="navbar-form" role="search">
                    <div class="input-group search-input">
                        <input type="text" class="form-control" placeholder="Search" name="srch-term" id="srch-term"/>

                        <div class="input-group-btn">
                            <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i>
                            </button>
                        </div>
                    </div>
                </form>
            </div>

            <a class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="glyphicon glyphicon-bar"></span>
                <span class="glyphicon glyphicon-bar"></span>
                <span class="glyphicon glyphicon-bar"></span>
            </a>
        </div>
        <div class="navbar-collapse pull-right">
            <ul class="nav navbar-nav">
                <li><a href="/user/${loggedInUser.username}">${loggedInUser.username}</a></li>
                <li><a href="/j_spring_security_logout">logout</a></li>
            </ul>
        </div>
        <!--/.navbar-collapse -->
    </div>
</div>
--%>


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
