var mysql = require('mysql');
var http = require('http');
var Response = require('./Response');

var connection = mysql.createConnection({
    host    : 'localhost',
    user    : 'root',
    password: ''
});
connection.connect();
connection.query('USE stackx');


var user = {};

user.get = function(req, callback) {
    http.post({
        hostname: 'localhost',
        port: '8080'
        path: '/Identity_Service/Authentication'
        agent: false
    }, function (res) {

    });
}

module.exports = user;

/*connection.connect();

connection.query('SELECT 1 + 1 AS solution', function(err, rows, fields) {
  if (err) throw err;
  console.log('The solution is: ', rows[0].solution);
});

connection.end();*/