package RxProcessor;

import java.awt.*;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NewPharmacist {

	public File pharmFileLocation;
	static FileWriter pharmacistFile;
	FileWriter pharEmpWriter;
	private JFrame frame;
	public JTextField pharName;
	private JButton btnSaveLocation;
	private JButton saveBtn;
	String line = null;
	FileWriter pharWriter;
	FileReader pharReader;
	String cwd = System.getProperty("user.dir");
	private JLabel lblPharmacists;
	static DefaultListModel<Object> listModel;
	private JButton buttonPlus;
	private JLabel lblAddPharmacist;
	private JTextField pharID;
	private JTable table;
	private DefaultTableModel model;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewPharmacist window = new NewPharmacist();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NewPharmacist() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (InstantiationException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IllegalAccessException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (UnsupportedLookAndFeelException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		frame = new JFrame();
		frame.setBounds(100, 100, 259, 490);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(220, 225, 200));
		frame.setLocationRelativeTo(null);

		ImageIcon img = new ImageIcon(cwd + "RxProcessor/rxLogo.jpg");
		frame.setIconImage(img.getImage());

		pharName = new JTextField();
		pharName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				int textPosition = pharName.getCaretPosition();
				pharName.setText(pharName.getText().toUpperCase());
				pharName.setCaretPosition(textPosition);
			}
//			@Override
//			public void keyTyped(KeyEvent evt) {
//				TFKeyTypedUpperCase(evt);
//			}
		});
		
		pharName.setBackground(Color.WHITE);
		pharName.setBounds(10, 57, 223, 20);
		frame.getContentPane().add(pharName);
		pharName.setColumns(10);

		saveBtn = new JButton("Save");
		saveBtn.setBackground(new Color(238, 232, 170));

		saveBtn.setBounds(164, 418, 66, 23);
		frame.getContentPane().add(saveBtn);

		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				LoginRxProcessor.frame.setVisible(true);
			}
		});

		JLabel fileLocationIndicator = new JLabel();

		fileLocationIndicator.setBounds(184, 282, 46, 14);
		frame.getContentPane().add(fileLocationIndicator);

		btnSaveLocation = new JButton("Save Location");
		btnSaveLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// JFileChooser chooser = new JFileChooser( new File("."));
				// chooser.setDialogTitle("Select target directory");
				// chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				// file = chooser.getSelectedFile();

				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int option = fileChooser.showOpenDialog(frame);
				if (option == JFileChooser.APPROVE_OPTION) {
					pharmFileLocation = fileChooser.getSelectedFile();
					fileLocationIndicator.setText(pharmFileLocation.toString());
				}

			}
		});

		btnSaveLocation.setBounds(10, 418, 123, 23);
		btnSaveLocation.setEnabled(false);
		btnSaveLocation.setVisible(false);
		frame.getContentPane().add(btnSaveLocation);

		// File f = new File("RxProcessor");
		// f.mkdir();

		lblAddPharmacist = new JLabel("Add Pharmacist");
		lblAddPharmacist.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAddPharmacist.setBounds(70, 18, 163, 14);
		frame.getContentPane().add(lblAddPharmacist);

		pharID = new JTextField();
		pharID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int textPosition = pharID.getCaretPosition();
				pharID.setText(pharID.getText().toUpperCase());
				pharID.setCaretPosition(textPosition);
			}
		});
		pharID.setBounds(10, 102, 223, 20);
		frame.getContentPane().add(pharID);
		pharID.setColumns(10);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 43, 163, 14);
		frame.getContentPane().add(lblName);

		JLabel lblId = new JLabel("Pharmacist ID (Barcode)");
		lblId.setBounds(10, 88, 163, 14);
		frame.getContentPane().add(lblId);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 152, 223, 260);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		DefaultTableModel model = (DefaultTableModel) table.getModel();

		model.addColumn("Name");
		model.addColumn("ID");

		JButton buttonRemove = new JButton("-");
		buttonRemove.setBackground(new Color(238, 232, 170));
		buttonRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] rowsSelected = table.getSelectedRows();
				for (int i = 0; i < rowsSelected.length; i++) {

					model.removeRow(rowsSelected[i] - i);
				}
			}
		});

		//display current phars
		
		try {
			pharReader = new FileReader(cwd + "RxProcessor/pharmacistFiles/pharmacistList.txt");
			BufferedReader pharBr = new BufferedReader(pharReader);

			Object[] pharLines = pharBr.lines().toArray();

			for (int i = 0; i < pharLines.length; i++) {
				String line = pharLines[i].toString().trim();
				String[] dataRow = line.split("/");
				model.addRow(dataRow);
			}

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		buttonRemove.setBounds(187, 128, 46, 22);
		frame.getContentPane().add(buttonRemove);

		//add phar to JTable, and then add to txt file by clicking save
		
		JButton buttonAdd = new JButton("+");
		buttonAdd.setOpaque(true);
		buttonAdd.setBackground(new Color(238, 232, 170));
		buttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String phar = pharID.getText();
				String pharN = pharName.getText();

				if ((!pharID.getText().equals("")) & (!pharName.getText().equals(""))) {

					model.addRow(new Object[] {

							pharN.toString().toUpperCase(), phar.toString().toUpperCase()

					});

				}
			}
		});

		buttonAdd.setBounds(141, 128, 46, 22);
		frame.getContentPane().add(buttonAdd);

		// saveBtn.addActionListener(this);

		//adding the updated info from the table to the txt file
		
		saveBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				//if ((!pharID.getText().equals("")) & (!pharName.getText().equals(""))) {
					try {
						pharEmpWriter = new FileWriter(cwd + "RxProcessor/employeeFiles/employeeList.txt", false);
						BufferedWriter pharEmpBw = new BufferedWriter(pharEmpWriter);

						pharWriter = new FileWriter(cwd + "RxProcessor/pharmacistFiles/pharmacistList.txt", false);
						BufferedWriter pharBw = new BufferedWriter(pharWriter);

						for (int rowNumber = 0; rowNumber < table.getRowCount(); rowNumber++) {
							for (int columnNumber = 0; columnNumber < table.getColumnCount(); columnNumber++) {

								pharBw.append(model.getValueAt(rowNumber, columnNumber).toString());
								pharBw.write("/");
								pharEmpBw.append(model.getValueAt(rowNumber, columnNumber).toString());
								pharEmpBw.write("/");
							}
							pharBw.newLine();
							pharEmpBw.newLine();
						}
						pharBw.close();
						pharEmpBw.close();

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				//}
				LoginRxProcessor empFrame;
				try {
					empFrame = new LoginRxProcessor();
					empFrame.setVisible(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} //
				

				frame.dispose();


			}
		});

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
