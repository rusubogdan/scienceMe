<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%--CSS and JS--%>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/login.css"/>">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600' rel='stylesheet'
          type='text/css'>
    <link href="//netdna.bootstrapcdn.com/font-awesome/3.1.1/css/font-awesome.css" rel="stylesheet">
    <link href="<c:url value="/resources/css/scienceMe.css"/>" rel="stylesheet"/>

    <script type="application/javascript" src="<c:url value="/resources/js/jquery-2.1.3.min.js"/>"></script>

    <%--it is important to place variables from server before loading the script for the login page--%>
    <script type="application/javascript">
        var showRegistrationForm = ${showRegistrationForm};
    </script>
    <script type="application/javascript" src="<c:url value="/resources/js/home.js"/> "></script>

    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
    <title>Welcome to Science Me !</title>
</head>

<body>
<sec:authorize access="isAnonymous()">
    <form id="login-form" method="post" action="<c:url value='j_spring_security_check'/>">
        <div class="wrap">
            <div class="avatar">
                <img alt="logo" src="<c:url value="/resources/img/atom.png"/> ">
            </div>
            <input type="text" name="j_username" id="j_username" placeholder="username"/>

            <div class="bar">
                <i></i>
            </div>
            <input type="password" name="j_password" id="j_password" placeholder="password"/>

            <button id="sign-in-submit" type="submit" value="Sign in">Sign in</button>

                <%--<div class="bar">--%>
                <%--<i></i>--%>
                <%--</div>--%>

            <div id="registerMe" class="custom-wrap wrap change-form">Register</div>
                <%--<div id="login-facebook" class="custom-wrap wrap fb">Login with Facebook</div>--%>


        </div>
    </form>
    <div class="panel panel-default">
        <div class="panel-body">
            <div class="row social-button-row">
                <div class="col-lg-4">
                    <!-- Add Facebook sign in button -->
                    <a href="${pageContext.request.contextPath}/auth/facebook">
                        <button class="btn btn-facebook">
                            <i class="icon-facebook"></i> | Sign in with Facebook
                        </button>
                    </a>
                </div>
            </div>
        </div>
    </div>
</sec:authorize>
<form id="register-form" method="post" action="<c:url value="/register"/> ">
    <div class="register">
        <div class="avatar">
            <img alt="atom" src="<c:url value="/resources/img/atom.png"/> ">
        </div>

        <label for="email" class="icon"><i class="icon-envelope"></i></label>
        <input type="text" name="email" id="email" placeholder="email"/>

        <label for="username" class="icon"><i class="icon-user"></i></label>
        <input type="text" name="username" id="username" placeholder="username"/>

        <label for="password" class="icon"><i class="icon-shield"></i></label>
        <input type="password" name="password" id="password" placeholder="password"/>


        <%--<div class="gender">
            <input type="radio" value="None" id="male" name="gender" checked/>
            <label for="male" class="radio">Male</label>
            <input type="radio" value="None" id="female" name="gender"/>
            <label for="female" class="radio">Female</label>
        </div>--%>

        <button class="custom-wrap wrap" type="submit" value="register">Register</button>
        <%--<a href="#" class="button" id="logmeIn">Log in</a>--%>
        <div id="logmeIn" class="custom-wrap wrap change-form">Log in</div>
        <div id="register-facebook" class="custom-wrap wrap fb">Register with Facebook</div>

    </div>
</form>
<div class="error-login-register">
    <p>
        <%--@elvariable id="error" type="java"--%>
        <c:if test="${error ne null}">
            <b class="error">${error}</b>
        </c:if>
    </p>
</div>
<div class="desc" id="description">
    <p>some videos and description of the site will be placed here</p>
</div>
</body>
</html>