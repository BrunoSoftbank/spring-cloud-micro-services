package br.com.softbank.relatorio.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import br.com.softbank.relatorio.annotations.RelatorioLabel;
import br.com.softbank.relatorio.reflection.Reflection;

@Service
public class TxtService {
	
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	public void gerarRelatorio(List<?> list, HttpServletResponse response) throws Exception {
		this.montarEstrutura(list, response);
	}
	
	@SuppressWarnings("resource")
	private void montarEstrutura(List<?> list, HttpServletResponse response) throws Exception {
		Reflection reflection = new Reflection();
		Class<?> classe = reflection.makeClass(list.get(0).getClass().getName());

		String nomeRecurso = classe.getDeclaredAnnotation(RelatorioLabel.class) != null
				? classe.getDeclaredAnnotation(RelatorioLabel.class).name()
				: classe.getSimpleName();

		String nomeArquivo = "Lista de " + nomeRecurso + " - " + LocalDate.now().format(FORMATTER) + ".txt";

		try {
			File file = new File(nomeArquivo);
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			Method[] methods = classe.getDeclaredMethods();
			this.ordenarCampos(methods);
			this.preencherDados(reflection, list, methods, bufferedWriter);

			bufferedWriter.close();
			fileWriter.close();

			response.setHeader("Content-Disposition", "attachment; filename=" + nomeArquivo);
			response.setContentType("application/octet-stream");

			int BUFF_SIZE = 1024;
			byte[] buffer = new byte[BUFF_SIZE];

			FileInputStream fileInputStream = new FileInputStream(nomeArquivo);
			OutputStream outputStream = response.getOutputStream();

			int byteCount = 0;
			while (byteCount != -1) {
				byteCount = fileInputStream.read(buffer);
				if (byteCount == -1) {
					break;
				}
				outputStream.write(buffer, 0, byteCount);
				outputStream.flush();
			}

			if (file.exists()) {
				file.delete();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		response.flushBuffer();
		response.getOutputStream().flush();
		response.getOutputStream().close();

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
	
	private void preencherDados(Reflection reflection, List<?> list, Method[] methods, BufferedWriter bufferedWriter)
			throws Exception {
		boolean escreverTitulo = true;

		for (Object obj : list) {

			// ESCREVE OS TITULOS
			for (Method method : methods) {
				if (escreverTitulo) {
					String relatorioLabel = method.getDeclaredAnnotation(RelatorioLabel.class) != null
							? method.getDeclaredAnnotation(RelatorioLabel.class).name()
							: method.getName();

					bufferedWriter.write(relatorioLabel + "; ");
				}
			}

			escreverTitulo = false;
			bufferedWriter.newLine();

			// ESCREVE OS VALORES
			for (Method method : methods) {

				Object retorno = reflection.invokeMethod(obj, method.getName());
				if (retorno instanceof Boolean) {
					if (retorno.toString().equalsIgnoreCase("true")) {
						retorno = "Ativo";
					} else {
						retorno = "Inativo";
					}
				} else if (retorno instanceof LocalDate) {
					retorno = ((LocalDate) retorno).format(FORMATTER);
				}
				bufferedWriter.write(retorno + "; ");
			}
		}
	}
}
