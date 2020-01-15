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
import javax.swing.JTextPane;
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
import java.awt.Window;

import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;

public class rxFillingScreen extends JFrame {

	public static JPanel contentPane;

	static String cwd = System.getProperty("user.dir");

	public static JPanel ptInfo_1 = new JPanel();
	public static JPanel prInfo_1 = new JPanel();
	public static JPanel drugInfo_1 = new JPanel();

	public static JLabel lblNameOfPt = new JLabel();
	public static JLabel lblNameOfPr = new JLabel();
	public static JLabel lblNameOfDrug = new JLabel();

	public static JTextField lNameFill = new JTextField();
	public static JTextField fNameFill = new JTextField();
	public static JTextField dobFill = new JTextField();
	public static JTextField phnFill = new JTextField();
	public static JTextField addFill = new JTextField();
	public static JTextField pnFill = new JTextField();

	public static String commentSave;

	public static Object lNameSave;
	public static Object fNameSave;
	public static Object dobSave;
	public static Object addSave;
	public static Object pnSave;
	public static Object phnSave;

	public static Object prLNameSave;
	public static Object prFNameSave;
	public static Object licenseSave;
	public static Object prAddSave;
	public static Object prPNSave;
	public static Object faxSave;

	public static Object drugNameSave;
	public static Object drugStrSave;
	public static Object drugDinSave;
	public static Object drugUpcSave;
	public static Object manuSave;

	private static String saveOldInfo;
	private static String saveOldInfoPr;
	private static String saveOldInfoDrug;

	public static JTextArea textArea = new JTextArea();

	public static JButton patientBtn = new JButton("Patient Search");
	public static JButton prescriberBtn = new JButton("Prescriber Search");
	public static JButton drugBtn = new JButton("Drug Search");

	public static JTextField lNameFieldPr = new JTextField();
	public static JTextField fNameFieldPr = new JTextField();
	public static JTextField licenseField = new JTextField();
	public static JTextField prAddressField = new JTextField();
	public static JTextField prPNField = new JTextField();
	public static JTextField faxField = new JTextField();

	public static JTextField drugNameField = new JTextField();
	public static JTextField drugStrField = new JTextField();
	public static JTextField drugDinField = new JTextField();
	public static JTextField drugUpcField = new JTextField();
	public static JTextField manuField = new JTextField();

	private JLabel drugManufacturerLabel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					rxFillingScreen frame = new rxFillingScreen();
					frame.setAlwaysOnTop(true);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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

		Window window = SwingUtilities.windowForComponent(contentPane);

		window.setLocationRelativeTo(null);

		SwingUtilities.getWindowAncestor(contentPane).setIconImage(img.getImage());

		java.net.URL url = ClassLoader.getSystemResource(cwd + "RxProcessor/rxLogo.jpg");

