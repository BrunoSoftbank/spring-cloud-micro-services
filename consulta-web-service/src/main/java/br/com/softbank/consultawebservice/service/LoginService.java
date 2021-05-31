package br.com.softbank.consultawebservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softbank.consultawebservice.dao.LoginDAO;
import br.com.softbank.consultawebservice.model.Login;

@Service
public class LoginService {
	
	@Autowired
	private LoginDAO loginDAO;

	public void insert(String nome, String senha) throws Exception {
		loginDAO.insert(nome, senha);
	}

	public List<Login> findAll() throws Exception {
		return loginDAO.findAll();
	}
	
	public Login findByUsuarioAndSenha(String usuario, String senha) throws Exception {
		return loginDAO.findByUsuarioAndSenha(usuario, senha);
	}
}
