package RxProcessor;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Window;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Comparator;
import java.util.Scanner;
import RxProcessor.rxFill;

import javax.imageio.IIOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

//import java.io.*;
import java.lang.Object;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class prescriberSearch {

	private JFrame frame;

	private JTextField fNameField;
	private JTextField lNameField;
	private JTextField addressField;
	private JTextField pnField;
	private JTextField faxField;
	private JTextField licenseField;

	public static Object lNameTable;
	public static Object fNameTable;
	public static Object fLicense;
	public static Object fpn;
	public static Object fFax;
	public static Object fAdd;

	private JLabel lblAddress;
	public static String prInfoFileName;

	public String fName = null;
	public String lName = null;

	public static File prFwInfo;
	public static FileWriter prFileWriter;

	static String cwd = System.getProperty("user.dir");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					prescriberSearch window = new prescriberSearch();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public prescriberSearch() {
		initialize();
	}

	public void initialize() {

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

		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				rxFillingScreen.patientBtn.setEnabled(true);
				rxFillingScreen.prescriberBtn.setEnabled(true);
				rxFillingScreen.drugBtn.setEnabled(true);

			}
		});
		frame.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {

				frame.dispose();

			}
		});
		frame.setBounds(100, 100, 632, 474);
		frame.getContentPane().setBackground(new Color(220, 225, 200));
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		ImageIcon img = new ImageIcon(cwd + "RxProcessor/rxLogo.jpg");
		frame.setIconImage(img.getImage());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		fNameField = new JTextField();

		fNameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int textPosition = fNameField.getCaretPosition();
				fNameField.setText(fNameField.getText().toUpperCase());
				fNameField.setCaretPosition(textPosition);
			}
		});
		fNameField.setBounds(429, 31, 165, 20);
		frame.getContentPane().add(fNameField);
		fNameField.setColumns(10);

		lNameField = new JTextField();

		lNameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int textPosition = lNameField.getCaretPosition();
				lNameField.setText(lNameField.getText().toUpperCase());
				lNameField.setCaretPosition(textPosition);
			}

		});
		lNameField.setBounds(107, 31, 165, 20);
		frame.getContentPane().add(lNameField);
		lNameField.setColumns(10);

		addressField = new JTextField();
		addressField.addKeyListener(new KeyAdapter() {

		});
		addressField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int textPosition = addressField.getCaretPosition();
				addressField.setText(addressField.getText().toUpperCase());
				addressField.setCaretPosition(textPosition);
			}
		});

		addressField.setColumns(10);
		addressField.setBounds(107, 65, 165, 20);
		frame.getContentPane().add(addressField);

		pnField = new JTextField();

		pnField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int textPosition = pnField.getCaretPosition();
				pnField.setText(pnField.getText().toUpperCase());
				pnField.setCaretPosition(textPosition);
			}
		});
		pnField.setColumns(10);
		pnField.setBounds(429, 96, 165, 20);
		frame.getContentPane().add(pnField);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(21, 34, 76, 14);
		frame.getContentPane().add(lblLastName);

		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(346, 34, 63, 14);
		frame.getContentPane().add(lblFirstName);

		JLabel lblPhone = new JLabel("Phone #");
		lblPhone.setBounds(363, 99, 46, 14);
		frame.getContentPane().add(lblPhone);

		lblAddress = new JLabel("Address");
		lblAddress.setBounds(31, 68, 46, 14);
		frame.getContentPane().add(lblAddress);

		faxField = new JTextField();
		faxField.setBounds(107, 96, 165, 20);
		frame.getContentPane().add(faxField);
		faxField.setColumns(10);

		faxField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int textPosition = faxField.getCaretPosition();
				faxField.setText(faxField.getText().toUpperCase());
				faxField.setCaretPosition(textPosition);
			}
		});

		licenseField = new JTextField();
		licenseField.setBounds(429, 65, 165, 20);
		frame.getContentPane().add(licenseField);
		licenseField.setColumns(10);
		licenseField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int textPosition = licenseField.getCaretPosition();
				licenseField.setText(licenseField.getText().toUpperCase());
				licenseField.setCaretPosition(textPosition);
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 143, 573, 254);
		frame.getContentPane().add(scrollPane);

		JTable table = new JTable();
		scrollPane.setViewportView(table);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		table.setDefaultEditor(Object.class, null);
		model.addColumn("Last Name");
		model.addColumn("First Name");
		model.addColumn("License #");
		model.addColumn("Phone #");
		model.addColumn("Fax #");
		model.addColumn("Address");

		JButton search = new JButton("Search");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				model.setRowCount(0);

				String searchVariables = lNameField.getText().toString() + "/" + fNameField.getText().toString();

				prescriberSearch prSeacrh = new prescriberSearch();
				try {
					prSeacrh.parseFile(cwd + "RxProcessor/prFiles/prList.txt", lNameField.getText().toString(),
							fNameField.getText().toString(), model, licenseField.getText().toString(),
							pnField.getText().toString(), faxField.getText().toString(),
							addressField.getText().toString());
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();

				}
			}
		});

		search.setBounds(505, 401, 89, 23);
		frame.getContentPane().add(search);

		JButton newPrescriber = new JButton("New");
		newPrescriber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {

					FileWriter prFw = new FileWriter(cwd + "RxProcessor/prFiles/prList.txt", true);
					BufferedWriter prBw = new BufferedWriter(prFw);

					prBw.newLine();
					prBw.write(lNameField.getText().toString() + "/" + fNameField.getText().toString() + "/"
							+ licenseField.getText().toString() + "/" + pnField.getText().toString() + "/"
							+ faxField.getText().toString() + "/" + addressField.getText().toString() + "/");
					prInfoFileName = lNameField.getText().toString() + "_" + fNameField.getText().toString() + "_"
							+ licenseField.getText().toString() + "_" + pnField.getText().toString() + "_"
							+ faxField.getText().toString() + "_" + addressField.getText().toString() + "_";
					prBw.newLine();

					File ptFwInfo = new File(cwd + "RxProcessor/prFiles/" + prInfoFileName + ".txt");

					prFileWriter = new FileWriter(ptFwInfo);
					BufferedWriter prFileWriterBr = new BufferedWriter(prFileWriter);
					prFileWriterBr.append("#### ####");

					prFw.flush();
					prBw.flush();
					prFileWriterBr.close();
					prFileWriter.close();
					prFw.close();
					prBw.close();

					search.doClick();

					JOptionPane.showMessageDialog(frame, "Prescriber Created", "Prescriber Created",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		newPrescriber.setBounds(406, 401, 89, 23);
		frame.getContentPane().add(newPrescriber);

		JLabel lblFax = new JLabel("Fax #");
		lblFax.setBounds(41, 99, 46, 14);
		frame.getContentPane().add(lblFax);

		JLabel lblLicense = new JLabel("License #");
		lblLicense.setBounds(356, 68, 46, 14);
		frame.getContentPane().add(lblLicense);

		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				if (mouseEvent.getClickCount() == 2) {

					int row = table.getSelectedRow();

					lNameTable = (Object) model.getValueAt(row, 0);
					fNameTable = (Object) model.getValueAt(row, 1);
					fLicense = (Object) model.getValueAt(row, 2);
					fpn = (Object) model.getValueAt(row, 3);
					fFax = (Object) model.getValueAt(row, 4);
					fAdd = (Object) model.getValueAt(row, 5);

					if (enterRx.rxEnterScreenPrSearch == false) {

						Window window = SwingUtilities.windowForComponent(table);

						window.setVisible(false);

						rxFillingScreen.ptInfo_1.setVisible(false);
						rxFillingScreen.drugInfo_1.setVisible(false);
						rxFillingScreen.prInfo_1.setVisible(true);
						rxFillingScreen.patientBtn.setEnabled(true);
						rxFillingScreen.prescriberBtn.setEnabled(true);
						rxFillingScreen.drugBtn.setEnabled(true);
						rxFillingScreen.prInfo_1.repaint();
						rxFillingScreen.lblNameOfPr.setText(fNameTable + " " + lNameTable);
						rxFillingScreen.setTextOfPrFields(lNameTable, fNameTable, fLicense, fpn, fFax, fAdd);

					} else {

						Window window2 = SwingUtilities.windowForComponent(table);

						window2.setVisible(false);
						
						enterRx.rxEnterScreenPrSearch = false;
						enterRx.prField.setText(fNameTable + " " + lNameTable + " | " + fLicense + " | " + fAdd);
						

					}

				}
			}

		};

		table.addMouseListener(mouseListener);

	}

	public void parseFile(String fileName, String lNameSearch, String fNameSearch, DefaultTableModel model,
			String licenseSearch, String pn, String faxSearch, String add) throws FileNotFoundException {
		try (Scanner scan = new Scanner(new File(fileName))) {
			String[] searchName = null;
			String[] fieldValues = null;
			String fName = null;
			String lName = null;
			String prLicense = null;
			String phoneNumber = null;
			String faxNumber = null;
			String address = null;
			int wordCount = 0;
			while (scan.hasNext()) {
				String line = scan.nextLine().toString().trim();

				if (line.contains(lNameSearch) && line.contains(fNameSearch) && line.contains(licenseSearch)
						&& line.contains(pn) && line.contains(faxSearch) && line.contains(add)) {

					searchName = line.toString().split("/");
					String[] ary = searchName;
					lName = ary[0];
					fName = ary[1];
					prLicense = ary[2];
					phoneNumber = ary[3];
					faxNumber = ary[4];
					address = ary[5];

					if ((lName.startsWith(lNameSearch)) && (fName.startsWith(fNameSearch))
							&& (prLicense.startsWith(licenseSearch.toString()))
							&& (phoneNumber.startsWith(pn.toString())) && (faxNumber.startsWith(faxSearch.toString()))
							&& (address.contains(add.toString()))) {

						wordCount++;

					}
					if (wordCount != 0) {
						model.addRow(ary);
					}
				}
			}

			scan.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void TFKeyTypedNumerical(java.awt.event.KeyEvent evt) {

		char TestChar = evt.getKeyChar();
		if (!(Character.isDigit(TestChar))) {
			evt.consume();

		}

	}

	public void setVisible(boolean b) {
		frame.setVisible(true);

	}
}
