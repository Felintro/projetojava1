<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Curso JSP</title>
	</head>
	
	
	<body>
	
	<h1>TELA.JSP</h1>
	
	<%
		String nome = request.getParameter("nome");
		String idade = request.getParameter("idade");
		out.println("Nome: " + nome + "<br>Idade: " + idade);
	%>
	
	<label></label>
	
	</body>
	
</html>