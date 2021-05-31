package br.com.softbank.consultawebservice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.softbank.consultawebservice.model.Consulta;

public class ConsultaMpper implements RowMapper<Consulta> {

	@Override
	public Consulta mapRow(ResultSet rs, int rowNum) throws SQLException {
		Consulta consulta = new Consulta();
		consulta.setId(rs.getInt("id"));
		consulta.setUsuarioId(rs.getInt("usuario_id"));
		consulta.setExameId(rs.getInt("exame_id"));
		consulta.setLaboratorioId(rs.getInt("laboratorio_id"));
		return consulta;
	}
}
