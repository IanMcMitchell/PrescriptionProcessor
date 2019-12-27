package RxProcessor;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import javax.swing.JLabel;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddEmployee {

	private JFrame frame;
	private JTextField empName;
	private JTextField empID;
	private String cwd = System.getProperty("user.dir");
	FileWriter employeeWriter;
	FileReader employeeReader;
	private JLabel lblAddEmployee;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton remove;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEmployee window = new AddEmployee();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AddEmployee() {
		initialize();
	}

	private void initialize() {
		
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
		ImageIcon img = new ImageIcon(cwd + "RxProcessor/rxLogo.jpg");
		frame.setIconImage(img.getImage());

		frame.setLocationRelativeTo(null);

		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				EmployeeLogin.frame.setVisible(true);
			}
		});

		empName = new JTextField();
		empName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int textPosition = empName.getCaretPosition();
				empName.setText(empName.getText().toUpperCase());
				empName.setCaretPosition(textPosition);
			}
		});
//			@Override
//			public void keyTyped(KeyEvent evt) {
//				TFKeyTypedUpperCase(evt);
//			}
//		});
		empName.setBounds(10, 62, 223, 20);
		frame.getContentPane().add(empName);
		empName.setColumns(10);

		empID = new JTextField();
		empID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int textPosition = empID.getCaretPosition();
				empID.setText(empID.getText().toUpperCase());
				empID.setCaretPosition(textPosition);
			}
		});
		empID.setBounds(10, 108, 223, 20);
		frame.getContentPane().add(empID);
		empID.setColumns(10);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 48, 46, 14);
		frame.getContentPane().add(lblName);

		JLabel lblEmplyeeIdupc = new JLabel("Emplyee ID (Barcode)");
		lblEmplyeeIdupc.setBounds(10, 93, 133, 14);
		frame.getContentPane().add(lblEmplyeeIdupc);

		lblAddEmployee = new JLabel("Add Employee");
		lblAddEmployee.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAddEmployee.setBounds(74, 11, 97, 20);
		frame.getContentPane().add(lblAddEmployee);

		JButton empAdd = new JButton("+");
		empAdd.setBounds(141, 134, 46, 22);
		empAdd.setBackground(new Color(225, 220, 200));
		frame.getContentPane().add(empAdd);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 162, 223, 254);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		// DefaultTableModel model = new DefaultTableModel();
		// table.setModel(model);

		DefaultTableModel model = (DefaultTableModel) table.getModel();

		model.addColumn("Name");
		model.addColumn("ID");

		scrollPane.setViewportView(table);

		JButton save = new JButton("Save");
		save.setBackground(new Color(225, 220, 200));

		save.setBounds(160, 422, 73, 22);
		frame.getContentPane().add(save);

		remove = new JButton("-");
		remove.setBackground(new Color(225, 220, 200));
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int[] rowsSelected = table.getSelectedRows();
				for (int i = 0; i < rowsSelected.length; i++) {

					model.removeRow(rowsSelected[i] - i);
				}

			}
		});
		remove.setBounds(187, 134, 46, 22);
		frame.getContentPane().add(remove);

		try {
			employeeReader = new FileReader(cwd + "RxProcessor/employeeFiles/employeeList.txt");
			BufferedReader empBr = new BufferedReader(employeeReader);

			Object[] empLines = empBr.lines().toArray();

			for (int i = 0; i < empLines.length; i++) {
				String line = empLines[i].toString().trim();
				String[] dataRow = line.split("/");
				model.addRow(dataRow);
			}

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// File emplFile = new File(cwd + "RxProcessor/employeeFiles");
		// emplFile.mkdir();

		empAdd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String emp = empID.getText();
				String empN = empName.getText();

				if ((!empID.getText().equals("")) & (!empName.getText().equals(""))) {

					model.addRow(new Object[] {

							empN.toString(), emp.toString()

					});

					/**
					 * 
					 * try {
					 * 
					 * System.out.println(empN);
					 * 
					 * employeeWriter = new FileWriter(cwd +
					 * "RxProcessor/employeeFiles/employees.txt", true); employeeReader = new
					 * FileReader(cwd + "RxProcessor/employeeFiles/employees.txt"); BufferedWriter
					 * empBw = new BufferedWriter(employeeWriter); BufferedReader empBr = new
					 * BufferedReader(employeeReader);
					 * 
					 * if (empBr.toString() != "") { empBw.newLine(); } empBw.append(empN + "/" +
					 * emp);
					 * 
					 * // employee1.write(emp);
					 * 
					 * // JOptionPane.showMessageDialog(frame, empN);
					 * 
					 * // String lineOne = empBr.readLine().trim(); // String[] name =
					 * lineOne.split("###");
					 * 
					 * Object[] empLines = empBr.lines().toArray();
					 * 
					 * for (int i = 0; i < empLines.length; i++) { String line =
					 * empLines[i].toString().trim(); String[] dataRow = line.split("/");
					 * model.addRow(dataRow); }
					 * 
					 * empBw.close();
					 * 
					 * } catch (IOException e1) { // TODO Auto-generated catch block
					 * e1.printStackTrace(); }
					 * 
					 * // EmployeeLogin.frame.setVisible(true);
					 * 
					 * // EmployeeLogin empFrame = new EmployeeLogin(); //
					 * empFrame.setVisible(true);
					 * 
					 * // frame.dispose();
					 */
				}
			}
		});

		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					employeeWriter = new FileWriter(cwd + "RxProcessor/employeeFiles/employeeList.txt", false);

					BufferedWriter empBw = new BufferedWriter(employeeWriter);

					for (int rowNumber = 0; rowNumber < table.getRowCount(); rowNumber++) {
						for (int columnNumber = 0; columnNumber < table.getColumnCount(); columnNumber++) {

							empBw.append(model.getValueAt(rowNumber, columnNumber).toString());
							empBw.write("/");
						}
						empBw.newLine();
					}
					empBw.close();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				EmployeeLogin empFrame = new EmployeeLogin(); //
				empFrame.setVisible(true);

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
