package RxProcessor;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class enterRx extends JFrame {

	private JPanel contentPane;

	static String cwd = System.getProperty("user.dir");

	static String drug;
	static String str;
	static String din;

	static String pr;
	static String add;
	static String li;

	public static JComboBox drugSearchBox = new JComboBox();

	public static JLabel ptName = new JLabel();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					enterRx frame = new enterRx();
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
	public enterRx() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 885, 672);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Window window = SwingUtilities.windowForComponent(contentPane);

		window.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(173, 216, 230));
		panel.setBounds(0, 0, 869, 633);
		contentPane.add(panel);
		panel.setLayout(null);

		// JLabel ptName = new JLabel();
		ptName.setFont(new Font("Tahoma", Font.PLAIN, 26));
		ptName.setBounds(10, 11, 849, 42);
		panel.add(ptName);

		List<String> strings = new ArrayList<String>();
		String[] searchDrug = null;

		try {

			BufferedReader input = new BufferedReader(new FileReader(cwd + "RxProcessor/drugFiles/drugList.txt"));

			try {

				String line = null;
				String id = null;
				drug = null;
				str = null;
				din = null;
				while ((line = input.readLine()) != null) {
					if (line.length() > 0) {
						searchDrug = line.split("__");
						String[] ary = searchDrug;
						drug = ary[0];
						str = ary[1];
						din = ary[2];
						drugSearchBox.addItem(drug.toString() + " " + str.toString() + "    |      " + din.toString());
					}
				}
			}

			catch (FileNotFoundException e) {
				System.err.println("Error, file " + cwd + "drugtList.txt" + " didn't exist.");
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

		boolean strictMatching = false;

		AutoCompleteDecorator.decorate(drugSearchBox);

		drugSearchBox.setBounds(10, 76, 849, 22);
		drugSearchBox.setEditable(true);
		panel.add(drugSearchBox);

		JComboBox prCombo = new JComboBox();

		prCombo.setEditable(true);
		prCombo.setBounds(10, 109, 849, 22);
		panel.add(prCombo);
		
		textField = new JTextField();
		textField.setBounds(10, 142, 849, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton Submit = new JButton("Submit");
		Submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});
		Submit.setBounds(770, 599, 89, 23);
		panel.add(Submit);

		String[] searchPr = null;
		
		try {

			BufferedReader inputPr = new BufferedReader(new FileReader(cwd + "RxProcessor/prFiles/prList.txt"));

			try {

				String line = null;
				pr = null;
				add = null;
				li = null;
				while ((line = inputPr.readLine()) != null) {
					if (line.length() > 0) {
						searchPr = line.split("/");
						String[] ary = searchPr;
						pr = ary[1] + " " + ary[0];
						add = ary[5];
						li = ary[2];
						prCombo.addItem(pr.toString() + " | " + add.toString() + " | " + li.toString());
						
					}
				}
			}

			catch (FileNotFoundException e) {
				System.err.println("Error, file " + cwd + "prList.txt" + " didn't exist.");
			} finally {
				inputPr.close();

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	

	}
}
