function confirmDelete(id) {
    var x;
    if (confirm("Are you sure to delete this post?") == true) {
        location.href = "delete_question.jsp?id="+id;
	}
}