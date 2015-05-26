<%--@elvariable id="loggedInUser" type="com.scncm.model.User"--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Profile</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <%--main stylesheet--%>
    <link href="<c:url value="/resources/css/bootstrap.min.css"/> " rel="stylesheet"/>
    <link href="<c:url value="/resources/css/bootstrap-theme.min.css"/> " rel="stylesheet"/>
    <link href="<c:url value="/resources/css/scienceMe.css"/> " rel="stylesheet"/>

    <script src="<c:url value="/resources/js/jquery-2.1.3.min.js"/> " rel="stylesheet"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/> " rel="stylesheet"></script>

</head>

<body>
<jsp:include page="header.jsp"/>

<div class="container bg-info">
    <div class="user-profile">

        <div class="row">

            <div class="col-sm-4 text-center ">

                <img src="<c:url value="/resources/img/helix2.png"/>"
                     class="img-rounded" alt="Profile" width="300" height="300">


            </div>

            <div class="col-sm-8">
                <div class="username">

                    <div class="panel panel-primary ">
                        <div class="panel-heading">
                            <h3 class="panel-title">Username</h3>
                        </div>
                        <div class="panel-body">
                            ${loggedInUser.username}



                        </div>
                    </div>

                </div>

                <div class="panel panel-primary ">
                    <div class="panel-heading">
                        <h3 class="panel-title">Email</h3>
                    </div>
                    <div class="panel-body">
                        ${loggedInUser.email}
                    </div>
                </div>
                <div class="panel panel-primary ">
                    <div class="panel-heading">
                        <h3 class="panel-title">Register Date</h3>
                    </div>
                    <div class="panel-body">
                        ${loggedInUser.registerDate}
                    </div>
                </div>

                <div class="panel panel-primary ">
                    <div class="panel-heading">
                        <h3 class="panel-title">Contributions to the site</h3>
                    </div>
                    <div class="panel-body">
                            <c:forEach items="${userArticles}" var="item">
                                <a href="/article/view/${item.token}">${item.title}</a><br>
                            </c:forEach>
                    </div>
                </div>

            </div>



        </div>





    </div>
</div>

<!-- <jsp:include page="footer.jsp"/>-->

</body>