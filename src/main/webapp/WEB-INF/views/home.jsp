<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%--CSS and JS--%>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/login.css"/>">
    <script type="application/javascript" src="<c:url value="/resources/js/firstPage.js"/> "></script>

    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
    <title>Welcome to Science Me !</title>
</head>


<body>

<form method="post" action="<c:url value='j_spring_security_check'/>" id="loginform">
    <div class="wrap">

        <div class="avatar">
            <img src="http://www.mbari.org/earth/images/atom.png">
        </div>

        <input type="text" name="j_username" id="j_username" placeholder="username"/></td>
        <div class="bar">
            <i></i>
        </div>

        <input type="password" name="j_password" id="j_password" placeholer="password"/></td>

        <button type="submit" value="Sign in">Sign in</button>
        <div class="bar">
            <i></i>
        </div>
        <button type="button" onclick="show()"> Register? </button>

        <p>
            <c:if test="${error == true}">
                <b class="error">Invalid username or password.</b>
            </c:if>

            <c:if test="${msg ne null}">
                <b class="logout-success">${msg}</b>
            </c:if>

            <c:if test="${notAllowed ne null}">
                <b class="not-allowed">${notAllowed}</b>
            </c:if>
        </p>

    </div>
</form>
<form method="post" action="<c:url value='j_spring_security_check'/>" id="registerform">
    <br>
    <br>
    <br>
    <br>
    <div class="register" >
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600' rel='stylesheet' type='text/css'>
        <link href="//netdna.bootstrapcdn.com/font-awesome/3.1.1/css/font-awesome.css" rel="stylesheet">

        <form action="/">
            <div class="avatar">
                <img src="http://www.mbari.org/earth/images/atom.png">
            </div>
            <br>

            <label class="icon" ><i class="icon-envelope "></i></label>
            <input type="text" name="j_addEmail" id="j_addEmail"  placeholder="Email"  />
            <label class="icon" ><i class="icon-user">     </i></label>
            <input type="text" name="j_addFirstname" id="j_addFirstname" placeholder="First Name"  />
            <label class="icon" ><i class="icon-user"></i></label>
            <input type="text" name="j_addLastname" id="j_addLastname" placeholder="Last Name"  />
            <label class="icon" ><i class="icon-shield"></i></label>
            <input type="password" name="j_addPassword" id="j_addPassword" placeholder="Password"   />
            <div class="gender">
                    <input type="radio" value="None" id="male" name="gender" checked/>
                    <label for="male" class="radio"  >Male</label>
                    <input type="radio" value="None" id="female" name="gender" />
                    <label for="female" class="radio">Female</label>
            </div>

            <a href="#" class="button">Register</a>
            <a href="#" class="button" onclick="hide()">Log in?</a>

        </form>
    </div>


</form>
</body>
</html>