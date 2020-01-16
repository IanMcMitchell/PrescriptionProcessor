package RxProcessor;

import javax.*;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.awt.Toolkit;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Button;
import java.awt.Choice;

public class LoginRxProcessor {

	public static JFrame frame;
	public static int launchCounter;
	private JFrame pharmSelect;
	static String pharmOptions1;
	static String pharmOptions2;
	static String pharmOptions3;
	static String name;
	public static String[] items = {};
	static String cwd = System.getProperty("user.dir");
	static int NEorNP = 0;

	public static void main(String[] args) {
		java.net.URL url = ClassLoader.getSystemResource(cwd + "RxProcessor/rxLogo.jpg");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginRxProcessor window = new LoginRxProcessor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginRxProcessor() throws IOException {
		initialize();
	}

	public static void initialize() throws IOException {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		//create a lot of directories 
		
		File mainFile = new File(cwd + "RxProcessor");
		mainFile.mkdir();
		File pharmFile = new File(cwd + "RxProcessor/pharmacistFiles");
		pharmFile.mkdir();
		File empFile = new File(cwd + "RxProcessor/employeeFiles");
		empFile.mkdir();
		File ptFile = new File(cwd + "RxProcessor/ptFiles");
		ptFile.mkdir();
		File prFile = new File(cwd + "RxProcessor/prFiles");
		prFile.mkdir();
		File drugFiles = new File(cwd + "RxProcessor/drugFiles");
		drugFiles.mkdir();
		File rxFiles = new File(cwd + "RxProcessor/rxFiles");
		rxFiles.mkdir();

		String cwd = System.getProperty("user.dir");

		frame = new JFrame();
		frame.setBounds(100, 100, 260, 170);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(220, 225, 200));
		frame.setTitle("Select Pharmacist");

		ImageIcon img = new ImageIcon(cwd + "RxProcessor/rxLogo.jpg");
		frame.setIconImage(img.getImage());

		frame.setLocationRelativeTo(null);

		Choice pharmChoice = new Choice();

		JLabel lblSelectPharmacist = DefaultComponentFactory.getInstance().createTitle("Select Pharmacist ");
		lblSelectPharmacist.setBounds(77, 11, 146, 14);
		frame.getContentPane().add(lblSelectPharmacist);

		//add a pharmacist
		
		JButton buttonAddPharmacist = new JButton("Add Pharmacist");
		buttonAddPharmacist.setBackground(new Color(225, 220, 200));
		buttonAddPharmacist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (pharmChoice.getItemCount() == 0) {
					launchCounter = 0;

					NewPharmacist npf = new NewPharmacist();
					npf.setVisible(true);

					frame.dispose();
				} else {

					NEorNP = 1;
					PharmPasswordWindow ppw = new PharmPasswordWindow();
					ppw.setVisible(true);

					frame.dispose();
				}
			}
		});

		buttonAddPharmacist.setBounds(10, 98, 224, 23);
		frame.getContentPane().add(buttonAddPharmacist);

		List<String> strings = new ArrayList<String>();
		String[] searchName = null;

		//add pharmacists from pharmacistList.txt to JComboBox
		
		try {

			BufferedReader input = new BufferedReader(
					new FileReader(cwd + "RxProcessor/pharmacistFiles/pharmacistList.txt"));

			try {

				String line = null;
				String id = null;
				name = null;
				while ((line = input.readLine()) != null) {
					searchName = line.split("/");
					String[] ary = searchName;
					id = ary[1];
					name = ary[0];
					pharmChoice.add(name.toString());
				}
			}

			catch (FileNotFoundException e) {
				System.err.println(
						"Error, file " + cwd + "RxProcessor/PharmacistFiles/pharmacistList.txt" + " didn't exist.");
			} finally {
				input.close();

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		String[] choose = strings.toArray(new String[] {});

		for (int i = 0; i < choose.length; i++) {
			// pharmChoice.add(choose[i]);

		}

		pharmChoice.setBounds(10, 36, 224, 20);
		frame.getContentPane().add(pharmChoice);

		//open employee pw screen
		
		JButton EmplyeeLogin = new JButton("Next");
		EmplyeeLogin.setBackground(new Color(225, 220, 200));
		EmplyeeLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Object varName = (Object) pharmChoice.getSelectedItem();

				EmployeeLogin d = new EmployeeLogin();
				d.setVisible(true);

				frame.dispose();

			}
		});
		EmplyeeLogin.setBounds(10, 70, 224, 22);
		frame.getContentPane().add(EmplyeeLogin);

	}

	public void setVisible(boolean b) {

		frame.setVisible(true);
	}
}
