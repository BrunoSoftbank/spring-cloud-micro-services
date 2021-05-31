package br.com.softbank.exame.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class StatusServiceTest {

	@Test
	public void teste() {
		//cenário
		int numero1 = 19;
		int numero2 = 2;
		
		
		//execução
		int soma = numero1 + numero2;
		
		//verificações
		Assertions.assertThat(soma).isBetween(12, 24);

	}
}
