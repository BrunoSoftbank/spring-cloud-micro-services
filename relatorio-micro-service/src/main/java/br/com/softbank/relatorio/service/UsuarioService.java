package br.com.softbank.relatorio.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.softbank.relatorio.dto.RelatorioUsuarioDTO;
import br.com.softbank.relatorio.integration.UsuarioIntegration;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioIntegration usuarioIntegration;

	@HystrixCommand(fallbackMethod = "findAllFallback")
	public List<RelatorioUsuarioDTO> findAll(String Authorization) {
		return usuarioIntegration.findAll(Authorization);
	}
	
	public List<RelatorioUsuarioDTO> findAllFallback(String Authorization) {
		return new ArrayList<>();
	}
}
