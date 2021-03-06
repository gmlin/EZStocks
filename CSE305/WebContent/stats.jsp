<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<title>Stats</title>
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
		<div class="row">
			<div class="col-sm-6">
				<h3>Sales report</h3>
				<form action="sales" method="get">
					<label for="month">Month: </label> <input type="number"
						name="month" id="month" /> <label for="year">Year: </label> <input
						type="number" name="year" id="year" />
					<button type="submit" class="btn btn-default">View Report</button>
				</form>
			</div>
			<div class="col-sm-6">
				<h3>Revenue summary</h3>
				<div class="row">
					<form action="revenue" method="get">
						<label for="stock">Stock: </label> <input type="text" name="stock"
							id="stock" />
						<button type="submit" class="btn btn-default">Submit</button>
					</form>
				</div>
				<div class="row">
					<form action="revenue" method="get">
						<label for="stockType">Stock type: </label> <input type="text"
							name="stocktype" id="stocktype" />
						<button type="submit" class="btn btn-default">Submit</button>
					</form>
				</div>
				<div class="row">
					</h3>
					<form action="revenue" method="get">
						<label for="first">First Name: </label> <input type="text"
							name="first" id="first" /> <label for="last">Last Name:
						</label> <input type="text" name="last" id="last" />
						<button type="submit" class="btn btn-default">Submit</button>
					</form>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-6">
				<h3>Top Broker</h3>
				<ul class="list-group">
					<li class="list-group-item"><c:out
							value="${requestScope.broker.name}" />
					<li class="list-group-item"><c:out
							value="${requestScope.broker.revenue}" /></li>
				</ul>
			</div>
			<div class="col-sm-6">
				<h3>Top Customer</h3>
				<ul class="list-group">
					<li class="list-group-item"><c:out
							value="${requestScope.customer.name}" />
					<li class="list-group-item"><c:out
							value="${requestScope.customer.revenue}" /></li>
				</ul>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-6">
				<h3>Top stocks</h3>
				<ul class="list-group">
					<c:forEach items="${requestScope.bestSellers}" var="bestSeller">
						<li class="list-group-item"><c:out
								value="${bestSeller.symbol}" /> - <c:out
								value="${bestSeller.numTraded}" /> traded</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/jquery-2.2.3.min.js"></script>
</body>
</html>