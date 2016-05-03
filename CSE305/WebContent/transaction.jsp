<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<title>Transaction</title>
<link rel="stylesheet" href="static/css/bootstrap.min.css">
<link rel="stylesheet" href="static/css/bootstrap-theme.min.css">

</head>
<body>
	<c:if test="${not empty requestScope.message}">
		<c:out value="${requestScope.message}" />
		<br>
	</c:if>
	<jsp:useBean id="transaction" class="beans.Transaction" scope="request" />
	Order Id: <c:out value="${transaction.order}" />
	<br>
	Fee: <c:out value="${transaction.fee}" />
	<br>
	Price Per Share: <c:out value="${transaction.pricePerShare}" />
	<br>
	Date Time: <c:out value="${transaction.dateTime}" />
	<br>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/jquery-2.2.3.min.js"></script>
</body>
</html>