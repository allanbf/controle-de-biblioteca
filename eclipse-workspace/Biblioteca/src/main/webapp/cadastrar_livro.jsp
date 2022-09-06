<%@page import="model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de Livro</title>
</head>
<body>
	<% 
	Usuario user = (Usuario) request.getSession().getAttribute("usuario");
	if(user != null) {
	%>
	<div>
		<h1>Cadastro de Livro</h1>
	</div>
	<form action="cadastrar_livro" method="post">
		<label>Nome:</label>
		<input name="nome" type="text">
		<label>Autor:</label>
		<input name="autor" type="text">
		<select name="status">
			<option value="DISPONIVEL">DISPONIVEL</option>
			<option value="EMPRESTADO">EMPRESTADO</option>
			<option value="INDISPONIVEL">INDISPONIVEL</option>
		</select>
		<button type="submit">Enviar</button>
	</form>
	<% 
	} else if(user == null) {
		request.getSession().invalidate();
		response.sendRedirect("login.jsp");
	} else {
		response.sendRedirect("biblioteca");
	}
	%>
</body>
</html>