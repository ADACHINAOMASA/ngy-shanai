package lotdsp.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jodd.mail.Email;
import jodd.mail.SendMailSession;
import jodd.mail.SmtpServer;
import lotdsp.common.flg.Flag;
import nis.framework.properties.MailProperties;

public class MailService {
	
	protected Log logger = LogFactory.getLog(getClass());
	
	public void send(Email... emails) {
		MailProperties.reload();
		
		if(emails==null || emails.length==0){
			logger.info("Emaiデータがありません。");
			return;
		}
		
		SmtpServer<?> smtpServer = SmtpServer.create(MailProperties.getSmtpServer());
		if (Flag.isOn(MailProperties.getUserAuthentication())) {
			smtpServer.authenticateWith(MailProperties.getSmtpUserName(), MailProperties.getSmtpPassword());
		}
		
		SendMailSession session = smtpServer.createSession();
		session.open();
		for(Email e:emails){
			if(e!=null){
				session.sendMail(e);
			}
		}
		session.close();
	}

}
