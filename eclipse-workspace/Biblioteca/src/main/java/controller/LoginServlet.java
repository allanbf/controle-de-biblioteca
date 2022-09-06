package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Dao;
import model.Usuario;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	
	Dao dao = new Dao();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session;
		
		String email = req.getParameter("email");
		String senha = req.getParameter("senha");
		
		Usuario user = verificarUsuario(email, senha);
		//System.out.println(user.toString());
		
		if(user!=null) {
			session = req.getSession(true);
			session.setAttribute("session", session);
			session.setAttribute("usuario", user);
			session.setAttribute("esta_logado", true);
			session.setMaxInactiveInterval(1200);
			resp.sendRedirect("biblioteca");
		} else {
			session = req.getSession(false); 
			req.setAttribute("session", session);
			resp.sendRedirect("login.jsp");
		}
	}
	
	private Usuario verificarUsuario(String email, String senha) {
		Usuario user = dao.consultarUsuario(email);
		if(user.getSenha().equals(senha)) {
			return user;
		} else {
			return null;
		}
		
	}
}
