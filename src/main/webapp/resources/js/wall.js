$(document).ready(function () {
    wall.test();
    wall.init.leftSideBar();


    $('.articles-carousel').slick({
        slidesToShow: 1,
        slidesToScroll: 1,
        dots: true,
        //adaptiveHeight: true,
        autoplay: true,
        autoplaySpeed: 7000,
        arrows: false
    });
});

// Cosmin - schimba :hover eventul
// apeleaza ajax-ul cand se schimba intervalul

var wall = {
    filterByNews: false ,
    filterByRating: false,
    barLowerBound: 0,
    barUpperBound: 23,
    startingSearchPoint: 0,
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
                isRange: true,
                onstatechange: function () {
                    wall.barLowerBound = $(".range-slider").val().split(",")[0];
                    wall.barUpperBound = $(".range-slider").val().split(",")[1];
                    wall.filterArticles(wall.filterByNews, wall.filterByRating, wall.barLowerBound, wall.barUpperBound, wall.startingSearchPoint);

                }
            });
        },
        buttonSideBar: function () {
            var newsFilter = $('#news-button-sidebar');
            var ratingFilter = $('#rating-button-sidebar');

            newsFilter.on({
                click: function () {
                    if (wall.filterByNews) {
                        newsFilter.css('background-color', 'transparent');
                        wall.filterByNews = false;
                    }
                    else {
                        newsFilter.css({
                            'background-color': 'rgb(81, 176, 74)'
                        });
                        wall.filterByNews = true;
                        wall.filterByRating = false;
                        ratingFilter.css({
                            'background-color': 'transparent'
                        });
                    }

                    // ajax to server for filtering the articles
                    wall.barLowerBound = $(".range-slider").val().split(",")[0];
                    wall.barUpperBound = $(".range-slider").val().split(",")[1];
                    wall.filterArticles(wall.filterByNews, wall.filterByRating, wall.barLowerBound, wall.barUpperBound, wall.startingSearchPoint);
                },

                mouseover: function () {
                    if (!wall.filterByNews) {
                        newsFilter.css('background-color', 'rgb(195, 195, 195)');
                        newsFilter.css('cursor', 'pointer');
                    }
                },
                mouseout: function () {
                    if (!wall.filterByNews) {
                        newsFilter.css('background-color', 'transparent');
                        newsFilter.css('cursor', 'default');
                    }
                }
            });

            ratingFilter.on({
                click: function () {
                    if (wall.filterByRating) {
                        ratingFilter.css('background-color', 'transparent');
                        wall.filterByRating = false;
                    }
                    else {
                        ratingFilter.css({
                            'background-color': 'rgb(81, 176, 74)'
                        });
                        wall.filterByRating = true;
                        wall.filterByNews = false;
                        newsFilter.css({
                            'background-color': 'transparent'
                        });
                    }

                    // ajax to server for filtering the articles
                    wall.barLowerBound = $(".range-slider").val().split(",")[0];
                    wall.barUpperBound = $(".range-slider").val().split(",")[1];
                    wall.filterArticles(wall.filterByNews, wall.filterByRating, wall.barLowerBound, wall.barUpperBound, wall.startingSearchPoint);
                },

                mouseover: function () {
                    if (!wall.filterByRating) {
                        ratingFilter.css('background-color', 'rgb(195, 195, 195)');
                        ratingFilter.css('cursor', 'pointer');
                    }
                },
                mouseout: function () {
                    if (!wall.filterByRating) {
                        ratingFilter.css('background-color', 'transparent');
                        ratingFilter.css('cursor', 'default');
                    }
                }
            });
        },
        leftSideBar: function () {
            wall.init.buttonSideBar();
            wall.init.timeBar();
            wall.filterArticles(wall.filterByNews, wall.filterByRating, wall.barLowerBound, wall.barUpperBound, wall.startingSearchPoint);
        }
    },
    test: function () {
        $('#test1Button').on({
            click: function () {
                /*$.get('wall/ajax/testArticle',  function (response) {
                    console.log(response.object);
                });*/
                $.ajax({
                    url: 'wall/ajax/testArticle',
                    method: 'get',
                    dataType: 'json',
                    success: function (response) {
                        console.log(response.user.email);
                    }
                });
            }
        });
    },
    filterArticles: function (news, rating, lowerBoundInterval, upperBoundInterval, startingSearchPoint) {
        console.log(news);
        console.log(rating);
        console.log(lowerBoundInterval);
        console.log(upperBoundInterval);
        $.get('wall/ajax/filterArticles',{"news": news,
                                          "rating": rating,
                                          "barLowerBound": lowerBoundInterval,
                                          "upperBoundInterval": upperBoundInterval,
                                          "startingSearchPoint": startingSearchPoint},
            function (response) {
            console.log(response);
        });

        $.post('search/ajax/filterArticles', {"searchQuery": "Romania"}, function (response) {
           console.log(response);
        });


    }
};
