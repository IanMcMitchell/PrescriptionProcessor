package RxProcessor;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.PageRanges;
import javax.print.attribute.standard.PresentationDirection;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JScrollPane;

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
	private JTextField prQty;
	private JTextField qty;
	private JTextField ds;
	private JTextField refills;

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
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				rxFillingScreen.newRx.setEnabled(true);
			}
		});

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 441, 361);
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

		JTextArea sigArea = new JTextArea();
		sigArea.getDocument().putProperty("filterNewlines", Boolean.TRUE);
		sigArea.setEditable(false);
		sigArea.setBounds(10, 163, 395, 69);
		sigArea.setLineWrap(true);
		sigArea.getDocument().putProperty("filterNewlines", Boolean.TRUE);
		panel.add(sigArea);

		sigField = new JTextField();
		sigField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				int textPosition = sigField.getCaretPosition();
				sigField.setText(sigField.getText().toUpperCase());
				sigField.setCaretPosition(textPosition);

				sigArea.setText(sigField.getText().toString());

				//QUICK CODES!!!
				
				if (sigField.getText().toString().contains("T1T")) {
					String replace = sigArea.getText();
					String replaceString = replace.replaceAll("T1T", "TAKE ONE TABLET BY MOUTH");
					sigArea.setText(replaceString);
				}
				if (sigField.getText().toString().contains("T1C")) {
					String replace = sigArea.getText();
					String replaceString = replace.replaceAll("T1C", "TAKE ONE CAPSULE BY MOUTH");
					sigArea.setText(replaceString);
				}
				if (sigField.getText().toString().contains("BID")) {
					String replace = sigArea.getText();
					String replaceString = replace.replaceAll("BID", "TWICE DAILY");
					sigArea.setText(replaceString);
				}
				if (sigField.getText().toString().contains("TID")) {
					String replace = sigArea.getText();
					String replaceString = replace.replaceAll("TID", "THREE TIMES DAILY");
					sigArea.setText(replaceString);
				}
				if (sigField.getText().toString().contains("QID")) {
					String replace = sigArea.getText();
					String replaceString = replace.replaceAll("QID", "FOUR TIMES DAILY");
					sigArea.setText(replaceString);
				}
				if (sigField.getText().toString().contains("PO")) {
					String replace = sigArea.getText();
					String replaceString = replace.replaceAll("PO", "BY MOUTH");
					sigArea.setText(replaceString);
				}
				if (sigField.getText().toString().contains("QAM")) {
					String replace = sigArea.getText();
					String replaceString = replace.replaceAll("QAM", "EVERY MORNING");
					sigArea.setText(replaceString);
				}
				if (sigField.getText().toString().contains("QHS")) {
					String replace = sigArea.getText();
					String replaceString = replace.replaceAll("QHS", "AT NIGHT");
					sigArea.setText(replaceString);
				}
				if (sigField.getText().toString().contains("UD")) {
					String replace = sigArea.getText();
					String replaceString = replace.replaceAll("UD", "AS DIRECTED");
					sigArea.setText(replaceString);
				}
				if (sigField.getText().toString().contains("PRN")) {
					String replace = sigArea.getText();
					String replaceString = replace.replaceAll("PRN", "AS NEEDED");
					sigArea.setText(replaceString);
				}
				if (sigField.getText().toString().contains("UF")) {
					String replace = sigArea.getText();
					String replaceString = replace.replaceAll("UF", "UNTIL FINISHED");
					sigArea.setText(replaceString);
				}
				if (sigField.getText().toString().contains("1D")) {
					String replace = sigArea.getText();
					String replaceString = replace.replaceAll("1D", "ONCE DAILY");
					sigArea.setText(replaceString);
				}
				if (sigField.getText().toString().contains(" F ")) {
					String replace = sigArea.getText();
					String replaceString = replace.replaceAll("F", "FOR");
					sigArea.setText(replaceString);
				}
				if (sigField.getText().toString().contains("DS")) {
					String replace = sigArea.getText();
					String replaceString = replace.replaceAll("DS", "DAYS");
					sigArea.setText(replaceString);
				}

			}
		});
		sigField.setBounds(39, 132, 366, 20);
		panel.add(sigField);
		sigField.setColumns(10);

		JLabel lblSig = new JLabel("SIG");
		lblSig.setBounds(10, 132, 46, 14);
		panel.add(lblSig);

		JLabel lblNewLabel = new JLabel("Presc. Qty");
		lblNewLabel.setBounds(10, 258, 73, 14);
		panel.add(lblNewLabel);

		prQty = new JTextField();
		prQty.setBounds(10, 275, 46, 20);
		panel.add(prQty);
		prQty.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Qty");
		lblNewLabel_1.setBounds(93, 258, 46, 14);
		panel.add(lblNewLabel_1);

		qty = new JTextField();
		qty.setBounds(80, 275, 46, 20);
		panel.add(qty);
		qty.setColumns(10);

		ds = new JTextField();
		ds.setBounds(148, 275, 46, 20);
		panel.add(ds);
		ds.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Days Supply");
		lblNewLabel_2.setBounds(142, 258, 59, 14);
		panel.add(lblNewLabel_2);

		refills = new JTextField();
		refills.setBounds(220, 275, 46, 20);
		panel.add(refills);
		refills.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Refills");
		lblNewLabel_3.setBounds(230, 258, 46, 14);
		panel.add(lblNewLabel_3);

		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Window window = SwingUtilities.windowForComponent(panel);

				window.setVisible(false);
				
				rxFillingScreen.newRx.setEnabled(true);
				try {
					
					
					FileWriter rxFw = new FileWriter(cwd + "RxProcessor/ptFiles/" + rxFillingScreen.fileName, true);
					BufferedWriter rxBw = new BufferedWriter(rxFw);

					//create pattern to save info
					
					Random rand = new Random();
					int randomRxNumber = rand.nextInt(9999999);
					String rxNumber = Integer.toString(randomRxNumber);

					rxBw.append("**********" + randomRxNumber + "**********" + prField.getText().toString()
							+ "**********" + drugField.getText().toString() + "**********"
							+ sigField.getText().toString() + "**********" + prQty.getText().toString() + "**********"
							+ qty.getText().toString() + "**********" + ds.getText().toString() + "**********"
							+ refills.getText().toString() + "**********");
					rxBw.newLine();
					
					File newRxFile = new File(cwd + "RxProcessor/rxFiles/" + randomRxNumber + ".txt");
					FileWriter rxFwTx = new FileWriter(newRxFile, true);
					BufferedWriter rxBwTx = new BufferedWriter(rxFwTx);
					
					rxBwTx.newLine();
					rxBwTx.append(rxNumber);
					rxBwTx.newLine();
					rxBwTx.append(rxFillingScreen.lNameSave + ", " + rxFillingScreen.fNameSave);
					rxBwTx.newLine();
					rxBwTx.append(prescriberSearch.lNameTable.toString() + ", " + prescriberSearch.fNameTable.toString());
					rxBwTx.newLine();
					rxBwTx.append(prescriberSearch.fLicense.toString() + " | " + prescriberSearch.fpn.toString() + " | " + prescriberSearch.fFax.toString() + " | " + prescriberSearch.fAdd.toString());
					rxBwTx.newLine();
					rxBwTx.append(drugField.getText().toString());
					rxBwTx.newLine();
					rxBwTx.append(drugSearch.strTable.toString() + " | " +
							 drugSearch.mfrTable.toString());
					rxBwTx.newLine();
					rxBwTx.append("DIN: " + drugSearch.dinTable.toString());
					rxBwTx.newLine();
					rxBwTx.newLine();
					rxBwTx.append(sigArea.getText().toString());
					rxBwTx.newLine();
					rxBwTx.newLine();
					rxBwTx.append(
							"Auth Qty: " + prQty.getText() + "   Qty: " + qty.getText().toString() + "   Days Supply: "
									+ ds.getText().toString() + "   Refills: " + refills.getText().toString());
					rxFw.flush();
					rxBw.flush();
					rxFw.close();
					rxBw.close();
					rxFwTx.flush();
					rxBwTx.flush();
					rxFwTx.close();
					rxBwTx.close();
					
					FileInputStream textStream;
					textStream = new FileInputStream(cwd + "RxProcessor/rxFiles/" + randomRxNumber + ".txt");

					  PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
					  aset.add(new PageRanges(1, 1));
					
					DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
					Doc mydoc = new SimpleDoc(textStream, flavor, null);

					//print when submitted
					
					   PrintService[] services = PrintServiceLookup.lookupPrintServices(
					                flavor, aset);
					   PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();

					   if(services.length == 0) {
					       if(defaultService == null) {
					             //no printer found

					       } else {
					            //print using default
					            DocPrintJob job = defaultService.createPrintJob();
					            try {
									job.print(mydoc, aset);
								} catch (PrintException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

					       }
					   }
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(316, 274, 89, 23);
		panel.add(btnNewButton);

	}
}
