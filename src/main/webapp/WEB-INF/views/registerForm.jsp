<%--
  Created by IntelliJ IDEA.
  User: mihai7
  Date: 3/27/2015
  Time: 11:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>Login page</title>

    <body>

    <div class="registerForm">
        <h1>Register</h1>

        <form action="/">

            <label id="icon" for="name"><i class="icon-envelope "></i></label>
            <input type="text" name="name" id="name" placeholder="Email" required/>
            <label id="icon" for="name"><i class="icon-user"></i></label>
            <input type="text" name="name" id="name" placeholder="First Name" required/>
            <label id="icon" for="name"><i class="icon-user"></i></label>
            <input type="text" name="name" id="name" placeholder="Last Name" required/>
            <label id="icon" for="name"><i class="icon-shield"></i></label>
            <input type="password" name="name" id="name" placeholder="Password" required/>
            <div class="gender">
                <input type="radio" value="None" id="male" name="gender" checked/>
                <label for="male" class="radio"  >Male</label>
                <input type="radio" value="None" id="female" name="gender" />
                <label for="female" class="radio">Female</label>
            </div>

            <a href="#" class="button">Register</a>
        </form>
    </div>

    </body>
</html>
