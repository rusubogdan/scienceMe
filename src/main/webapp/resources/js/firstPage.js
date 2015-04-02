$(document).ready(function(){
    $("#registerMe").click(function(){
        $("#loginform").hide();
        $("#registerform").show();
    });


    $("#logmeIn").click(function(){
        $("#loginform").show();
        $("#registerform").hide();
    })
});
