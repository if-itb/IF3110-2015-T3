(function($){
    $(function(){

        setTimeout(function(){
            $('#recent-post .card .fixed-action-btn > .btn-floating').click()
        }, 500)

        $('#recent-post .card .fixed-action-btn > .btn-floating').click(function(e){
            e.stopPropagation()
        })

        $('#recent-post .card').hover(function(){
            $(this).find('.fixed-action-btn > .btn-floating').click();
        }, function(){
            $(this).find('.fixed-action-btn > .btn-floating').click();
        })

        $('.modal-trigger').leanModal();

        function getParameterByName(name) {
            name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
            var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
                results = regex.exec(location.search);
            return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
        }

        $('#recent-post .card').click(function(){
            if (getParameterByName('token')){
                window.location.href="/answer?token=" + getParameterByName('token') + "&id=" + $(this).attr('data-id')
            } else{
                window.location.href="/answer?id=" + $(this).attr('data-id')
            }

        })

        if (getParameterByName('token')){
            $('#add-question-form').attr('action', '/ask?token=' + getParameterByName('token'));
        } else {
            $('#add-question-form').attr('action', '/ask');
        }

        $('.question-upvote-btn').click(function(e){
            e.stopPropagation()
            var id = $(this).closest('.card').attr('data-id')
            $('<form action="/question/upvote?token=' + getParameterByName('token') + '" method="POST">' +
            '<input type="hidden" name="id" value="' + id + '"/></form>').appendTo('body').submit();
        })

        $('.question-downvote-btn').click(function(e){
            e.stopPropagation()
            var id = $(this).closest('.card').attr('data-id')
            $('<form action="/question/downvote?token=' + getParameterByName('token') + '" method="POST">' +
            '<input type="hidden" name="id" value="' + id + '"/></form>').appendTo('body').submit();
        })


    }); // end of document ready
})(jQuery); // end of jQuery name space