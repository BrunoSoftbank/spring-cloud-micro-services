package br.com.softbank.consulta.service;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.softbank.consulta.dto.ExameDTO;
import br.com.softbank.consulta.dto.TipoDTO;
import br.com.softbank.consulta.exception.BusinessException;
import br.com.softbank.consulta.exception.GenericException;
import br.com.softbank.consulta.integration.ExameIntegration;

@RunWith(SpringRunner.class)
public class ExameServiceTest {
	
	@InjectMocks
	private ExameService exameService;
	
	@MockBean
	private ExameIntegration exameIntegration;
	
	private ExameDTO exame;
	private TipoDTO tipo;
	private static final Long ID = 1L;
	private static final String AUTHORIZATION = "Bearer token value";
	
	@Before
	public void initialSetUp() {
		MockitoAnnotations.initMocks(this);
		
		exame = new ExameDTO();
		exame.setId(ID);
		exame.setNome("Exame de Sangue");
		
		tipo = new TipoDTO();
		tipo.setDescricao("Exame Clínico");
		exame.setTipo(tipo);
	}


	@Test
	public void shouldFindAnExameById() {
		exameService.findById(AUTHORIZATION, ID);
			
		Mockito.verify(exameIntegration, Mockito.times(1)).findById(AUTHORIZATION, ID);	
		Mockito.verifyNoMoreInteractions(exameIntegration);
		
		Assertions.assertThat(exame).isNotNull();
		Assertions.assertThatNoException();
	}
	
	@Test
	public void shouldThrowsAnBusinessException() {
		exameService.findById(AUTHORIZATION, ID);
		
		Mockito.when(exameService.findById(AUTHORIZATION, ID)).thenThrow(BusinessException.class);
					
		Mockito.verify(exameIntegration, Mockito.times(1)).findById(AUTHORIZATION, ID);
		
		Assertions.assertThatExceptionOfType(BusinessException.class);
	}
	

	@Test
	public void shouldThrowsAnGenericException() {
		exameService.findById(AUTHORIZATION, ID);
		
		Mockito.when(exameService.findById(AUTHORIZATION, ID)).thenThrow(GenericException.class);
					
		Mockito.verify(exameIntegration, Mockito.times(1)).findById(AUTHORIZATION, ID);
		
		Assertions.assertThatExceptionOfType(GenericException.class);
	}
}
