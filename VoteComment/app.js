var express = require('express');
var bodyParser = require('body-parser');
var cors = require('cors');
var answerComment = require('./routes/answercomment'); //routes are defined here
var questionComment = require('./routes/questioncomment');
var answer = require('./routes/answer');
var question = require('./routes/question');
var user = require('./routes/user');
var app = express(); //Create the Express app

//configure body-parser
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));
app.use(cors());
app.use('/', answerComment); //This is our route middleware
app.use('/', questionComment); //This is our route middleware
app.use('/', answer); //This is our route middleware
app.use('/', question); //This is our route middleware
app.use('/', user); //This is our route middleware

module.exports = app;