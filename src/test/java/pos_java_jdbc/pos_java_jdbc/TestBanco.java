package pos_java_jdbc.pos_java_jdbc;

import java.util.List;

import org.junit.Test;

import dao.UserposDAO;
import model.Userposjava;

public class TestBanco {

	@Test
	public void initBanco() {

		UserposDAO dao = new UserposDAO();

		Userposjava userposjava = new Userposjava();

		userposjava.setNome("Nivea");
		userposjava.setEmail("nivea_rock@yahoo.com.br");
		dao.salvar(userposjava);
	}

	@Test
	public void initListar() {

		UserposDAO dao = new UserposDAO();

		List<Userposjava> list = dao.listar();
		for (Userposjava userposjava : list) {
			System.out.println(userposjava);
		}
	}

	@Test
	public void initBuscarPorId() {

		UserposDAO dao = new UserposDAO();

		Userposjava userposjava = dao.buscarPorId(3L);

		System.out.println(userposjava);
	}

	@Test
	public void initAtualizar() {

		UserposDAO dao = new UserposDAO();

		Userposjava userpojava = dao.buscarPorId(3L);

		userpojava.setNome("Maricanita Gonçalves de Souza");
		dao.atualizar(userpojava);

	}

	@Test
	public void deletar() {
		UserposDAO dao = new UserposDAO();

		dao.deletar(1L);
	}
}
