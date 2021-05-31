package br.com.softbank.exame.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softbank.exame.enuns.ErrosDefaultEnum;
import br.com.softbank.exame.exception.TipoExameNotFoundException;
import br.com.softbank.exame.model.Tipo;
import br.com.softbank.exame.repository.TipoExameRepository;

@Service
public class TipoExameService {

	@Autowired
	private TipoExameRepository tipoExameRepository;
	
	public Tipo findById(Long id) {
		Optional<Tipo> timeExameOptional = tipoExameRepository.findById(id);
		if(timeExameOptional.isPresent()) {
			return timeExameOptional.get();
		}
		throw new TipoExameNotFoundException(String.format(ErrosDefaultEnum.TIPO_EXAME_NAO_ENCONTRADO.getDescricao(), id));
	}
}
