<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<title>Stock History</title>
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
		<c:if test="${not empty requestScope.message}">
			<c:out value="${requestScope.message}" />
			<br>
		</c:if>
		<h3>
			<c:out value="${requestScope.stock}" />
			Price History
		</h3>
		<form action="stockHistory" method="GET">
			<input type="hidden" name="stock" id="stock" value="${requestScope.stock}" />
			<label for="start">Show prices since: </label>
			<input type="datetime-local" name="start" id="start" value="${param.start}" />
			<button type="submit" class="btn btn-default">Set</button>
		</form>
		<table class="table">
			<thead>
				<tr>
					<th>Date/Time</th>
					<th>Price</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.stockPrices}" var="stockPrice">
					<tr>
						<td><c:out value="${stockPrice.dateTime}" /></td>
						<td><c:out value="${stockPrice.price}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/jquery-2.2.3.min.js"></script>
</body>
</html>