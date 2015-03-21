<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/scienceMe.css"/> ">
    <title>Home page</title>
</head>
<body>
<h1>Home page</h1>

<p>this will be the first page to appear to an user<br/>
    after login a redirect to the wall will take place <br/>
    <a href="${pageContext.request.contextPath}/login">Login</a><br/>
</p>
</body>
</html>
