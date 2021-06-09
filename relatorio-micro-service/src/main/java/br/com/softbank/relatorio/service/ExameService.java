package br.com.softbank.relatorio.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.softbank.relatorio.integration.ExameIntegration;
import br.com.softbank.relatorio.response.ExameResponse;

@Service
public class ExameService {
	
	@Autowired
	private ExameIntegration exameIntegration;

	@HystrixCommand(fallbackMethod = "findAllFallback")
	public List<ExameResponse> findAll(String Authorization) {
		return exameIntegration.findAll(Authorization);
	}
	
	public List<ExameResponse> findAllFallback(String Authorization) {
		return new ArrayList<>();
	}
}
