package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import security.MailAuthenticator;
import security.TaskListener;
import systeminfo.ScreenSize;
import systeminfo.ServiceConfig;

public class ConfigFrame extends ScreenSize {
	private Thread thread;
	private JFrame frame;

	private ServiceConfig serviceConfig = new ServiceConfig();

	public ConfigFrame() {
		frame = new JFrame("Cracking Online Exam Configuration");
		frame.setSize(getScreenWidth(), getSreenHeight());
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setLayout(new GridBagLayout());
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(new Color(250, 205, 205));
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets = new Insets(100, 100, 100, 100);

		// main panel
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(Color.DARK_GRAY, 3, true), "Cracking Online Exam Configuration",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel.setBackground(new Color(255, 255, 255));
		panel.setLayout(new GridBagLayout());
		frame.add(panel, gridBagConstraints);

		// sender email
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets = new Insets(50, 50, 25, 25);
		JLabel senderLabel = new JLabel("Sender Email");
		senderLabel.setFont(new Font("arial", Font.CENTER_BASELINE, 20));
		senderLabel.setForeground(Color.BLUE);
		senderLabel.setBorder(BorderFactory.createEtchedBorder());
		senderLabel.setHorizontalTextPosition(AbstractButton.CENTER);
		senderLabel.setVerticalTextPosition(AbstractButton.CENTER);
		panel.add(senderLabel, gridBagConstraints);

		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets = new Insets(50, 25, 25, 50);
		JTextField senderEmailTextField = new JTextField();
		if (serviceConfig.getSenderEmail() != null)
			senderEmailTextField.setText(serviceConfig.getSenderEmail());
		senderEmailTextField.setPreferredSize(new Dimension(300, 30));
		senderEmailTextField.setBackground(new Color(250, 205, 205));
		senderEmailTextField.setForeground(Color.BLUE);
		senderEmailTextField.setFont(new Font("arial", Font.CENTER_BASELINE, 20));
		senderEmailTextField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if (senderEmailTextField.getText().length() >= 100)
					e.consume();
			}

		});
		panel.add(senderEmailTextField, gridBagConstraints);

		// sender email password
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets = new Insets(25, 50, 25, 25);
		JLabel senderPasswordLabel = new JLabel("Sender Email Password");
		senderPasswordLabel.setFont(new Font("arial", Font.CENTER_BASELINE, 20));
		senderPasswordLabel.setForeground(Color.BLUE);
		senderPasswordLabel.setBorder(BorderFactory.createEtchedBorder());
		senderPasswordLabel.setHorizontalTextPosition(AbstractButton.CENTER);
		senderPasswordLabel.setVerticalTextPosition(AbstractButton.CENTER);
		panel.add(senderPasswordLabel, gridBagConstraints);

		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets = new Insets(25, 25, 25, 50);
		JPasswordField senderEmailPasswordTextField = new JPasswordField();
		if (serviceConfig.getSenderEmailPassword() != null)
			senderEmailPasswordTextField.setText(serviceConfig.getSenderEmailPassword());
		senderEmailPasswordTextField.setPreferredSize(new Dimension(300, 30));
		senderEmailPasswordTextField.setBackground(new Color(250, 205, 205));
		senderEmailPasswordTextField.setForeground(Color.BLUE);
		senderEmailPasswordTextField.setFont(new Font("arial", Font.CENTER_BASELINE, 20));
		senderEmailPasswordTextField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if (senderEmailTextField.getText().length() >= 50)
					e.consume();
			}

		});
		panel.add(senderEmailPasswordTextField, gridBagConstraints);

		// reciever email
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.insets = new Insets(25, 50, 25, 25);
		JLabel recieverEmailLabel = new JLabel("Reciever Email");
		recieverEmailLabel.setFont(new Font("arial", Font.CENTER_BASELINE, 20));
		recieverEmailLabel.setForeground(Color.BLUE);
		recieverEmailLabel.setBorder(BorderFactory.createEtchedBorder());
		recieverEmailLabel.setHorizontalTextPosition(AbstractButton.CENTER);
		recieverEmailLabel.setVerticalTextPosition(AbstractButton.CENTER);
		panel.add(recieverEmailLabel, gridBagConstraints);

		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.insets = new Insets(25, 25, 25, 50);
		JTextField recieverEmailTextField = new JTextField();
		if (serviceConfig.getRecieverEmail() != null)
			recieverEmailTextField.setText(serviceConfig.getRecieverEmail());
		recieverEmailTextField.setPreferredSize(new Dimension(300, 30));
		recieverEmailTextField.setBackground(new Color(250, 205, 205));
		recieverEmailTextField.setForeground(Color.BLUE);
		recieverEmailTextField.setFont(new Font("arial", Font.CENTER_BASELINE, 20));
		recieverEmailTextField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if (senderEmailTextField.getText().length() >= 100)
					e.consume();
			}

		});
		panel.add(recieverEmailTextField, gridBagConstraints);

		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.insets = new Insets(25, 25, 25, 50);
		JButton submitButton = new JButton(" Authenticate ");
		submitButton.setFont(new Font("arial", Font.CENTER_BASELINE, 20));
		submitButton.setBackground(new Color(250, 205, 50));
		submitButton.setForeground(Color.blue);
		submitButton.setVerticalTextPosition(AbstractButton.CENTER);
		submitButton.setHorizontalTextPosition(AbstractButton.CENTER);
		submitButton.setBorder(BorderFactory.createEtchedBorder());
		panel.add(submitButton, gridBagConstraints);
		submitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if (submitButton.getText().equalsIgnoreCase(" Authenticate ")) {
					thread = new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							if (validation(senderEmailTextField, senderEmailPasswordTextField,
									recieverEmailTextField)) {
								JOptionPane.showMessageDialog(null,
										"Before Authenticating you must need to on Less Secure App in Sender email Account After that work otherwise not work",
										"Warning", JOptionPane.WARNING_MESSAGE);
								submitButton.setText(" Authenticating... ");
								MailAuthenticator mailAuthenticator = new MailAuthenticator(
										senderEmailTextField.getText(), senderEmailPasswordTextField.getText(),
										recieverEmailTextField.getText());
								mailAuthenticator.authenticate(new security.Authenticator() {

									@Override
									public void authenticationSuccess() {
										// TODO Auto-generated method stub
										ServiceConfig serviceConfig = new ServiceConfig(senderEmailTextField.getText(),
												senderEmailPasswordTextField.getText(),
												recieverEmailTextField.getText());
										serviceConfig.saveInfo(new TaskListener() {

											@Override
											public void onSuccess(String message) {
												// TODO Auto-generated method stub
												submitButton.setText(" Authenticate ");
												JOptionPane.showMessageDialog(null, message);
											}

											@Override
											public void onFailure(String error) {
												// TODO Auto-generated method stub
												submitButton.setText(" Authenticate ");
												showMessage(error);
											}
										});
									}

									@Override
									public void authenticationFailed(String error) {
										// TODO Auto-generated method stub
										submitButton.setText(" Authenticate ");
										showMessage(error);
									}
								});
							}
						}
					});
					thread.start();
				}
			}
		});

		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				frame.removeAll();
				frame.setVisible(false);
				new HomePage();
			}
		});
	}

	private void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	}

	private boolean validation(JTextField email, JTextField pass, JTextField reciever) {
		email.setBackground(new Color(250, 205, 205));
		pass.setBackground(new Color(250, 205, 205));
		reciever.setBackground(new Color(250, 205, 205));
		if (!email.getText().isBlank() && email.getText().length() > 12)
			if (!pass.getText().isBlank() && pass.getText().length() > 4)
				if (!reciever.getText().isBlank() && reciever.getText().length() > 12)
					return true;
				else
					reciever.setBackground(Color.RED);
			else
				pass.setBackground(Color.RED);
		else
			email.setBackground(Color.RED);

		showMessage("Please enter valid details inside the red indicated fields");
		return false;
	}

}
