import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import main.HomePage;
import security.Security;

public class App extends Security {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		new App().inputPassword();
		new HomePage();
//		new MailOperation().autoReply();
//		new App();
	}

	private void create() throws Exception {
		File file = getDeveloperFile();
//		FileInputStream fileInputStream = new FileInputStream(file);
//		FileOutputStream fileOutputStream = new FileOutputStream(file.getParent() + "//developer.shiv");
//		fileOutputStream.write(getDevEncryptedData(fileInputStream.readAllBytes()));
//		fileOutputStream.flush();
//		fileOutputStream.close();
//		fileInputStream.close();

		// next
		File file1 = new File(file.getParent() + "//developer.shiv");
		System.out.println(file1.getAbsolutePath());
		FileInputStream fileInputStream = new FileInputStream(file1);
		FileOutputStream fileOutputStream = new FileOutputStream(file.getParent() + "//developer.jpg");
		fileOutputStream.write(getDevDecryptedData(fileInputStream.readAllBytes()));
		fileOutputStream.flush();
		fileOutputStream.close();
		fileInputStream.close();

		System.out.println("Done file");
	}

	private void inputPassword() {
		JPasswordField passwordField = new JPasswordField();
		Object[] object = { new JLabel("Enter System build Password which provided by developer"), passwordField };
		int result = JOptionPane.showConfirmDialog(null, object, "System Authentication", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			if (!passwordField.getText().equals(getSystemPassword())) {
				JOptionPane.showMessageDialog(null, "Incorrect System build Password please try again",
						"Unauthorized person", JOptionPane.ERROR_MESSAGE);
				inputPassword();
			}
		} else
			System.exit(0);
	}

	private String getSystemPassword() {
		return "PowerOfJava272206@";
	}

	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
