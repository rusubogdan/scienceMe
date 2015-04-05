$(document).ready(function () {
    wall.test();
    wall.init.leftSideBar();
    carousel.init();
    wall.recommendArticles();
});

var carousel = {
    obj: null,
    init: function () {
        carousel.obj = $('#articles-carousel');

        carousel.obj.slick({
            slidesToShow: 1,
            slidesToScroll: 1,
            dots: true,
            //adaptiveHeight: true,
            autoplay: true,
            autoplaySpeed: 6000,
            arrows: false
        });
    },
    addArticle: function (html) {
        carousel.obj.slick('slickAdd', html);
    },
    removeFirstArticle: function () {
        carousel.obj.slick('slickRemove', true);
    },
    destroy: function () {
        carousel.obj.slick('unslick');
    }

};

var articles;
var wall = {
    filterByNews: true,
    filterByRating: false,
    barLowerBound: 1,
    barUpperBound: 23,
    startingSearchPoint: 0,
    init: {
        timeBar: function () {
            $('.range-slider').jRange({
                from: 1,
                to: 60,
                step: 1,
                scale: [1, 15, 30, 45, 60],
                format: '%s',
                width: 150,
                theme: "theme-blue",
                showLabels: true,
                isRange: true,
                onstatechange: function () {
                    var $range = $(".range-slider");
                    wall.barLowerBound = $range.val().split(",")[0];
                    wall.barUpperBound = $range.val().split(",")[1];

                    wall.filterArticles(wall.filterByNews, wall.filterByRating, wall.barLowerBound,
                        wall.barUpperBound, wall.startingSearchPoint);

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
                    var $range = $(".range-slider");
                    wall.barLowerBound = $range.val().split(",")[0];
                    wall.barUpperBound = $range.val().split(",")[1];
                    if (wall.barUpperBound == null) {
                        wall.barUpperBound = wall.barLowerBound;
                        wall.barLowerBound = 0;
                    }

                    wall.filterArticles(wall.filterByNews, wall.filterByRating, wall.barLowerBound,
                        wall.barUpperBound, wall.startingSearchPoint);
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
                    var $range = $(".range-slider");
                    wall.barLowerBound = $range.val().split(",")[0];
                    wall.barUpperBound = $range.val().split(",")[1];
                    if (wall.barUpperBound == null) {
                        wall.barUpperBound = wall.barLowerBound;
                        wall.barLowerBound = 0;
                    }

                    wall.filterArticles(wall.filterByNews, wall.filterByRating, wall.barLowerBound,
                        wall.barUpperBound, wall.startingSearchPoint);
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
            $('#news-button-sidebar').css({
                'background-color': 'rgb(81, 176, 74)'
            });

            wall.filterArticles(wall.filterByNews, wall.filterByRating, wall.barLowerBound, wall.barUpperBound,
                wall.startingSearchPoint);
        }
    },
    test: function () {
        $('#test1Button').on({
            click: function () {
                $.ajax({
                    url: 'wall/ajax/testArticle',
                    method: 'get',
                    dataType: 'json',
                    success: function (response) {
                    }
                });
            }
        });
    },
    filterArticles: function (news, rating, lowerBoundInterval, upperBoundInterval, startingSearchPoint) {
        $.get('/wall/ajax/filterArticles', {"news": news,
                "rating": rating,
                "barLowerBound": lowerBoundInterval,
                "upperBoundInterval": upperBoundInterval,
                "startingSearchPoint": startingSearchPoint},
            function (response) {
                var articles = response.articles;

                wall.createNewestArticles(articles, null, $('#sample-article-container'), false);
                wall.createNewestArticles(articles, $('#newest-article-container'), $('#sample-article-preview'), true);

                // for date !! in JS new Date(timestamp).getDay()/getMonth()/getYear
            });

    },
    createNewestArticles: function (articles, container, sample, newest) {
        var $newArticle;

        if (newest) {
            $('#fixed-loader-newest').show();
//            container.empty();
            container.find('*').not('#fixed-loader-newest').remove();
        } else {
//            carousel.obj.empty();
            carousel.obj.find('*').not('#fixed-loader-carousel').remove();
            carousel.destroy();
            carousel.init();
            $('#fixed-loader-carousel').show();
        }

        for (var i = 0; i < articles.length; i++) {
            $newArticle = sample.clone();
            $newArticle.attr('id', $newArticle.attr('id').substr(7) + '-' + i);
            $newArticle.find('.article-title').html(articles[i].title);
            $newArticle.find('.article-text').html(articles[i].description);
            $newArticle.find('.article-reference').attr('href', articles[i].link);
            $newArticle.find('.article-author')
                .html(articles[i].owner.username)
                .on({
                    click: function () {
                        location.href('/user/' + articles[i].owner.username);
                    }
                });

            $newArticle.show();

            if (newest) {
                container.append($newArticle);
            } else {
                carousel.addArticle($newArticle);
            }
        }

        if (newest) {
            $('#fixed-loader-newest').hide();
        } else {
            $('#fixed-loader-carousel').hide();
        }
    recommendArticles: function () {
        $.get('wall/ajax/getRecommendation',
            function(response){
                console.log(response);
            });
    }
};
