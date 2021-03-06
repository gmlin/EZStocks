<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<title>Edit Client</title>
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
		<c:if test="${sessionScope.role eq 'Manager'}">
			<div class="row">
				<div class="col-sm-6">
					<h3>Edit employee</h3>
					<form action="add_client" method="post">
						<input type="hidden" name="id" value="${param.id}"/>
						<div class="form-group">
							<label for="username">Username: </label> <input type="text"
								name="username" id="username" value="${requestScope.user.username}"/>
						</div>
						<div class="form-group">
							<label for="password">Password: </label> <input type="password"
								name="password" id="password" value="${requestScope.user.password}"/>
						</div>
						<div class="form-group">
							<label for="ssn">SSN: </label> <input type="number" name="ssn"
								id="ssn" value="${requestScope.user.ssn}"/>
						</div>
						<div class="form-group">
							<label for="first">First Name: </label> <input type="text"
								name="first" id="first" value="${requestScope.user.firstName}"/>
						</div>
						<div class="form-group">
							<label for="last">Last Name: </label> <input type="text"
								name="last" id="last" value="${requestScope.user.lastName}"/>
						</div>
						<div class="form-group">
							<label for="address">Address: </label> <input type="text"
								name="address" id="address" value="${requestScope.user.address}"/>
						</div>
						<div class="form-group">
							<label for="city">City: </label> <input type="text" name="city"
								id="city" value="${requestScope.user.city}"/>
						</div>
						<div class="form-group">
							<label for="state">State: </label> <select name="state"
								id="state">
								<option value="AL">Alabama</option>
								<option value="AK">Alaska</option>
								<option value="AZ">Arizona</option>
								<option value="AR">Arkansas</option>
								<option value="CA">California</option>
								<option value="CO">Colorado</option>
								<option value="CT">Connecticut</option>
								<option value="DE">Delaware</option>
								<option value="DC">District Of Columbia</option>
								<option value="FL">Florida</option>
								<option value="GA">Georgia</option>
								<option value="HI">Hawaii</option>
								<option value="ID">Idaho</option>
								<option value="IL">Illinois</option>
								<option value="IN">Indiana</option>
								<option value="IA">Iowa</option>
								<option value="KS">Kansas</option>
								<option value="KY">Kentucky</option>
								<option value="LA">Louisiana</option>
								<option value="ME">Maine</option>
								<option value="MD">Maryland</option>
								<option value="MA">Massachusetts</option>
								<option value="MI">Michigan</option>
								<option value="MN">Minnesota</option>
								<option value="MS">Mississippi</option>
								<option value="MO">Missouri</option>
								<option value="MT">Montana</option>
								<option value="NE">Nebraska</option>
								<option value="NV">Nevada</option>
								<option value="NH">New Hampshire</option>
								<option value="NJ">New Jersey</option>
								<option value="NM">New Mexico</option>
								<option value="NY">New York</option>
								<option value="NC">North Carolina</option>
								<option value="ND">North Dakota</option>
								<option value="OH">Ohio</option>
								<option value="OK">Oklahoma</option>
								<option value="OR">Oregon</option>
								<option value="PA">Pennsylvania</option>
								<option value="RI">Rhode Island</option>
								<option value="SC">South Carolina</option>
								<option value="SD">South Dakota</option>
								<option value="TN">Tennessee</option>
								<option value="TX">Texas</option>
								<option value="UT">Utah</option>
								<option value="VT">Vermont</option>
								<option value="VA">Virginia</option>
								<option value="WA">Washington</option>
								<option value="WV">West Virginia</option>
								<option value="WI">Wisconsin</option>
								<option value="WY">Wyoming</option>
							</select>
						</div>
						<div class="form-group">
							<label for="zip">Zip: </label> <input type="number" name="zip"
								id="zip" value="${requestScope.user.zipCode}"/>
						</div>
						<div class="form-group">
							<label for="phone">Phone: </label> <input type="number"
								name="phone" id="phone" value="${requestScope.user.phoneNumber}"/>
						</div>
						<div class="form-group">
							<label for="email">Email: </label> <input type="email"
								name="email" id="email" value="${requestScope.client.email}"/>
						</div>
						<div class="form-group">
							<label for="cc">CC: </label> <input type="number"
								name="cc" id="cc" value="${requestScope.client.creditCard}"/>
						</div>
						<button type="submit" class="btn btn-default">Change</button>
					</form>
				</div>
			</div>
		</c:if>
	</div>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/jquery-2.2.3.min.js"></script>
</body>
</html>