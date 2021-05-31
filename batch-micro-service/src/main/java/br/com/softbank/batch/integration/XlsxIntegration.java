package br.com.softbank.batch.integration;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import br.com.softbank.batch.dto.LaboratorioFromFileDTO;
import br.com.softbank.batch.enuns.LaboratorioFields;

@Component
public class XlsxIntegration {

	public List<LaboratorioFromFileDTO> getFromFile() {
		List<LaboratorioFromFileDTO> list = new ArrayList<LaboratorioFromFileDTO>();

		try {
			Workbook workbook = new XSSFWorkbook(new FileInputStream("src/main/resources/Laboratórios.xlsx"));
			Sheet sheet = workbook.getSheet("Modelo Laboratórios.xlsx");

			int rowNumber = 1;
			Row rowValue = sheet.getRow(rowNumber);

			while (rowValue != null) {
				String nome = rowValue.getCell(LaboratorioFields.NOME.getPosition()).getStringCellValue();
				String cidade = rowValue.getCell(LaboratorioFields.CIDADE.getPosition()).getStringCellValue();
				String bairro = rowValue.getCell(LaboratorioFields.BAIRRO.getPosition()).getStringCellValue();
				String rua = rowValue.getCell(LaboratorioFields.RUA.getPosition()).getStringCellValue();

				String numero;
				try {
					numero = rowValue.getCell(LaboratorioFields.NUMERO.getPosition()).getStringCellValue();
				} catch (Exception e) {
					numero = String
							.valueOf(rowValue.getCell(LaboratorioFields.NUMERO.getPosition()).getNumericCellValue());
				}

				list.add(new LaboratorioFromFileDTO(nome, cidade, bairro, rua, numero));

				rowValue = sheet.getRow(++rowNumber);
				if (rowValue == null) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Ocorreu um erro durante a conversão");
		}
		return list;
	}
}
