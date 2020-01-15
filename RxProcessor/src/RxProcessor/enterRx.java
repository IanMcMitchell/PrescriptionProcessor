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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class enterRx extends JFrame {

	private JPanel contentPane;

	static String cwd = System.getProperty("user.dir");

	static String drug;
	static String str;
	static String din;

	static String pr;
	static String add;
	static String li;

	public static boolean rxEnterScreenPrSearch = false;
	public static boolean rxEnterScreenDrugSearch = false;
	
	public static JComboBox drugSearchBox = new JComboBox();

	public static JLabel ptName = new JLabel();

	public static JTextField prField = new JTextField();
	public static JTextField drugField = new JTextField();
	private JTextField sigField;

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
		
		JButton btnPrescriberSearch = new JButton("Prescriber Search");
		btnPrescriberSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				rxEnterScreenPrSearch = true;
				prescriberSearch fillingPrSeacrh = new prescriberSearch();
				fillingPrSeacrh.setVisible(true);
				
			}
		});
		btnPrescriberSearch.setBounds(10, 64, 128, 23);
		panel.add(btnPrescriberSearch);
		
		prField = new JTextField();
		prField.setBounds(148, 65, 257, 20);
		prField.setEditable(false);
		panel.add(prField);
		prField.setColumns(10);
		
		JButton btnDSearch = new JButton("Drug Search");
		btnDSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				rxEnterScreenDrugSearch = true;
				drugSearch fillingDrugSeacrh = new drugSearch();
				fillingDrugSeacrh.setVisible(true);
				
				
			}
		});
		btnDSearch.setBounds(10, 98, 128, 23);
		panel.add(btnDSearch);
		
		drugField.setBounds(148, 99, 257, 20);
		drugField.setEditable(false);
		panel.add(drugField);
		drugField.setColumns(10);
		
		sigField = new JTextField();
		sigField.setBounds(10, 132, 395, 20);
		panel.add(sigField);
		sigField.setColumns(10);
		
		JTextArea sigArea = new JTextArea();
		sigArea.setBounds(20, 163, 374, 69);
		panel.add(sigArea);
		
		


	}
}
