/* 
 * Tugas 3 IF3110 Pengembangan Aplikasi Web
 * Website StackExchangeWS Sederhana
 * dengan tambahan web security dan frontend framework
 * 
 * @author Irene Wiliudarsan - 13513002
 * @author Angela Lynn - 13513032
 * @author Devina Ekawati - 13513088
 */

'use strict';

var stackExchange = angular.module('stackExchange', []);

stackExchange.controller('VoteController', function ($scope, $http, $location) {
    
    // Mendapatkan elemen dari div
    var div = document.getElementById('vote');
    $scope.item = { type : div.getAttribute("vote-type"),
                    id : div.getAttribute("id")
    };
    
    dataString = "voteType=" + $scope.item.type + "&id=" + $scope.item.id;
    
    var token = $location.search().token;
    var questionId = $location.search().qid;
    
    // Membuat http request
    $http({
      method: "POST",
      url: "http://localhost:8083/Comment___Vote_Service/VoteController",
      dataType: "json",
      data: dataString,
      
      // Hasil terima response dari server
      success: function(data, textStatus, jqXHR) {
       window.location.href = "http://localhost:8080/Frontend_WebApp/QusetionDetailController?token=" + token + "&qid=" +questionId;
      },
      // Tidak ada response dari server
      error: function(jqXHR, textStatus, errorThrown) {
        console.log("Something really bad happened " + textStatus + "<br>Please reload ths page");
        alert(jqXHR.responseText);
      },
      beforeSend: function(jqXHR, settings) {
        // Menambah data dummy ke request
        $("#log-in-submit").attr("disabled", true);
      },
      complete: function(jqXHR, textStatus) {
        $("#log-in-submit").attr("disabled", false);
      }
    });
});