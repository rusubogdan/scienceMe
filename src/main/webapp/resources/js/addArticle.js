$(document).ready(function(){
   addArticle.init();

});


var addArticle = {
    init: function () {
        console.log("ajunge");

        $.validator.addMethod(
            "invalidLinkRegex",
            function(value, element, regexp) {
                var reg = new RegExp(regexp);
                if (reg.test(value) == true) {
                    return false;
                }else{
                    return true;
                }
            },
            "The description can't contain links!"
        );

        $.validator.addMethod(
            "regex",
            function(value, element, regexp) {
                var reg = new RegExp(regexp);
                return reg.test(value);
            },
            "Invalid format!"
        );

        jQuery.validator.setDefaults({
            debug: true,
            success: "valid"
        });


        var add_article_form = $("#bootstrapTagsInputForm").validate({
//            ignore: ".ignore",
            // Rules for form validation
            rules : {

                link : {
                    required : true,
                    minlength : 6

                },
                description: {
                    required: true,
                    minlength: 10
                },
                time: {
                    required: true,
                    number: true,
                    max: 60,
                    min:1
                }

            }
        });


        $('#saveArticleBtn').on({
            click: function () {

                if($("#bootstrapTagsInputForm").valid()){
                    console.log('este valid');
                    $('#loadingmessage').removeClass("hidden");
                    $.ajax({
                        url: "add-article-in-database",
                        type: "POST",
                        cache: false,
                        data: $("#bootstrapTagsInputForm").serialize(),
                        success : function(html){
                            $('#loadingmessage').addClass("hidden"); // hide the loading message
                            location.reload();
                        }
                    });
//                    $.post('add-article-in-database',$("#bootstrapTagsInputForm").serialize()  , function (response) {
//
//                        if(response["ok"] == "1"){
//                            location.reload();
//                            console.log("da");
//                        }else{
//                            console.log("nu");
//                        }
//
////                        console.log(response);
//                    });
                    //poti face submit la formular
                } else {
                    console.log($("#bootstrapTagsInputForm").valid());
                }

            }
        });

        console.log("ajunge2");
    },

   addArticleFromForm: function(){
        console.log("send info");
    },

//
//    checkValidatorForAddArticle: function(){
//        var form = $("#bootstrapTagsInputForm");
//        if(form.valid()){
//            console.log('este valid');
//            $("#bootstrapTagsInputForm").submit();
//            //poti face submit la formular
//        } else {
//            console.log($("#bootstrapTagsInputForm").valid());
//        }
//    },

    checkValidatorForAddArticle: function () {

    }

};
