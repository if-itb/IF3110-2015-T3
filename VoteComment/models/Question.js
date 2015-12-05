var mysql = require('mysql');
var Response = require('./Response');

var connection = mysql.createConnection({
    host    : 'localhost',
    user    : 'root',
    password: ''
});
connection.connect();
connection.query('USE stackx');


var question = {};

question.vote = function(req, callback) {
    var sql = 'SELECT question_id, user_id, value FROM question WHERE question_id=? AND user_id=?';
    connection.query(sql, [req.commentId, req.userId], function(err, results) {
        if (results.length === 0) {
            var sql = 'INSERT INTO question (question_id, user_id, value) VALUES (?, ?, ?)';

            connection.query(sql, [req.commentId, req.userId, req.value], function(err, results) {
                var resp;
                if (err) {
                    resp = Response(err.errno, err.code, {});
                } else {
                    resp = Response(0, '', results);
                }
                callback(resp);
            });
        } else {
            if (results[0].value !== req.value) {
                var sql = 'UPDATE question SET value=? WHERE question_id=? AND user_id=?';
                connection.query(sql, [req.value, req.questionId, req.userId], function(err, results) {
                    var resp;
                    if (err) {
                        resp = Response(err.errno, err.code, {});
                    } else {
                        resp = Response(0, '', results);
                    }
                    callback(resp);
                });
            } else {
                callback(Response(1, 'You can only vote once.', {}));
            }
        }
    })
}

module.exports = question;

/*connection.connect();

connection.query('SELECT 1 + 1 AS solution', function(err, rows, fields) {
  if (err) throw err;
  console.log('The solution is: ', rows[0].solution);
});

connection.end();*/