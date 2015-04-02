<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>
        Add Article
    </title>
    <meta charset="ISO-8859-1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/img/atom.png"/> " />

    <%--main stylesheet--%>
    <link href="<c:url value="/resources/css/bootstrap.min.css"/> " rel="stylesheet"/>
    <link href="<c:url value="/resources/css/bootstrap-theme.min.css"/> " rel="stylesheet"/>
    <link href="<c:url value="/resources/css/scienceMe.css"/> " rel="stylesheet"/>

    <script src="<c:url value="/resources/js/jquery-2.1.3.min.js"/> " rel="stylesheet"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/> " rel="stylesheet"></script>

    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="http://jqueryvalidation.org/files/dist/jquery.validate.min.js"></script>
    <script src="http://jqueryvalidation.org/files/dist/additional-methods.min.js"></script>

    <script src="<c:url value="/resources/js/addArticle.js"/> " rel="stylesheet"></script>




    <link rel="stylesheet" href="//cdn.jsdelivr.net/bootstrap.tagsinput/0.4.2/bootstrap-tagsinput.css" />
    <script src="//cdn.jsdelivr.net/bootstrap.tagsinput/0.4.2/bootstrap-tagsinput.min.js"></script>

</head>
<body>
<jsp:include page="header.jsp"/>
<div class="row">

    <div class="col-md-2"></div>
    <div class="col-md-8">
        <div class="container-view-article">
            <br><br><br>
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <h1>
                    Add new article
                </h1>
            </div>
            <div class="col-md-4"></div>
            <div class="clearfix"></div>



<%--    informatiile din aces formular trebuie trimise in articlecontroller in functia addarticle--%>
            <%--    In aceasta functie iau informatiile de la link si le bag  in baza de date --%>
            <form id="bootstrapTagsInputForm" method="post" action="add-article-in-database" class="form-horizontal">

                <div class="form-group">
                    <label class="col-xs-3 control-label">
                        Link to article:
                    </label>
                    <div class="col-xs-5">
                        <input type="text" name="link" class="form-control"
                               placeholder="Add link to your article..." />
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-3 control-label">
                        Your description:
                    </label>
                    <div class="col-xs-5">
                        <textarea name="description" class="form-control"></textarea>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-3 control-label">
                        Add Tag:
                    </label>
                    <div class="col-md-5">
                        <input type="text" name="tags" class="form-control"
                               value="" data-role="tagsinput" />
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-3 control-label">
                        How long did you read the article?
                    </label>
                    <div class="col-xs-5">
                        <input type="text" name="time" class="form-control" placeholder="minutes"/>
                    </div>
                </div>



                <div class="form-group">
                    <div class="col-xs-5 col-xs-offset-5">
                        <button type="button" id="saveArticleBtn" class="btn btn-success">
                            Save &nbsp; <img id="loadingmessage" src="/resources/img/add-article-loading.gif" class="hidden">
                        </button>
                    </div>
                </div>



            </form>
        </div>
    </div>
    <div class="col-md-2"></div>

</div>

</body>
</html>
