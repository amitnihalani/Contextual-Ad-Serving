<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="edu.buffalo.ktmb.bean.QueryResult"%>
<%@page import="java.util.Map"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
	function updateAdClicks(obj) {
		document.getElementById('userQueries').value = document.getElementById('userQ').value;
		document.getElementById('clickedAdID').value = obj.getAttribute("href");
		document.AdForm.submit();
		return false;
	}
	
	function searchClick(obj) {
		document.MainForm.submit();
		return false;
	}
</script>
<%
	List<QueryResult> resultSet;
	String userQuery;
	userQuery = (String)request.getAttribute("userQuery");
	resultSet = (List<QueryResult>) request.getAttribute("result");
	Map<String, Map<String, List<String>>> snippetMap = (Map<String, Map<String, List<String>>>) request.getAttribute("snippetMap");
%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>NOVA Search</title>
</head>
<div>
	<img src="logo.jpg" style="width: 30%; height: 3%; margin-left: 35%;">
	<br> <br>

	<FORM NAME="MainForm" METHOD="POST" action="SearchServlet"
		style="width: 100%; margin-left: 15%;">
		<% if (userQuery == null) {%>
		<input type="text" id="userQ" name="userQuery"
			style="width: 60%; border: 1px solid black;">
		
		<%}
			 else {%>
		<input type="text" id="userQ" name="userQuery"
			style="width: 60%; border: 1px solid black;" value="<%=userQuery%>">

		<%} %>
		<input type="hidden" id="postReq" name="postRequest" value="search" />

		<INPUT TYPE="button" Style="border: 1px solid black;" VALUE="Search"
			onclick="searchClick(this); return false;">
	</FORM>

	<form name="AdForm" method="post" action="SearchServlet">
		<input type="hidden" id="postReq" name="postRequest" value="adUpdate" />
		<input type="hidden" id="clickedAdID" name="clickedAd" />
        <input type="hidden" id="userQueries" name="userQuery" />
	</form>
</div>

<div
	style="float: left; width: 60%; word-wrap: break-word; overflow: hidden;">
	<ul style="list-style-type: none;">
	
		<% if (resultSet!=null && resultSet.isEmpty()) {%>
			<b>Your search - <font color="red"> <%=userQuery%> </font> - did not match any documents.</b>
			<p><b>Suggestions:</b><br>  -Make sure all words are spelled correctly.<br>  -Try different keywords.
			<br>  -Try more general keywords.</p>
		<% } %>
		
		<%
				if (resultSet != null && !resultSet.isEmpty()) {
					Float qTime = (Float)request.getAttribute("queryTime"); %>
					<Font color="Grey" size="2">Response Time: <%=qTime%>s</Font><br><br>
					<% for (int i = 0; i < resultSet.size(); i++) {
			%>
			
			
		<li><a href="<%=resultSet.get(i).getUrl()%>"><%=resultSet.get(i).getTitle()%></a></li>
		<li><FONT SIZE=2 COLOR="009933"><%=resultSet.get(i).getUrl() %></FONT></li>
		<%		
					String snippet = "";
					if(snippetMap != null) {
						Map<String, List<String>> fieldSnippet = snippetMap.get(resultSet.get(i).getUrl());
						if(fieldSnippet != null) {
							List<String> snippetList = fieldSnippet.get("full_text");
							if(snippetList != null && snippetList.size() > 0) {
								for(String sn: snippetList) {
									snippet += sn;
								}
								snippet = snippet.replaceAll("<em>", "<b>");
								snippet = snippet.replaceAll("</em>", "</b>");
							}
						}
					}
					if(snippet.isEmpty() || "".equals(snippet)) {
						snippet = resultSet.get(i).getLead_paragraph();
					}
			%>

		<li><font size=3><%=snippet%></font></li>
		<br>
		<%
				}
			}else{%>
				
			<%}
			%>
	</ul>
</div>

<div style="float: right; width: 35%; word-wrap: break-word;">
	<ul style="list-style-type: none;">
		<%
					List<String> adList = (ArrayList<String>) request.getAttribute("adList");
					List<String> adSnippetList = (ArrayList<String>) request.getAttribute("adSnippetList");
					List<String> adTitleList = (ArrayList<String>) request.getAttribute("adTitleList");
					if (adList != null) {
				%>
					<br /><div style="font-size: 11pt;">Sponsored Ads<br /><br />
				<%
						for (int i=0; i<adList.size(); i++) {
				%>
					<li><a href="<%=adList.get(i)%>" onclick="updateAdClicks(this); return false;"><%=adTitleList.get(i)%></a></li>
					<li><%=adSnippetList.get(i) %></li>
					<br /><br />
		<%
					}
					}
				%>
			</div>
	</ul>
</div>





</body>

</html>
