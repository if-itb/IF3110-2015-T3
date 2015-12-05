var questionComment = require('../models/QuestionComment.js');

var express = require('express');
var router = express.Router();

router.route('/question/comment/:id').get(function(req, res) {
    questionComment.getById(req.params.id, function(r) {
        res.json(r);
    });
});

router.route('/question/comment/:id/upvote').post(function(req,res) {
    questionComment.vote({commentId: req.params.id, userId: req.body.user_id, value: 1}, function(r) {
        res.json(r);
    });
});

router.route('/question/comment/:id/downvote').post(function(req,res) {
    questionComment.vote({commentId: req.params.id, userId: req.body.user_id, value: -1}, function(r) {
        res.json(r);
    });
});

//create question comment
router.route('/question/:id/comment')
    .get(function(req, res){
        questionComment.getByAnswerId(req.params.id, function(r) {
            res.json(r);
        });
    })
    .post(function(req,res) {
        questionComment.create({answerId: req.params.id, userId: req.body.user_id, content: req.body.content}, function(r) {
            res.json(r);
        });
    });

module.exports = router;