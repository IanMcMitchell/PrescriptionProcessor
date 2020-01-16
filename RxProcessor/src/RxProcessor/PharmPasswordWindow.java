package RxProcessor;

import java.awt.Color;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PharmPasswordWindow {

	private JFrame frame;
	private JTextField textField;
	static String cwd = System.getProperty("user.dir");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PharmPasswordWindow window = new PharmPasswordWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public PharmPasswordWindow() {
		initialize();
	}


	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frame = new JFrame();
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				LoginRxProcessor.frame.setVisible(true);
			}
		});
		frame.setBounds(100, 100, 260, 168);
		frame.getContentPane().setBackground(new Color(220, 225, 200));
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		java.net.URL url = ClassLoader.getSystemResource(cwd + "RxProcessor/rxLogo.jpg");
		ImageIcon img = new ImageIcon(cwd + "RxProcessor/rxLogo.jpg");
		frame.setIconImage(img.getImage());
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int textPosition = textField.getCaretPosition();
				textField.setText(textField.getText().toUpperCase());
				textField.setCaretPosition(textPosition);
			}

		});
		textField.setBounds(10, 53, 224, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblPharmacistLogin = new JLabel("Pharmacist Verification");
		lblPharmacistLogin.setBounds(59, 27, 185, 14);
		frame.getContentPane().add(lblPharmacistLogin);

		//check to see if the phar password/id exists
		
		Action action = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					FileReader loginScreenFR = new FileReader(cwd + "RxProcessor/PharmacistFiles/pharmacistList.txt");
					BufferedReader loginScreenBR = new BufferedReader(loginScreenFR);

					String[] searchID = null;
					String[] searchName = null;
					String line;
					String id = null;
					String name = null;
					String input = textField.getText().toString();
					int wordCount = 0;
					while ((line = loginScreenBR.readLine()) != null) {
						searchID = line.split("/"); //sort by the pattern created by "/"

						for (String word : searchID) {
							if (word.equals(input)) {
								searchName = line.split("/");
								String[] ary = searchName;
								id = ary[1];
								name = ary[0];
								if (id.equals(input)) {
									wordCount++;
								}

							}

						}
					}
					if (wordCount != 0) {
						if (LoginRxProcessor.NEorNP == 1) {
							NewPharmacist newPharmframe = new NewPharmacist();
							newPharmframe.setVisible(true);
							frame.dispose();
						}
						if (LoginRxProcessor.NEorNP == 2) {
							AddEmployee addEmpFrame = new AddEmployee();
							addEmpFrame.setVisible(true);
							frame.dispose();
						}
					} else {
						JOptionPane.showMessageDialog(frame, "Not Authorized");
					}

				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		};

		JButton button = new JButton("Accept");
		button.setBackground(new Color(225, 220, 200));
		button.addActionListener(action);

		button.setBounds(10, 81, 224, 22);
		frame.getContentPane().add(button);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		textField.addActionListener(action);
	}

	private void TFKeyTypedUpperCase(java.awt.event.KeyEvent evt) {

		char TestChar = evt.getKeyChar();
		if (!(Character.isUpperCase(TestChar))) {
			evt.consume();

		}

	}

	public void setVisible(boolean b) {
		frame.setVisible(true);

	}
}
