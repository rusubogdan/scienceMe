$(document).ready(function(){
    $("registerMe").click(function(){
        $("loginform").style.display="none";
        $("registerform").style.display="block";
    })
    $("logmeIn").click(function(){
        $("loginform").style.display="block";
        $("registerform").style.display="none";
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


