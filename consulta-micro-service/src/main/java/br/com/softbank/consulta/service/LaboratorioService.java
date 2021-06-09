package br.com.softbank.consulta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.com.softbank.consulta.enuns.ErrosDefaultEnum;
import br.com.softbank.consulta.exception.BusinessException;
import br.com.softbank.consulta.exception.GenericException;
import br.com.softbank.consulta.integration.LaboratorioIntegration;
import br.com.softbank.consulta.response.LaboratorioResponse;
import br.com.softbank.consulta.response.ResponseDefault;
import feign.FeignException;

@Service
public class LaboratorioService {

	@Autowired
	private LaboratorioIntegration laboratorioIntegration;

	public LaboratorioResponse findById(String Authorization, Long id) {
		try {
			return laboratorioIntegration.findById(Authorization, id);
		} catch (Exception e) {
			if (e instanceof FeignException) {
				ResponseDefault response = new Gson().fromJson(((FeignException) e).contentUTF8(), ResponseDefault.class);
				throw new BusinessException(response);
			}
			throw new GenericException(ErrosDefaultEnum.GENERIC_ERROR.getDescricao());
		}
	}
}
