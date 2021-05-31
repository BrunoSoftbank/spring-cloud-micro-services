package br.com.softbank.laboratorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softbank.laboratorio.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	Endereco findByCidadeIgnoreCaseAndBairroIgnoreCaseAndRuaIgnoreCaseAndNumeroIgnoreCase(String cidade, String bairro, String rua, String numero);
}
