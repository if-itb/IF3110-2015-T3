var questionComment = require('../models/QuestionComment.js');
var user = require('../models/User');
var Const = require('../models/Const');
var Response = require('../models/Response');

var express = require('express');
var router = express.Router();

router.route('/question/comment/:id').get(function(req, res) {
    questionComment.getById(req.params.id, function(r) {
        res.status(r.status + '');
        res.json(r);
    });
});

router.route('/question/comment/:id/upvote').post(function(req,res) {
    var auth = {
        token: req.query.token,
        user_agent: req.headers['user-agent'],
        ip_address: "0:0:0:0:0:0:0:1"
        //ip_address: (req.headers['x-forwarded-for'] || req.connection.remoteAddress).replace("::ffff:", "")
    };
    var u = user.get(auth, function(usr) {
        if (usr.status === Const.STATUS_OK) {
            questionComment.vote({commentId: req.params.id, userId: usr.data.user_id, value: 1}, function(r) {
                res.status(r.status + '');
                res.json(r);
            });
        } else {
            res.status(usr.status + '');
            res.json(Response(usr.status, usr.message));
        }
    });
});

router.route('/question/comment/:id/downvote').post(function(req,res) {
    var auth = {
        token: req.query.token,
        user_agent: req.headers['user-agent'],
        ip_address: "0:0:0:0:0:0:0:1"
        //ip_address: (req.headers['x-forwarded-for'] || req.connection.remoteAddress).replace("::ffff:", "")
    };
    var u = user.get(auth, function(usr) {
        if (usr.status === Const.STATUS_OK) {
            questionComment.vote({commentId: req.params.id, userId: usr.data.user_id, value: -1}, function(r) {
                res.status(r.status + '');
                res.json(r);
            });
        } else {
            res.status(usr.status + '');
            res.json(Response(usr.status, usr.message));
        }
    });
});

//create question comment
router.route('/question/:id/comment')
    .get(function(req, res){
        questionComment.getByQuestionId(req.params.id, function(r) {
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
                questionComment.create({questionId: req.params.id, userId: usr.data.user_id, content: req.body.content}, function(r) {
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