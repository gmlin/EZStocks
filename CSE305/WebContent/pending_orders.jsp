<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<title>Pending Orders</title>
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
		<h3>Pending Orders</h3>
		<table class="table">
			<thead>
				<tr>
					<th>Order Id</th>
					<th>Client</th>
					<th>Stock</th>
					<th>Price</th>
					<th>Percent</th>
					<th>Num Shares</th>
					<th>Price Type</th>
					<th>Order Type</th>
					<th>Time Placed</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${requestScope.orders}" var="order">
					<tr>
						<td><c:out value="${order.id}" /></td>
						<td><c:out value="${order.clientName}" /></td>
						<td><c:out value="${order.stock}" /></td>
						<td><c:out value="${order.pricePerShare}" /></td>
						<td><c:if test="${order.priceType eq 'TrailingStop'}">
								<c:out value="${order.percentage}" />
							</c:if> <c:if test="${order.priceType ne 'TrailingStop'}">
						N/A</c:if></td>
						<td><c:out value="${order.numShares}" /></td>
						<td><c:out value="${order.priceType}" /></td>
						<td><c:out value="${order.orderType}" /></td>
						<td><c:out value="${order.dateTime}" /></td>
						<td><a href="modify_order?status=Approved&order=${order.id}">Approve</a></td>
						<td><a href="modify_order?status=Rejected&order=${order.id}">Reject</a></td>
					</tr>

				</c:forEach>
			</tbody>
		</table>
	</div>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/jquery-2.2.3.min.js"></script>
</body>
</html>