/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function checkLogin(access_token) {
    if (access_token === null) {
        alert("Anda harus login terlebih dahulu");
        window.location="index.jsp";
    } 
    
}