		// JButton patientBtn = new JButton("Patient Search");
		patientBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				patientBtn.setEnabled(false);
				drugBtn.setEnabled(false);
				prescriberBtn.setEnabled(false);
				PtSearch ptFrame = new PtSearch();
				ptFrame.setVisible(true);

			}
		});

		drugInfo_1.setBackground(new Color(255, 245, 238));
		drugInfo_1.setBounds(10, 44, 817, 450);
		contentPane.add(drugInfo_1);
		drugInfo_1.setLayout(null);

		JButton drugSave = new JButton("\u2611 ");
		drugSave.setBounds(10, 11, 56, 23);
		drugInfo_1.add(drugSave);

		JButton drugX = new JButton("X");
		drugX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drugInfo_1.setVisible(false);
			}
		});
		drugX.setForeground(Color.RED);
		drugX.setBounds(767, 11, 40, 23);
		drugInfo_1.add(drugX);

		drugNameField.setColumns(10);
		drugNameField.setBounds(172, 78, 233, 20);
		drugInfo_1.add(drugNameField);

		drugStrField.setColumns(10);
		drugStrField.setBounds(512, 78, 233, 20);
		drugInfo_1.add(drugStrField);

		drugDinField.setBounds(512, 109, 233, 20);
		drugInfo_1.add(drugDinField);

		drugUpcField.setEditable(true);
		drugUpcField.setColumns(10);
		drugUpcField.setBackground(Color.WHITE);
		drugUpcField.setBounds(172, 109, 233, 20);
		drugInfo_1.add(drugUpcField);

		manuField.setColumns(10);
		manuField.setBounds(172, 140, 233, 20);
		drugInfo_1.add(manuField);

		lblNameOfDrug.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNameOfDrug.setBounds(77, 11, 680, 23);
		drugInfo_1.add(lblNameOfDrug);

		JLabel nameOfDrug = new JLabel("Drug Name");
		nameOfDrug.setBounds(97, 81, 64, 14);
		drugInfo_1.add(nameOfDrug);

		JLabel drugUpcLbl = new JLabel("UPC");
		drugUpcLbl.setBounds(128, 112, 34, 14);
		drugInfo_1.add(drugUpcLbl);

		JLabel drugStrLbl = new JLabel("Strength");
		drugStrLbl.setBounds(456, 81, 46, 14);
		drugInfo_1.add(drugStrLbl);

		JLabel drugDinLbl = new JLabel("DIN");
		drugDinLbl.setBounds(456, 112, 46, 14);
		drugInfo_1.add(drugDinLbl);

		drugManufacturerLabel = new JLabel("Manufacturer");
		drugManufacturerLabel.setBounds(89, 143, 73, 14);
		drugInfo_1.add(drugManufacturerLabel);

		ptInfo_1 = new JPanel();
		ptInfo_1.setBounds(10, 44, 816, 450);
		contentPane.add(ptInfo_1);
		ptInfo_1.setToolTipText("F12 for New Rx");
		ptInfo_1.setBackground(new Color(220, 225, 200));
		ptInfo_1.setLayout(null);

		JButton newRx = new JButton("New Rx");
		newRx.setBounds(10, 401, 69, 44);
		newRx.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				enterRx enterAnRx = new enterRx();
				enterRx.ptName.setText(fNameSave + " " + lNameSave);
				enterAnRx.setVisible(true);

			}
		});
		ptInfo_1.add(newRx);

		lNameFill = new JTextField();
		lNameFill.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				int textPosition = lNameFill.getCaretPosition();
				lNameFill.setText(lNameFill.getText().toUpperCase());
				lNameFill.setCaretPosition(textPosition);
				if (!(lNameFill.getText().equals(lNameSave.toString()))) {
					// lblNameOfPt.setForeground(Color.RED);
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
					// lblNameOfPt.setForeground(Color.RED);
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
					// lblNameOfPt.setForeground(Color.RED);
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

		phnFill = new JTextField();
		phnFill.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				int textPosition = phnFill.getCaretPosition();
				phnFill.setText(phnFill.getText().toUpperCase());
				phnFill.setCaretPosition(textPosition);
				if (!(phnFill.getText().equals(phnSave.toString()))) {
					// lblNameOfPt.setForeground(Color.RED);
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
					// lblNameOfPt.setForeground(Color.RED);
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
					// lblNameOfPt.setForeground(Color.RED);
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
				textArea.setText("");
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

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(321, 186, 385, 216);
		ptInfo_1.add(scrollPane);
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);
		textArea.getDocument().putProperty("filterNewlines", Boolean.TRUE);

		prescriberBtn.setBounds(276, 10, 123, 23);
		contentPane.add(prescriberBtn);

		JButton save = new JButton("\u2611 ");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				saveOldInfo = (lNameSave.toString() + "_" + fNameSave.toString() + "_" + dobSave.toString() + "_"
						+ pnSave.toString() + "_" + phnSave.toString() + "_" + addSave.toString() + "_" + ".txt");

				lblNameOfPt.setForeground(Color.BLACK);

				if (textArea.getText() != commentSave) {
					try {
						FileWriter commentWriter = new FileWriter(cwd + "RxProcessor/ptFiles/" + lNameSave.toString()
								+ "_" + fNameSave.toString() + "_" + dobSave.toString() + "_" + pnSave.toString() + "_"
								+ phnSave.toString() + "_" + addSave.toString() + "_" + ".txt", true);
						BufferedWriter commentWriterBr = new BufferedWriter(commentWriter);
						commentWriterBr.newLine();
						commentWriterBr.append("####" + textArea.getText().toString() + "####");
						commentWriterBr.newLine();

						commentWriterBr.close();
						commentWriter.close();

						File inputFile = new File(cwd + "RxProcessor/ptFiles/" + lNameSave.toString() + "_"
								+ fNameSave.toString() + "_" + dobSave.toString() + "_" + pnSave.toString() + "_"
								+ phnSave.toString() + "_" + addSave.toString() + "_" + ".txt");
						File tempFile = new File(cwd + "RxProcessor/ptFiles/" + "TEMP" + lNameSave.toString() + "_"
								+ fNameSave.toString() + "_" + dobSave.toString() + "_" + pnSave.toString() + "_"
								+ phnSave.toString() + "_" + addSave.toString() + "_" + ".txt");

						BufferedReader reader = new BufferedReader(new FileReader(inputFile));
						BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

						String lineToRemove = "####" + commentSave + "####";
						String currentLine;

						while ((currentLine = reader.readLine()) != null) {
							// trim newline when comparing with lineToRemove
							String trimmedLine = currentLine.trim();
							if (trimmedLine.equals(lineToRemove))
								continue;
							writer.write(currentLine + System.getProperty("line.separator"));
						}
						writer.close();
						reader.close();

						if (!inputFile.delete()) {
							JOptionPane.showMessageDialog(ptInfo_1, "NO");
							return;
						}

						if (!tempFile.renameTo(inputFile)) {
							JOptionPane.showMessageDialog(ptInfo_1, "NO 2");
						}

						commentSave = textArea.getText();

					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

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

						String s;
						String g = textArea.getText();
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
						e.printStackTrace();
					}

				}

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
							e.printStackTrace();
						}

					}

				}

			}

		});

		save.setBounds(10, 11, 56, 23);
		ptInfo_1.add(save);
		ptInfo_1.setVisible(false);
		drugInfo_1.setVisible(false);

		prescriberBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				patientBtn.setEnabled(false);
				drugBtn.setEnabled(false);
				prescriberBtn.setEnabled(false);
				prescriberSearch prFrame = new prescriberSearch();
				prFrame.setVisible(true);

			}
		});

		drugBtn.setBounds(143, 10, 123, 23);
		contentPane.add(drugBtn);

		drugBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				patientBtn.setEnabled(false);
				drugBtn.setEnabled(false);
				prescriberBtn.setEnabled(false);
				drugSearch drugFrame = new drugSearch();
				drugFrame.setVisible(true);

			}
		});

		contentPane.setLayout(null);
		prInfo_1.setBackground(new Color(230, 230, 250));

		prInfo_1.setBounds(10, 44, 816, 450);
		contentPane.add(prInfo_1);
		prInfo_1.setLayout(null);

		licenseField.setBounds(564, 88, 153, 20);
		prInfo_1.add(licenseField);

		licenseField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				int textPosition = licenseField.getCaretPosition();
				licenseField.setText(licenseField.getText().toUpperCase());
				licenseField.setCaretPosition(textPosition);
			}
		});

		prAddressField.setColumns(10);
		prAddressField.setBounds(73, 157, 318, 20);
		prInfo_1.add(prAddressField);

		prAddressField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				int textPosition = prAddressField.getCaretPosition();
				prAddressField.setText(prAddressField.getText().toUpperCase());
				prAddressField.setCaretPosition(textPosition);
			}
		});

		prPNField.setColumns(10);
		prPNField.setBounds(564, 157, 153, 20);
		prInfo_1.add(prPNField);

		prPNField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				int textPosition = prPNField.getCaretPosition();
				prPNField.setText(prPNField.getText().toUpperCase());
				prPNField.setCaretPosition(textPosition);
			}
		});

		faxField.setEditable(true);
		faxField.setColumns(10);
		faxField.setBackground(new Color(255, 255, 255));
		faxField.setBounds(401, 157, 153, 20);
		prInfo_1.add(faxField);

		faxField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				int textPosition = faxField.getCaretPosition();
				faxField.setText(faxField.getText().toUpperCase());
				faxField.setCaretPosition(textPosition);
			}
		});

		JLabel label = new JLabel("Last Name");
		label.setBounds(73, 69, 50, 14);
		prInfo_1.add(label);

		JLabel label_1 = new JLabel("First Name");
		label_1.setBounds(332, 69, 51, 14);
		prInfo_1.add(label_1);

		JLabel lblLicense = new JLabel("License");
		lblLicense.setBounds(564, 69, 35, 14);
		prInfo_1.add(lblLicense);

		JLabel label_2 = new JLabel("Street Address ");
		label_2.setBounds(73, 137, 75, 14);
		prInfo_1.add(label_2);

		JLabel label_3 = new JLabel("Phone Number");
		label_3.setBounds(564, 137, 70, 14);
		prInfo_1.add(label_3);

		JLabel lblFaxNumber = new JLabel("Fax Number");
		lblFaxNumber.setBounds(401, 137, 58, 14);
		prInfo_1.add(lblFaxNumber);

		lNameFieldPr = new JTextField();
		lNameFieldPr.setBounds(73, 88, 233, 20);
		prInfo_1.add(lNameFieldPr);
		lNameFieldPr.setColumns(10);

		lNameFieldPr.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				int textPosition = lNameFieldPr.getCaretPosition();
				lNameFieldPr.setText(lNameFieldPr.getText().toUpperCase());
				lNameFieldPr.setCaretPosition(textPosition);
			}
		});

		fNameFieldPr = new JTextField();
		fNameFieldPr.setColumns(10);
		fNameFieldPr.setBounds(321, 88, 233, 20);
		prInfo_1.add(fNameFieldPr);

		fNameFieldPr.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				int textPosition = fNameFieldPr.getCaretPosition();
				fNameFieldPr.setText(fNameFieldPr.getText().toUpperCase());
				fNameFieldPr.setCaretPosition(textPosition);
			}
		});

		JButton prSave = new JButton("\u2611 ");
		prSave.setBounds(10, 11, 56, 23);
		prInfo_1.add(prSave);

		JButton prX = new JButton("X");

		prX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				prInfo_1.setVisible(false);

			}
		});
		prX.setForeground(Color.RED);
		prX.setBounds(766, 11, 40, 23);
		prInfo_1.add(prX);

		lblNameOfPr.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNameOfPr.setBounds(73, 11, 680, 23);
		prInfo_1.add(lblNameOfPr);
		prInfo_1.setVisible(false);
		patientBtn.setBounds(10, 10, 123, 23);
		contentPane.add(patientBtn);

		prSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				saveOldInfoPr = (prLNameSave.toString() + "_" + prFNameSave.toString() + "_" + licenseSave.toString()
						+ "_" + prPNSave.toString() + "_" + faxSave.toString() + "_" + prAddSave.toString() + "_"
						+ ".txt");

				try {
					File inputFilePr = new File(cwd + "RxProcessor/prFiles/prList.txt");
					File tempFilePr = new File(cwd + "RxProcessor/prFiles/prListTemp.txt");
					BufferedReader readerPr = new BufferedReader(new FileReader(inputFilePr));
					BufferedWriter writerPr = new BufferedWriter(new FileWriter(tempFilePr));

					String lineToRemovePr = prLNameSave.toString() + "/" + prFNameSave.toString() + "/"
							+ licenseSave.toString() + "/" + prPNSave.toString() + "/" + faxSave.toString() + "/"
							+ prAddSave.toString() + "/";

					String currentLinePr;
					while ((currentLinePr = readerPr.readLine()) != null) {
						String trimmedLinePr = currentLinePr.trim();
						if (trimmedLinePr.equals(lineToRemovePr))
							continue;
						writerPr.write(currentLinePr + System.getProperty("line.separator"));

					}
					writerPr.write(lNameFieldPr.getText().toString() + "/" + fNameFieldPr.getText().toString() + "/"
							+ licenseField.getText().toString() + "/" + prPNField.getText().toString() + "/"
							+ faxField.getText().toString() + "/" + prAddressField.getText().toString() + "/");

					File tempPrFile = new File(cwd + "RxProcessor/prFiles/" + lNameFieldPr.getText().toString() + "_"
							+ fNameFieldPr.getText().toString() + "_" + licenseField.getText().toString() + "_"
							+ prPNField.getText().toString() + "_" + faxField.getText().toString() + "_"
							+ prAddressField.getText().toString() + "_" + ".txt");
					FileWriter tempFileWpr = new FileWriter(tempPrFile);
					BufferedWriter tempFileBwPr = new BufferedWriter(tempFileWpr);

					FileReader prInfoFileReader = new FileReader(cwd + "RxProcessor/prFiles/" + prLNameSave.toString()
							+ "_" + prFNameSave.toString() + "_" + licenseSave.toString() + "_" + prPNSave.toString()
							+ "_" + faxSave.toString() + "_" + prAddSave.toString() + "_" + ".txt");
					BufferedReader prInfoFile = new BufferedReader(prInfoFileReader);

					String s;
					while ((s = prInfoFile.readLine()) != null) { // read a line
						tempFileWpr.write(s); // write to output file
						tempFileWpr.flush();
					}
					prInfoFile.close();
					tempFileWpr.close();

					tempFileBwPr.close();
					tempFileWpr.close();
					writerPr.newLine();
					writerPr.close();
					readerPr.close();

					lblNameOfPr.setText(fNameFieldPr.getText().toString() + " " + lNameFieldPr.getText().toString());

					if (!inputFilePr.delete()) {
						JOptionPane.showMessageDialog(ptInfo_1, "NO");
						return;
					}

					if (!tempFilePr.renameTo(inputFilePr)) {
						JOptionPane.showMessageDialog(ptInfo_1, "NO 2");
					}

					Files.deleteIfExists(Paths.get(cwd + "RxProcessor/prFiles/" + saveOldInfoPr));

					prLNameSave = lNameFieldPr.getText().toString();
					prFNameSave = fNameFieldPr.getText().toString();
					licenseSave = licenseField.getText().toString();
					faxSave = faxField.getText().toString();
					prPNSave = prPNField.getText().toString();
					prAddSave = prAddressField.getText().toString();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		drugSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				saveOldInfoDrug = (drugNameSave.toString() + "_" + drugStrSave.toString() + "_" + drugDinSave.toString()
						+ "_" + drugUpcSave.toString() + "_" + manuSave.toString() + "_" + ".txt");

				try {
					File inputFileDrug = new File(cwd + "RxProcessor/drugFiles/drugList.txt");
					File tempFileDrug = new File(cwd + "RxProcessor/drugFiles/drugListTemp.txt");
					BufferedReader readerDrug = new BufferedReader(new FileReader(inputFileDrug));
					BufferedWriter writerDrug = new BufferedWriter(new FileWriter(tempFileDrug));

					String lineToRemoveDrug = drugNameSave.toString() + "__" + drugStrSave.toString() + "__"
							+ drugDinSave.toString() + "__" + drugUpcSave.toString() + "__" + manuSave.toString()
							+ "__";

					String currentLineDrug;
					while ((currentLineDrug = readerDrug.readLine()) != null) {
						String trimmedLineDrug = currentLineDrug.trim();
						if (trimmedLineDrug.equals(lineToRemoveDrug))
							continue;
						writerDrug.write(currentLineDrug + System.getProperty("line.separator"));

					}
					writerDrug.write(drugNameField.getText().toString() + "__" + drugStrField.getText().toString()
							+ "__" + drugDinField.getText().toString() + "__" + drugUpcField.getText().toString() + "__"
							+ manuField.getText().toString() + "__");

					File tempDrugFile = new File(cwd + "RxProcessor/drugFiles/" + drugNameField.getText().toString()
							+ "_" + drugStrField.getText().toString() + "_" + drugDinField.getText().toString() + "_"
							+ drugUpcField.getText().toString() + "_" + manuField.getText().toString() + "_" + ".txt");
					FileWriter tempFileWDrug = new FileWriter(tempDrugFile);
					BufferedWriter tempFileBwDrug = new BufferedWriter(tempFileWDrug);

					FileReader DrugInfoFileReader = new FileReader(cwd + "RxProcessor/drugFiles/"
							+ drugNameSave.toString() + "_" + drugStrSave.toString() + "_" + drugDinSave.toString()
							+ "_" + drugUpcSave.toString() + "_" + manuSave.toString() + "_" + ".txt");
					BufferedReader drugInfoFile = new BufferedReader(DrugInfoFileReader);

					String s;
					while ((s = drugInfoFile.readLine()) != null) { // read a line
						tempFileBwDrug.write(s); // write to output file
						tempFileBwDrug.flush();
					}
					drugInfoFile.close();
					tempFileBwDrug.close();

					tempFileBwDrug.close();
					tempFileWDrug.close();
					writerDrug.newLine();
					writerDrug.close();
					readerDrug.close();

					lblNameOfPr.setText(drugNameField.getText().toString() + " " + drugStrField.getText().toString());

					if (!inputFileDrug.delete()) {
						JOptionPane.showMessageDialog(ptInfo_1, "NO");
						return;
					}

					if (!tempFileDrug.renameTo(inputFileDrug)) {
						JOptionPane.showMessageDialog(ptInfo_1, "NO 2");
					}

					Files.deleteIfExists(Paths.get(cwd + "RxProcessor/drugFiles/" + saveOldInfoDrug));

					drugNameSave = drugNameField.getText().toString();
					drugStrSave = drugStrField.getText().toString();
					drugDinSave = drugDinField.getText().toString();
					drugUpcSave = drugUpcField.getText().toString();
					manuSave = manuField.getText().toString();

				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});

	}

	public static void setTextOfFields(Object lNameTable, Object fNameTable, Object fDOB, Object fpn, Object fPHN,
			Object fAdd) throws IOException {

		lNameFill.setText(lNameTable.toString());
		fNameFill.setText(fNameTable.toString());
		dobFill.setText(fDOB.toString());
		pnFill.setText(fpn.toString());
		phnFill.setText(fPHN.toString());
		addFill.setText(fAdd.toString());

		BufferedReader br = new BufferedReader(new FileReader(new File(cwd + "RxProcessor/ptFiles/"
				+ lNameTable.toString() + "_" + fNameTable.toString() + "_" + fDOB.toString() + "_" + fpn.toString()
				+ "_" + fPHN.toString() + "_" + fAdd.toString() + "_" + ".txt")));
		String line;

		while ((line = br.readLine()) != null) {

			if (line.contains("####")) {

				String[] commentLine = line.split("####");
				String[] ary = commentLine;
				String comment = ary[1];
				textArea.setText(comment);

			}

		}
		br.close();

		lNameSave = lNameTable.toString();
		fNameSave = fNameTable.toString();
		dobSave = fDOB.toString();
		phnSave = fPHN.toString();
		pnSave = fpn.toString();
		addSave = fAdd.toString();
		commentSave = textArea.getText().toString();

	}

	public static void setTextOfPrFields(Object lNameTable, Object fNameTable, Object fLicense, Object fpn, Object fFax,
			Object fAdd) {
		lNameFieldPr.setText(lNameTable.toString());
		fNameFieldPr.setText(fNameTable.toString());
		licenseField.setText(fLicense.toString());
		prPNField.setText(fpn.toString());
		faxField.setText(fFax.toString());
		prAddressField.setText(fAdd.toString());

		prLNameSave = lNameTable.toString();
		prFNameSave = fNameTable.toString();
		licenseSave = fLicense.toString();
		faxSave = fFax.toString();
		prPNSave = fpn.toString();
		prAddSave = fAdd.toString();

	}

	public static void setTextOfDrugFields(Object drugNameTable, Object strTable, Object dintable, Object upcTable,
			Object mfrtable) {
		drugNameField.setText(drugNameTable.toString());
		drugStrField.setText(strTable.toString());
		drugDinField.setText(dintable.toString());
		drugUpcField.setText(upcTable.toString());
		manuField.setText(mfrtable.toString());

		drugNameSave = drugNameTable.toString();
		drugStrSave = strTable.toString();
		drugDinSave = dintable.toString();
		drugUpcSave = upcTable.toString();
		manuSave = mfrtable.toString();

	}
}