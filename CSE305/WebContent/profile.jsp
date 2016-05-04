<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<title>Profile</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="static/css/bootstrap.min.css">
<link rel="stylesheet" href="static/css/bootstrap-theme.min.css">

</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<ul class="nav navbar-nav navbar-left">
				<li><a href="profile">Profile</a></li>
				<c:if test="${sessionScope.role eq 'Client'}">
					<li><a href="accounts">Accounts</a></li>
				</c:if>
				<li><a href="stocks">Stocks</a></li>
				<li><a href="logout">Logout</a></li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<c:if test="${not empty requestScope.message}">
			<div class="row">
				<c:out value="${requestScope.message}" />
			</div>
		</c:if>
		<jsp:useBean id="user" class="beans.User" scope="request" />
		<ul class="list-group">
			<li class="list-group-item">Username: <c:out value="${user.username}" />
			</li>
			<li class="list-group-item">SSN: <c:out value="${user.ssn}" />
			</li>
			<li class="list-group-item">Last Name: <c:out value="${user.lastName}" />
			</li>
			<li class="list-group-item">First Name: <c:out value="${user.firstName}" />
			</li>
			<li class="list-group-item">Address: <c:out value="${user.address}" />
			</li>
			<li class="list-group-item">City: <c:out value="${user.city}" />
			</li>
			<li class="list-group-item">State: <c:out value="${user.state}" />
			</li>
			<li class="list-group-item">Zip: <c:out value="${user.zipCode}" />
			</li>
			<li class="list-group-item">Phone: <c:out value="${user.phoneNumber}" />
			</li>
			<c:if test="${sessionScope.role eq 'Client'}">
				<jsp:useBean id="client" class="beans.Client" scope="request" />
				<li class="list-group-item">Email: <c:out value="${client.email}" />
				</li>
				<li class="list-group-item">Credit Card: <c:out value="${client.creditCard}" />
				</li>
				<li class="list-group-item">Rating: <c:out value="${client.rating}" />
				</li>
			</c:if>
			<c:if test="${sessionScope.role ne 'Client'}">
				<jsp:useBean id="employee" class="beans.Employee" scope="request" />
				<li class="list-group-item">Start Date: <c:out value="${employee.startDate}" />
				</li>
			</c:if>
		</ul>
	</div>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/jquery-2.2.3.min.js"></script>
</body>
</html>