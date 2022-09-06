package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Dao {
	
	//db_biblioteca.tb_livros
	
	public int cadastrarLivro(Livro livro) {
		int resultado = 0;
		try {
			String sql = "INSERT INTO tb_livros VALUES (null, '"+
						livro.getNome() + "', '" +
						livro.getAutor() + "', '" +
						livro.getDataCriacao() + "', '" +
						livro.getStatus() + "');";
			System.out.println(sql);
		
			Connection conn = Conexao.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.executeUpdate();
			resultado = pst.getUpdateCount();
			conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return resultado;
	}
	
	public int alterarStatusLivro(Integer id, String status) {
		int resultado = 0;
		try {
			String sql = "UPDATE tb_livros SET status = '" + status + "' WHERE id = " + id + ";";
			System.out.println(sql);
			
			Connection conn = Conexao.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.executeUpdate();
			resultado = pst.getUpdateCount();
			conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return resultado;
	}
	
	public Livro consultarLivro(Integer id) {
		Livro livro = null;
		try {
			String sql = "SELECT * FROM tb_livros WHERE id = " + id + ";";
			System.out.println(sql);
			
			Connection conn = Conexao.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				livro = new Livro();
				livro.setId(rs.getInt("id"));
				livro.setNome(rs.getString("nome"));
				livro.setAutor(rs.getString("autor"));
				livro.setDataCriacao(rs.getDate("data_criacao"));
				
				if(rs.getString("status").equals("DISPONIVEL")) {
					livro.setStatus(Status.DISPONIVEL);
				} else if(rs.getString("status").equals("EMPRESTADO")) {
					livro.setStatus(Status.EMPRESTADO);
				} else {
					livro.setStatus(Status.INDISPONIVEL);
				}
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return livro;
	}
	
	public ArrayList<Livro> consultarLivros() {
		ArrayList<Livro> consulta = new ArrayList<>();
		try {
			String sql = "SELECT * FROM tb_livros;";
			System.out.println(sql);
			
			Connection conn = Conexao.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				Livro livro = new Livro();
				livro.setId(rs.getInt("id"));
				livro.setNome(rs.getString("nome"));
				livro.setAutor(rs.getString("autor"));
				livro.setDataCriacao(rs.getDate("data_criacao"));
				
				if(rs.getString("status").equals("DISPONIVEL")) {
					livro.setStatus(Status.DISPONIVEL);
				} else if(rs.getString("status").equals("EMPRESTADO")) {
					livro.setStatus(Status.EMPRESTADO);
				} else {
					livro.setStatus(Status.INDISPONIVEL);
				}
				//System.out.println(livro.toString());
				consulta.add(livro);
			}
			
			conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return consulta;
	}
	
	public int excluirLivro(Integer id) {
		int resultado = 0;
		
		try {
			String sql = "DELETE FROM tb_livros WHERE id = " + id + ";";
			System.out.println(sql);
			
			Connection conn = Conexao.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.executeUpdate();
			resultado = pst.getUpdateCount();
			conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return resultado;
	}
	
	//db_biblioteca.tb_usuarios
	
	public int cadastrarUsuario(Usuario user) {
		int resultado = 0;
		
		try {
			String sql = "INSERT INTO tb_usuarios VALUES (null, '" + 
							user.getNome() + "', '" + user.getEmail() + "', '" +
							user.getSenha() + "', '" + user.getTipo() + "');";
			System.out.println(sql);
			
			Connection conn = Conexao.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.executeUpdate();
			resultado = pst.getUpdateCount();
			conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return resultado;
	}
	
	public Usuario consultarUsuario(String email) {
		Usuario user = new Usuario();
		try {
			String sql = "SELECT * FROM tb_usuarios WHERE email = '" + email + "';";
			System.out.println(sql);
			
			Connection conn = Conexao.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				user.setId(rs.getInt("id"));
				user.setNome(rs.getString("nome"));
				user.setEmail(rs.getString("email"));
				user.setSenha(rs.getString("senha"));
				user.setTipo(rs.getString("tipo"));
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return user;
	}
	
}
