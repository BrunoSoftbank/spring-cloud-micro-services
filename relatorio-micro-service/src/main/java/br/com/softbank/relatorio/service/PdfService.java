package br.com.softbank.relatorio.service;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.softbank.relatorio.annotations.RelatorioLabel;
import br.com.softbank.relatorio.reflection.Reflection;

@Service
public class PdfService {
	
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	private static final String LOGO = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJyEtvD6UodmrnGSTVfSCC_mUR7sSDFMgv4ALiWNpaD20rj0P9aqm5vSwkY2gfaHDfu1QCol7RIF1ed9J_dm9Hk-jRUrzfsZf7JA&usqp=CAU&ec=45750088";

	public void gerarRelatorio(List<?> list, HttpServletResponse response) throws Exception {
		this.montarEstrutura(list, response);
	}
	
	private void montarEstrutura(List<?> list, HttpServletResponse response) throws Exception {
		Reflection reflection = new Reflection();
		Class<?> classe = reflection.makeClass(list.get(0).getClass().getName());

		String nomeRecurso = classe.getDeclaredAnnotation(RelatorioLabel.class) != null
				? classe.getDeclaredAnnotation(RelatorioLabel.class).name()
				: classe.getSimpleName();

		String nomeArquivo = "Lista de " + nomeRecurso + " - " + LocalDate.now().format(FORMATTER) + ".pdf";

		Document document = new Document();

		Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();

		Image image = Image.getInstance(LOGO);
		image.setAlignment(2);
		image.scaleToFit(100, 50);
		document.add(image);

		Paragraph paragraph = new Paragraph(nomeArquivo.replace(".pdf", ""), headFont);
		paragraph.setAlignment(1);
		document.add(paragraph);
		document.add(new Paragraph(" "));

		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(80);
		table.setWidths(new int[] { 3, 3 });

		Method[] methods = classe.getDeclaredMethods();
		this.ordenarCampos(methods);
		this.preencherDados(reflection, list, methods, table, headFont);

		response.setHeader("content-disposition", "attachment; filename=" + nomeArquivo);
		response.setContentType("application/pdf");

		document.add(table);
		document.close();

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
	
	private void preencherDados(Reflection reflection, List<?> list, Method[] methods, PdfPTable table, Font headFont)
			throws Exception {
		PdfPCell hcell;
		PdfPCell cell;

		for (Object obj : list) {
			for (Method method : methods) {

				String relatorioLabel = method.getDeclaredAnnotation(RelatorioLabel.class) != null
						? method.getDeclaredAnnotation(RelatorioLabel.class).name()
						: method.getName();

				hcell = new PdfPCell(new Phrase(relatorioLabel, headFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(hcell);

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

				cell = new PdfPCell(new Phrase(retorno.toString()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
			}
			hcell = new PdfPCell(new Phrase("-", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			cell = new PdfPCell(new Phrase("-"));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
		}
	}

}
