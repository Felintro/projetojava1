<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html lang="pt-br">

	<head>
		<title>Projetinho Java</title>
		<meta charset = "UTF-8">
		<style type="text/css">
			body {
				background-color: #708090;
			}
		</style>
	</head>

	<body>

		<form action="" method="post" onsubmit="return validarDados() ? true : false">
	
			<table style="background: black; color: white; width: 250px">
	
				<tr>
					<td><label>Telinha de cadastro:</label></td>
				</tr>
	
			</table>
	
			<table style="background: black; color: white; width: 250px">
	
				<tr>
					<td><label>Nome:</label></td>
					<td><input type="text" id="nome" name="nome"
						style="width: 95%; border-radius: 5px"></td>
				</tr>
	
				<tr>
					<td><label style="color: white">Login:</label></td>
					<td><input type="text" id="login" name="login"
						style="width: 95%; border-radius: 5px"></td>
				</tr>
	
				<tr>
					<td><label style="color: white">Senha:</label></td>
					<td><input type="password" id="senha" name="senha"
						style="width: 95%; border-radius: 5px"></td>
				</tr>
				
				<tr>
					<td><label style="color: white">Idade:</label></td>
					<td><input type="text" id="idade" name="idade" 
						style="width: 95%; border-radius: 5px"></td>
				</tr>
	
				<tr>
					<td />
					<td><input type="submit" value="Cadastrar"
						style="width: 100%; border-radius: 5px;"></td>
				</tr>
	
			</table>
	
		</form>
	
		<%
		
		String nome = request.getParameter("nome");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String idade = request.getParameter("idade"); /* Alterar para tipo numérico */
		
		
		
		%>
	
	</body>
</html>