/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//create request
function createRequest() {
  var result = null;
  if (window.XMLHttpRequest) {
    // FireFox, Safari, etc.
    result = new XMLHttpRequest();
    if (typeof result.overrideMimeType != 'undefined') {
      result.overrideMimeType('text/xml'); // Or anything else
    }
  }
  else if (window.ActiveXObject) {
    // MSIE
    result = new ActiveXObject("Microsoft.XMLHTTP");
  } 
  else {
    // No known mechanism -- consider aborting the application
  }
  return result;
}


//convert
function xmlToClist(xmlStr){
    parser = new DOMParser();
    var xmlDoc = parser.parseFromString(xmlStr,"text/xml");
    
    var comments = xmlDoc.getElementsByTagName("comment");
    var arr = [];
    for (i=0;i<comments.length;i++){
        var key = comments[i];
        var comment = {
            cid : key.getAttribute("cid"),
            Email : key.getAttribute("Email"),
            AuthorName : key.getAttribute("AuthorName"),
            created_at : key.getAttribute("created_at"),
            qcomment : key.childNodes[0].nodeValue
        };
        
        arr.push(comment);
    }
    console.log(arr);
    return arr;
}



//data model
var app = angular.module("CommentAndVoteApp",[]);

var update_interval = 3000;
var firstupdate_interval = 10;

app.controller('commentCtrl',function($scope,$interval,$http){
    $scope.qid;
    $scope.clist=[];
    $scope.clisterror="";
    
    function updateComments(){
        $http.get("../CommentandVoteService/Comment?qid="+$scope.qid)
                .then(function successCallback(response){
          console.log(response.data);
          $scope.clist = xmlToClist(response.data);
          // ... and use it as needed by your app.
        }, function errorCallback(response) {
            alert("error getting comments");
        }
        );
        
    }
    
    
    var promise = $interval(function(){
        updateComments();
        $interval.cancel(promise);
    },firstupdate_interval);
    $interval(updateComments,update_interval);
    
});

app.controller('voteCtrl',function($scope,$interval,$http){
   $scope.qid;
   $scope.aid=-1;
   $scope.votenum="...";
   
   function updateVote(){
       var url="../CommentandVoteService/Vote?qid="+$scope.qid;
        if ($scope.aid>=0){
            url += "&aid="+$scope.aid;
        }
        $http.get(url)
                .then(function successCallback(response){
          console.log(response.data);
          $scope.votenum= Number(response.data);
          // ... and use it as needed by your app.
        }, function errorCallback(response) {
            alert("error getting comments");
        });
        
        
        
   };
   
   var promise = $interval(function(){
        updateVote();
        $interval.cancel(promise);
    },firstupdate_interval);
   $interval(updateVote,update_interval);
});