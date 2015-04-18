<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>This is the error page!</title>

    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/img/atom.png"/> "/>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link href="<c:url value="/resources/css/bootstrap.min.css"/> " rel="stylesheet"/>
    <link href="<c:url value="/resources/css/bootstrap-theme.min.css"/> " rel="stylesheet"/>

    <%--always the last included style !!!--%>
    <link href="<c:url value="/resources/css/scienceMe.css"/> " rel="stylesheet"/>

    <script src="<c:url value="/resources/js/jquery-2.1.3.min.js"/> " rel="stylesheet"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/> " rel="stylesheet"></script>
</head>
<body>

    <div class="container">
        <h1>SERVER ERROR</h1>
        <h2>${exception}</h2>
        <h2>${url}</h2>
    </div>

<%-----------footer-----------%>
<jsp:include page="footer.jsp"/>
<%----------------------------%>
</body>
</html>
