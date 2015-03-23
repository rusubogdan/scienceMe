<%--<div class="navbar navbar-inverse navbar-static-top" role="navigation">

    <div class="col-sm-3 col-md-3 pull-left">
        <div class="input-group">
            <h2 style="color: blue;">HOME</h2>
        </div>
    </div>

    <div class="col-sm-6 col-md-6 pull-left">
        <form class="navbar-form" role="search">
            <div class="input-group full-width">
                <input type="text" class="form-control" placeholder="Search" name="srch-term" id="srch-term"/>
                <div class="input-group-btn">
                    <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                </div>
            </div>
        </form>
    </div>--%>

<%--really commented--%>
<%--    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" rel="home" href="/" title="Aahan Krish's Blog - Homepage">ITSMEEE</a>
    </div>--%>

<%--<div class="collapse navbar-collapse navbar-ex1-collapse">--%>
<%--<ul class="nav navbar-nav">--%>
<%--<li><img src=""/></li>--%>
<%--<li><a href="/user/${loggedInUser.username}">${loggedInUser.username}</a></li>--%>
<%--<li><span>Rating: 10</span></li>--%>
<%--<li><span>logout</span></li>--%>
<%--</ul>--%>
<%--</div>--%>
<%--</div>--%>

<div class="navbar navbar-fixed-top navbar-default">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">home</a>
            <a class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="glyphicon glyphicon-bar"></span>
            </a>
        </div>
        <div class="navbar-header" style="width: 80%;">
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