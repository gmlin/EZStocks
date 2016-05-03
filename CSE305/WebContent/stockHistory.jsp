<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<title>Stock History</title>
<link rel="stylesheet" href="static/css/bootstrap.min.css">
<link rel="stylesheet" href="static/css/bootstrap-theme.min.css">

</head>
<body>
	<c:if test="${not empty requestScope.message}">
		<c:out value="${requestScope.message}" /><br>
	</c:if>
	Stock: <c:out value="${requestScope.stock}" /><br>
	<c:forEach items="${requestScope.stockPrices}" var="stockPrice">
		Date Time: <c:out value="${stockPrice.dateTime}" />
		<br>
		Price: <c:out value="${stockPrice.price}" />
		<br>
	</c:forEach>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/jquery-2.2.3.min.js"></script>
</body>
</html>