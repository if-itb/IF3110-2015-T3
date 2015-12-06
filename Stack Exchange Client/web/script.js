/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validateDelete(id) {
	var txt;
    var r = confirm("Do you really want to delete this question?");
    if (r == true) {
    	var location = "/Stack_Exchange_Client/DelQuestion?qid=" + id;
        window.location.href=location;

    } 
}