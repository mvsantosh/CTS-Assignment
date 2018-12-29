<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Rambo bank Statement Process</title>
</head>
<body>

<div><h2>The Below are validation failed records.</h2></div>
<div>
<table border="1" width='400' cellspacing='0' cellpadding='0' border-spacing='0' style="margin:0;padding:0;">
<thead>
<tr>
<th>Transaction reference</td>
<th>Description</td>
</tr>
</thead>
<tbody>
<c:forEach items="${failedTxnList}" var="custStatement">
<tr>
<td>${custStatement.transactionReference}</td>
<td>${custStatement.description}</td>
</tr>
</c:forEach>
</tbody>
</table>
</div>
<br>
<div><a href="/index">Back</a></div>
</body>
</html>