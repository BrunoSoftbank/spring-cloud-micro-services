package br.com.softbank.file.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.softbank.file.enuns.ResourceEnum;
import br.com.softbank.file.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/files")
@Api(tags = "Recurso de Files")
public class FileController {

	@Autowired
	private FileService fileService;
	
	@PostMapping
	@ApiOperation(value = "Upload do arquivo xlsx para o servidor sftp")
	public ResponseEntity<Void> upload(@RequestHeader String  Authorization, @RequestParam ResourceEnum resource, MultipartFile file) throws Exception {
		fileService.upload(resource, file);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	@ApiOperation(value = "Download do modelo de arquivo xlsx que deve ser enviado")
	public ResponseEntity<Void> download(@RequestHeader String  Authorization, @RequestParam ResourceEnum resource, HttpServletResponse response) throws Exception {
		fileService.download(resource, response);
		response.getOutputStream().close();
		return ResponseEntity.noContent().build();
	}
}
