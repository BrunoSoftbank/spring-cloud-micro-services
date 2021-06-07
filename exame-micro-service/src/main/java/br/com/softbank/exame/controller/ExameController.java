package br.com.softbank.exame.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softbank.exame.dto.AtualizarExameDTO;
import br.com.softbank.exame.dto.NovoExameDTO;
import br.com.softbank.exame.model.Exame;
import br.com.softbank.exame.service.ExameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/exames")
@Api(tags = "Recurso de Exames")
public class ExameController {

	@Autowired
	private ExameService exameService;
	
	@GetMapping
	@ApiOperation(value = "Listar todos os exames")
	public ResponseEntity<List<Exame>> findAll(@RequestHeader String  Authorization) {
		return ResponseEntity.ok(exameService.findAll());
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Busca um exame por id")
	public ResponseEntity<Exame> findById(@RequestHeader String  Authorization, @PathVariable Long id) {
		return ResponseEntity.ok(exameService.findById(id));
	}

	@PostMapping
	@ApiOperation(value = "Cadastro de exames")
	public ResponseEntity<Exame>save(@RequestHeader String  Authorization, @Valid @RequestBody NovoExameDTO request) {
		return new ResponseEntity<Exame>(exameService.save(request.convertToEntity()), HttpStatus.CREATED);
	}
	
	@PutMapping
	@ApiOperation(value = "Atualização de exames")
	public ResponseEntity<Exame> update(@RequestHeader String  Authorization, @Valid @RequestBody AtualizarExameDTO request) {
		return ResponseEntity.ok(exameService.update(request.convertToEntity()));
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleção de exames")
	public ResponseEntity<Void> deleteById(@RequestHeader String  Authorization, @PathVariable Long id) {
		exameService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PatchMapping("/{id}")
	@ApiOperation(value = "Ativar ou Desativar um exame")
	public ResponseEntity<Exame> patch(@RequestHeader String  Authorization, @PathVariable Long id) {
		return ResponseEntity.ok(exameService.patch(id));
	}
	
}
