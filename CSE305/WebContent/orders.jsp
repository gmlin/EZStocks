<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<title>Orders</title>
<link rel="stylesheet" href="static/css/bootstrap.min.css">
<link rel="stylesheet" href="static/css/bootstrap-theme.min.css">

</head>
<body>
	<c:if test="${not empty requestScope.message}">
		<c:out value="${requestScope.message}" />
		<br>
	</c:if>
	Account Number: <c:out value="${requestScope.accountNum}" />
	<br>
	<c:forEach items="${requestScope.orders}" var="order">
		Order Id: <c:out value="${order.id}" /><br>
		Stock: <c:out value="${order.stock}" /><br>
		Broker: <c:out value="${order.employee}" /><br>
		Number of Shares: <c:out value="${order.numShares}" /><br>
		Date Time: <c:out value="${order.dateTime}" /><br>
		Price Per Share: <c:out value="${order.pricePerShare}" /><br>
		Percentage: <c:out value="${order.percentage}" /><br>
		Price Type: <c:out value="${order.priceType}" /><br>
		Order Type: <c:out value="${order.orderType}" /><br>
		<c:if test="${order.status eq 'Completed'}">
			Status: <a href="transaction?order=${order.id}"><c:out value="${order.status}" /></a><br>
		</c:if>
		<c:if test="${order.status ne 'Completed'}">
			Status: <c:out value="${order.status}" /><br>
		</c:if>
	</c:forEach>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/jquery-2.2.3.min.js"></script>
</body>
</html>