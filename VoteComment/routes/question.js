var question = require('../models/Question.js');

var express = require('express');
var router = express.Router();

router.route('/question/:id/upvote').get(function(req, res) {
    question.vote({questionId: req.params.id, userId: req.body.user_id, value: 1}, function(r) {
        res.json(r);
    });
});

router.route('/question/:id/downvote').get(function(req, res) {
    question.vote({questionId: req.params.id, userId: req.body.user_id, value: 1}, function(r) {
        res.json(r);
    });
});

//create question comment
router.route('/question/:id/comment')
    .get(function(req, res){
        question.getByQuestionId(req.params.id, function(r) {
            res.json(r);
        });
    })
    .post(function(req,res) {
        question.create({answerId: req.params.id, userId: req.body.user_id, content: req.body.content}, function(r) {
            res.json(r);
        });
    });

module.exports = router;