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


var answer = {};

answer.getById = function(id, callback) {
    var sql = "SELECT answer_id, question_id, u.name AS user_name, a.user_id as user_id, content, a.create_date, SUM( vote ) AS vote" +
                " FROM (" +
                    " SELECT q.answer_id AS answer_id, q.question_id AS question_id, q.user_id AS user_id, content, create_date, IFNULL( vq.value, 0 ) AS vote" +
                    " FROM answer AS q" +
                    " LEFT OUTER JOIN vote_answer AS vq ON q.answer_id = vq.answer_id" +
                    " ) AS a LEFT OUTER JOIN user AS u ON u.user_id = a.user_id" +
                " WHERE answer_id=?" +
                " GROUP BY answer_id";

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

answer.vote = function(req, callback) {
    var sql = 'SELECT answer_id, user_id, value FROM vote_answer WHERE answer_id=? AND user_id=?';
    connection.query(sql, [req.answerId, req.userId], function(err, results) {
        if (results.length === 0) {
            var sql = 'INSERT INTO vote_answer (answer_id, user_id, value) VALUES (?, ?, ?)';

            connection.query(sql, [req.answerId, req.userId, req.value], function(err, results) {
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
                var sql = 'UPDATE vote_answer SET value=? WHERE answer_id=? AND user_id=?';
                connection.query(sql, [req.value, req.answerId, req.userId], function(err, results) {
                    var resp;
                    if (err) {
                        resp = Response(err.errno, err.message);
                        callback(resp);
                    } else {
                        answer.getById(req.answerId, function(r) {
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

module.exports = answer;

/*connection.connect();

connection.query('SELECT 1 + 1 AS solution', function(err, rows, fields) {
  if (err) throw err;
  console.log('The solution is: ', rows[0].solution);
});

connection.end();*/