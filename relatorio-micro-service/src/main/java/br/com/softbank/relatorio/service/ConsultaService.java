package br.com.softbank.relatorio.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.softbank.relatorio.dto.ConsultaDTO;
import br.com.softbank.relatorio.integration.ConsultaIntegration;

@Service
public class ConsultaService {

	@Autowired
	private ConsultaIntegration consultaIntegration;

	@HystrixCommand(fallbackMethod = "findAllFallback")
	public List<ConsultaDTO> findAll(String Authorization) {
		return consultaIntegration.findAll(Authorization);
	}
	
	public List<ConsultaDTO> findAllFallback(String Authorization) {
		return new ArrayList<>();
	}
}
