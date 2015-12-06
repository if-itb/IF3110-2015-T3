<script>
function delQuestion(id,isList) {
	var r = confirm("Apakah Anda mau menghapus pertanyaan ini?");
    if (r == true) {
		var deleteData = {access_token:"<%= request.getParameter("access_token") %>",id_question:id}
		
		var deleteUrl = "delete2.jsp";
		$.ajax({
		    url: deleteUrl,
		    data: deleteData,
		    dataType: "json",
		    type: "POST",
		    success: function(data) {
				if(data==1){
					alert("Question berhasil dihapus");
					if(isList){
						$("#question_item_"+id).hide();
					}
					else{
						window.location.href = "index.jsp";
					}
				} else if (data ==0){
					alert("Tidak bisa menghapus pertanyaan");
				}
				
		    }
		});
    }
    return r;
	
}
</script>