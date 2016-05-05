<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<title>Accounts</title>
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
				<c:if test="${sessionScope.role ne 'Client'}">
				<li><a href="clients">Clients</a></li>
				<li><a href="employees">Employees</a></li>
				<li><a href="mailing_list">Mailing List</a></li>
				<li><a href="pending_orders">Pending Orders</a></li>
				</c:if>
				<c:if test="${sessionScope.role eq 'Manager'}">
				<li><a href="manager_orders">Orders</a></li>
				<li><a href="stats">Stats</a></li>
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
		<h3>My Accounts</h3>
		<table class="table">
			<thead>
				<tr>
					<th>Account Number</th>
					<th>Date Opened</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.accounts}" var="account">
					<tr>
						<td><c:out value="${account.accountNum}" /></td>
						<td><c:out value="${account.dateOpened}" /></td>
						<td><a href="portfolio?account=${account.accountNum}">Portfolio</a></td>
						<td><a href="orders?account=${account.accountNum}">Orders</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/jquery-2.2.3.min.js"></script>
</body>
</html>