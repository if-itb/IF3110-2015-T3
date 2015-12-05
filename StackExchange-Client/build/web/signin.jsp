<%@include file="header.jsp" %>

		<h1 class="tag">Sign In</h1>
		<form onsubmit="return validasi(this);" class="form makeQuestion" method="post" action="login">
			<div class="innerForm">
				<input class="textForm" type="email" placeholder="Email" name="email">
			</div>
			<div class="innerForm">
				<input class="textForm" type="password" placeholder="Password" name="password">
			</div>
			<div class="innerForm">
				<input class="submitButton" type="submit" placeholder="Sign In" value="Sign In">
			</div>
		</form>

	</div>
	<div class="footer">
	
	</div>
	<script src="js/function.js"></script>
	</body>
</html>