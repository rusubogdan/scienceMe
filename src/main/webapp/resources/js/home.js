$(document).ready(function () {
    home.init();
});

var home = {
    loginForm: function () {
        return $("#login-form");
    },
    registerForm: function () {
        return $("#register-form");
    },
    init: function () {
        $("#registerMe").click(function(){
            home.showRegisterForm();
        });

        $("#logmeIn").click(function(){
            home.showLoginForm();
        });

        if (showRegistrationForm) {
            home.showRegisterForm();
        }
    },
    showLoginForm: function () {
        home.loginForm().show();
        home.registerForm().hide();
    },
    showRegisterForm: function () {
        home.loginForm().hide();
        home.registerForm().show();
    }
};