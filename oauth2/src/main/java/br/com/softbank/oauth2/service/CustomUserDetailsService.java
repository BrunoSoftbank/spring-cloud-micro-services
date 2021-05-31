package br.com.softbank.oauth2.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.stereotype.Service;

import br.com.softbank.oauth2.domain.Usuario;
import br.com.softbank.oauth2.enuns.ErrosDefaultEnum;
import br.com.softbank.oauth2.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private RoleService roleService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
		if(!usuarioOptional.isPresent()) {
			throw new InvalidGrantException(ErrosDefaultEnum.USUARIO_JA_NAO_AUTORIZADO.getDescricao());			
		} 
		return usuarioOptional.get();
	}
	
	public void createUserAdminIfNotExists() {
		Optional<Usuario> usuarioOptional = this.usuarioRepository.findByEmail("zeus.softbank@gmail.com");
		if (!usuarioOptional.isPresent()) {
			Usuario usuario = new Usuario();
			usuario.setDataCadastro(LocalDate.now());
			usuario.setEmail("zeus.softbank@gmail.com");
			usuario.setNome("zeus");
			usuario.setRoles(Arrays.asList(roleService.findByNome("ROLE_ADMIN")));
			usuario.setSenha(new BCryptPasswordEncoder().encode("zeus123"));
			usuario.setIsAtivo(true);
			this.usuarioRepository.save(usuario);
		}
	}
}
