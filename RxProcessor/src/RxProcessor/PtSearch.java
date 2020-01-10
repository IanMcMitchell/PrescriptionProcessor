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

public class PtSearch {

	protected static PtSearch ptFrame;
	private JFrame frame;
	private JTextField fNameField;
	private JTextField lNameField;
	private JTextField phnField;
	private JTextField addressField;
	private JTextField dobField;
	private JTextField pnField;
	private JLabel lblAddress;
	protected Writer ptBw;
	public String fName = null;
	public String lName = null;
	public static File ptFwInfo;
	public static FileWriter patientFileWriter;
	public static String ptInfoFileName;

	static String cwd = System.getProperty("user.dir");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PtSearch window = new PtSearch();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PtSearch() {
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

		phnField = new JTextField();

		phnField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int textPosition = phnField.getCaretPosition();
				phnField.setText(phnField.getText().toUpperCase());
				phnField.setCaretPosition(textPosition);
			}
		});
		phnField.setColumns(10);
		phnField.setBounds(107, 62, 165, 20);
		frame.getContentPane().add(phnField);

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
		addressField.setBounds(107, 93, 165, 20);
		frame.getContentPane().add(addressField);

		dobField = new JTextField();
		dobField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int textPosition = dobField.getCaretPosition();
				dobField.setText(dobField.getText().toUpperCase());
				dobField.setCaretPosition(textPosition);
			}
		});
		dobField.setColumns(10);
		dobField.setBounds(429, 62, 165, 20);
		frame.getContentPane().add(dobField);

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
		pnField.setBounds(429, 93, 165, 20);
		frame.getContentPane().add(pnField);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(21, 34, 76, 14);
		frame.getContentPane().add(lblLastName);

		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(352, 34, 63, 14);
		frame.getContentPane().add(lblFirstName);

		JLabel lblDob = new JLabel("DOB");
		lblDob.setBounds(352, 65, 46, 14);
		frame.getContentPane().add(lblDob);

		JLabel lblPhone = new JLabel("Phone #");
		lblPhone.setBounds(352, 96, 46, 14);
		frame.getContentPane().add(lblPhone);

		JLabel lblPhn = new JLabel("PHN");
		lblPhn.setBounds(21, 65, 46, 14);
		frame.getContentPane().add(lblPhn);

		lblAddress = new JLabel("Address");
		lblAddress.setBounds(21, 96, 46, 14);
		frame.getContentPane().add(lblAddress);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 143, 573, 254);
		frame.getContentPane().add(scrollPane);

		JTable table = new JTable();
		scrollPane.setViewportView(table);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		table.setDefaultEditor(Object.class, null);
		model.addColumn("Last Name");
		model.addColumn("First Name");
		model.addColumn("DOB");
		model.addColumn("Phone #");
		model.addColumn("PHN");
		model.addColumn("Address");

		JButton Search = new JButton("Search");
		Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				model.setRowCount(0);

				String searchVariables = lNameField.getText().toString() + "/" + fNameField.getText().toString();

				if ((!dobField.getText().toString().equals("") & (dobField.getText().length() != 8))) {
					JOptionPane.showMessageDialog(frame, "Use YYYYMMDD", "DOB Error", JOptionPane.WARNING_MESSAGE);

				} else {

					PtSearch ptSearch = new PtSearch();
					try {
						ptSearch.parseFile(cwd + "RxProcessor/ptFiles/ptList.txt", lNameField.getText().toString(),
								fNameField.getText().toString(), model, dobField.getText().toString(),
								pnField.getText().toString(), phnField.getText().toString(),
								addressField.getText().toString());
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		Search.setBounds(524, 403, 70, 22);
		frame.getContentPane().add(Search);

		JButton newPt = new JButton("New");
		newPt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if ((dobField.getText().toString().equals("") | (dobField.getText().length() == 8))) {

					try {

						FileWriter ptFw = new FileWriter(cwd + "RxProcessor/ptFiles/ptList.txt", true);
						BufferedWriter ptBw = new BufferedWriter(ptFw);

						ptBw.newLine();
						ptBw.write(lNameField.getText().toString() + "/" + fNameField.getText().toString() + "/"
								+ dobField.getText().toString() + "/" + pnField.getText().toString() + "/"
								+ phnField.getText().toString() + "/" + addressField.getText().toString() + "/");
						ptInfoFileName = lNameField.getText().toString() + "_" + fNameField.getText().toString() + "_"
								+ dobField.getText().toString() + "_" + pnField.getText().toString() + "_"
								+ phnField.getText().toString() + "_" + addressField.getText().toString() + "_";
						ptBw.newLine();

						File ptFwInfo = new File(cwd + "RxProcessor/ptFiles/" + ptInfoFileName + ".txt");
//						FileReader ptFwInfo2 = new FileReader(ptFwInfo);
//						BufferedReader ptInfoBr = new BufferedReader(ptFwInfo2);

						patientFileWriter = new FileWriter(ptFwInfo);
						BufferedWriter patientFileWriterBr = new BufferedWriter(patientFileWriter);
						patientFileWriterBr.append("#### ####");

						ptFw.flush();
						ptBw.flush();
						patientFileWriterBr.close();
						patientFileWriter.close();
						ptFw.close();
						ptBw.close();

						// Window window = SwingUtilities.windowForComponent(table);

						// window.setVisible(false);

						Search.doClick();

						JOptionPane.showMessageDialog(frame, "Patient Created", "Patient Created",
								JOptionPane.INFORMATION_MESSAGE);

					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Use YYYYMMDD", "DOB Error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		newPt.setBounds(443, 403, 70, 22);
		frame.getContentPane().add(newPt);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnNewButton.setBounds(21, 403, 89, 23);
		frame.getContentPane().add(btnNewButton);

		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				if (mouseEvent.getClickCount() == 2) {

					Window window = SwingUtilities.windowForComponent(table);

					window.setVisible(false);

					int row = table.getSelectedRow();

					Object lNameTable = (Object) model.getValueAt(row, 0);
					Object fNameTable = (Object) model.getValueAt(row, 1);
					Object fDOB = (Object) model.getValueAt(row, 2);
					Object fpn = (Object) model.getValueAt(row, 3);
					Object fPHN = (Object) model.getValueAt(row, 4);
					Object fAdd = (Object) model.getValueAt(row, 5);

					

					rxFillingScreen.ptInfo_1.setVisible(true);
					rxFillingScreen.patientBtn.setEnabled(true);
					rxFillingScreen.prescriberBtn.setEnabled(true);
					rxFillingScreen.drugBtn.setEnabled(true);
					rxFillingScreen.ptInfo_1.repaint();
					rxFillingScreen.lblNameOfPt.setText(fNameTable + " " + lNameTable);
					try {
						rxFillingScreen.setTextOfFields(lNameTable, fNameTable, fDOB, fpn, fPHN, fAdd);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		};

		table.addMouseListener(mouseListener);

	}

	public void parseFile(String fileName, String lNameSearch, String fNameSearch, DefaultTableModel model, String DOB,
			String pn, String phn, String add) throws FileNotFoundException {
		try (Scanner scan = new Scanner(new File(fileName))) {
			String[] searchName = null;
			String[] fieldValues = null;
			String fName = null;
			String lName = null;
			String ptDob = null;
			String phoneNumber = null;
			String healthcareNumber = null;
			String address = null;
			int wordCount = 0;
			while (scan.hasNext()) {
				String line = scan.nextLine().toString().trim();

				if (line.contains(lNameSearch) && line.contains(fNameSearch) && line.contains(DOB) && line.contains(pn)
						&& line.contains(phn) && line.contains(add)) {

					searchName = line.toString().split("/");
					String[] ary = searchName;
					lName = ary[0];
					fName = ary[1];
					ptDob = ary[2];
					phoneNumber = ary[3];
					healthcareNumber = ary[4];
					address = ary[5];

					if ((lName.startsWith(lNameSearch)) && (fName.startsWith(fNameSearch))
							&& (ptDob.startsWith(DOB.toString())) && (phoneNumber.startsWith(pn.toString()))
							&& (healthcareNumber.startsWith(phn.toString())) && (address.contains(add.toString()))) {

						// JOptionPane.showMessageDialog(frame, ary);
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

	private void TFKeyTypedUpperCase(java.awt.event.KeyEvent evt) {

		char TestChar = evt.getKeyChar();
		if (!(Character.isUpperCase(TestChar))) {
			evt.consume();

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
