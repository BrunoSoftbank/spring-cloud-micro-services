package br.com.softbank.laboratorio.controller;

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

import br.com.softbank.laboratorio.dto.AtualizarLaboratorioDTO;
import br.com.softbank.laboratorio.dto.NovoLaboratorioDTO;
import br.com.softbank.laboratorio.model.Laboratorio;
import br.com.softbank.laboratorio.service.LaboratorioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/laboratorios")
@Api(tags = "Recurso de Laboratórios")
public class LaboratorioController {

	@Autowired
	private LaboratorioService laboratorioService;

	@GetMapping
	@ApiOperation(value = "Listar todos os laboratórios")
	public ResponseEntity<List<Laboratorio>> findAll(@RequestHeader String Authorization) {
		return ResponseEntity.ok(laboratorioService.findAll());
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Busca um laboratório por id")
	public ResponseEntity<Laboratorio> findById(@RequestHeader String Authorization, @PathVariable Long id) {
		return ResponseEntity.ok(laboratorioService.findById(id));
	}

	@PostMapping
	@ApiOperation(value = "Cadastro de laboratórios")
	public ResponseEntity<Laboratorio> save(@RequestHeader String Authorization,
			@Valid @RequestBody NovoLaboratorioDTO request) {
		return new ResponseEntity<Laboratorio>(laboratorioService.save(request.convertToEntity()), HttpStatus.CREATED);
	}

	@PutMapping
	@ApiOperation(value = "Atualização de laboratórios")
	public ResponseEntity<Laboratorio> update(@RequestHeader String Authorization,
			@Valid @RequestBody AtualizarLaboratorioDTO request) {
		return ResponseEntity.ok(laboratorioService.update(request.convertToEntity()));
	}

	@DeleteMapping
	@ApiOperation(value = "Deleção lógica de laboratórios")
	public ResponseEntity<Void> deleteById(@RequestHeader String Authorization, Long id) {
		laboratorioService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PatchMapping("/{id}")
	@ApiOperation(value = "Ativar ou Desativar um laboratório")
	public ResponseEntity<Laboratorio> patch(@RequestHeader String Authorization, @PathVariable Long id) {
		return ResponseEntity.ok(laboratorioService.patch(id));
	}
}
