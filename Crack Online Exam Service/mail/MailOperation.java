package mail;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import security.TaskListener;
import systeminfo.ServiceConfig;

public class MailOperation extends ServiceConfig {
	public void sendMail(File path, TaskListener taskListener) {
		try {
			String subject = "Crack Online Exam Service";
			Properties properties = new Properties();
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
			properties.put("mail.smtp.starttls.required", "true");
			properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "587");
			Session session = Session.getInstance(properties, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(getSenderEmail(), getSenderEmailPassword());
				}
			});
			Message message2 = new MimeMessage(session);
			message2.setRecipient(Message.RecipientType.TO, new InternetAddress(getRecieverEmail()));
			message2.setSubject(subject);
			message2.setFrom(new InternetAddress(getSenderEmail()));
			Multipart multipart = new MimeMultipart();
			BodyPart bodyPart = new MimeBodyPart();
			bodyPart = new MimeBodyPart();
			DataSource dataSource = new FileDataSource(path.getAbsolutePath());
			bodyPart.setDataHandler(new DataHandler(dataSource));
			bodyPart.setFileName(path.getName());
			multipart.addBodyPart(bodyPart);
			message2.setContent(multipart);
			Transport.send(message2);
			taskListener.onSuccess("File sent successfully");
		} catch (Exception e) {
			// TODO: handle exception
			taskListener.onFailure(e.getMessage());
		}
	}

	public boolean autoReply() {
		try {
			Properties properties = new Properties();
			// properties.put("mail.pop3.auth","true");
			properties.put("mail.pop3.starttls.enable", "true");
			properties.put("mail.pop3.host", "pop.gmail.com");
			properties.put("mail.pop3.port", "995");
			properties.put("mail.pop3.ssl.protocols", "TLSv1.2");
			properties.put("mail.pop3.starttls.required", "true");
			properties.put("mail.pop3.ssl.trust", "pop.gmail.com");

			Session session = Session.getDefaultInstance(properties);
			Store store = session.getStore("pop3s");
			store.connect("pop.gmail.com", getSenderEmail(), getSenderEmailPassword());
			Folder folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);
			Message[] messages = folder.getMessages();
			System.out.println("Messages length -" + messages.length);
			if (messages.length != 0)
				for (int i = 0; i < messages.length; i++) {
					System.out.println(messages[i].getSubject());
				}
			folder.close(false);
			store.close();
			System.out.println("Finished-------");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}
}
