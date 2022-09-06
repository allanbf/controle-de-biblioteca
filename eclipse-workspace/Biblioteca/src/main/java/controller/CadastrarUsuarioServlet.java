package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Dao;
import model.Usuario;

@WebServlet("/cadastrar_usuario")
public class CadastrarUsuarioServlet extends HttpServlet {
	
	Dao dao = new Dao();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Usuario user = (Usuario) req.getSession().getAttribute("usuario");
		
		if(user!=null && user.getTipo().equals("administrador")) {
			
			Usuario usuario = new Usuario();
			String temp;
			
			usuario.setId(null);
			
			temp = (String) req.getParameter("nome");
			usuario.setNome(temp);
			
			temp = (String) req.getParameter("email");
			usuario.setEmail(temp);
			
			temp = (String) req.getParameter("senha");
			usuario.setSenha(temp);
			
			temp = (String) req.getParameter("tipo_usuario");
			usuario.setTipo(temp);
			
			dao.cadastrarUsuario(usuario);
			
		} else if (user == null) {
			req.getSession().invalidate();
		}
		resp.sendRedirect("biblioteca");
	}
}
