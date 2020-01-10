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

	protected static prescriberSearch ptFrame;
	private JFrame frame;
	private JTextField fNameField;
	private JTextField lNameField;
	private JTextField addressField;
	private JTextField pnField;
	private JLabel lblAddress;
	protected Writer ptBw;
	public String fName = null;
	public String lName = null;
	public static File ptFwInfo;
	public static FileWriter patientFileWriter;
	public static String ptInfoFileName;

	static String cwd = System.getProperty("user.dir");
	private JTextField textField;
	private JTextField textField_1;

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

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 143, 573, 254);
		frame.getContentPane().add(scrollPane);

		
		
		JButton search = new JButton("Search");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		search.setBounds(505, 401, 89, 23);
		frame.getContentPane().add(search);
		
		JButton newPrescriber = new JButton("New");
		newPrescriber.setBounds(406, 401, 89, 23);
		frame.getContentPane().add(newPrescriber);
		
		textField = new JTextField();
		textField.setBounds(107, 96, 165, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(429, 65, 165, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblFax = new JLabel("Fax #");
		lblFax.setBounds(41, 99, 46, 14);
		frame.getContentPane().add(lblFax);
		
		JLabel lblLicense = new JLabel("License #");
		lblLicense.setBounds(356, 68, 46, 14);
		frame.getContentPane().add(lblLicense);
		
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
