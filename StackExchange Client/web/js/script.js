$(document).ready(function() {
    $(".vote-button").on("click", function() {
        var id = $(this).data("id");
        var type = $(this).data("type");
        var action = $(this).data("action");
        $.ajax({
            method: "POST",
            url: "vote",
            data: "id=" + id + "&type=" + type + "&action=" + action
        })
        .done(function(result) {
            if (result.hasOwnProperty("votes")) {
                var thisVote = $('#'+type+'-'+id+'-'+action);
                var otherVote = action === "up"? $('#'+type+'-'+id+'-down') : $('#'+type+'-'+id+'-up');
                thisVote.removeClass("vote-button").addClass("vote-button-yes");
                otherVote.removeClass("vote-button").addClass("vote-button-no");
                $('#'+type+'-'+id).html(result.votes);
                thisVote.off("click");
                otherVote.off("click");
            }
        });
    });
});

