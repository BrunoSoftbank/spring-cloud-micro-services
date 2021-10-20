package br.com.softbank.exame.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.softbank.exame.enuns.ErrosDefaultEnum;
import br.com.softbank.exame.exception.ExameNotFoundException;
import br.com.softbank.exame.model.Exame;
import br.com.softbank.exame.repository.ExameRepository;

@Service
public class ExameService {

	private static final Logger LOG = LoggerFactory.getLogger(ExameService.class);

	@Autowired
	private ExameRepository exameRepository;

	@Cacheable(cacheNames = "Exame", key = "#root.method.name")
	public List<Exame> findAll() {
		return exameRepository.findAll();
	}

	@Cacheable(cacheNames = "Exame", key = "#id")
	public Exame findById(String id) {
		return exameRepository.findById(Long.valueOf(id)).orElseThrow(() ->new ExameNotFoundException(String.format(ErrosDefaultEnum.EXAME_NAO_ENCONTRADO.getDescricao(), id)));	
	}

	@CacheEvict(cacheNames = "Exame", allEntries = true)
	public void deleteById(String id) {
		LOG.warn(this.getClass().getSimpleName() + ".deleteById(Long id) " + String.valueOf(id));
		exameRepository.delete(this.findById(id));
	}

	@CacheEvict(cacheNames = "Exame", allEntries = true)
	public Exame save(Exame exame) {
		LOG.warn(this.getClass().getSimpleName() + ".save(Exame exame) " + exame);
		return exameRepository.save(exame);
	}

	@CacheEvict(cacheNames = "Exame", allEntries = true)
	public Exame update(String id, Exame exame) {
		LOG.warn(this.getClass().getSimpleName() + ".update(Long id, Exame exame) " + id + ", " + exame);

		Exame exameDB = this.findById(id);
		exameDB.setNome(exame.getNome());
		exameDB.setDescricao(exame.getDescricao());
		return exameRepository.save(exameDB);
	}
}
