package br.com.softbank.relatorio.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.softbank.relatorio.dto.LaboratorioDTO;
import br.com.softbank.relatorio.integration.LaboratorioIntegration;

@Service
public class LaboratorioService {
	
	@Autowired
	private LaboratorioIntegration laboratorioIntegration;

	@HystrixCommand(fallbackMethod = "findAllFallback")
	public List<LaboratorioDTO> findAll(String Authorization) {
		return laboratorioIntegration.findAll(Authorization);
	}
	
	public List<LaboratorioDTO> findAllFallback(String Authorization) {
		return new ArrayList<>();
	}
}
