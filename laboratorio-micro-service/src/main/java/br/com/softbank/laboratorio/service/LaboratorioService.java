package br.com.softbank.laboratorio.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.softbank.laboratorio.enuns.ErrosDefaultEnum;
import br.com.softbank.laboratorio.enuns.StatusEnum;
import br.com.softbank.laboratorio.exception.LaboratorioAlreadyExistsException;
import br.com.softbank.laboratorio.exception.LaboratorioNotFoundException;
import br.com.softbank.laboratorio.model.Endereco;
import br.com.softbank.laboratorio.model.Laboratorio;
import br.com.softbank.laboratorio.model.Status;
import br.com.softbank.laboratorio.repository.LaboratorioRepository;

@Service
public class LaboratorioService {

	private static final Logger LOG = LoggerFactory.getLogger(LaboratorioService.class);

	@Autowired
	private StatusService statusService;
	@Autowired
	private EnderecoService enderecoService;

	@Autowired
	private LaboratorioRepository laboratorioRepository;

	@Cacheable(cacheNames = "Laboratorio", key = "#root.method.name")
	public List<Laboratorio> findAll() {
		return laboratorioRepository.findAll();
	}

	@Cacheable(cacheNames = "Laboratorio", key = "#id")
	public Laboratorio findById(Long id) {
		Optional<Laboratorio> laboratorioOptional = laboratorioRepository.findById(id);
		if (laboratorioOptional.isPresent()) {
			return laboratorioOptional.get();
		}
		throw new LaboratorioNotFoundException(
				String.format(ErrosDefaultEnum.LABORATORIO_NAO_ENCONTRADO.getDescricao(), id));
	}

	@Transactional
	@CacheEvict(cacheNames = "Laboratorio", allEntries = true)
	public void deleteById(Long id) {
		LOG.warn(this.getClass().getSimpleName() + ".deleteById(Long id) " + String.valueOf(id));

		Laboratorio laboratorio = this.findById(id);
		laboratorioRepository.delete(laboratorio);

	}

	@Transactional
	@CacheEvict(cacheNames = "Laboratorio", allEntries = true)
	public Laboratorio save(Laboratorio laboratorio) {
		LOG.warn(this.getClass().getSimpleName() + ".save(Laboratorio laboratorio) " + laboratorio);
		
		Optional<Laboratorio> laboratorioOptional = this.laboratorioRepository.findByNome(laboratorio.getNome());
		if(laboratorioOptional.isPresent()) {
			throw new LaboratorioAlreadyExistsException(
					String.format(ErrosDefaultEnum.LABORATORIO_JA_CADASTRADO.getDescricao(), laboratorio.getNome()));
		}

		Status status = statusService.findById(StatusEnum.ATIVO.getId());
		laboratorio.setStatus(status);

		Endereco endereco = enderecoService.findByCidadeAndBairroAndRuaAndNumero(laboratorio.getEndereco().getCidade(),
				laboratorio.getEndereco().getBairro(), laboratorio.getEndereco().getRua(),
				laboratorio.getEndereco().getNumero());

		if (endereco == null) {
			endereco = enderecoService.save(laboratorio.getEndereco());
		}
		laboratorio.setEndereco(endereco);
		return laboratorioRepository.save(laboratorio);
	}

	@Transactional
	@CacheEvict(cacheNames = "Laboratorio", allEntries = true)
	public Laboratorio update(Laboratorio laboratorio) {

		LOG.warn(this.getClass().getSimpleName() + ".update(Laboratorio laboratorio) " + laboratorio);

		Laboratorio laboratorioDB = this.findById(laboratorio.getId());
		
		Optional<Laboratorio> laboratorioOptional = this.laboratorioRepository.findByNome(laboratorio.getNome());
		if(laboratorioOptional.isPresent() && laboratorioDB.getId().equals(laboratorioOptional.get().getId())) {
			throw new LaboratorioAlreadyExistsException(
					String.format(ErrosDefaultEnum.LABORATORIO_JA_CADASTRADO.getDescricao(), laboratorio.getNome()));
		}
		
		laboratorioDB.setNome(laboratorio.getNome());

		Endereco endereço = enderecoService.findByCidadeAndBairroAndRuaAndNumero(laboratorio.getEndereco().getCidade(),
				laboratorio.getEndereco().getBairro(), laboratorio.getEndereco().getRua(), laboratorio.getEndereco().getNumero());
		if (endereço == null) {
			endereço = enderecoService.save(laboratorio.getEndereco());
		}
		laboratorioDB.setEndereco(endereço);

		return laboratorioRepository.save(laboratorioDB);
	}

	@CacheEvict(cacheNames = "Laboratorio", allEntries = true)
	public Laboratorio patch(Long id) {
		LOG.warn(this.getClass().getSimpleName() + ".patch(Long id) " + String.valueOf(id));

		Laboratorio laboratorio = this.findById(id);
		if (laboratorio.getStatus().getId() == StatusEnum.ATIVO.getId()) {
			laboratorio.setStatus(statusService.findById(StatusEnum.INATIVO.getId()));
		} else {
			laboratorio.setStatus(statusService.findById(StatusEnum.ATIVO.getId()));
		}
		return laboratorioRepository.save(laboratorio);
	}
}
