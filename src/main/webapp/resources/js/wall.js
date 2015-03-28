$(document).ready(function () {
    wall.test();
    $('.articles-carousel').slick({
        slidesToShow: 1,
        slidesToScroll: 1,
        dots: true,
        autoplay: true,
        autoplaySpeed: 7000,
        arrows: false
    });
});

var wall = {
    init: function() {

    },
    test: function () {
        $('#test1Button').on({
            click: function () {
                $.get('wall/ajax/testArticle',  function (response) {
                    console.log(response);
                });
            }
        });
    }
};