package br.com.softbank.exame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softbank.exame.model.Status;
import br.com.softbank.exame.repository.StatusRepository;

@Service
public class StatusService {

	@Autowired
	private StatusRepository statusRepository;
	
	public Status findById(Long id) {
		return statusRepository.findById(id).get();
	}
}
