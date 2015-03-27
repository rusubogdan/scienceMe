$(document).ready(function () {
    wall.test();
    wall.init.timeBar();
    wall.init.buttonSideBar();
});

var wall = {
    init: {
        timeBar: function () {
            $('.range-slider').jRange({
                from: 0,
                to: 60,
                step: 1,
                scale: [0, 15, 30, 45, 60],
                format: '%s',
                width: 150,
                theme: "theme-blue",
                showLabels: true,
                isRange: true
            });
        },
        buttonSideBar: function () {
            $('#news-button-sidebar').on({
                click: function () {
                    if($('#news-button-sidebar').css('background-color') == "rgb(0, 128, 0)"){
                        $('.sidebar-button').css('background-color','transparent');
                    }
                    else{
                        $('.sidebar-button').css('background-color','transparent');
                        $('#news-button-sidebar').css('background-color','green');
                    }
                }
            });
            $('#rating-button-sidebar').on({
                click: function () {
                    if($('#rating-button-sidebar').css('background-color') == 'rgb(0, 128, 0)'){
                        $('.sidebar-button').css('background-color','transparent');
                    }
                    else{
                        $('.sidebar-button').css('background-color','transparent');
                        $('#rating-button-sidebar').css('background-color','green');
                    }
                }
            });

        }

    },
    test: function () {
        $('#test1Button').on({
            click: function () {
                $.get('wall/ajax/testArticle', function (response) {
                    console.log(response);
                });
            }
        });
    }
};