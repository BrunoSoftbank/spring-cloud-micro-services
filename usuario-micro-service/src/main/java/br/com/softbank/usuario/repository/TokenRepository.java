package br.com.softbank.usuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softbank.usuario.model.Token;
import br.com.softbank.usuario.model.Usuario;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

	Optional<Token> findByUsuario(Usuario user);
	Optional<Token> findByValor(String valor);
}
