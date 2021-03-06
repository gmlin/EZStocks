<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<title>Stocks</title>
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
				<li><a href="help.jsp">Help</a></li>
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
		<c:if test="${sessionScope.role eq 'Client'}">
			<div class="row">
				<div class="col-sm-6">
					<h3>Best sellers</h3>
					<ol class="list-group">
						<c:forEach items="${requestScope.bestSellers}" var="bestSeller">
							<li class="list-group-item"><c:out
									value="${bestSeller.symbol}" /> - <c:out
									value="${bestSeller.numSold}" /> sold</li>
						</c:forEach>
					</ol>
				</div>
				<c:if test="${not empty requestScope.recommendations}">
					<div class="col-sm-6">
						<h3>Recommended for you</h3>
						<ul class="list-group">
							<c:forEach items="${requestScope.recommendations}"
								var="recommendation">
								<li class="list-group-item"><c:out
										value="${recommendation.symbol}" /></li>
							</c:forEach>
						</ul>
					</div>
				</c:if>
			</div>
		</c:if>
		<div class="row">
			<div class="col-sm-6">
				<h3>Search by type</h3>
				<form action="stocks" method="GET">
					<label for="type">Type: </label> <input type="text" id="type"
						name="type" />
					<button type="submit" class="btn btn-default">Search</button>
				</form>
			</div>
			<div class="col-sm-6">
				<h3>Search by keyword</h3>
				<form action="stocks" method="GET">
					<label for="keyword">Keyword: </label> <input type="text"
						id="keyword" name="keyword" />
					<button type="submit" class="btn btn-default">Search</button>
				</form>
			</div>
		</div>
		<c:if test="${sessionScope.role eq 'Manager'}">
			<form action="stocks" method="post">
				<input type="hidden" name="close" value="yes" />
				<button type="submit" class="btn btn-default">Close Market</button>
			</form>
		</c:if>
		<h3>Available Stocks</h3>
		<table class="table">
			<thead>
				<tr>
					<th>Stock</th>
					<th>Company</th>
					<th>Type</th>
					<th>Price</th>
					<th>Num Shares</th>
					<c:if test="${sessionScope.role eq 'Client'}">
						<th></th>
					</c:if>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.stocks}" var="stock">

					<tr>
						<td><c:out value="${stock.symbol}" /></td>
						<td><c:out value="${stock.company}" /></td>
						<td><c:out value="${stock.type}" /></td>
						<c:if test="${sessionScope.role eq 'Manager'}">
							<td>
								<form action="stocks" method="post">
									<input type="hidden" name="stock" value="${stock.symbol}" /> <input
										type="text" name="price" id="price"
										value="${stock.pricePerShare}" />
									<button type="submit" class="btn btn-default">Set</button>
								</form>
						</c:if>
						<c:if test="${sessionScope.role ne 'Manager'}">
							<td><c:out value="${stock.pricePerShare}" /></td>
						</c:if>
						<td><c:out value="${stock.numShares}" /></td>
						<c:if test="${sessionScope.role eq 'Client'}">
							<td><a href="create_order?type=buy&stock=${stock.symbol}">Buy</a></td>
						</c:if>
						<td><a href="stockHistory?stock=${stock.symbol}">Price
								History</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/jquery-2.2.3.min.js"></script>
</body>
</html>