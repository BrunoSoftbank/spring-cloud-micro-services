package br.com.softbank.oauth2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softbank.oauth2.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	public Role findByNome(String nome);
}
