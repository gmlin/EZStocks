<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<title>Stocks</title>
<link rel="stylesheet" href="static/css/bootstrap.min.css">
<link rel="stylesheet" href="static/css/bootstrap-theme.min.css">

</head>
<body>
	<c:if test="${not empty requestScope.message}">
		<c:out value="${requestScope.message}" /><br>
	</c:if>
	<c:forEach items="${requestScope.stocks}" var="stock">
		Stock: <c:out value="${stock.symbol}" />
		<br>
		Company: <c:out value="${stock.company}" />
		<br>
		Type: <c:out value="${stock.type}" />
		<br>
		Price: <c:out value="${stock.pricePerShare}" />
		<br>
		Number of Shares: <c:out value="${stock.numShares}" />
		<br>
	</c:forEach>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/jquery-2.2.3.min.js"></script>
</body>
</html>