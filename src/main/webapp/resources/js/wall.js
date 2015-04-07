$(document).ready(function () {
    wall.test();
    wall.init.leftSideBar();
    wall.init.readMoreButton();
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

                    $('#fixed-loader-newest').show();
                    $("#newest-article-container .article-preview-container").remove();
                    wall.startingSearchPoint = 0;
                    wall.filterArticles(wall.filterByNews, wall.filterByRating, wall.barLowerBound,
                        wall.barUpperBound, wall.startingSearchPoint);

                }
            });
        },
        buttonSideBar: function () {
            ////test

            $('#time-label').on({
                click: function () {
                    $.get('/wall/ajax/testArticle', function (response) {
                        console.log(response);
                    })
                }
            });




            var newsFilter = $('#news-button-sidebar');
            var ratingFilter = $('#rating-button-sidebar');

            newsFilter.on({
                click: function () {
                    if (!wall.filterByNews) {
                        $('#fixed-loader-newest').show();
                        $("#newest-article-container .article-preview-container").remove();
                        wall.startingSearchPoint = 0;
                        newsFilter.css({
                            'background-color': 'rgb(81, 176, 74)'
                        });
                        wall.filterByNews = true;
                        wall.filterByRating = false;
                        ratingFilter.css({
                            'background-color': 'transparent'
                        });

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
                    }
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
                    if (!wall.filterByRating) {
                        $('#fixed-loader-newest').show();
                        $("#newest-article-container .article-preview-container").remove();
                        wall.startingSearchPoint = 0;
                        ratingFilter.css({
                            'background-color': 'rgb(81, 176, 74)'
                        });
                        wall.filterByRating = true;
                        wall.filterByNews = false;
                        newsFilter.css({
                            'background-color': 'transparent'
                        });

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
                    }
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
        },
        readMoreButton:function(){
            var learnMoreButton = $('#learn-more-button');
            learnMoreButton.on({
                click: function () {
                    $('#fixed-loader-newest-end').show();
                    wall.startingSearchPoint += 10 ;
                    wall.filterArticles(wall.filterByNews, wall.filterByRating, wall.barLowerBound, wall.barUpperBound,
                        wall.startingSearchPoint);
                }
            })

        }
    },
    test: function () {

    },
    filterArticles: function (news, rating, lowerBoundInterval, upperBoundInterval, startingSearchPoint) {
        $.get('/wall/ajax/filterArticles', {"news": news,
                                            "rating": rating,
                                            "barLowerBound": lowerBoundInterval,
                                            "upperBoundInterval": upperBoundInterval,
                                            "startingSearchPoint": startingSearchPoint},
            function (response) {
//                list of objects of type article and integer
                var articlesMap = response;
                wall.createNewestArticles(articlesMap, $('#newest-article-container'), $('#sample-article-preview'), true);
                // for date !! in JS new Date(timestamp).getDay()/getMonth()/getYear
            }
        );

    },
    createNewestArticles: function (articlesMap, container, sample, newest) {
        var $newArticle;

        if (!newest) {
//            carousel.obj.empty();
            carousel.obj.find('*').not('#fixed-loader-carousel').remove();
            carousel.destroy();
            carousel.init();
            carousel.removeFirstArticle();
            $('#fixed-loader-carousel').show();
        }

        for (var i = 0; i < articlesMap.length; i++) {
            var $article = articlesMap[i].article;
            $newArticle = sample.clone();
            $newArticle.attr('id', $newArticle.attr('id').substr(7) + '-' + i);
            $newArticle.find('.article-title').html($article.title);
            $newArticle.find('.article-text').html($article.description);
            $newArticle.find('.article-reference').attr('href', '/article/view/' + $article.token);
            $newArticle.find('.article-author a')
                .attr('href', '/user/' + $article.owner.username)
                .html($article.owner.username);

            $newArticle.show();

            if (newest) {
                $newArticle.insertBefore("#newest-article-container #fixed-loader-newest-end" );
//                container.append($newArticle);
            } else {
                carousel.addArticle($newArticle);
            }
        }

        if (newest) {
            $('#fixed-loader-newest').hide();
            $('#fixed-loader-newest-end').hide();
        } else {
            $('#fixed-loader-carousel').hide();
        }
    },

    recommendArticles: function () {
        $.get('wall/ajax/getRecommendation',
            function(articlesMap){
                wall.createNewestArticles(articlesMap, null, $('#sample-article-container'), false);
            });
    }
};
