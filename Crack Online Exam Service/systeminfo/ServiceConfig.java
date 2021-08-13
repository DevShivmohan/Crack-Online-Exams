package systeminfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import security.Security;
import security.TaskListener;

public class ServiceConfig extends DirStructure {
	private String email, password, reciever, key;

	public ServiceConfig() {
	}

	public ServiceConfig(String email, String password, String reciever) {
		this.email = email;
		this.password = password;
		this.reciever = reciever;
	}

	public String getRandom() {
		String string = "qwertyuiopasdfghjklzxcvbnm";
		string += string.toUpperCase() + "0123456789";
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < 400; i++) {
			int index = (int) ((int) string.length() * Math.random());
			stringBuilder.append(string.charAt(index));
		}
		return stringBuilder.toString();
	}

	public void saveInfo(TaskListener taskListener) {
		try {
			String keyString = getRandom();
			Security security = new Security();
			FileOutputStream fileOutputStream = new FileOutputStream(getSenderEmailFile());
			fileOutputStream.write(security.getEncryptedData(email, keyString.getBytes()));
			fileOutputStream.flush();
			fileOutputStream.close(); // email writed
			fileOutputStream = new FileOutputStream(getSenderEmailPasswordFile());
			fileOutputStream.write(security.getEncryptedData(password, keyString.getBytes()));
			fileOutputStream.flush();
			fileOutputStream.close(); // email password writed
			fileOutputStream = new FileOutputStream(getRecieverEmailFile());
			fileOutputStream.write(security.getEncryptedData(reciever, keyString.getBytes()));
			fileOutputStream.flush();
			fileOutputStream.close(); // reciever email writed
			fileOutputStream = new FileOutputStream(getKeyDataFile());
			fileOutputStream.write(security.getEncryptedKeyData(keyString));
			fileOutputStream.flush();
			fileOutputStream.close(); // decryption key writed
			taskListener.onSuccess("Authentication success and data are saved");
		} catch (Exception e) {
			// TODO: handle exception
			taskListener.onFailure(e.getMessage());
		}
	}

	public String getSenderEmail() {
		try {
			FileInputStream fileInputStream = new FileInputStream(getSenderEmailFile());
			String dString = new Security().getDecryptedData(fileInputStream.readAllBytes());
			return dString;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public String getSenderEmailPassword() {
		try {
			FileInputStream fileInputStream = new FileInputStream(getSenderEmailPasswordFile());
			String dString = new Security().getDecryptedData(fileInputStream.readAllBytes());
			return dString;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public String getRecieverEmail() {
		try {
			FileInputStream fileInputStream = new FileInputStream(getRecieverEmailFile());
			String dString = new Security().getDecryptedData(fileInputStream.readAllBytes());
			return dString;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public File getDevImageData() {
		try {
			Security security = new Security();
			File inputFile = getDeveloperFile();
			File outputFile = getDevDataFile();
			if (!inputFile.exists())
				return null;
			FileInputStream fileInputStream = new FileInputStream(inputFile);
			FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
			fileOutputStream.write(security.getDevDecryptedData(fileInputStream.readAllBytes()));
			fileOutputStream.flush();
			fileOutputStream.close();
			fileInputStream.close();
			return outputFile;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public boolean isExist() {
		return (getSenderEmail() != null && getSenderEmailPassword() != null && getRecieverEmail() != null
				&& getDevImageData() != null);
	}
}
