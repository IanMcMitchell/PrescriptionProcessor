package RxProcessor;

import java.awt.EventQueue;

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
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EmployeeLogin {

	public static JFrame frame;
	
	private JTextField textField;
	String cwd = System.getProperty("user.dir");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeLogin window = new EmployeeLogin();
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EmployeeLogin() {
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
		
		File emplFile = new File(cwd + "RxProcessor/employeeFiles");
		emplFile.mkdir();

		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(220, 225, 200));
		frame.setBounds(100, 100, 260, 170);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Employee Login");
		
		ImageIcon img = new ImageIcon(cwd + "RxProcessor/rxLogo.jpg");
		frame.setIconImage(img.getImage());
		
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				LoginRxProcessor.frame.setVisible(true);
			}
		});

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int textPosition = textField.getCaretPosition();
				textField.setText(textField.getText().toUpperCase());
				textField.setCaretPosition(textPosition);
			}
		});
		
		textField.setBackground(Color.WHITE);
		textField.setBounds(10, 42, 224, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		

		JLabel lblScanEmployeeUpc = new JLabel("Scan Employee Barcode (Enter ID)");
		lblScanEmployeeUpc.setBounds(20, 11, 224, 20);
		frame.getContentPane().add(lblScanEmployeeUpc);

		JButton newEmployee = new JButton("Add Employee");
		newEmployee.setBackground(new Color(225,220,200));
		newEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//AddEmployee empAddFrame = new AddEmployee();
				//empAddFrame.setVisible(true);

				LoginRxProcessor.NEorNP = 2;
				PharmPasswordWindow ppw = new PharmPasswordWindow();
				ppw.setVisible(true);
				
				
				frame.dispose();

			}
		});
		newEmployee.setBounds(10, 102, 224, 22);
		frame.getContentPane().add(newEmployee);

	
		Action action = new AbstractAction()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		        
		    	
		    	try {
					FileReader employeeFileSearch = new FileReader(cwd + "RxProcessor/employeeFiles/employeeList.txt");
					BufferedReader employeeFSBR = new BufferedReader(employeeFileSearch);
					String[] searchID = null;
					String[] searchName = null;
					String line;
					String id = null;
					String name = null;
					String input = textField.getText().toString();
					int wordCount = 0;
					while((line = employeeFSBR.readLine()) != null) {
						searchID = line.split("/");
						
						for (String word : searchID) {
							if (word.equals(input)) {
								searchName = line.split("/");
								String [] ary = searchName;
								id = ary[1];
								name = ary[0];
								if (id.equals(input)) {
									wordCount++;
								}
								
							}
						}
					}
					if (wordCount!=0) {
						JOptionPane.showMessageDialog(frame, "Login Successful. Welcome, " + name + ".");
						rxFillingScreen rxFrame = new rxFillingScreen();
						rxFrame.setVisible(true);
						frame.dispose();
					} else {
						JOptionPane.showMessageDialog(frame, "Unknown ID");
					}
					
					employeeFileSearch.close();
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				// if (new File(cwd + "RxProcessor/employeeFiles/" +
				// textField.getText()+".txt").isFile()) {

				// JOptionPane.showMessageDialog(frame, "Login Successful");

				// }
				
				/*
				try {
					String id = "";
					Scanner inputStream  = new Scanner(new File(cwd + "RxProcessor/employeeFiles/employees.txt"));
					String line;
					while (inputStream.hasNextLine()) {
						line = inputStream.nextLine();
						String [] ary = line.split("/");
						id = ary[1];
						String name = ary[0];
					}
					if (id.equals(textField.getText().toString())) {
					JOptionPane.showMessageDialog(frame, "Login Successful");
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				**/
			}

		    	
		    	
		    
		};

		
		
		JButton employeeEnter = new JButton("Enter");
		employeeEnter.setBackground(new Color(225,220,200));
		//employeeEnter.addActionListener(new ActionListener() {
		//	public void actionPerformed(ActionEvent e) {		
		//	}
		//	});
		employeeEnter.addActionListener(action);
		employeeEnter.setBounds(10, 74, 224, 22);
		frame.getContentPane().add(employeeEnter);
		
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
