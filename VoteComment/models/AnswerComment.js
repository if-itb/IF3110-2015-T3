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


var answerComment = {};

answerComment.getById = function(id, callback) {
    connection.query('SELECT comment_id, answer_id, user_id, content, create_date FROM comment_answer WHERE comment_id=?', [id], function(err, results) {
        var resp;
        if (err) {
            resp = Response(err.errno, err.message, {});
        } else {
            resp = Response(Const.STATUS_OK, '', results[0]);
        }
        callback(resp);

        return resp;
    });
}

answerComment.getByAnswerId = function(id, callback) {
    var sql = 'SELECT comment_id, answer_id, user_id, content, create_date, SUM(vote) AS vote' +
        ' FROM (' +
            ' SELECT c.comment_id AS comment_id, c.answer_id AS answer_id, c.user_id AS user_id, content, create_date, IFNULL( v.value, 0 ) AS vote' +
            ' FROM comment_answer AS c' +
            ' LEFT OUTER JOIN vote_comment_answer AS v ON c.comment_id = v.comment_id' +
        ' ) AS a' +
        ' WHERE answer_id=?' +
        ' GROUP BY answer_id' +
        ' ORDER BY vote, create_date DESC';

    connection.query(sql, [id], function(err, results) {
        var resp;
        if (err) {
            resp = Response(err.errno, err.message, {});
        } else {
            resp = Response(Const.STATUS_OK, '', results);
        }
        callback(resp);

        return results;
    });
}

answerComment.vote = function(req, callback) {
    var sql = 'SELECT comment_id, user_id, value FROM vote_comment_answer WHERE comment_id=? AND user_id=?';
    connection.query(sql, [req.commentId, req.userId], function(err, results) {
        if (results.length === 0) {
            var sql = 'INSERT INTO vote_comment_answer (comment_id, user_id, value)' +
                ' VALUES (?, ?, ?)';

            connection.query(sql, [req.commentId, req.userId, req.value], function(err, results) {
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
                var sql = 'UPDATE vote_comment_answer SET value=? WHERE comment_id=? AND user_id=?';
                connection.query(sql, [req.value, req.commentId, req.userId], function(err, results) {
                    var resp;
                    if (err) {
                        resp = Response(err.errno, err.message, {});
                    } else {
                        resp = Response(Const.STATUS_OK, '', results);
                    }
                    callback(resp);
                });
            } else {
                callback(Response(1, 'You can only vote once.', {}));
            }
        }
    })

}

answerComment.create = function(req, callback) {
    var sql = 'INSERT INTO comment_answer (answer_id, user_id, content, create_date)' +
        ' VALUES (?, ?, ?, CURRENT_TIMESTAMP)';

    connection.query(sql, [req.answerId, req.userId, req.content], function(err,results) {
        var resp;
        if (err) {
            resp = Response(err.errno, err.message, {});
        } else {
            resp = Response(Const.STATUS_OK, '', results);
        }
        callback(resp);

        return results;
    })
}

module.exports = answerComment;

/*connection.connect();

connection.query('SELECT 1 + 1 AS solution', function(err, rows, fields) {
  if (err) throw err;
  console.log('The solution is: ', rows[0].solution);
});

connection.end();*/