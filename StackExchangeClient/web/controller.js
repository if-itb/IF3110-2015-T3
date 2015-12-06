/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var myApp = angular.module('myApp', []);

myApp.controller('myController', ['$scope', function ($comment) {
    $comment.add = function (id, username) {

            // ADD A NEW ELEMENT.
            if(id < 5 ){
                $comment.list.push({ username: username, content: $comment.content, id: id });
            }
            // CLEAR THE FIELDS.
            $comment.username = '';
            $comment.content = '';
    }
} ]
);