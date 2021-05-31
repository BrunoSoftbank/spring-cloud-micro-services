package br.com.softbank.batch.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Component;

import br.com.softbank.batch.dto.LaboratorioRequestDTO;

@Component
public class RestIntegration {

	private static final Logger LOG = LoggerFactory.getLogger(RestIntegration.class);

	@Autowired
	private OAuth2RestOperations oAuth2RestOperations;
	@Autowired
	private LaboratorioIntegration laboratorioIntegration;

	private String getAccessToken() {
		return "Bearer ".concat(oAuth2RestOperations.getAccessToken().getValue());
	}

	public void salvar(LaboratorioRequestDTO request) {
		if (request != null) {
			try {
				String Authorization = this.getAccessToken();
				this.laboratorioIntegration.save(Authorization, request);
			} catch (Exception e) {
				LOG.error("Problema ao cadastrar Laboratório: " + request);
			}
		}
	}
}
