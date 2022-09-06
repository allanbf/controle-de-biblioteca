package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Dao;
import model.Livro;
import model.Usuario;

@WebServlet("/biblioteca")
public class BibliotecaServlet extends HttpServlet {
	
	Dao dao = new Dao();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Usuario user = (Usuario) req.getSession().getAttribute("usuario");
		if(user != null) {
			ArrayList<Livro> livros = dao.consultarLivros();
			for (Livro livro:livros)
				//System.out.println(livro);
			req.setAttribute("livros", livros);
			RequestDispatcher rd = req.getRequestDispatcher("biblioteca.jsp");
			rd.forward(req, resp);
		} else {
			resp.sendRedirect("login.jsp");
		}
	}
}
