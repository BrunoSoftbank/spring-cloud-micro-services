package br.com.softbank.laboratorio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softbank.laboratorio.model.Status;
import br.com.softbank.laboratorio.repository.StatusRepository;

@Service
public class StatusService {

	@Autowired
	private StatusRepository statusRepository;
	
	public Status findById(Long id) {
		return statusRepository.findById(id).get();
	}
}
