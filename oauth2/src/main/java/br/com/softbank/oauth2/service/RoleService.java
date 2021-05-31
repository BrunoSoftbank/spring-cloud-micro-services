package br.com.softbank.oauth2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softbank.oauth2.domain.Role;
import br.com.softbank.oauth2.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public Role findByNome(String nome) {
		return this.roleRepository.findByNome(nome);
	}
}
