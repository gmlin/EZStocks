<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<title>Create Order</title>
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
		<c:if test="${requestScope.type eq 'buy'}">
			<h3>
				Buy
				<c:out value="${requestScope.stock}" />
			</h3>
		</c:if>
		<c:if test="${requestScope.type eq 'sell'}">
			<h3>
				Sell
				<c:out value="${requestScope.stock}" />
			</h3>
		</c:if>
		<form action="create_order" method="post">
			<input type="hidden" name="stock" value="${requestScope.stock}" />
			<input type="hidden" name="type" value="${requestScope.type}" />
			<c:if test="${not empty requestScope.account}">
				<input type="hidden" name="account" value="${requestScope.account}" />
			</c:if>
			<c:if test="${requestScope.type eq 'buy'}">
				<div class="form-group">
					<label for="account">Account: </label> <select name="account"
						id="account" required>
						<c:forEach items="${requestScope.accounts}" var="account">
							<option value="${account.accountNum}">${account.accountNum}</option>
						</c:forEach>
					</select>
				</div>
			</c:if>
			<c:if test="${requestScope.type eq 'sell'}">
				<input type="hidden" value="${requestScope.account}" />
			</c:if>
			<div class="form-group">
				<label for="priceType">Price Type: </label> <select name="priceType"
					id="priceType" required>
					<option value="Market">Market</option>
					<option value="MarketOnClose">Market On Close</option>
					<option value="TrailingStop">Trailing Stop</option>
					<option value="HiddenStop">Hidden Stop</option>
				</select>
			</div>
			<div class="form-group">
				<label for="numShares">Num Shares: </label> <input type="number"
					name="numShares" id="numShares" required />
			</div>
			<div class="form-group">
				<label for="hiddenprice">Hidden Stop Price: </label> <input
					type="number" name="hiddenprice" id="hiddenprice" />
			</div>
			<div class="form-group">
				<label for="trailing">Trailing Stop %: </label> <input type="number"
					name="trailing" id="trailing" />
			</div>
			<button type="submit" class="btn btn-default">Order</button>
		</form>
	</div>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/jquery-2.2.3.min.js"></script>
</body>
</html>