package br.com.softbank.batch.integration;

import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.integration.sftp.session.SftpSession;
import org.springframework.stereotype.Component;

import br.com.softbank.batch.enuns.ErrosDefaultEnum;
import br.com.softbank.batch.enuns.ResourceEnum;
import br.com.softbank.batch.enuns.SftpActionEnum;

@Component
public class SftpIntegration {

	private static final Logger LOG = LoggerFactory.getLogger(SftpIntegration.class);
	private static final String LOCAL_FILE_PATH = "src/main/resources/Laboratórios.xlsx";
	private static final String SFTP_FILE_PATH = "upload/Laboratórios.xlsx";

	@Value("${sftp_host}")
	private String host;
	@Value("${sftp_port}")
	private String port;
	@Value("${sftp_user}")
	private String user;
	@Value("${sftp_pass_word}")
	private String passWord;

	private DefaultSftpSessionFactory factory() {
		DefaultSftpSessionFactory factory = new DefaultSftpSessionFactory();
		factory.setHost(host);
		factory.setPort(Integer.valueOf(port));
		factory.setUser(user);
		factory.setPassword(passWord);
		factory.setAllowUnknownKeys(true);
		return factory;
	}

	public void downloadOrDelete(ResourceEnum resource, SftpActionEnum action) throws Exception {
		SftpSession session = null;
		FileOutputStream fileOutputStream = null;

		session = this.factory().getSession();
		
		boolean hasFileOnSftpServer = session.exists(SFTP_FILE_PATH);
		try {
			if (action.equals(SftpActionEnum.DOWNLOAD) && hasFileOnSftpServer) {
				fileOutputStream = new FileOutputStream(LOCAL_FILE_PATH);
				session.read(SFTP_FILE_PATH, fileOutputStream);
				
				fileOutputStream.flush();
				fileOutputStream.close();				
			} else if (action.equals(SftpActionEnum.DELETE) && hasFileOnSftpServer) {
				session.remove(SFTP_FILE_PATH);
			}
		} catch (Exception e) {
			LOG.error(ErrosDefaultEnum.ARQUIVO_NAO_ENCONTRADO.getDescricao());
		}
		session.close();
	}
}
