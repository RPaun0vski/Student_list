<%@ page import="model.Student" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
String msg=(String)request.getAttribute("msg");    
msg = msg!=null ? msg:"";

Student student=(Student)request.getAttribute("student");
if(student!=null){
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Podaci o studentima</title>
</head>
<body>
	<h2>Informacije o studentu	</h2>
	<table align="center" width="80%" border="3" cellpadding="5">
		<caption>${requestScope.msg }</caption>
		<tr>
			<th>Ime</th>
			<th>Prezime</th>
			<th>Prosek</th>
		</tr>
		<tr>
			<td><%= student.getIme() %></td>
			<td><%= student.getPrezime() %></td>
			<td><%= student.getProsek() %></td>
		</tr>
		<tr>
			<td>${requestScope.student.ime }</td>
			<td>${requestScope.student.prezime }</td>
			<td>${requestScope.student.prosek }</td>
		</tr>
	
	</table>
	<%=msg %>
</body>
</html>
<% } else response.sendRedirect("Index.jsp"); %>

<!--    
nakon definisanja objekta tipa Student mozemo postaviti if uslov
if(student!=null){
.
.ceo HTML kod
.
 } else response.sendRedirect("Index.jsp"); 

-->
