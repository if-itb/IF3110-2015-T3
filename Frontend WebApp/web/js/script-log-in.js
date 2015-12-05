/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
  // LOG IN FORM
  // Menghentikan submit request
  $("#log-in-form").submit(function(e) {
    e.preventDefault(); 
  });
  
  // Memeriksa tombol submit di log in page di klik
  $("#log-in-submit").click(function(e){
    // Ambil form data secara serializable
    dataString = $("#log-in-form").serialize();

    // Ambil form data
    var email = $("#log-in-email").val();
    dataString = "email=" + email;
    var password = $("#log-in-password").val();
    dataString += "&password=" + password;

    // Buat AJAX request
    $.ajax({
      type: "POST",
      url: "http://localhost:8082/Identity_Service/FrontEndTokenController",
      data: dataString,
      dataType: "json",
      // Hasil terima response dari server
      success: function(data, textStatus, jqXHR) {
       window.location.href = "http://localhost:8080/Frontend_WebApp/IndexController?token=" + data.access_token;
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
});

