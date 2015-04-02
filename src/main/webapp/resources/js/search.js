

var search = {
    init: {
        wallPage: function () {
            var searchButton = $('aa');

            searchButton.on({
                click: function () {
                    var searchInput  = $('#ssa');

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
            $.post('search', {'searchString': searchString});
        }
    },
    searchPage: function () {

    }
};