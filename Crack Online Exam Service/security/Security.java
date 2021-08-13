package security;

import java.io.FileInputStream;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import systeminfo.ServiceConfig;

public class Security extends ServiceConfig {

	public Security() {
	}

	public byte[] getEncryptedData(String data, byte[] key) {
		try {
			Cipher cipher = Cipher.getInstance("AES");
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
			key = messageDigest.digest(key);
			key = Arrays.copyOf(key, 16);
			SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			return cipher.doFinal(data.getBytes());
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public String getDecryptedData(byte[] data) {
		try {
			Cipher cipher = Cipher.getInstance("AES");
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
			byte[] key = getKey();
			key = messageDigest.digest(key);
			key = Arrays.copyOf(key, 16);
			SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
			return new String(cipher.doFinal(data));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public byte[] getDevEncryptedData(byte[] data) {
		try {
			byte[] key = getDeveloperKey().getBytes();
			Cipher cipher = Cipher.getInstance("AES");
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
			key = messageDigest.digest(key);
			key = Arrays.copyOf(key, 16);
			SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			return cipher.doFinal(data);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public byte[] getDevDecryptedData(byte[] data) {
		try {
			Cipher cipher = Cipher.getInstance("AES");
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
			byte[] key = getDeveloperKey().getBytes();
			key = messageDigest.digest(key);
			key = Arrays.copyOf(key, 16);
			SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
			return cipher.doFinal(data);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public byte[] getEncryptedKeyData(String data) {
		try {
			byte[] key = "1010101Shivmohan".getBytes();
			Cipher cipher = Cipher.getInstance("AES");
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
			key = messageDigest.digest(key);
			key = Arrays.copyOf(key, 16);
			SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			return cipher.doFinal(data.getBytes());
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public String getDecryptedKeyData(byte[] data) {
		try {
			byte[] key = "1010101Shivmohan".getBytes();
			Cipher cipher = Cipher.getInstance("AES");
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
			key = messageDigest.digest(key);
			key = Arrays.copyOf(key, 16);
			SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
			return new String(cipher.doFinal(data));
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	private byte[] getKey() {
		try {
			FileInputStream fileInputStream = new FileInputStream(getKeyDataFile());
			byte[] key = getDecryptedKeyData(fileInputStream.readAllBytes()).getBytes();
			fileInputStream.close();
			return key;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public String getDeveloperKey() {
		StringBuilder stringBuilder = new StringBuilder();
		String alphaString = "poiuytrewqlkjhgfdsamnbvcxz";
		alphaString += alphaString.toUpperCase() + "0123456789" + "~!@#$%^&*()_+?><:";
		alphaString += alphaString.toUpperCase() + "0123456789" + "~!@#$%^&*()_+?><:";
		for (int i = 1; i <= 500; i++) {
			if (i % 2 != 0)
				if (i < alphaString.length())
					stringBuilder.append(alphaString.charAt(i));
				else
					break;
		}
		return stringBuilder.toString();
	}
}
