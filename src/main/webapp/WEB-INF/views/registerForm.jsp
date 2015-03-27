<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%--CSS and JS--%>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/register.css"/>">
    <script type="application/javascript" src="<c:url value="/resources/js/firstPage.js"/> "></script>

    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
    <title>Register page</title>
</head>

    <body>

    <div class="registerForm">
        <h1>Register page</h1>

        <form action="/">

            <label id="icon" for="j_email"><i class="icon-envelope "></i></label>
                <input type="text" name="j_email" id="j_email" placeholder="Email" required/>
            <label id="icon" for="j_firstname"><i class="icon-user"></i></label>
                 <input type="text" name="j_firstname" id="j_firstname" placeholder="First Name" required/>
            <label id="icon" for="j_lastname"><i class="icon-user"></i></label>
                <input type="text" name="j_lastname" id="j_lastname" placeholder="Last Name" required/>
            <label id="icon" for="j_password"><i class="icon-shield"></i></label>
                <input type="password" name="j_password" id="j_password" placeholder="Password" required/>
            <div class="gender">
                <input type="radio" value="None" id="male" name="gender" checked/>
                <label for="male" class="radio"  >Male</label>
                <input type="radio" value="None" id="female" name="gender" />
                <label for="female" class="radio">Female</label>
            </div>

            <a href="#" class="button">Register</a>
        </form>
    </div>

    <p>
        <a href="${pageContext.request.contextPath}/index.html">Home page</a><br/>
    </p>

    </body>
</html>
