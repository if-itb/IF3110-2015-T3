<%@ include file="header.jsp" %>
<jsp:useBean id="error" type="String" scope="request" />
		<h1 class="tag">Sign Up</h1>
                <div style="color:red"><%= error %></div>
		<form onsubmit="return validasi(this);" class="form makeQuestion" method="post" action="register">
			<div class="innerForm">
				<input class="textForm" type="text" placeholder="Name" name="name">
			</div>
			<div class="innerForm">
				<input class="textForm" type="email" placeholder="Email" name="email">
			</div>
			<div class="innerForm">
				<input class="textForm" type="password" placeholder="Password" name="password">
			</div>
			<div class="innerForm">
				<input class="submitButton" type="submit" placeholder="Sign Up" value="Sign Up">
			</div>
		</form>

<%@ include file="footer.jsp" %>