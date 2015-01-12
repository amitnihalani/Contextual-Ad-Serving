<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Bids</title>
</head>
<body>

	<form action = "BiddingServlet" method = "post">
			<input type = "hidden" name = "postRequest" value = "updateBids" />
			<input type = "submit" value = "Update" />
	</form>
	<br /><br />
	<%
		String success = (String)request.getAttribute("result");
		if("success".equals(success)) {
	%>
		Updated!
	<%
		}
	%>
</body>
</html>