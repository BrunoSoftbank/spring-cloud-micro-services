package br.com.softbank.consulta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.com.softbank.consulta.dto.ExameDTO;
import br.com.softbank.consulta.dto.ResponseDTO;
import br.com.softbank.consulta.enuns.ErrosDefaultEnum;
import br.com.softbank.consulta.exception.BusinessException;
import br.com.softbank.consulta.exception.GenericException;
import br.com.softbank.consulta.integration.ExameIntegration;
import feign.FeignException;

@Service
public class ExameService {

	@Autowired
	private ExameIntegration exameIntegration;

	public ExameDTO findById(String Authorization, Long id) {
		try {
			return exameIntegration.findById(Authorization, id);
		} catch (Exception e) {
			if (e instanceof FeignException) {
				ResponseDTO response = new Gson().fromJson(((FeignException) e).contentUTF8(), ResponseDTO.class);
				throw new BusinessException(response);
			}
			throw new GenericException(ErrosDefaultEnum.GENERIC_ERROR.getDescricao());
		}
	}
}
