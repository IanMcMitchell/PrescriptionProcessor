package RxProcessor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class rxFillingScreen extends JFrame {

	public static JPanel contentPane;
	static String cwd = System.getProperty("user.dir");
	public static JPanel ptInfo_1 = new JPanel();
	public static JLabel lblNameOfPt = new JLabel();
	public static JTextField lNameFill = new JTextField();
	public static JTextField fNameFill = new JTextField();
	public static JTextField dobFill = new JTextField();
	public static JTextField phnFill = new JTextField();
	public static JTextField addFill = new JTextField();
	public static JTextField pnFill = new JTextField();
	public static Object lNameSave;
	public static Object fNameSave;
	public static Object dobSave;
	public static Object addSave;
	public static Object pnSave;
	public static Object phnSave;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					rxFillingScreen frame = new rxFillingScreen();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public rxFillingScreen() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		ImageIcon img = new ImageIcon(cwd + "RxProcessor/rxLogo.jpg");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 853, 544);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(225, 203, 200));
		setContentPane(contentPane);

		SwingUtilities.getWindowAncestor(contentPane).setIconImage(img.getImage());

		java.net.URL url = ClassLoader.getSystemResource(cwd + "RxProcessor/rxLogo.jpg");

		JButton patientBtn = new JButton("Patient Search");
		patientBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				PtSearch ptFrame = new PtSearch();
				ptFrame.setVisible(true);

			}
		});
		contentPane.setLayout(null);
		patientBtn.setBounds(10, 10, 123, 23);
		contentPane.add(patientBtn);

		JButton drugBtn = new JButton("Drug Search");
		drugBtn.setBounds(143, 10, 123, 23);
		contentPane.add(drugBtn);

		JButton prescriberBtn = new JButton("Prescriber Search");
		prescriberBtn.setBounds(276, 10, 123, 23);
		contentPane.add(prescriberBtn);

		ptInfo_1 = new JPanel();
		ptInfo_1.setToolTipText("F12 for New Rx");

		ptInfo_1.setBounds(11, 38, 816, 456);
		ptInfo_1.setBackground(new Color(220, 225, 200));
		ptInfo_1.setLayout(null);

		JButton newRx = new JButton("New Rx");
		newRx.setBounds(10, 401, 69, 44);
		ptInfo_1.add(newRx);

		lNameFill = new JTextField();
		lNameFill.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				int textPosition = lNameFill.getCaretPosition();
				lNameFill.setText(lNameFill.getText().toUpperCase());
				lNameFill.setCaretPosition(textPosition);
				if (!(lNameFill.getText().equals(lNameSave.toString()))) {
					lblNameOfPt.setForeground(Color.RED);
				} 
//				else if ((lNameFill.getText().equals(lNameSave.toString()))) {
//					lblNameOfPt.setForeground(Color.BLACK);
//				}
			}
		});
		lNameFill.setBackground(new Color(250, 250, 210));
		lNameFill.setBounds(76, 82, 173, 20);
		ptInfo_1.add(lNameFill);
		lNameFill.setColumns(10);

		fNameFill = new JTextField();
		fNameFill.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				int textPosition = fNameFill.getCaretPosition();
				fNameFill.setText(fNameFill.getText().toUpperCase());
				fNameFill.setCaretPosition(textPosition);
				if (!(fNameFill.getText().equals(fNameSave.toString()))) {
					lblNameOfPt.setForeground(Color.RED);
				} 
//				else if ((fNameFill.getText().equals(fNameSave.toString()))) {
//					lblNameOfPt.setForeground(Color.BLACK);
//				}
			}
		});
		fNameFill.setBackground(new Color(250, 250, 210));
		fNameFill.setBounds(316, 82, 173, 20);
		ptInfo_1.add(fNameFill);
		fNameFill.setColumns(10);

		dobFill = new JTextField();
		dobFill.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				int textPosition = dobFill.getCaretPosition();
				dobFill.setText(dobFill.getText().toUpperCase());
				dobFill.setCaretPosition(textPosition);
				if (!(dobFill.getText().equals(dobSave.toString()))) {
					lblNameOfPt.setForeground(Color.RED);
				} 
