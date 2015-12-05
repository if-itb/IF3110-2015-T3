var mysql = require('mysql');
var Response = require('./Response');
var Const = require('./Const');

var connection = mysql.createConnection({
    host    : 'localhost',
    user    : 'root',
    password: ''
});
connection.connect();
connection.query('USE stackx');


var question = {};

question.getById = function(id, callback) {
    var sql = "SELECT question_id, a.user_id as user_id, u.name AS user_name, title, content, a.create_date, SUM( vote ) AS vote" +
                " FROM (" +
                    " SELECT q.question_id AS question_id, q.user_id AS user_id, title, content, create_date, IFNULL( vq.value, 0 ) AS vote" +
                    " FROM question AS q" +
                    " LEFT OUTER JOIN vote_question AS vq ON q.question_id = vq.question_id" +
                    " ) AS a LEFT OUTER JOIN user AS u ON u.user_id = a.user_id" +
                " WHERE question_id=?" +
                " GROUP BY question_id";

    connection.query(sql, [id], function(err, results) {
        var resp;
        if (err) {
            resp = Response(err.errno, err.message);
        } else {
            resp = Response(Const.STATUS_OK, '', results[0]);
        }
        callback(resp);

        return resp;
    });
}

question.vote = function(req, callback) {
    var sql = 'SELECT question_id, user_id, value FROM vote_question WHERE question_id=? AND user_id=?';
    connection.query(sql, [req.questionId, req.userId], function(err, results) {
        if (results.length === 0) {
            var sql = 'INSERT INTO vote_question (question_id, user_id, value) VALUES (?, ?, ?)';

            connection.query(sql, [req.questionId, req.userId, req.value], function(err, results) {
                var resp;
                if (err) {
                    resp = Response(err.errno, err.message, {});
                } else {
                    resp = Response(Const.STATUS_OK, '', results);
                }
                callback(resp);
            });
        } else {
            if (results[0].value !== req.value) {
                var sql = 'UPDATE vote_question SET value=? WHERE question_id=? AND user_id=?';
                connection.query(sql, [req.value, req.questionId, req.userId], function(err, results) {
                    var resp;
                    if (err) {
                        resp = Response(err.errno, err.message);
                        callback(resp);
                    } else {
                        question.getById(req.questionId, function(r) {
                            resp = r;
                            callback(resp);
                        });
                    }
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