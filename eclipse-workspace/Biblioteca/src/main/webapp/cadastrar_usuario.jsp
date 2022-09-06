<%@page import="model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastrar Usuario</title>
</head>
<body>
	<% 
	Usuario user = (Usuario) request.getSession().getAttribute("usuario");
	if(user != null && user.getTipo().equals("administrador")) {
	%>
	<a href="biblioteca"><button>Voltar</button></a>
	<h1>Cadastrar Usuário</h1>
	<form action="cadastrar_usuario" method="post">
		<label>Nome:</label>
		<input type="text" name="nome">
		<br>
		<label>Email:</label>
		<input type="email" name="email">
		<br>
		<label>Senha:</label>
		<input type="password" name="senha">
		<br>
		<label>Tipo de usuário:</label>
		<select name="tipo_usuario">
			<option value="administrador">Administrador</option>
			<option value="usuario">Usuário Comum</option>
		</select>
		<br>
		<button type="submit">Enviar</button>
	</form>
		
	
	<% 
	} else if(user == null) {
		session.invalidate();
		response.sendRedirect("login.jsp");
	} else {
		response.sendRedirect("biblioteca");
	}
	%>
</body>
</html>