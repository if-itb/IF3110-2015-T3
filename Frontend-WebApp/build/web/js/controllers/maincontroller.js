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
function xmlToClist(xmlDoc){
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

app.controller('commentCtrl',function($scope,$interval){
    $scope.qid;
    $scope.clist=[];
    $scope.clisterror="";
    
    function updateComments(){
        var req = createRequest(); // defined above
        // Create the callback:
        req.onreadystatechange = function() {
          if (req.readyState != 4) return; // Not there yet
          if (req.status != 200) {
            $scope.clisterror=req.status;
              alert("error getting comments");
            // Handle request failure here...
            return;
          }
          // Request successful, read the response
          xmlDoc = req.responseXML;
          $scope.clist = xmlToClist(xmlDoc);
          // ... and use it as needed by your app.
        }
        
        req.open("GET","../CommentandVoteService/Comment?qid="+$scope.qid,true);
        req.send();
    }
    
    updateComments();
    
    $interval(updateComments,update_interval);
    
});