<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Curso JSP</title>
	</head>
	
	<body>
	
		<h1>INDEX.JSP</h1>
	
		<fieldset>
	
			<form action="tela.jsp" method="post">
				<div>
					<label>Nome:</label> 
					<input name="nome"> <br> 
				</div>
				
				<div>	
					<label>Idade:</label>
					<input name="idade">
				</div>
				
				<input type="submit" value="Enviar">
				
			</form>
	
	
		</fieldset>
	
	</body>

</html>