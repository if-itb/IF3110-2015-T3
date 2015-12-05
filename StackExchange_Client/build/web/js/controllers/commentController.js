/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
app.controller('commentController', [ '$http', function ($http)
    {
        $http({
           url: "http://localhost:8080/StackExchange_Client/", 
           method: "GET",
           params: {qid: getParameterByName("id")}
        }).success(function(data){
            if (!data.error)
            {
                this.commentItems = data;
            }
        })
    }    
])



