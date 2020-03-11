package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaobanco.SingleConnection;
import model.Userposjava;

public class UserposDAO {

	private static Connection connection;

	public UserposDAO() {
		connection = SingleConnection.getConnection();

	}

	public void salvar(Userposjava userposjava) {

		try {
			String sql = "insert into userposjava (nome, email) values (?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);

			insert.setString(1, userposjava.getNome());
			insert.setString(2, userposjava.getEmail());

			insert.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public List<Userposjava> listar() {
		List<Userposjava> list = new ArrayList<Userposjava>();

		try {

			String sql = "select * from userposjava";
			PreparedStatement statment = connection.prepareStatement(sql);
			ResultSet resultado = statment.executeQuery();

			while (resultado.next()) {
				Userposjava userposjava = new Userposjava();

				userposjava.setId(resultado.getLong("id"));
				userposjava.setNome(resultado.getString("nome"));
				userposjava.setEmail(resultado.getString("email"));

				list.add(userposjava);
			}

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		return list;
	}

	public Userposjava buscarPorId(Long id) {
		Userposjava retorno = new Userposjava();
		try {
			String sql = "select * from userposjava where id = " + id;
			PreparedStatement statment = connection.prepareStatement(sql);
			ResultSet resultado = statment.executeQuery();

			while (resultado.next()) {
				retorno.setId(resultado.getLong("id"));
				retorno.setNome(resultado.getString("nome"));
				retorno.setEmail(resultado.getString("email"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return retorno;
	}

	public void atualizar(Userposjava userpojava) {

		try {
			String sql = "update userposjava set nome = ? where id = " + userpojava.getId();
			PreparedStatement statment = connection.prepareStatement(sql);
			statment.setString(1, userpojava.getNome());

			statment.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void deletar(Long id) {

		try {
			String sql = "delete from userposjava where id = " + id;
			PreparedStatement deletar = connection.prepareStatement(sql);
			deletar.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

}
