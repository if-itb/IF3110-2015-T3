var answer = require('../models/Answer.js');
var user = require('../models/User.js');
var Const = require('../models/Const');
var Response = require('../models/Response');

var express = require('express');
var router = express.Router();

router.route('/answer/:id/upvote').post(function(req, res) {
    var auth = {
        token: req.query.token,
        user_agent: req.headers['user-agent'],
        ip_address: "0:0:0:0:0:0:0:1",
        //ip_address: (req.headers['x-forwarded-for'] || req.connection.remoteAddress).replace("::ffff:", "")
    };
    }
    var u = user.get(auth, function(usr) {
        if (usr.status === Const.STATUS_OK) {
            answer.vote({answerId: req.params.id, userId: usr.data.user_id, value: 1}, function(r) {
                res.status(r.status + '');
                res.json(r);
            });
        } else {
            res.status(usr.status + '');
            res.json(Response(usr.status, usr.message));
        }
    });
});

router.route('/answer/:id/downvote').post(function(req, res) {
    var auth = {
        token: req.query.token,
        user_agent: req.headers['user-agent'],
        ip_address: "0:0:0:0:0:0:0:1",
        //ip_address: (req.headers['x-forwarded-for'] || req.connection.remoteAddress).replace("::ffff:", "")
    };
    }
    var u = user.get(auth, function(usr) {
        if (usr.status === Const.STATUS_OK) {
            answer.vote({answerId: req.params.id, userId: usr.data.user_id, value: -1}, function(r) {
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