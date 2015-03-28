$(document).ready(function () {
    wall.test();
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