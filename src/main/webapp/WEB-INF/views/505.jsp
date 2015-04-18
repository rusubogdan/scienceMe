<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>505 - server error</title>
    <style>
        @import url(http://fonts.googleapis.com/css?family=Bree+Serif|Source+Sans+Pro:300,400);
        *{
            maring: 0;
            padding: 0;
        }
        body{
            font-family: 'Source Sans Pro', sans-serif;
            background: #3f6eb3;
            color: #1f3759;
        }
        a:link{
            color: #1f3759;
            text-decoration: none;
        }
        a:active{
            color: #1f3759;
            text-decoration: none;
        }
        a:hover{
            color: #9fb7d9;
            text-decoration: none;
        }
        a:visited{
            color: #1f3759;
            text-decoration: none;
        }
        a.underline, .underline{
            text-decoration: underline;
        }
        .bree-font{
            font-family: 'Bree Serif', serif;
        }
        #content{
            margin: 0 auto;
            width: 960px;
        }
        .clearfix:after {
            content: ".";
            display: block;
            clear: both;
            visibility: hidden;
            line-height: 0;
            height: 0;
        }
        .clearfix {
            display: block;
        }
        #logo {
            margin: 1em;
            float: left;
            display: block;
        }
        nav{
            float: right;
            display: block;
        }
        nav ul > li{
            list-style: none;
            float: left;
            margin: 0 2em;
            display: block;
        }
        #main-body{
            text-align: center;
        }
        .enormous-font{
            font-size: 10em;
            margin-bottom: 0em;
        }
        .big-font{
            font-size: 2em;
        }
        hr{
            width: 25%;
            height: 1px;
            background: #1f3759;
            border: 0px;
        }
    </style>
</head>
<body>
<div id="content">
    <header>
        <div id="logo">

            <img alt="logo" src="<c:url value="/resources/img/atom.png"/> ">
        </div>
        <nav>
            <ul>
                <li><a href="/">Home</a></li>
            </ul>
        </nav>
    </header>

    <div class="clearfix"></div>

    <div id="main-body">
        <p class="enormous-font bree-font"> 505 </p>
        <p class="big-font"> Ooops. Server error... </p>
        <hr>
        <p class="big-font"> Let's return to the   <a href="/" class="underline">home page.</a></p>
    </div>
</div>
</body>
</html>