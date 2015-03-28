$(document).ready(function () {
    wall.test();
    wall.init.timeBar();
    wall.init.buttonSideBar();
});

// Cosmin - schimba :hover eventul
// apeleaza ajax-ul cand se schimba intervalul

var wall = {
    filterByNews: false,
    filterByRating: false,
    barLowerBound: 0,
    barUpperBound: 60,
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
            var newsFilter = $('#news-button-sidebar');
            var ratingFilter = $('#rating-button-sidebar');

            newsFilter.on({
                click: function () {
                    if (wall.filterByNews) {
                        newsFilter.css('background-color', 'transparent');
                        wall.filterByNews = false;
                        wall.filterByRating = true;
                        ratingFilter.css({
                            'background-color': 'green'
                        });
                    }
                    else {
                        newsFilter.css({
                            'background-color': 'green'
                        });
                        wall.filterByNews = true;
                        wall.filterByRating = false;
                        ratingFilter.css({
                            'background-color': 'transparent'
                        });
                    }

                    // ajax to server for filtering the articles
                    wall.filterArticles(wall.filterByNews, wall.filterByRating, wall.barLowerBound, wall.barUpperBound);
                }
            });

            ratingFilter.on({
                click: function () {
                    if (wall.filterByRating) {
                        ratingFilter.css('background-color', 'transparent');
                        wall.filterByRating = false;
                        wall.filterByNews = true;
                        newsFilter.css({
                            'background-color': 'green'
                        });
                    }
                    else {
                        ratingFilter.css({
                            'background-color': 'green'
                        });
                        wall.filterByRating = true;
                        wall.filterByNews = false;
                        newsFilter.css({
                            'background-color': 'transparent'
                        });
                    }

                    // ajax to server for filtering the articles
                    wall.filterArticles(wall.filterByNews, wall.filterByRating, wall.barLowerBound, wall.barUpperBound);
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
    },
    filterArticles: function (news, rating, lowerBoundInterval, upperBoundInterval) {
        $.get('wall/ajax/filterArticles', function (response) {
            console.log(response.message);
        });
    }
};
