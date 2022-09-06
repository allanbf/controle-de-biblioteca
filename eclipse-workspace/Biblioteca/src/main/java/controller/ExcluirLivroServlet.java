package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Dao;
import model.Usuario;

@WebServlet("/excluir_livro")
public class ExcluirLivroServlet extends HttpServlet {
	
	Dao dao = new Dao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Usuario user = (Usuario) req.getSession().getAttribute("usuario");
		if(user != null) {
			Integer id = Integer.parseInt(req.getParameter("id"));
			dao.excluirLivro(id);
		} else {
			resp.sendRedirect("login.jsp");
		}
		resp.sendRedirect("biblioteca");
	}

}
