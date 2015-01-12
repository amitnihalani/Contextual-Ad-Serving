<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>NOVA Search</title>

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<script>
	/* $(document).ready(function(){
		//alert("Welcome");
		
	}); */
	function getMinPrice() {
		//alert("Changed!");
		var query = $("#queryInput").val();
		var value = {
			'postRequest' : 'getMinPrice',
			'queryInput' : query
		};
		$
				.ajax({
					url : "SearchServlet",
					type : "POST",
					data : value,
					success : function(data) {
						//alert(data);
						$("#minBidPrice").html(
								"   (Minimum Bid Price: $" + data + ")");
					}
				});

	}
</script>


</head>

<body background="/WEB-INF/img/background.jpg">
	<img src="logo.jpg" style="width: 30%; height: 3%; margin-left: 35%;">
	<br>
	<br>
	<div id="formDiv" align="center">
		<form action="BiddingServlet" method="post" name="queryForm"
			id="queryForm">
			<input type="hidden" name="postRequest" value="postBid" />
			<table width="50%"
				style="font-family: sans-serif; font-size: 20px; table-layout: fixed;">
				<tr>
					<td width="35%">Keyword:</td>
					<td width="35%"><input type="text" name="queryInput"
						id="queryInput"
						style="border: 1px solid black; height: 20px; font-size: 18px"
						onchange="getMinPrice();" /></td>

					<td width="30%"><div id="minBidPrice" style="font-size: 16px"></div></td>
				</tr>
				<tr>
					<td width="35%">Bid Amount:</td>
					<td width="35%"><input type="text" name="bidAmt"
						style="border: 1px solid black; height: 20px; font-size: 18px" /></td>
				</tr>
				<tr>
					<td width="35%">Advertisement Link:</td>
					<td width="35%"><input type="text" name="advLink"
						style="border: 1px solid black; height: 20px; font-size: 18px" /></td>
				</tr>
			</table>
			<br /> <input type="submit" value="Submit"
				style="border: 1px solid black; font-family: sans-serif; font-size: 18px"></input>
		</form>
	</div>
	<br />

	<div id="resultsDiv" style="font-family: sans-serif; font-size: 18px;"
		align="center">

		<%
			String success = "fail";
			success = (String) request.getAttribute("result");
			if ("success".equals(success)) {
		%>
		Bid Placed Successfully!
		<%
			} else if ("failure".equals(success)) {
		%>
		Failed to place the bid!
		<%
			}
		%>
	</div>
	<!-- <form action="SearchServlet" method="post" name="queryBidPrice" id="queryBidPrice">
		<input type="hidden" name="postRequest" value="getMinPrice" />
	
	</form> -->

</body>
</html>