//				else if ((dobFill.getText().equals(dobSave.toString()))) {
//					lblNameOfPt.setForeground(Color.BLACK);
//				}
			}
		});
		dobFill.setBackground(new Color(250, 250, 210));
		dobFill.setBounds(578, 82, 128, 20);
		ptInfo_1.add(dobFill);
		dobFill.setColumns(10);

		contentPane.add(ptInfo_1);

		phnFill = new JTextField();
		phnFill.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				int textPosition = phnFill.getCaretPosition();
				phnFill.setText(phnFill.getText().toUpperCase());
				phnFill.setCaretPosition(textPosition);
				if (!(phnFill.getText().equals(phnSave.toString()))) {
					lblNameOfPt.setForeground(Color.RED);
				} 
//					else if ((phnFill.getText().equals(phnSave.toString()))) {
//					lblNameOfPt.setForeground(Color.BLACK);
//				}
			}
		});
		
		
		phnFill.setBackground(new Color(230, 230, 250));
		phnFill.setEditable(true);
		phnFill.setBounds(76, 188, 173, 20);
		ptInfo_1.add(phnFill);
		phnFill.setColumns(10);

		addFill = new JTextField();
		addFill.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				int textPosition = addFill.getCaretPosition();
				addFill.setText(addFill.getText().toUpperCase());
				addFill.setCaretPosition(textPosition);
				if (!(addFill.getText().equals(addSave.toString()))) {
					lblNameOfPt.setForeground(Color.RED);
				} 
//				else if ((addFill.getText().equals(addSave.toString()))) {
//					lblNameOfPt.setForeground(Color.BLACK);
//				}
			}
		});
		addFill.setBounds(76, 131, 413, 20);
		ptInfo_1.add(addFill);
		addFill.setColumns(10);

		pnFill = new JTextField();
		pnFill.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				int textPosition = pnFill.getCaretPosition();
				pnFill.setText(pnFill.getText().toUpperCase());
				pnFill.setCaretPosition(textPosition);
				if (!(pnFill.getText().equals(pnSave.toString()))) {
					lblNameOfPt.setForeground(Color.RED);
				} 
//				else if ((pnFill.getText().equals(pnSave.toString()))) {
//					lblNameOfPt.setForeground(Color.BLACK);
//				}
			}
		});
		pnFill.setColumns(10);
		pnFill.setBounds(578, 131, 128, 20);
		ptInfo_1.add(pnFill);

		JButton x = new JButton("X");
		x.setForeground(Color.RED);
		x.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ptInfo_1.setVisible(false);
			}
		});
		x.setBounds(766, 11, 40, 23);
		ptInfo_1.add(x);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(76, 65, 56, 14);
		ptInfo_1.add(lblLastName);

		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(316, 65, 61, 14);
		ptInfo_1.add(lblFirstName);

		JLabel lblDob = new JLabel("DOB");
		lblDob.setBounds(578, 65, 46, 14);
		ptInfo_1.add(lblDob);

		lblNameOfPt = new JLabel("Name of PT");
		lblNameOfPt.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNameOfPt.setBounds(76, 11, 680, 23);
		ptInfo_1.add(lblNameOfPt);

		JLabel lblAddress = new JLabel("Street Address ");
		lblAddress.setBounds(76, 113, 91, 14);
		ptInfo_1.add(lblAddress);

		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setBounds(578, 113, 91, 14);
		ptInfo_1.add(lblPhoneNumber);

		JLabel lblPhn = new JLabel("PHN");
		lblPhn.setBounds(76, 173, 46, 14);
		ptInfo_1.add(lblPhn);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(321, 186, 385, 216);
		ptInfo_1.add(textArea);

