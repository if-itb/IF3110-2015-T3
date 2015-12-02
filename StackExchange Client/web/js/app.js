(function() {
    var app = angular.module('stackexchange', [ ]);
    
    var comment = [
        {
            username: "Husni",
            content: "This is comment 1"
        },
        {
            username: "Ferry",
            content: "This is comment 2"
        },
        {
            username: "Bayu",
            content: "This is comment 3"
        }
    ]
    app.controller('commentController', function() {
        this.commentItems = comment;
    });
})();
