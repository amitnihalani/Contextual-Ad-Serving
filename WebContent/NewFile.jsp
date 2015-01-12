<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Engine</title>
</head>
<body background="/WEB-INF/img/background.jpg" >
<form action="SearchServlet" method="post" name="queryForm">
<input type="text" name="queryInput"></input> <br />
<input type = "text" name = "id" /><br />
<input type = "text" name = "name" /> <br />
<input type = "text" name = "salary" /> <br />
<input type="submit" value="Submit"></input>
</form>
</body>
</html>