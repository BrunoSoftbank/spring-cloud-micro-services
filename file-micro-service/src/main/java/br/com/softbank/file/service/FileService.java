package br.com.softbank.file.service;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.softbank.file.enuns.ErrosDefaultEnum;
import br.com.softbank.file.enuns.LaboratorioFields;
import br.com.softbank.file.enuns.ResourceEnum;
import br.com.softbank.file.exception.HeaderNotFoundException;
import br.com.softbank.file.exception.InvalidFieldException;
import br.com.softbank.file.exception.MediaTypeNotSupportedException;
import br.com.softbank.file.integration.SftpIntegration;

@Service
public class FileService {

	@Autowired
	private SftpIntegration sftpIntegration;

	public void upload(ResourceEnum resource, MultipartFile file) throws Exception {
		this.validarFormato(file);
		this.validarHeader(file.getInputStream());
		this.validarCampos(file.getInputStream());
		try {
			sftpIntegration.upload(file.getInputStream(), "upload/Laboratórios.xlsx");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void download(ResourceEnum resource, HttpServletResponse response) throws Exception {
		String arquivo = "src/main/resources/modelo/arquivos/".concat(resource.getDescricao().concat(".xlsx"));

		Workbook workbook = new XSSFWorkbook(new FileInputStream(arquivo));

		response.setHeader("content-disposition", "attachment; filename=" + resource.getDescricao().concat(".xlsx"));
		response.setContentType("application/xlsx");

		workbook.write(response.getOutputStream());

		response.flushBuffer();
		response.getOutputStream().flush();
		response.getOutputStream().close();

	}

	private void validarFormato(MultipartFile file) {
		if (!file.getOriginalFilename().endsWith(".xlsx") && !file.getOriginalFilename().endsWith(".XLSX")) {
			throw new MediaTypeNotSupportedException(ErrosDefaultEnum.TIPO_MIDIA_NAO_SUPORTADO.getDescricao());
		}
	}

	@SuppressWarnings("resource")
	private void validarHeader(InputStream inputStream) throws Exception {
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet sheet = null;
		try {
			sheet = workbook.getSheet("Modelo Laboratórios.xlsx");
		} catch (Exception e) {
			sheet = workbook.createSheet("Modelo Laboratórios.xlsx");
		}
		Row headLines = sheet.getRow(0);

		try {
			String headNome = headLines.getCell(LaboratorioFields.NOME.getPosition()).getStringCellValue();
			if (!headNome.equalsIgnoreCase(LaboratorioFields.NOME.getDescription())) {
				throw new RuntimeException();
			}
		} catch (Exception e) {
			workbook.close();
			throw new HeaderNotFoundException(String.format(ErrosDefaultEnum.HEADER_NOT_FOUND.getDescricao(),
					(1 + LaboratorioFields.NOME.getPosition()), LaboratorioFields.NOME.getDescription()));
		}

		try {
			String headCidade = headLines.getCell(LaboratorioFields.CIDADE.getPosition()).getStringCellValue();
			if (!headCidade.equalsIgnoreCase(LaboratorioFields.CIDADE.getDescription())) {
				throw new RuntimeException();
			}
		} catch (Exception e) {
			workbook.close();
			throw new HeaderNotFoundException(String.format(ErrosDefaultEnum.HEADER_NOT_FOUND.getDescricao(),
					(1 + LaboratorioFields.CIDADE.getPosition()), LaboratorioFields.CIDADE.getDescription()));
		}

		try {
			String headBairro = headLines.getCell(LaboratorioFields.BAIRRO.getPosition()).getStringCellValue();
			if (!headBairro.equalsIgnoreCase(LaboratorioFields.BAIRRO.getDescription())) {
				throw new RuntimeException();
			}
		} catch (Exception e) {
			workbook.close();
			throw new HeaderNotFoundException(String.format(ErrosDefaultEnum.HEADER_NOT_FOUND.getDescricao(),
					(1 + LaboratorioFields.BAIRRO.getPosition()), LaboratorioFields.BAIRRO.getDescription()));
		}

		try {
			String headRua = headLines.getCell(LaboratorioFields.RUA.getPosition()).getStringCellValue();
			if (!headRua.equalsIgnoreCase(LaboratorioFields.RUA.getDescription())) {
				throw new RuntimeException();
			}
		} catch (Exception e) {
			workbook.close();
			throw new HeaderNotFoundException(String.format(ErrosDefaultEnum.HEADER_NOT_FOUND.getDescricao(),
					(1 + LaboratorioFields.RUA.getPosition()), LaboratorioFields.RUA.getDescription()));
		}

		try {
			String headNumero = headLines.getCell(LaboratorioFields.NUMERO.getPosition()).getStringCellValue();
			if (!headNumero.equalsIgnoreCase(LaboratorioFields.NUMERO.getDescription())) {
				throw new RuntimeException();
			}
		} catch (Exception e) {
			workbook.close();
			throw new HeaderNotFoundException(String.format(ErrosDefaultEnum.HEADER_NOT_FOUND.getDescricao(),
					(1 + LaboratorioFields.NUMERO.getPosition()), LaboratorioFields.NUMERO.getDescription()));
		}
	}

	@SuppressWarnings("resource")
	private void validarCampos(InputStream inputStream) throws Exception {
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet sheet = null;
		try {
			sheet = workbook.getSheet("Modelo Laboratórios.xlsx");
		} catch (Exception e) {
			sheet = workbook.createSheet("Modelo Laboratórios.xlsx");
		}

		int rowNumber = 1;
		Row rowValue = sheet.getRow(rowNumber);

		while (rowValue != null) {
			try {
				String nome = rowValue.getCell(LaboratorioFields.NOME.getPosition()).getStringCellValue();
				if (nome == null || nome.trim().isEmpty()) {
					throw new RuntimeException();
				}
			} catch (Exception e) {
				workbook.close();
				throw new InvalidFieldException(String.format(ErrosDefaultEnum.INVALID_FIELD.getDescricao(),LaboratorioFields.NOME.getDescription(), rowNumber));
			}
			
			try {
				String cidade = rowValue.getCell(LaboratorioFields.CIDADE.getPosition()).getStringCellValue();
				if (cidade == null || cidade.trim().isEmpty()) {
					throw new RuntimeException();
				}
			} catch (Exception e) {
				workbook.close();
				throw new InvalidFieldException(String.format(ErrosDefaultEnum.INVALID_FIELD.getDescricao(),LaboratorioFields.CIDADE.getDescription(), rowNumber));
			}
			
			try {
				String bairro = rowValue.getCell(LaboratorioFields.BAIRRO.getPosition()).getStringCellValue();
				if (bairro == null || bairro.trim().isEmpty()) {
					throw new RuntimeException();
				}
			} catch (Exception e) {
				workbook.close();
				throw new InvalidFieldException(String.format(ErrosDefaultEnum.INVALID_FIELD.getDescricao(),LaboratorioFields.BAIRRO.getDescription(), rowNumber));
			}
			
			try {
				String rua = rowValue.getCell(LaboratorioFields.RUA.getPosition()).getStringCellValue();
				if (rua == null || rua.trim().isEmpty()) {
					throw new RuntimeException();
				}
			} catch (Exception e) {
				workbook.close();
				throw new InvalidFieldException(String.format(ErrosDefaultEnum.INVALID_FIELD.getDescricao(),LaboratorioFields.RUA.getDescription(), rowNumber));
			}
			
			try {
				String numero;
				try {
					numero = rowValue.getCell(LaboratorioFields.NUMERO.getPosition()).getStringCellValue();
				} catch (Exception e) {
					numero = String.valueOf(rowValue.getCell(LaboratorioFields.NUMERO.getPosition()).getNumericCellValue());
				}
				if (numero == null || numero.trim().isEmpty()) {
					throw new RuntimeException();
				}
			} catch (Exception e) {
				workbook.close();
				throw new InvalidFieldException(String.format(ErrosDefaultEnum.INVALID_FIELD.getDescricao(),LaboratorioFields.NUMERO.getDescription(), rowNumber));
			}

			rowValue = sheet.getRow(++rowNumber);
			if (rowValue == null) {
				break;
			}
		}
	}
}
