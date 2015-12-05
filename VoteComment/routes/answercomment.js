var answerComment = require('../models/AnswerComment.js');
var user = require('../models/User');
var Const = require('../models/Const');
var Response = require('../models/Response');

var express = require('express');
var router = express.Router();

router.route('/answer/comment/:id').get(function(req, res) {
    answerComment.getById(req.params.id, function(r) {
        res.status(r.status + '');
        res.json(r);
    });
});

router.route('/answer/comment/:id/upvote').post(function(req,res) {
    var auth = {
        token: req.query.token,
        user_agent: req['user-agent'],
        ip_address: req.ip
    }
    var u = user.get(auth, function(usr) {
        if (usr.status === Const.STATUS_OK) {
            answerComment.vote({commentId: req.params.id, userId: usr.data.user_id, value: 1}, function(r) {
                res.status(r.status + '');
                res.json(r);
            });
        } else {
            res.status(usr.status + '');
            res.json(Response(usr.status, usr.message));
        }
    });
});

router.route('/answer/comment/:id/downvote').post(function(req,res) {
    var auth = {
        token: req.query.token,
        user_agent: req['user-agent'],
        ip_address: req.ip
    }
    var u = user.get(auth, function(usr) {
        if (usr.status === Const.STATUS_OK) {
            answerComment.vote({commentId: req.params.id, userId: usr.data.user_id, value: -1}, function(r) {
                res.status(r.status + '');
                res.json(r);
            });
        } else {
            res.status(usr.status + '');
            res.json(Response(usr.status, usr.message));
        }
    });
});

//create answer comment
router.route('/answer/:id/comment')
    .get(function(req, res){
        answerComment.getByAnswerId(req.params.id, function(r) {
        res.status(r.status + '');
            res.json(r);
        });
    })
    .post(function(req,res) {
        var auth = {
            token: req.query.token,
            user_agent: req['user-agent'],
            ip_address: req.ip
        }
        var u = user.get(auth, function(usr) {
            if (usr.status === Const.STATUS_OK) {
                answerComment.create({answerId: req.params.id, userId: usr.data.user_id, content: req.body.content}, function(r) {
                    res.status(r.status + '');
                    res.json(r);
                });
            } else {
                res.status(usr.status + '');
                res.json(Response(usr.status, usr.message));
            }
        });
    });

module.exports = router;