package br.com.softbank.relatorio.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softbank.relatorio.dto.RelatorioConsultaDTO;
import br.com.softbank.relatorio.dto.RelatorioLaboratorioDTO;
import br.com.softbank.relatorio.enuns.ErrosDefaultEnum;
import br.com.softbank.relatorio.enuns.ResourceEnum;
import br.com.softbank.relatorio.enuns.TipoRelatorioEnum;
import br.com.softbank.relatorio.exception.RecursoNotFoundException;
import br.com.softbank.relatorio.exception.TipoRelatorioNotFoundException;

@Service
public class RelatorioService {

	@Autowired
	private ExameService exameService;
	@Autowired
	private LaboratorioService laboratorioService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private ConsultaService consultaService;
	@Autowired
	private PdfService pdfService;
	@Autowired
	private XlsxService xlsxService;
	@Autowired
	private TxtService txtService;
	@Autowired
	private WordService wordService;

	public void gerarRelatorio(String Authorization, ResourceEnum resource, TipoRelatorioEnum tipoRelatorio,
			HttpServletResponse response) throws Exception {
		List<?> list = buscarDados(Authorization, resource);
		if (list != null && !list.isEmpty()) {
			switch (tipoRelatorio) {
			case pdf:
				this.pdfService.gerarRelatorio(list, response);
				break;
			case xlsx:
				this.xlsxService.gerarRelatorio(list, response);
				break;
			case txt:
				this.txtService.gerarRelatorio(list, response);
				break;
			case word:
				this.wordService.gerarRelatorio(list, response);
				break;
			default:
				throw new TipoRelatorioNotFoundException(
						String.format(ErrosDefaultEnum.TIPO_RELATORIO_NAO_ENCONTRADO.getDescricao(), tipoRelatorio));
			}
		}
	}

	private List<?> buscarDados(String Authorization, ResourceEnum resource) {
		switch (resource) {
		case exames:
			return exameService.findAll(Authorization);
		case laboratorios:
			return laboratorioService.findAll(Authorization).stream()
					.map(l -> new RelatorioLaboratorioDTO(l.getId(), l.getNome(), l.getEndereco().getCidade(),
							l.getEndereco().getBairro(), l.getEndereco().getRua(), l.getEndereco().getNumero(),
							l.getStatus().getDescricao()))
					.collect(Collectors.toList());
		case usuarios:
			return usuarioService.findAll(Authorization);
		case consultas:
			return consultaService
					.findAll(Authorization).stream().map(c -> new RelatorioConsultaDTO(c.getId(),
							c.getUsuarioDTO().getNome(), c.getExameDTO().getNome(), c.getLaboratorioDTO().getNome()))
					.collect(Collectors.toList());
		default:
			throw new RecursoNotFoundException(
					String.format(ErrosDefaultEnum.RECURSO_NAO_ENCONTRADO.getDescricao(), resource));
		}
	}
}
