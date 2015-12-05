var mysql = require('mysql');
var request = require('request');
var Response = require('./Response');
var Const = require('./Const');

var connection = mysql.createConnection({
    host    : 'localhost',
    user    : 'root',
    password: ''
});
connection.connect();
connection.query('USE stackx');


var user = {};

user.get = function(req, callback) {
    request({
        url: 'http://localhost:8080/Identity_Service/Authentication',
        method: 'POST',
        json: {
            token: req.token,
            user_agent: req.user_agent,
            ip_address: req.ip_address
        }
    }, function(error, response, body) {
        if (!error && response.statusCode == 200) {
            var resp;
            if (body.is_valid == -2) {
                resp = Response(Const.STATUS_INVALID_TOKEN, 'Token is invalid. Please login.', {});
            } else if (body.is_valid == -1) {
                resp = Response(Const.STATUS_EXPIRED_TOKEN, 'Token has expired. Please relogin.', {});
            } else {
                resp = Response(Const.STATUS_OK, '', body);
            }
            callback(resp);
        } else {
            callback(Response(response.statusCode, error, body));
        }
    });
}

module.exports = user;

/*connection.connect();

connection.query('SELECT 1 + 1 AS solution', function(err, rows, fields) {
  if (err) throw err;
  console.log('The solution is: ', rows[0].solution);
});

connection.end();*/