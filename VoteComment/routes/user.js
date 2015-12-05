var user = require('../models/User.js');

var express = require('express');
var router = express.Router();

router.route('/token/:t/:ua/:ip').get(function(req, res) {
    user.get({token: req.params.t, user_agent: req.params.ua, ip_address: req.params.ip}, function(r) {
        res.status(r.status + '');
        res.json(r);
    });
});

module.exports = router;