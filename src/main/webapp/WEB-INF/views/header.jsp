<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand hidden-xs" href="#">HOME</a>
            <a class="navbar-brand visible-xs" href="#">S</a>

            <form class="navbar-form pull-left" role="search">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Search">

                    <div class="input-group-btn">
                        <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span>
                        </button>
                    </div>
                </div>
            </form>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/user/${loggedInUser.email}">${loggedInUser.email}</a></li>
                <li><a href="/j_spring_security_logout">logout</a></li>
            </ul>
        </div>
    </div>
</div>
