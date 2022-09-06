package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Dao;
import model.Livro;
import model.Usuario;

@WebServlet("/editar_status")
public class EditarStatusServlet extends HttpServlet{
	
	Dao dao = new Dao(); 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Usuario user = (Usuario) req.getSession().getAttribute("usuario");
		if(user != null) {
			
			Integer id = Integer.parseInt(req.getParameter("id"));
			
			Livro livro = dao.consultarLivro(id);
			
			req.setAttribute("livro", livro);
			
			RequestDispatcher rd = req.getRequestDispatcher("editar_status.jsp");
			rd.forward(req, resp);
		} else {
			resp.sendRedirect("login.jsp");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Usuario user = (Usuario) req.getSession().getAttribute("usuario");
		if(user != null) {
			
			Integer id = Integer.parseInt(req.getParameter("id"));
			String status = req.getParameter("status");
			
			//System.out.println(id + ", " + status);
			
			Livro livro = dao.consultarLivro(id);
			
			if(livro != null) {
				dao.alterarStatusLivro(id, status);
			}
		} else {
			resp.sendRedirect("login.jsp");
		}
		resp.sendRedirect("biblioteca");
	}
}
