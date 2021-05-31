package br.com.softbank.consultawebservice.dao;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import br.com.softbank.consultawebservice.model.Login;

@Repository
public class LoginDAO extends JdbcDaoSupport {
	
	private static final String BASE_URL = "src/main/resources/db/query/login/";
	
	@Autowired 
	private DataSource dataSource;
	
	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}

	public List<Login> findAll() throws Exception {
		Path path = Paths.get((BASE_URL.concat("select_all.sql")));
		String query = new String(Files.readAllBytes(path)).toString();
		return getJdbcTemplate().query(query, new LoginMapper());
	}
	
	public Login findByUsuarioAndSenha(String usuario, String senha) throws Exception {
		Path path = Paths.get((BASE_URL.concat("select_by_filters.sql")));
		String query = new String(Files.readAllBytes(path)).toString();
		try {
			return getJdbcTemplate().queryForObject(query, new Object[] {usuario, senha}, new LoginMapper());
		} catch (Exception e) {
			return null;
		}	
	}
	
	public void insert(String nome, String senha) throws Exception {
		Path path = Paths.get((BASE_URL.concat("insert.sql")));
		String query = new String(Files.readAllBytes(path)).toString();
		getJdbcTemplate().update(query, new Object[] { nome, senha });
	}
}
