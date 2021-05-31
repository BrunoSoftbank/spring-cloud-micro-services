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

import br.com.softbank.consultawebservice.model.Consulta;

@Repository
public class ConsultaDAO extends JdbcDaoSupport {

	private static final String BASE_URL = "src/main/resources/db/query/consulta/";

	@Autowired
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	public Consulta salvar(int usuarioId, int exameId, int laboratorioId) throws Exception {
		Path path = Paths.get((BASE_URL.concat("insert.sql")));
		String query = new String(Files.readAllBytes(path)).toString();
		getJdbcTemplate().update(query, new Object[] { usuarioId, exameId, laboratorioId });
		return this.findLastSaved();
	}

	public Consulta findById(int id) {
		try {
			Path path = Paths.get((BASE_URL.concat("select_by_id.sql")));
			String query = new String(Files.readAllBytes(path)).toString();
			return getJdbcTemplate().queryForObject(query, new Object[] { id }, new ConsultaMpper());
		} catch (Exception e) {
			return null;
		}
	}

	public Consulta findByFilters(int usuarioId, int exameId, int laboratorioId) {
		try {
			Path path = Paths.get((BASE_URL.concat("select_by_filters.sql")));
			String query = new String(Files.readAllBytes(path)).toString();
			return getJdbcTemplate().queryForObject(query, new Object[] { usuarioId, exameId, laboratorioId },
					new ConsultaMpper());
		} catch (Exception e) {
			return null;
		}
	}

	public void delete(int id) throws Exception {
		Path path = Paths.get((BASE_URL.concat("delete.sql")));
		String query = new String(Files.readAllBytes(path)).toString();
		getJdbcTemplate().update(query, new Object[] { id });
	}

	public List<Consulta> findAll() throws Exception {
		Path path = Paths.get((BASE_URL.concat("select_all.sql")));
		String query = new String(Files.readAllBytes(path)).toString();
		return getJdbcTemplate().query(query, new ConsultaMpper());
	}

	private Consulta findLastSaved() throws Exception {
		Path path = Paths.get((BASE_URL.concat("select_last_saved.sql")));
		String query = new String(Files.readAllBytes(path)).toString();
		return getJdbcTemplate().queryForObject(query, new ConsultaMpper());
	}
}
