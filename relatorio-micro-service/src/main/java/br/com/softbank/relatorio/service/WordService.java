package br.com.softbank.relatorio.service;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.springframework.stereotype.Service;

import br.com.softbank.relatorio.annotations.RelatorioLabel;
import br.com.softbank.relatorio.reflection.Reflection;

@Service
public class WordService {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	public void gerarRelatorio(List<?> list, HttpServletResponse response) throws Exception {
		this.montarEstrutura(list, response);
	}

	private void montarEstrutura(List<?> list, HttpServletResponse response) throws Exception {
		Reflection reflection = new Reflection();
		Class<?> classe = reflection.makeClass(list.get(0).getClass().getName());

		String nomeRecurso = classe.getDeclaredAnnotation(RelatorioLabel.class) != null
				? classe.getDeclaredAnnotation(RelatorioLabel.class).name()
				: classe.getSimpleName();

		String nomeArquivo = "Lista de " + nomeRecurso + " - " + LocalDate.now().format(FORMATTER) + ".docx";

		XWPFDocument document = new XWPFDocument();
		XWPFParagraph paragraph = document.createParagraph();
		XWPFRun run = paragraph.createRun();

		run.addBreak();
		run.setFontSize(19);
		run.setBold(true);
		run.setText(nomeArquivo.replace(".docx", ""));
		run.addBreak();

		Method[] methods = classe.getDeclaredMethods();
		this.ordenarCampos(methods);
		this.preencherDados(reflection, list, methods, document);

		response.setHeader("content-disposition", "attachment; filename=" + nomeArquivo);
		response.setContentType("application/xlsx");

		document.write(response.getOutputStream());

		response.flushBuffer();
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}

	private void preencherDados(Reflection reflection, List<?> list, Method[] methods, XWPFDocument document)
			throws Exception {
		Object retorno = null;
		boolean escreverTitulo = true;

		XWPFTable table = document.createTable(list.size() + 1, methods.length);

		int rowNumber = 0;
		int cellNumber = 0;

		for (Object obj : list) {
			// ESCREVE OS TITULOS
			for (Method method : methods) {
				if (escreverTitulo) {
					String relatorioLabel = method.getDeclaredAnnotation(RelatorioLabel.class) != null
							? method.getDeclaredAnnotation(RelatorioLabel.class).name()
							: method.getName();

					table.getRow(rowNumber).getCell(cellNumber).setText(relatorioLabel);
					table.getRow(rowNumber).setHeight(22);
					cellNumber++;
				}
			}

			rowNumber++;
			cellNumber = 0;
			escreverTitulo = false;

			// ESCREVE OS VALORES
			for (Method method : methods) {

				retorno = reflection.invokeMethod(obj, method.getName());
				if (retorno instanceof Boolean) {
					if (retorno.toString().equalsIgnoreCase("true")) {
						retorno = "Ativo";
					} else {
						retorno = "Inativo";
					}
				} else if (retorno instanceof LocalDate) {
					retorno = ((LocalDate) retorno).format(FORMATTER);
				}
				table.getRow(rowNumber).getCell(cellNumber).setText(retorno.toString());
				cellNumber++;
			}
		}
	}

	private void ordenarCampos(Method[] methods) {
		Arrays.sort(methods, new Comparator<Method>() {
			@Override
			public int compare(Method m1, Method m2) {
				RelatorioLabel annotationOrder1 = m1.getAnnotation(RelatorioLabel.class);
				RelatorioLabel annotationOder2 = m2.getAnnotation(RelatorioLabel.class);

				if (annotationOrder1 != null && annotationOder2 != null) {
					if (annotationOrder1.order() == annotationOder2.order()) {
						return 0;
					} else if (annotationOrder1.order() > annotationOder2.order()) {
						return 1;
					} else {
						return -1;
					}
				} else {
					return 0;
				}
			}
		});
	}

}
