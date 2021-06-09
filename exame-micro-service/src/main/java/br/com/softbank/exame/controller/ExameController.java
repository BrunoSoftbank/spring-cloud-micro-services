package br.com.softbank.exame.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import br.com.softbank.exame.converter.ExameConverter;
import br.com.softbank.exame.model.Exame;
import br.com.softbank.exame.request.ExameRequest;
import br.com.softbank.exame.response.ExameResponse;
import br.com.softbank.exame.service.ExameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/exames")
@Api(tags = "Recurso de Exames")
public class ExameController {

	@Autowired
	private ExameService exameService;
	@Autowired
	private ExameConverter exameConverter;
	
	@GetMapping
	@ApiOperation(value = "Listar todos os exames")
	public ResponseEntity<List<ExameResponse>> findAll(@RequestHeader String  Authorization) {
		return ResponseEntity.ok(exameService.findAll().stream().map(exame -> exameConverter.convertExameEntityToExameResponse(exame)).collect(Collectors.toList()));
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Busca um exame por id")
	public ResponseEntity<ExameResponse> findById(@RequestHeader String  Authorization, @PathVariable Long id) {
		Exame exame = exameService.findById(id);
		return ResponseEntity.ok(exameConverter.convertExameEntityToExameResponse(exame));
	}

	@PostMapping
	@ApiOperation(value = "Cadastro de exames")
	public ResponseEntity<ExameResponse>save(@RequestHeader String  Authorization, @Valid @RequestBody ExameRequest request) {
		Exame exame = exameService.save(exameConverter.convertExameRequestToExameEntity(request));
		return new ResponseEntity<ExameResponse>(exameConverter.convertExameEntityToExameResponse(exame), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Atualização de exames")
	public ResponseEntity<ExameResponse> update(@RequestHeader String  Authorization, @PathVariable Long id, @Valid @RequestBody ExameRequest request) {
		Exame exame = exameService.update(id, exameConverter.convertExameRequestToExameEntity(request));
		return ResponseEntity.ok(exameConverter.convertExameEntityToExameResponse(exame));
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleção de exames")
	public ResponseEntity<Void> deleteById(@RequestHeader String  Authorization, @PathVariable Long id) {
		exameService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PatchMapping("/{id}")
	@ApiOperation(value = "Ativar ou Desativar um exame")
	public ResponseEntity<ExameResponse> patch(@RequestHeader String  Authorization, @PathVariable Long id) {
		Exame exame = exameService.patch(id);
		return ResponseEntity.ok(exameConverter.convertExameEntityToExameResponse(exame));
	}
	
}
