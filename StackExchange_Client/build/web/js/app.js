/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var app = angular.module("stackExchange", ['ngCookies']).config(function($locationProvider,$httpProvider) {
    $locationProvider.html5Mode({
      enabled: true,
      requireBase: false
    });
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
});


