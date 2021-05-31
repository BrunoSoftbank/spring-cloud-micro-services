package br.com.softbank.usuario.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.softbank.usuario.enuns.ErrosDefaultEnum;
import br.com.softbank.usuario.exception.TokenInvalidException;
import br.com.softbank.usuario.model.Token;
import br.com.softbank.usuario.model.Usuario;
import br.com.softbank.usuario.repository.TokenRepository;

@Service
public class TokenService {
	
	@Autowired
	private TokenRepository tokenRepository;

	public Token save(Usuario usuario) {
		Optional<Token> tokenOptional = tokenRepository.findByUsuario(usuario);
		if(tokenOptional.isPresent()) {
			return tokenOptional.get();
		}
		return tokenRepository.save(new Token(new BCryptPasswordEncoder().encode(usuario.getEmail()).replaceAll("/", ""), usuario));
	}
	
	public Token findByValor(String valor) {
		Optional<Token> tokenOptional = tokenRepository.findByValor(valor);
		if(tokenOptional.isPresent()) {
			return tokenOptional.get();
		}
		throw new TokenInvalidException(String.format(ErrosDefaultEnum.TOKEN_INVALIDO.getDescricao(), valor));
	}

	public void delete(Token token) {
		tokenRepository.delete(token);	
	}
}