//		if ((ptInfo_1.isVisible() == true) && !(addFill.getText().equals(addSave.toString())) || !(pnFill.getText().equals(pnSave.toString()))
//				|| !(phnFill.getText().equals(phnSave.toString()))
//				|| !(lNameFill.getText().equals(lNameSave.toString())) || !(fNameFill.getText().equals(fNameSave))) {
//			
//			lblNameOfPt.setBackground(Color.RED);
//			return;
//
//		} 

		JButton save = new JButton("\u2611 ");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String saveOldInfo = (lNameSave.toString() + "_" + fNameSave.toString() + "_" + dobSave.toString() + "_"
						+ pnSave.toString() + "_" + phnSave.toString() + "_" + addSave.toString() + "_" + ".txt");

				lblNameOfPt.setForeground(Color.BLACK);
				
				if (!(addFill.getText().equals(addSave.toString())) || !(pnFill.getText().equals(pnSave.toString()))
						|| !(phnFill.getText().equals(phnSave.toString()))) {

					try {

						File inputFile = new File(cwd + "RxProcessor/ptFiles/ptList.txt");
						File tempFile = new File(cwd + "RxProcessor/ptFiles/ptListTemp.txt");
						BufferedReader reader = new BufferedReader(new FileReader(inputFile));
						BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
						String lineToRemove = lNameSave.toString() + "/" + fNameSave.toString() + "/"
								+ dobSave.toString() + "/" + pnSave.toString() + "/" + phnSave.toString() + "/"
								+ addSave.toString() + "/";

						String currentLine;
						while ((currentLine = reader.readLine()) != null) {
							String trimmedLine = currentLine.trim();
							if (trimmedLine.equals(lineToRemove))
								continue;
							writer.write(currentLine + System.getProperty("line.separator"));

						}
						writer.write(lNameFill.getText().toString() + "/" + fNameFill.getText().toString() + "/"
								+ dobFill.getText().toString() + "/" + pnFill.getText().toString() + "/"
								+ phnFill.getText().toString() + "/" + addFill.getText().toString() + "/");

						File tempPtFile = new File(cwd + "RxProcessor/ptFiles/" + lNameFill.getText().toString() + "_"
								+ fNameFill.getText().toString() + "_" + dobFill.getText().toString() + "_"
								+ pnFill.getText().toString() + "_" + phnFill.getText().toString() + "_"
								+ addFill.getText().toString() + "_" + ".txt");
						FileWriter tempFileW = new FileWriter(tempPtFile);
						BufferedWriter tempFileBw = new BufferedWriter(tempFileW);

						FileReader ptInfoFileReader = new FileReader(cwd + "RxProcessor/ptFiles/" + lNameSave.toString()
								+ "_" + fNameSave.toString() + "_" + dobSave.toString() + "_" + pnSave.toString() + "_"
								+ phnSave.toString() + "_" + addSave.toString() + "_" + ".txt");
						BufferedReader ptInfoFile = new BufferedReader(ptInfoFileReader);

						// tempFileBw.write("Hi");
						String s;
						while ((s = ptInfoFile.readLine()) != null) { // read a line
							tempFileW.write(s); // write to output file
							tempFileW.flush();
						}
						ptInfoFile.close();
						tempFileW.close();

						tempFileBw.close();
						tempFileW.close();
						writer.newLine();
						writer.close();
						reader.close();

						lblNameOfPt.setText(fNameFill.getText().toString() + " " + lNameFill.getText().toString());

						if (!inputFile.delete()) {
							JOptionPane.showMessageDialog(ptInfo_1, "NO");
							return;
						}
//						try {
//							TimeUnit.SECONDS.sleep(5);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
						if (!tempFile.renameTo(inputFile)) {
							JOptionPane.showMessageDialog(ptInfo_1, "NO 2");
						}

						Files.deleteIfExists(Paths.get(cwd + "RxProcessor/ptFiles/" + saveOldInfo));

						lNameSave = lNameFill.getText().toString();
						fNameSave = fNameFill.getText().toString();
						dobSave = dobFill.getText().toString();
						phnSave = phnFill.getText().toString();
						pnSave = pnFill.getText().toString();
						addSave = addFill.getText().toString();

						lblNameOfPt.setForeground(Color.BLACK);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else

				if (!(lNameFill.getText().equals(lNameSave.toString())) || !(fNameFill.getText().equals(fNameSave))
						|| !(dobFill.getText().equals(dobSave))) {
					int confirmationNameChange = JOptionPane.showConfirmDialog(contentPane,
							"Do you wish to change mandatory fields?", "Change PT Info?", JOptionPane.YES_NO_OPTION);
					// 0=yes, 1=no, 2=cancel
					if (confirmationNameChange == 1) {
						lNameFill.setText(lNameSave.toString());
						fNameFill.setText(fNameSave.toString());
					} else if (confirmationNameChange == 0) {
						try {

							File inputFile = new File(cwd + "RxProcessor/ptFiles/ptList.txt");
							File tempFile = new File(cwd + "RxProcessor/ptFiles/ptListTemp.txt");
							BufferedReader reader = new BufferedReader(new FileReader(inputFile));
							BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
							String lineToRemove = lNameSave.toString() + "/" + fNameSave.toString() + "/"
									+ dobSave.toString() + "/" + pnSave.toString() + "/" + phnSave.toString() + "/"
									+ addSave.toString() + "/";

							String currentLine;
							while ((currentLine = reader.readLine()) != null) {
								String trimmedLine = currentLine.trim();
								if (trimmedLine.equals(lineToRemove))
									continue;
								writer.write(currentLine + System.getProperty("line.separator"));

							}
							writer.write(lNameFill.getText().toString() + "/" + fNameFill.getText().toString() + "/"
									+ dobFill.getText().toString() + "/" + pnFill.getText().toString() + "/"
									+ phnFill.getText().toString() + "/" + addFill.getText().toString() + "/");

							File tempPtFile = new File(cwd + "RxProcessor/ptFiles/" + lNameFill.getText().toString()
									+ "_" + fNameFill.getText().toString() + "_" + dobFill.getText().toString() + "_"
									+ pnFill.getText().toString() + "_" + phnFill.getText().toString() + "_"
									+ addFill.getText().toString() + "_" + ".txt");
							FileWriter tempFileW = new FileWriter(tempPtFile);
							BufferedWriter tempFileBw = new BufferedWriter(tempFileW);

							FileReader ptInfoFileReader = new FileReader(
									cwd + "RxProcessor/ptFiles/" + lNameSave.toString() + "_" + fNameSave.toString()
											+ "_" + dobSave.toString() + "_" + pnSave.toString() + "_"
											+ phnSave.toString() + "_" + addSave.toString() + "_" + ".txt");
							BufferedReader ptInfoFile = new BufferedReader(ptInfoFileReader);

							// tempFileBw.write("Hi");
							String s;
							while ((s = ptInfoFile.readLine()) != null) { // read a line
								tempFileW.write(s); // write to output file
								tempFileW.flush();
							}
							ptInfoFile.close();
							tempFileW.close();

							tempFileBw.close();
							tempFileW.close();
							writer.newLine();
							writer.close();
							reader.close();

							lblNameOfPt.setText(fNameFill.getText().toString() + " " + lNameFill.getText().toString());

							if (!inputFile.delete()) {
								JOptionPane.showMessageDialog(ptInfo_1, "NO");
								return;
							}
//							try {
//								TimeUnit.SECONDS.sleep(5);
//							} catch (InterruptedException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
							if (!tempFile.renameTo(inputFile)) {
								JOptionPane.showMessageDialog(ptInfo_1, "NO 2");
							}

							Files.deleteIfExists(Paths.get(cwd + "RxProcessor/ptFiles/" + saveOldInfo));

							lNameSave = lNameFill.getText().toString();
							fNameSave = fNameFill.getText().toString();
							dobSave = dobFill.getText().toString();
							phnSave = phnFill.getText().toString();
							pnSave = pnFill.getText().toString();
							addSave = addFill.getText().toString();

							lblNameOfPt.setForeground(Color.BLACK);

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				}

			}
			
		});
		save.setBounds(10, 11, 56, 23);
		ptInfo_1.add(save);
		ptInfo_1.setVisible(false);

	}



	public static void setTextOfFields(Object lNameTable, Object fNameTable, Object fDOB, Object fpn, Object fPHN,
			Object fAdd) {

		lNameFill.setText(lNameTable.toString());
		fNameFill.setText(fNameTable.toString());
		dobFill.setText(fDOB.toString());
		pnFill.setText(fpn.toString());
		phnFill.setText(fPHN.toString());
		addFill.setText(fAdd.toString());

		lNameSave = lNameTable;
		fNameSave = fNameTable;
		dobSave = fDOB;
		phnSave = fPHN;
		pnSave = fpn;
		addSave = fAdd;

	}
}
