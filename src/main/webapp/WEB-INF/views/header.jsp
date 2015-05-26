<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand hidden-xs" href="<c:url value="/wall"/> ">Home</a>
            <a class="navbar-brand hidden-xs" href="<c:url value="/article/add"/> ">Add article</a>
            <a class="navbar-brand visible-xs" href="#">H</a>
            <a class="navbar-brand visible-xs" href="#">ADD</a>

            <form class="navbar-form pull-left" method="post" action="/search" role="search">
                <div class="input-group">
                    <input type="text" class="form-control" name="searchQuery" placeholder="Search">

                    <div class="input-group-btn">
                        <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span>
                        </button>
                    </div>
                </div>
            </form>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/user/${userName}">${userName}</a></li>
                <li><a href="/j_spring_security_logout">logout</a></li>
            </ul>
        </div>
    </div>
</div>
