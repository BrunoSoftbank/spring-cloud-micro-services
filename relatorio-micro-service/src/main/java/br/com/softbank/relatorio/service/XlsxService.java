package br.com.softbank.relatorio.service;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import br.com.softbank.relatorio.annotations.RelatorioLabel;
import br.com.softbank.relatorio.reflection.Reflection;

@Service
public class XlsxService {

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

		String nomeArquivo = "Lista de " + nomeRecurso + " - " + LocalDate.now().format(FORMATTER) + ".xlsx";

		try (Workbook workbook = new XSSFWorkbook()) {
			Sheet sheet = workbook.createSheet(nomeArquivo);
			sheet.setDefaultColumnWidth(30);

			CellStyle style = workbook.createCellStyle();
			org.apache.poi.ss.usermodel.Font font = workbook.createFont();
			font.setFontName("Arial");
			font.setBold(true);
			style.setFont(font);

			Method[] methods = classe.getDeclaredMethods();
			this.ordenarCampos(methods);
			this.preencherDados(reflection, list, methods, sheet, style);

			response.setHeader("content-disposition", "attachment; filename=" + nomeArquivo);
			response.setContentType("application/xlsx");

			workbook.write(response.getOutputStream());

			response.flushBuffer();
			response.getOutputStream().flush();
			response.getOutputStream().close();
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
	
	private void preencherDados(Reflection reflection, List<?> list, Method[] methods, Sheet sheet, CellStyle style)
			throws Exception {
		Object retorno = null;
		int position = 0;
		int rowCount = 1;

		Row header = sheet.createRow(position);
		Row claimRow = sheet.createRow(rowCount++);

		for (Object obj : list) {
			for (Method method : methods) {
				String relatorioLabel = method.getDeclaredAnnotation(RelatorioLabel.class) != null
						? method.getDeclaredAnnotation(RelatorioLabel.class).name()
						: method.getName();

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

				header.createCell(position).setCellValue(relatorioLabel);
				header.getCell(position).setCellStyle(style);
				claimRow.createCell(position).setCellValue(retorno.toString());
				position++;
			}
			position = 0;
			claimRow = sheet.createRow(rowCount++);
		}
	}
}
