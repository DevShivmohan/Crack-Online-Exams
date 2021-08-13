package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import mail.MailOperation;
import security.TaskListener;
import systeminfo.ScreenSize;

public class HomePage extends ScreenSize implements TaskListener {
	private JFrame frame;
	private Thread bgThread;

	private boolean stopStatus = false, runStatus = false;

	public HomePage() {
		frame = new JFrame("Cracking Online Exam");
		frame.setSize(getScreenWidth(), getSreenHeight());
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setLayout(new GridBagLayout());
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(new Color(250, 205, 205));
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;

		// services panel
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(Color.DARK_GRAY, 3, true), "Cracking Online Exam Services",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel.setBackground(new Color(255, 255, 255));
		panel.setLayout(new GridBagLayout());
		frame.add(panel, gridBagConstraints);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets = new Insets(80, 80, 80, 40);
		JButton button1 = new JButton(" Start ");
		button1.setToolTipText("Start/Stop the service");
		button1.setFont(new Font("arial", Font.CENTER_BASELINE, 20));
		button1.setBorder(BorderFactory.createEtchedBorder());
		button1.setBackground(Color.DARK_GRAY);
		button1.setForeground(Color.BLUE);
		button1.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if (getDevImageData() != null) {
					if (button1.getText().equalsIgnoreCase(" Start ")) {
						if (!isExist()) {
							showMessage("Please Configure the accounts after that start");
							return;
						}
						JOptionPane.showMessageDialog(null,
								"Dear user we are start the service easly access data with running in background",
								"Warning", JOptionPane.WARNING_MESSAGE);
						button1.setText(" Stop ");
						// starting the services
						runStatus = true;
						stopStatus = false;
						bgTask();
					} else {
						stopStatus = true;
						button1.setText(" Start ");
						if (bgThread != null && bgThread.isAlive())
							bgThread = null;
						JOptionPane.showMessageDialog(null, "Service has been stoped successfully");
					}
				} else
					showMessage("Some input files are corrupted so that please Re-install or fix this type of error");
			}
		});
		panel.add(button1, gridBagConstraints);

		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets = new Insets(80, 40, 80, 40);
		JButton button2 = new JButton(" Configuration ");
		button2.setToolTipText("Configuration like as Sender,Reciever email");
		button2.setFont(new Font("arial", Font.CENTER_BASELINE, 20));
		button2.setBorder(BorderFactory.createEtchedBorder());
		button2.setBackground(Color.DARK_GRAY);
		button2.setForeground(Color.BLUE);
		button2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stubif (isThread()) {
				if (getDevImageData() != null) {
					if (bgThread != null && bgThread.isAlive())
						showMessage("Please terminate the task and try again");
					else {
						frame.removeAll();
						frame.setVisible(false);
						new ConfigFrame();
					}
				} else
					showMessage("Some input files are corrupted so that please Re-install or fix this type of error");
			}
		});
		panel.add(button2, gridBagConstraints);

		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets = new Insets(80, 40, 80, 80);
		JButton button3 = new JButton(" Developer Details ");
		button3.setToolTipText("Developer details");
		button3.setFont(new Font("arial", Font.CENTER_BASELINE, 20));
		button3.setBorder(BorderFactory.createEtchedBorder());
		button3.setBackground(Color.DARK_GRAY);
		button3.setForeground(Color.BLUE);
		button3.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (getDevImageData() != null) {
					File file = getDevImageData();
					ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());
					JPopupMenu popupMenu = new JPopupMenu();
					popupMenu.setLayout(null);
					popupMenu.setSize(500, 500);
					JLabel imageLabel = new JLabel(new ImageIcon(
							imageIcon.getImage().getScaledInstance(500, 500, Image.SCALE_AREA_AVERAGING)));
					imageLabel.setBounds(0, 0, 500, 500);
					popupMenu.add(imageLabel);
					popupMenu.show(panel, e.getX(), e.getY());
					file.delete();
				} else {
					showMessage("Some input files are corrupted so that please Re-install or fix this type of error");
				}
			}
		});
		panel.add(button3, gridBagConstraints);

		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				if (bgThread != null && bgThread.isAlive()) {
					int a = JOptionPane.showConfirmDialog(null,
							"This task is running in background you will not be able to terminate this task directoly\nAre you sure to run in background ?");
					if (a == 0)
						frame.setVisible(false);
				} else
					System.exit(0);
			}
		});
	}

	private void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
	}

	private void bgTask() {
		bgThread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					if (runStatus && !stopStatus) {
						runStatus = false;
						doInBackground();
					}
					if (stopStatus)
						break;
				}
			}
		});
		bgThread.start();
	}

	public void doInBackground() {
		// TODO Auto-generated method stub
		try {
			MailOperation mailOperation = new MailOperation();
			System.out.println("Initiated");
			File file = getScreenShotFile(getTime());
			Robot robot = new Robot();
			Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage bufferedImage = robot.createScreenCapture(rectangle);
			ImageIO.write(bufferedImage, "jpg", file);
			mailOperation.sendMail(file, this);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private String getTime() {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyHHmmss");
		return simpleDateFormat.format(date);
	}

	@Override
	public void onSuccess(String message) {
		// TODO Auto-generated method stub
		System.out.println("Success " + message);
		runStatus = true;
	}

	@Override
	public void onFailure(String error) {
		// TODO Auto-generated method stub
		System.out.println("Error " + error);
		runStatus = true;

	}
}
