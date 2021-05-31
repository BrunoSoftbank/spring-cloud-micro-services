package br.com.softbank.file.integration;

import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.stereotype.Component;

@Component
public class SftpIntegration {
	
	@Value("${sftp_host}")
	private String host;
	@Value("${sftp_port}")
	private String port;
	@Value("${sftp_user}")
	private String user;
	@Value("${sftp_pass_word}")
	private String passWord;
	
	private DefaultSftpSessionFactory factory ;
	
	@PostConstruct
	public void init() {
		factory = new DefaultSftpSessionFactory();
	}

	private DefaultSftpSessionFactory factory() {
		if(factory != null) {
			factory = new DefaultSftpSessionFactory();
			factory.setHost(host);
			factory.setPort(Integer.valueOf(port));
			factory.setUser(user);
			factory.setPassword(passWord);
			factory.setAllowUnknownKeys(true);	
		}	
		return factory;
	}
	
	public void upload(InputStream inputStream, String destination) throws Exception {
		this.factory().getSession().write(inputStream, destination);
		this.factory().getSession().close();
	}
}
