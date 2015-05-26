$(document).ready(function () {
    home.init();

/*    $.ajaxSetup({ cache: true });
    $.getScript('//connect.facebook.net/en_US/sdk.js', function(){
        FB.init({
            appId: '801865369905189',
            version: 'v2.3' // or v2.0, v2.1, v2.0
        });
//        $('#login-facebook').removeAttr('disabled');
        FB.getLoginStatus(updateStatusCallback);
    });*/
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