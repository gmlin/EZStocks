<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<title>Accounts</title>
<link rel="stylesheet" href="static/css/bootstrap.min.css">
<link rel="stylesheet" href="static/css/bootstrap-theme.min.css">

</head>
<body>
	<c:if test="${not empty requestScope.message}">
		<c:out value="${requestScope.message}" />
		<br>
	</c:if>
	<c:forEach items="${sessionScope.accounts}" var="account">
			Account Number: <c:out value="${account.accountNum}" />
		<br>
			Date Opened: <c:out value="${account.dateOpened}" />
		<br>
		<c:forEach items="${account.accountStocks}" var="accountStock">
			Stock: <c:out value="${accountStock.stock}" />
		<br>
			Number of Shares: <c:out value="${accountStock.numShares}" />
		<br>
	</c:forEach>
	</c:forEach>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/jquery-2.2.3.min.js"></script>
</body>
</html>