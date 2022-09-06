<%@page import="model.Livro"%>
<%@page import="model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Status</title>
</head>
<body>
	<div>
		<h1>Editar Status</h1>
	</div>
	<% 
	Usuario user = (Usuario) request.getSession().getAttribute("usuario");
	if(user != null){
		Livro livro = (Livro) request.getAttribute("livro");
	%>
	<div>
		<form action="editar_status" method="post">
			<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Nome</th>
					<th>Opção</th>
				</tr>
			</thead>
			<tr>
					<th><%= livro.getId() %></th>
					<input type="hidden" name="id" value="<%= livro.getId() %>">
					<th><%= livro.getNome() %></th>
					<th>
						<select name="status">
							<option value="DISPONIVEL">DISPONIVEL</option>
							<option value="EMPRESTADO">EMPRESTADO</option>
							<option value="INDISPONIVEL">INDISPONIVEL</option>
						</select>
					</th>
					<th>
						<button type="submit">Enviar</button>
					</th>
			</tr>
		</table>
		</form>
	</div>
	<%
	} else {
		response.sendRedirect("login.jsp");
	}
	%>
</body>
</html>