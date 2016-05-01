<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<title>EZStocks</title>
<link rel="stylesheet" href="static/css/bootstrap.min.css">
<link rel="stylesheet" href="static/css/bootstrap-theme.min.css">

</head>
<body>
	<c:if test="${not empty requestScope.message}">
		<c:out value="${requestScope.message}" /><br>
	</c:if>
	<c:if test="${not empty sessionScope.user}">
		<jsp:useBean id="user" class="beans.User" scope="session" />
		Username: <c:out value="${user.username}" /><br>
		Password: <c:out value="${user.password}" /><br>
		SSN: <c:out value="${user.ssn}" /><br>
		Last Name: <c:out value="${user.lastName}" /><br>
		First Name: <c:out value="${user.firstName}" /><br>
		Address: <c:out value="${user.address}" /><br>
		City: <c:out value="${user.city}" /><br>
		State: <c:out value="${user.state}" /><br>
		Zip: <c:out value="${user.zipCode}" /><br>
		Phone: <c:out value="${user.phoneNumber}" /><br>
	</c:if>
	Role: <c:out value="${sessionScope.role}" /><br>
	<c:if test="${sessionScope.role eq 'Client'}">
		<jsp:useBean id="client" class="beans.Client" scope="session" />
		Email: <c:out value="${client.email}" /><br>
		Credit Card: <c:out value="${client.creditCard}" /><br>
		Rating: <c:out value="${client.rating}" /><br>
		<c:forEach items="${sessionScope.accounts}" var="account">
			Account Number: <c:out value="${account.accountNum}" /><br>
			Date Opened: <c:out value="${account.dateOpened}" /><br>
		</c:forEach>
		<c:forEach items="${sessionScope.accountStocks}" var="accountStock">
			Account Number: <c:out value="${accountStock.account.accountNum}" /><br>
			Stock: <c:out value="${accountStock.stock}" /><br>
			Number of Shares: <c:out value="${accountStock.numShares}" /><br>
		</c:forEach>
	</c:if>
	<c:if test="${sessionScope.role ne 'Client'}">
		<jsp:useBean id="employee" class="beans.Employee" scope="session" />
		Id: <c:out value="${employee.id}" /><br>
		Start Date: <c:out value="${employee.startDate}" /><br>
		Hourly Rate: <c:out value="${employee.hourlyRate}" /><br>
	</c:if>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/jquery-2.2.3.min.js"></script>
</body>
</html>