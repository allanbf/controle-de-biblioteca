package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Dao;
import model.Livro;
import model.Status;
import model.Usuario;

@WebServlet("/cadastrar_livro")
public class CadastrarLivroServlet extends HttpServlet {
	
	Dao dao = new Dao();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Usuario user = (Usuario) req.getSession().getAttribute("usuario");
		
		if(user!=null) {
			Livro livro = new Livro();
			
			livro.setId(null);
			
			String temp = (String)req.getParameter("nome");
			livro.setNome(temp);
			
			temp  = (String)req.getParameter("autor");
			livro.setAutor(temp);
			
			//temp = (String)req.getAttribute("data_criacao");
			//SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
			//Date dataFormatada = formato.parse(temp);
			
			// create a java calendar instance
			Calendar calendar = Calendar.getInstance();

			// get a java date (java.util.Date) from the Calendar instance.
			// this java date will represent the current date, or "now".
			java.util.Date currentDate = calendar.getTime();

			// now, create a java.sql.Date from the java.util.Date
			java.sql.Date date = new java.sql.Date(currentDate.getTime());
			
			livro.setDataCriacao(date);
						
			temp  = (String) req.getParameter("status");
			if(temp.equals("DISPONIVEL")) {
				livro.setStatus(Status.DISPONIVEL);
			} else if(temp.equals("EMPRESTADO")) {
				livro.setStatus(Status.EMPRESTADO);
			} else {
				livro.setStatus(Status.INDISPONIVEL);
			}
			
			dao.cadastrarLivro(livro);
			
			resp.sendRedirect("biblioteca");
			
		} else {
			req.getSession().invalidate();
			resp.sendRedirect("login.jsp");
		}
	}
}
