package br.com.softbank.consultawebservice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.softbank.consultawebservice.model.Login;

public class LoginMapper implements RowMapper<Login> {

	@Override
	public Login mapRow(ResultSet rs, int rowNum) throws SQLException {
		Login login = new Login();
		login.setUsuario(rs.getString("nome"));
		login.setSenha(rs.getString("senha"));
		return login;
	}
}
