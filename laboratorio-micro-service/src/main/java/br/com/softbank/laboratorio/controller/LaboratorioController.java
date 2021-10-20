package br.com.softbank.laboratorio.controller;

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

import br.com.softbank.laboratorio.converter.LaboratorioConverter;
import br.com.softbank.laboratorio.model.Laboratorio;
import br.com.softbank.laboratorio.request.LaboratorioRequest;
import br.com.softbank.laboratorio.response.LaboratorioResponse;
import br.com.softbank.laboratorio.service.LaboratorioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/laboratorios")
@Api(tags = "Recurso de Laboratórios")
public class LaboratorioController {

	@Autowired
	private LaboratorioService laboratorioService;
	@Autowired
	private LaboratorioConverter laboratorioConverter;

	@GetMapping
	@ApiOperation(value = "Listar todos os laboratórios")
	public ResponseEntity<List<LaboratorioResponse>> findAll(@RequestHeader String Authorization) {
		List<Laboratorio> laboratorios = laboratorioService.findAll();
		return ResponseEntity.ok(laboratorios.stream().map(laboratorio -> laboratorioConverter.convertLaboratorioEntityToLaboratorioResponse(laboratorio)).collect(Collectors.toList()));
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Busca um laboratório por id")
	public ResponseEntity<LaboratorioResponse> findById(@RequestHeader String Authorization, @PathVariable Long id) {
		Laboratorio laboratorio = laboratorioService.findById(id);
		return ResponseEntity.ok(laboratorioConverter.convertLaboratorioEntityToLaboratorioResponse(laboratorio));
	}

	@PostMapping
	@ApiOperation(value = "Cadastro de laboratórios")
	public ResponseEntity<LaboratorioResponse> save(@RequestHeader String Authorization, @Valid @RequestBody LaboratorioRequest request) {
		Laboratorio laboratorio = laboratorioService.save(laboratorioConverter.convertLaboratorioRequestToLaboratorioEntity(request));
		return new ResponseEntity<LaboratorioResponse>(laboratorioConverter.convertLaboratorioEntityToLaboratorioResponse(laboratorio), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualização de laboratórios")
	public ResponseEntity<LaboratorioResponse> update(@RequestHeader String Authorization, @PathVariable Long id, @Valid @RequestBody LaboratorioRequest request) {
		Laboratorio laboratorio = laboratorioService.update(id, laboratorioConverter.convertLaboratorioRequestToLaboratorioEntity(request));
		return ResponseEntity.ok(laboratorioConverter.convertLaboratorioEntityToLaboratorioResponse(laboratorio));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleção de laboratórios")
	public ResponseEntity<Void> deleteById(@RequestHeader String Authorization, @PathVariable Long id) {
		laboratorioService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PatchMapping("/{id}")
	@ApiOperation(value = "Ativar ou Desativar um laboratório")
	public ResponseEntity<LaboratorioResponse> patch(@RequestHeader String Authorization, @PathVariable Long id) {
		Laboratorio laboratorio = laboratorioService.patch(id);
		return ResponseEntity.ok(laboratorioConverter.convertLaboratorioEntityToLaboratorioResponse(laboratorio));
	}
}
