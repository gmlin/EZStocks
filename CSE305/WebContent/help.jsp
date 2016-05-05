<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<title>EZStocks Help</title>
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
		<h1>Help Menu</h1>
		<h2>Manager Account</h2>

		<ol>
			<li>Profile</li>Personal information of this manager account.
			<li>Clients
				<ul>
					A list of information of all the clients.
					<li>To add a new client, fill out the blank and then click the
						add button.</li>
					<li>To see a list of stock suggestions for a given customer
						(based on that customer's past orders), click suggestions button
						on the rightmost side of the list.</li>
					<li>To edit client information, click the edit button.</li>
					<li>To delete the client, click the delete button.</li>
				</ul>
			</li>
			<li>Employees</li>Information of employees.
			<li>To edit employee information, click the edit button.</li>
			<li>To delete the employee, click the delete button.</li>
			<li>Mailing List</li>Customer mailing list.
			<li>Pending Orders
				<ul>
					Information of the pending orders.
					<li>To approve the order, click the approve button.</li>
					<li>To reject the order, click the reject button.</li>
				</ul>
			</li>
			<li>Orders
				<ul>
					A list of information of all the orders.
					<li>To search an order by stock symbol, fill out the blank
						after stock symbol and then click the search button.</li>
					<li>To search an order by client name, fill out the blank of
						First Name and Last Name and then click the search button.</li>
				</ul>
			</li>
			<li>Stats
				<ul>
					Sales Report.
					<li>To view a Sales Report, fill out the blank of Month and
						Year and then click the View Report button.</li>
				</ul>
			</li>
			<li>Stocks
				<ul>
					A list of information of all the stocks.
					<li>To search a stock by type, fill out the blank after type
						and then click the search button.</li>
					<li>To search a stock by keyword, fill out the blank after
						keyword and then click the search button.</li>
					<li>To set the stock price, fill out the blank and then click
						the set button.</li>
				</ul>
			</li>
			<li>Logout</li>Exit the program.
		</ol>

		<h2>Broker Account</h2>

		<ol>
			<li>Profile</li>Personal information of this broker account.
			<li>Clients
				<ul>
					A list of information of all the clients.
					<li>To add a new client, fill out the blank and then click the
						add button.</li>
					<li>To see a list of stock suggestions for a given customer
						(based on that customer's past orders), click suggestions button
						on the rightmost side of the list.</li>
					<li>To edit the client information, click the edit button.</li>
				</ul>
			</li>
			<li>Employees</li>Information of employees(excluding hourly rate).
			<li>Mailing List</li>Customer mailing list.
			<li>Pending Orders
				<ul>
					Information of the pending orders.
					<li>To approve the order, click approve.</li>
					<li>To reject the order, click reject.</li>
				</ul>
			</li>
			<li>Stocks
				<ul>
					A list of information of all the stocks.
					<li>To search a stock by type, fill out the blank after type
						and then click the search button.</li>
					<li>To search a stock by keyword, fill out the blank after
						keyword and then click the search button.</li>
				</ul>
			</li>
			<li>Logout</li>Exit the program.
		</ol>

		<h2>Client Account</h2>

		<ol>
			<li>Profile</li>Personal information of this client account.
			<li>Accounts
				<ul>
					A list of the accounts of the current client
					<li>To view the list of the stocks of the specific account,
						click portfolio button.</li>
					<li>To view the list of the orders of the specific account,
						click Orders button.</li>
				</ul>
			</li>
			<li>Stocks
				<ul>
					A list of information of all the stocks.
					<li>To search a stock by type, fill out the blank after type
						and then click the search button.</li>
					<li>To search a stock by keyword, fill out the blank after
						keyword and then click the search button.</li>
				</ul>
			</li>
			<li>Logout</li>Exit the program.
		</ol>
	</div>
</body>
</html>