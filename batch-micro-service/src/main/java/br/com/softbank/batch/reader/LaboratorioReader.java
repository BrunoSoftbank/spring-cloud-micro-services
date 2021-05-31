package br.com.softbank.batch.reader;

import java.util.Iterator;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.softbank.batch.dto.LaboratorioFromFileDTO;
import br.com.softbank.batch.integration.XlsxIntegration;

@Component
public class LaboratorioReader implements ItemReader<LaboratorioFromFileDTO>, StepExecutionListener {

	private Iterator<LaboratorioFromFileDTO> laboratoriosIterator;

	@Autowired
	private XlsxIntegration xlsxIntegration;

	@Override
	public LaboratorioFromFileDTO read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		if (this.laboratoriosIterator != null && this.laboratoriosIterator.hasNext()) {
			return this.laboratoriosIterator.next();
		}
		return null;
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		this.laboratoriosIterator = xlsxIntegration.getFromFile().iterator();
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		return ExitStatus.COMPLETED;
	}
}
