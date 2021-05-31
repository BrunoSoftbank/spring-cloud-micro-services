package br.com.softbank.usuario.controller;

import java.util.List;

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

import br.com.softbank.usuario.dto.UsuarioDTO;
import br.com.softbank.usuario.dto.UsuarioResponseDTO;
import br.com.softbank.usuario.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/usuarios")
@Api(tags = "Recurso de Usuários")
public class UsuarioController {
	
	@Autowired
	private UserService userService;

	@PostMapping
	@ApiOperation(value = "Cadastro de usuários")
	public ResponseEntity<UsuarioResponseDTO> save(@Valid @RequestBody UsuarioDTO request) {
		return new ResponseEntity<UsuarioResponseDTO>(userService.save(request.convertToEntity()), HttpStatus.CREATED);
	}
	
	@PutMapping("/{token}")
	@ApiOperation(value = "Ativação de usuário através do Token")
	public ResponseEntity<Void> update(@PathVariable String token) {
		userService.update(token);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResponseDTO> findById(@RequestHeader String Authorization, @PathVariable Long id) {
		return ResponseEntity.ok(userService.findById(Authorization, id));
	}
	
	@GetMapping
	public ResponseEntity<List<UsuarioResponseDTO>> findAll(@RequestHeader String Authorization,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "limit", required = false, defaultValue = "6") int limit) {
		return new ResponseEntity<List<UsuarioResponseDTO>>(userService.findAll(Authorization, page, limit), HttpStatus.OK);
	}
}
