$(document).ready(function(){
    $("#registerMe").click(function(){
        $("#loginform").hide();
        $("#registerform").show();
    })
    $("#logmeIn").click(function(){
        $("#loginform").show();
        $("#registerform").hide();
    })
});


/*
function show(){ //Reg
    document.getElementById("loginform").style.display="none";
    document.getElementById("registerform").style.display="block";
}

function hide(){ //LogIn
    document.getElementById("loginform").style.display="block";
    document.getElementById("registerform").style.display="none";
}
*/


