package br.com.softbank.usuario.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.softbank.usuario.dto.UsuarioEmailDTO;
import br.com.softbank.usuario.dto.UsuarioResponseDTO;
import br.com.softbank.usuario.enuns.ErrosDefaultEnum;
import br.com.softbank.usuario.exception.UsuarioAlreadyExistsException;
import br.com.softbank.usuario.exception.UsuarioNotFoundException;
import br.com.softbank.usuario.integration.Oauth2Integration;
import br.com.softbank.usuario.integration.RabbitEmailProducerIntegration;
import br.com.softbank.usuario.model.Role;
import br.com.softbank.usuario.model.Token;
import br.com.softbank.usuario.model.Usuario;
import br.com.softbank.usuario.repository.UsuarioRepository;

@Service
public class UserService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UsuarioRepository userRepository;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private RabbitEmailProducerIntegration emailProducer;
	@Autowired
	private Oauth2Integration oauth2Integration;
	
	public List<UsuarioResponseDTO> findAll(String Authorization, int page, int limit) {
		UsuarioResponseDTO authenticatedUser = getAuthenticatedUser(Authorization);
		List<Usuario> usuarios = userRepository.findAll(PageRequest.of(page, limit > 0 ? limit : 6, Sort.Direction.ASC, "nome")).getContent();
		
		if(authenticatedUser.getPerfil().equalsIgnoreCase("ROLE_USER")) {
			usuarios = usuarios.stream().filter(u -> u.getEmail().equalsIgnoreCase(authenticatedUser.getEmail())).collect(Collectors.toList());
		} 
		
		return usuarios.stream().map(u -> new UsuarioResponseDTO(u.getId(), u.getNome(), u.getEmail(), u.getIsAtivo(),
								u.getRoles().get(0).getNome(), u.getDataCadastro())).collect(Collectors.toList());
	}

	public UsuarioResponseDTO save(Usuario usuario) {
		LOG.warn(this.getClass().getSimpleName() + ".save(Usuario usuario) " + usuario);
		
		Optional<Usuario> usuarioOptional = this.userRepository.findByEmail(usuario.getEmail());
		if (usuarioOptional.isPresent()) {
			throw new UsuarioAlreadyExistsException(String.format(ErrosDefaultEnum.USUARIO_JA_CADASTRADO.getDescricao(), usuario.getEmail()));
		}

		usuario.getRoles().add(new Role(1, "ROLE_USER"));
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		usuario.setIsAtivo(false);
		usuario = userRepository.save(usuario);

		emailProducer.sendToQueue(new UsuarioEmailDTO(usuario.getNome(), usuario.getEmail(), tokenService.save(usuario).getValor()));
		return new UsuarioResponseDTO().fromEntity(usuario);
	}

	public void update(String valorToken) {
		LOG.warn(this.getClass().getSimpleName() + ".update(String valorToken) " + valorToken);
		
		Token token = tokenService.findByValor(valorToken);
		Usuario user = token.getUsuario();
		enableUser(user);
		tokenService.delete(token);
	}

	private void enableUser(Usuario user) {
		user.setIsAtivo(true);
		userRepository.save(user);
	}
	
	public UsuarioResponseDTO getAuthenticatedUser(String Authorization) {
		return oauth2Integration.getAuthenticatedUser(Authorization);
	}

	public UsuarioResponseDTO findById(String Authorization, Long id) {
		UsuarioResponseDTO authenticatedUser = this.getAuthenticatedUser(Authorization);
		Optional<Usuario> userOptional = userRepository.findById(id);
		
		if(userOptional.isPresent()) {
			Usuario usuario = userOptional.get();
			if(authenticatedUser.getPerfil().equalsIgnoreCase("ROLE_USER") && authenticatedUser.getId() != usuario.getId()) {
				throw new UsuarioNotFoundException(String.format(ErrosDefaultEnum.USUARIO_NAO_ENCONTRADO.getDescricao(), id));
			}
			return new UsuarioResponseDTO().fromEntity(usuario);
		}
		throw new UsuarioNotFoundException(String.format(ErrosDefaultEnum.USUARIO_NAO_ENCONTRADO.getDescricao(), id));
	}
}
