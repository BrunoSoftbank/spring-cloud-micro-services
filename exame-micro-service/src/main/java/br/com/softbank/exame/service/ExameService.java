package br.com.softbank.exame.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.softbank.exame.enuns.ErrosDefaultEnum;
import br.com.softbank.exame.enuns.StatusEnum;
import br.com.softbank.exame.exception.ExameNotFoundException;
import br.com.softbank.exame.model.Exame;
import br.com.softbank.exame.model.Status;
import br.com.softbank.exame.model.Tipo;
import br.com.softbank.exame.repository.ExameRepository;

@Service
public class ExameService {

	private static final Logger LOG = LoggerFactory.getLogger(ExameService.class);

	@Autowired
	private StatusService statusService;
	@Autowired
	private TipoExameService tipoExameService;
	@Autowired
	private ExameRepository exameRepository;

	@Cacheable(cacheNames = "Exame", key = "#root.method.name")
	public List<Exame> findAll() {
		return exameRepository.findAll();
	}

	@Cacheable(cacheNames = "Exame", key = "#id")
	public Exame findById(Long id) {
		return exameRepository.findById(id).orElseThrow(() ->new ExameNotFoundException(String.format(ErrosDefaultEnum.EXAME_NAO_ENCONTRADO.getDescricao(), id)));	
	}

	@Transactional
	@CacheEvict(cacheNames = "Exame", allEntries = true)
	public void deleteById(Long id) {
		LOG.warn(this.getClass().getSimpleName() + ".deleteById(Long id) " + String.valueOf(id));
		exameRepository.delete(this.findById(id));
	}

	@Transactional
	@CacheEvict(cacheNames = "Exame", allEntries = true)
	public Exame save(Exame exame) {
		LOG.warn(this.getClass().getSimpleName() + ".save(Exame exame) " + exame);
		
		Status status = statusService.findById(StatusEnum.ATIVO.getId());
		exame.setStatus(status);

		Tipo tipo = tipoExameService.findById(exame.getTipo().getId());
		exame.setTipo(tipo);

		return exameRepository.save(exame);
	}

	@Transactional
	@CacheEvict(cacheNames = "Exame", allEntries = true)
	public Exame update(Long id, Exame exame) {
		LOG.warn(this.getClass().getSimpleName() + ".update(Long id, Exame exame) " + id + ", " + exame);

		Exame exameDB = this.findById(id);
		exameDB.setNome(exame.getNome());

		Tipo tipo = tipoExameService.findById(exame.getTipo().getId());
		exameDB.setTipo(tipo);

		return exameRepository.save(exameDB);
	}

	@CacheEvict(cacheNames = "Exame", allEntries = true)
	public Exame patch(Long id) {
		LOG.warn(this.getClass().getSimpleName() + ".patch(Long id) " + String.valueOf(id));

		Exame exame = this.findById(id);
		if (exame.getStatus().getId() == StatusEnum.ATIVO.getId()) {
			exame.setStatus(statusService.findById(StatusEnum.INATIVO.getId()));
		} else {
			exame.setStatus(statusService.findById(StatusEnum.ATIVO.getId()));
		}
		return exameRepository.save(exame);
	}
}
