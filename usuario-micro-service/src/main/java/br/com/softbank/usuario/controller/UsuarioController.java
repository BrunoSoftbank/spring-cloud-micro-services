package br.com.softbank.usuario.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.softbank.usuario.converter.UsuarioConverter;
import br.com.softbank.usuario.model.Usuario;
import br.com.softbank.usuario.request.UsuarioRequest;
import br.com.softbank.usuario.response.UsuarioResponse;
import br.com.softbank.usuario.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/usuarios")
@Api(tags = "Recurso de Usuários")
public class UsuarioController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private UsuarioConverter usuarioConverter;
	

	@PostMapping
	@ApiOperation(value = "Cadastro de usuários")
	public ResponseEntity<UsuarioResponse> save(@Valid @RequestBody UsuarioRequest request) {
		Usuario usuario = userService.save(usuarioConverter.convertUsuarioRequestToUsuarioEntity(request));
		return new ResponseEntity<UsuarioResponse>(usuarioConverter.convertUsuarioEntityToUsuarioResponse(usuario), HttpStatus.CREATED);
	}
	
	@PutMapping
	@ApiOperation(value = "Ativação de usuário através do Token")
	public ResponseEntity<Void> update(@RequestParam String token) {
		userService.update(token);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResponse> findById(@RequestHeader String Authorization, @PathVariable Long id) {
		Usuario usuario = userService.findById(Authorization, id);
		return ResponseEntity.ok(usuarioConverter.convertUsuarioEntityToUsuarioResponse(usuario));
	}
	
	@GetMapping
	public ResponseEntity<List<UsuarioResponse>> findAll(@RequestHeader String Authorization,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "limit", required = false, defaultValue = "6") int limit) {
		List<Usuario> usuarios = userService.findAll(Authorization, page, limit);
		return new ResponseEntity<List<UsuarioResponse>>(usuarios.stream().map(usuario -> usuarioConverter.convertUsuarioEntityToUsuarioResponse(usuario)).collect(Collectors.toList()), HttpStatus.OK);
	}
}
