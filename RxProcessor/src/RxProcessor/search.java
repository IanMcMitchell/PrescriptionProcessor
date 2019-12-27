package RxProcessor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class search {

	public int gitTest;
	private JFrame frame;
	private JTextField textField;
	private JTable table;
	private JTextComponent label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					search window = new search();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public search() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 32, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String searchString = textField.getText().toString();
				String line = "McMitchell";
				String line2 = "Bob";
				
				 if(line.startsWith(searchString) || line2.startsWith(searchString)){
		                JOptionPane.showConfirmDialog(frame, "Hi");
		            }
				
				
				
				
			}
		});
		btnSearch.setBounds(97, 31, 89, 23);
		frame.getContentPane().add(btnSearch);
		
		table = new JTable();
		table.setBounds(38, 79, 253, 153);
		frame.getContentPane().add(table);
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 186, 33);
		frame.getContentPane().add(label);
		
	//	https://stackoverflow.com/questions/29529102/searching-through-a-text-file-java
		
		
	}

}

