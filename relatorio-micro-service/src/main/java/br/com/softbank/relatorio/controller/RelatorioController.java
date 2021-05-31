package br.com.softbank.relatorio.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.softbank.relatorio.enuns.ResourceEnum;
import br.com.softbank.relatorio.enuns.TipoRelatorioEnum;
import br.com.softbank.relatorio.service.RelatorioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/relatorios")
@Api(tags = "Recurso de Relatórios")
public class RelatorioController {

	@Autowired
	private RelatorioService relatorioService;

	@GetMapping
	@ApiOperation(value = "Relatório PDF, XLSX ou TXT do recurso selecionado")
	public void teste(@RequestHeader String  Authorization, @RequestParam ResourceEnum resource, @RequestParam TipoRelatorioEnum tipoRelatorio,  HttpServletResponse response) throws Exception {
		relatorioService.gerarRelatorio(Authorization, resource, tipoRelatorio, response);
		response.getOutputStream().close();
	}
}
