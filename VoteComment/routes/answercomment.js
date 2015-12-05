var answerComment = require('../models/AnswerComment.js');

var express = require('express');
var router = express.Router();

router.route('/answer/comment/:id').get(function(req, res) {
    answerComment.getById(req.params.id, function(r) {
        res.json(r);
    });
});

router.route('/answer/comment/:id/upvote').post(function(req,res) {
    answerComment.vote({commentId: req.params.id, userId: req.body.user_id, value: 1}, function(r) {
        res.json(r);
    });
});

router.route('/answer/comment/:id/downvote').post(function(req,res) {
    answerComment.vote({commentId: req.params.id, userId: req.body.user_id, value: -1}, function(r) {
        res.json(r);
    });
});

//create answer comment
router.route('/answer/:id/comment')
    .get(function(req, res){
        answerComment.getByAnswerId(req.params.id, function(r) {
            res.json(r);
        });
    })
    .post(function(req,res) {
        answerComment.create({answerId: req.params.id, userId: req.body.user_id, content: req.body.content}, function(r) {
            res.json(r);
        });
    });

module.exports = router;