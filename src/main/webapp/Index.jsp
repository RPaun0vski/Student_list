<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	String message=(String)request.getAttribute("message");
	message = message!=null ? message:"";
 %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Evidencija studenata</title>
</head>
<body>
	<h2>Unos studenata</h2>
	<form action="StudentController" method="get">
		Ime:<br>
		<input type="text" name="ime"><br>
		Prezime:<br>
		<input type="text" name="prezime"><br>
		Prosek:<br>
		<input type="text" name="prosek"><br>
		
		Sifra:<br>
		<input type="password" name="sifra">
		<input type="submit" name="action" value="Unesi">
	</form>
	<%= message %><br>
	<!-- ispis poruke na drugi nacin -->
	${requestScope.message }
	<br>
	<form action="StudentController" method="get">
		Uneti minimalni prosek za prikaz:<br>
		<input type="text" name="prosek"><br>
		<input type="submit" name="action" value="Prikazi filtrirano">
	</form>
	<br>
	<a href="StudentController?action=Prikazi">Prikazi sve</a>
</body>
</html>

<!--
h1-h6 tagovi za naslove od najveceg do najmanjeg

<form action="#" - ostani na iostoj stranici

Sifra se uvek salje na POST. Kada god se nesto od informacija saljem na bekend
onda se dalje sa POST, a kada nesto trazimo sa bekenda onda trazimo sa GET

Preporuka na se kroz rad i testiranje koristi GET pa tek nakon toga prebaci u 
POST gde ima potrebe jer su sa GET-om vidljivi svi parametri u URL 
(kao npr i uneta sifra)
	-->