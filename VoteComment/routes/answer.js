var answer = require('../models/Answer.js');

var express = require('express');
var router = express.Router();

router.route('/answer/:id/upvote').get(function(req, res) {
    answer.vote({answerId: req.params.id, userId: req.body.user_id, value: 1}, function(r) {
        res.json(r);
    });
});

router.route('/answer/:id/downvote').get(function(req, res) {
    answer.vote({answerId: req.params.id, userId: req.body.user_id, value: 1}, function(r) {
        res.json(r);
    });
});

//create answer comment
router.route('/answer/:id/comment')
    .get(function(req, res){
        answer.getByAnswerId(req.params.id, function(r) {
            res.json(r);
        });
    })
    .post(function(req,res) {
        answer.create({answerId: req.params.id, userId: req.body.user_id, content: req.body.content}, function(r) {
            res.json(r);
        });
    });

module.exports = router;