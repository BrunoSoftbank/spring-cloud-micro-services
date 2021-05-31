package br.com.softbank.email.service;

import java.nio.charset.StandardCharsets;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import br.com.softbank.email.dto.UsuarioEmailDTO;

@Service
public class EmailService {
	
	private static final Logger LOG = LoggerFactory.getLogger(EmailService.class);

	@Autowired 
	private JavaMailSender mailSender;
	
	@Value("${EMAIL_FROM}")
	private String EMAIL_FROM;
	@Autowired
    private SpringTemplateEngine templateEngine;
	
	public void emailWellcome(UsuarioEmailDTO usuarioEmailDTO) {		
		try {				 
			MimeMessage mimeMessage = this.mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
			
			Context ctx = prepareContext(usuarioEmailDTO);
			String htmlTemplate = this.templateEngine.process("html/emailWellcome.html", ctx);

			helper.setTo(usuarioEmailDTO.getEmail());
			helper.setSubject("Ative seu cadastro");
			helper.setFrom(EMAIL_FROM);
			helper.setText(htmlTemplate, true);
			mailSender.send(mimeMessage);
		} catch (Exception e) {
			LOG.warn(this.getClass().getSimpleName() + ".emailWellcome(UsuarioEmailDTO usuarioEmailDTO) " + e.getMessage());
		}
	}
	
	private Context prepareContext(UsuarioEmailDTO usuarioEmailDTO) {
		Context ctx = new Context();
		ctx.setVariable("name", usuarioEmailDTO.getNome());
		ctx.setVariable("token", usuarioEmailDTO.getToken());
		return ctx;
	}
}
