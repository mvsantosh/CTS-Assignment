<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Rambo bank statement process</title>
</head>
<body>
<h2 style="text-align:center">Welcome to Rabo Bank Statement Process</h2>
<form action="/importexcel" method="post" enctype="multipart/form-data">
<div class="container">
<hr border="1">
		<div class="starter-template">
		<div>Choose File : <input type="file" name="file"/></div>
		<input type="submit" value="Submit"/>
		</div>
	</div>
</form>
</body>
</html>