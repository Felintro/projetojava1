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
	
			<form action="ServletLogin" method="post">
				<table>
	
					<tr>
						<td><label>Login: </label></td>
	
						<td><input name="login" type="text"></td>
					</tr>
	
					<tr>
						<td><label>Senha: </label></td>
	
						<td><input name="senha" type="password"></td>
					</tr>
	
					<tr>
						<td colspan="2"><input type="submit" value="Enviar"></td>
					</tr>

				</table>
	
			</form>
			
			<h4>${teste}</h4>
	
		</fieldset>
	
	</body>

</html>