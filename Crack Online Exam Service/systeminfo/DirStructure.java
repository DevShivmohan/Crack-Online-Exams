package systeminfo;

import java.io.File;

public class DirStructure {
	private String rootPathString, devRoot;

	public DirStructure() {
		devRoot = System.getProperty("user.dir");
		rootPathString = System.getProperty("user.home");
		File file = new File(rootPathString + "//crackexamonline//config");
		file.mkdirs();
		rootPathString = file.getAbsolutePath();
	}

	public File getKeyDataFile() {
		return new File(rootPathString + "//key.shiv");
	}

	public File getSenderEmailFile() {
		return new File(rootPathString + "//sender_email.shiv");
	}

	public File getSenderEmailPasswordFile() {
		return new File(rootPathString + "//sender_email_pass.shiv");
	}

	public File getRecieverEmailFile() {
		return new File(rootPathString + "//reciever_email.shiv");
	}

	public File getStopTaskFile() {
		return new File(rootPathString + "//stop.shiv");
	}

	public File getDeveloperFile() {
		return new File(devRoot + "//dev" + "//developer.shiv");
	}

	public File getScreenShotFile(String fileName) {
		return new File(rootPathString + "//" + fileName + ".jpg");
	}

	public File getDevDataFile() {
		File file = new File(rootPathString + "//data");
		file.mkdirs();
		return new File(file.getAbsolutePath() + "//developer.jpg");
	}
}
