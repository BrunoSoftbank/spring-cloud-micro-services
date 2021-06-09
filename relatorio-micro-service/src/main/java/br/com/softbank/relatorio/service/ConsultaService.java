package br.com.softbank.relatorio.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.softbank.relatorio.integration.ConsultaIntegration;
import br.com.softbank.relatorio.response.ConsultaResponse;

@Service
public class ConsultaService {

	@Autowired
	private ConsultaIntegration consultaIntegration;

	@HystrixCommand(fallbackMethod = "findAllFallback")
	public List<ConsultaResponse> findAll(String Authorization) {
		return consultaIntegration.findAll(Authorization);
	}
	
	public List<ConsultaResponse> findAllFallback(String Authorization) {
		return new ArrayList<>();
	}
}
