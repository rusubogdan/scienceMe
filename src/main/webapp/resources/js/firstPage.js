$(document).ready(function(){
    $("#registerMe").click(function(){
        $("#login-form").hide();
        $("#register-form").show();
    });


    $("#logmeIn").click(function(){
        $("#login-form").show();
        $("#register-form").hide();
    })
});
