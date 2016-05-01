<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<title>EZStocks Login</title>
<link rel="stylesheet" href="static/css/bootstrap.min.css">
<link rel="stylesheet" href="static/css/bootstrap-theme.min.css">
</head>
<body>
	<c:if test="${not empty requestScope.message}">
		<c:out value="${requestScope.message}" />
	</c:if>
	<form action="login" method="POST">
		<label for="username">Username</label> <input id="username"
			name="username" type="text"><br> <label for="password">Password</label>
		<input id="password" name="password" type="password"><br>
		<input type="submit" value="Submit">
	</form>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/jquery-2.2.3.min.js"></script>
</body>
</html>