
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Student"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%
String msg=(String)request.getAttribute("msg");    
msg = msg!=null ? msg:"";
ArrayList<Student> studenti=(ArrayList<Student>)request.getAttribute("studenti");
// kao parametar postavljamo ono sto je premet metode, u ovom slucaju Array List
if(studenti!=null){
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Spisak studenata</title>
</head>
<body>
	<h2>Informacije o studentu	</h2>
	<table align="center" width="80%" border="3" cellpadding="5">
		<caption>${requestScope.msg }</caption>
		<tr>
			
			<th>Ime</th>
			<th>Prezime</th>
			<th>Prosek</th>
			<th>AKCIJA</th>
			
		</tr>
		<%
		for(Student student : studenti){
		%>
		<tr>
			<td><%= student.getIme() %></td>
			<td><%= student.getPrezime() %></td>
			<td><%= student.getProsek() %></td>
			<td><a href="StudentController?action=DELETE&id=<%= student.getId() %>">DELETE</a></td>
		</tr>
		<%} %>
	</table>
	<%=msg %>
</body>
</html>
<% } else response.sendRedirect("Index.jsp"); %>