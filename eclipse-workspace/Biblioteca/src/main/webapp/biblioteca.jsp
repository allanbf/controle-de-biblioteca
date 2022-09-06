<%@page import="model.Livro"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Dao"%>
<%@page import="model.Usuario"%>
<%  %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>istema de Gerenciamento de Biblioteca</title>

<script type="text/javascript">
	function logout(){
		var teste=confirm("Deseja Realmente Sair?")
		if (teste==true)	{
			
		}
	}
</script>

</head>
<body>
	<%
	Usuario user = (Usuario) request.getSession().getAttribute("usuario");
	if(user != null){
	%>
		<div>
			<h1>Biblioteca</h1>
		</div>
		<div>
			<a href="cadastrar_livro.jsp">
			<button>Cadastrar Livro</button>
			</a>
			<% if(user.getTipo().equals("administrador")) {%>
			<a href="cadastrar_usuario.jsp">
				<button>Cadastrar Usuario</button>
			</a>
			<% } %>
			<a href="logout">
				<input type="button" value="Logout" onclick=logout()>
			</a>
		</div>
		<div>
			<table>
				<thead>
					<tr>
						<th>ID</th>
						<th>Livro</th>
						<th>Autor</th>
						<th>Data do Cadastro</th>
						<th>Status</th>
						<th>Opções</th>
					</tr>
				</thead>
					<% 
					ArrayList<Livro> livros = (ArrayList<Livro>) request.getAttribute("livros");
					for(Livro livro : livros){
					%>
						<tr>
							<th><%= livro.getId() %></th>
							<th><%= livro.getNome() %></th>
							<th><%= livro.getAutor() %></th>
							<th><%= livro.getDataCriacao() %></th>
							<th><%= livro.getStatus() %></th>
							<th>
								<a href="editar_status?id=<%=livro.getId()%>"><input type="button" value="Alterar Status"></a>
								<script type="text/javascript">
									function pergunta() {
            							if (confirm("Deseja confirmar essa operação?")) {
                							return true;
                							<% System.out.println("mensagem"); %>
            							} else {
											return false;
										}
        							}
    							</script>
								<a href="excluir_livro?id=<%=livro.getId()%>"><input type="button" value="Excluir" onclick=pergunta()></a>
							</th>
						</tr>
					<% } %>
			</table>
		</div>
	<%
	} else { 
		response.sendRedirect("login.jsp");
	}
	%>
</body>
</html>