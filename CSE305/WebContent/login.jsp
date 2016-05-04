<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<title>EZStocks Login</title>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="static/css/bootstrap.min.css">
<link rel="stylesheet" href="static/css/bootstrap-theme.min.css">
</head>
<body>
	<div class="container">
		<c:if test="${not empty requestScope.message}">
			<div class="row">
				<c:out value="${requestScope.message}" />
			</div>
		</c:if>
		<form action="login" method="POST" class="form-horizontal">
			<div class="form-group">
				<label for="username" class="control-label col-sm-2">
					Username: </label>
				<div class="col-sm-10">
					<input id="username" name="username" type="text"
						class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label for="password" class="control-label col-sm-2">
					Password: </label>
				<div class="col-sm-10">
					<input id="password" name="password" type="password"
						class="form-control">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-10 col-sm-offset-2">
					<button type="submit" class="btn btn-default">Login</button>
				</div>
			</div>
		</form>
	</div>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/jquery-2.2.3.min.js"></script>
</body>
</html>