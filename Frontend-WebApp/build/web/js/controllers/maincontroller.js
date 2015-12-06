/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//access token
function getCookie(cname) {
    console.log(document.cookie);
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
        if (c.indexOf(name) == 0) return c.substring(name.length,c.length);
    }
    return "";
}

function getAccessToken(){
    return getCookie("access_token");
}

function serializeData( data ) { 
    // If this is not an object, defer to native stringification.
    if ( ! angular.isObject( data ) ) { 
        return( ( data == null ) ? "" : data.toString() ); 
    }

    var buffer = [];

    // Serialize each key in the object.
    for ( var name in data ) { 
        if ( ! data.hasOwnProperty( name ) ) { 
            continue; 
        }

        var value = data[ name ];

        buffer.push(
            encodeURIComponent( name ) + "=" + encodeURIComponent( ( value == null ) ? "" : value )
        ); 
    }

    // Serialize the buffer and clean it up for transportation.
    var source = buffer.join( "&" ).replace( /%20/g, "+" ); 
    return( source ); 
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
   
   $scope.newcomment;
   $scope.postnew = function(){
       var at;
               at = window.getAccessToken();
       if (at===""){
           alert("sign in first");
       }else{
            $http({
                method: 'POST',
                url: "../CommentandVoteService/Comment",
                params: 
                {
                    'qid': $scope.qid,
                    'access_token': at,
                    'qcomment': $scope.newcomment
                }
            }).then(function successCallback(response){
                updateComments();
            // ... and use it as needed by your app.
            }, function errorCallback(response) {
                var help = "";
                if (response.status==401)
                    help = "retry signing in";
                alert("error posting comment:\n"+response.status + " " +
                    response.statusText+"\n"+response.data + help);
            });
       }
   }
    
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
   
   $scope.postnew = function(up){
       var at;
               at = window.getAccessToken();
       if (at===""){
           alert("sign in first");
       }else{
            _params = 
                {
                    'qid': $scope.qid,
                    'access_token': at,
                    'up': up
                };
            if ($scope.aid>=0)
                _params['aid']=$scope.aid;

            $http({
                method: 'POST',
                url: "../CommentandVoteService/Vote",
                params: _params}
            ).then(function successCallback(response){
                updateVote();
            // ... and use it as needed by your app.
            }, function errorCallback(response) {
                var help = "";
                if (response.status==401)
                    help = "retry signing in";
                else if (response.status==409)
                    help = "you already voted";
                alert("error posting comment:\n"+response.status + " " +
                    response.statusText+"\n"+response.data + help);
            });
       }
   }   
   var promise = $interval(function(){
        updateVote();
        $interval.cancel(promise);
    },firstupdate_interval);
   $interval(updateVote,update_interval);
});