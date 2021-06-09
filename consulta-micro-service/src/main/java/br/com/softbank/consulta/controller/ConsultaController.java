package br.com.softbank.consulta.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softbank.consulta.request.ConsultaCustomRequest;
import br.com.softbank.consulta.response.ConsultaCustomResponse;
import br.com.softbank.consulta.service.ConsultaService;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/v1/consultas")
@Api(tags = "Recurso de Consultas")
public class ConsultaController {

	@Autowired
	private ConsultaService consultaService;
	
	@PostMapping
	public ResponseEntity<ConsultaCustomResponse> salvar(@RequestHeader String Authorization, @Valid @RequestBody ConsultaCustomRequest request) {
		return ResponseEntity.ok(consultaService.save(Authorization, request));
	}
	
	@GetMapping
	public ResponseEntity<List<ConsultaCustomResponse>> findAll(@RequestHeader String Authorization) {
		return ResponseEntity.ok(consultaService.findAll(Authorization));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ConsultaCustomResponse> findById(@RequestHeader String Authorization, @PathVariable Long id) {
		return ResponseEntity.ok(consultaService.findById(Authorization, id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@RequestHeader String Authorization, @PathVariable Long id) {
		consultaService.delete(Authorization, id);
		return ResponseEntity.noContent().build();
	}
}
