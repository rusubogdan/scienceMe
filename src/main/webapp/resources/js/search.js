$(document).ready(function () {
//    search.init.wallPage();
});

var search = {
    init: {
        wallPage: function () {
            var searchButton = $('button.btn.btn-default');

            searchButton.on({
                click: function (e) {
                    e.preventDefault();
                    var searchInput  = $('input[name="searchQuery"]');

                    console.log(searchInput.val());
                    search.wallPage.redirectToSearchPage(searchInput.val());
                }
            });
        },
        searchPage: function () {

        }
    },
    wallPage: {
        redirectToSearchPage: function (searchString) {
            // no returned function
            $.post("/search/ajax/search", {"searchQuery": searchString});
        }
    },
    searchPage: function () {

    },




    createNewestArticles: function (articlesMap, container, sample) {
        var $newArticle;

        for (var i = 0; i < articlesMap.length; i++) {
            var $article = articlesMap[i];
            $newArticle = sample.clone();
            $newArticle.attr('id', $newArticle.attr('id').substr(7) + '-' + i);
            $newArticle.find('.article-title').html($article.title);
            $newArticle.find('.article-text').html($article.description);
            $newArticle.find('.article-reference').attr('href', '/article/view/' + $article.token);
            $newArticle.find('.article-author a')
                .attr('href', '/user/' + $article.ownerUsername)
                .html($article.ownerUsername);
            $newArticle.find('.article-rating span').html('Rating: ' + $article.rating + '/5');
            if($article.imageLink != null) {
                $newArticle.find('.image-holder').css({background: 'url(' + $article.imageLink + ')no-repeat center'});
            }

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
                wall.createNewestArticles(articlesMap, null, $('#sample-article-container'));
            });
    }

};