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

public class drugSearch {

	private JFrame frame;
	
	private JTextField strengthField; //textField in drugSearch()
	private JTextField drugNameField;
	private JTextField manufactureField;
	private JTextField upcField;
	private JTextField dinField;
	
	private JLabel lblManufacturer;
	
	public String fName = null;
	public String lName = null;
	
	public static Object drugNameTable; //info gathered from table x5
	public static Object strTable;
	public static Object dinTable;
	public static Object upcTable;
	public static Object mfrTable;
	
	
	public static File prFwInfo;
	public static FileWriter drugFileWriter;
	public static String drugInfoFileName;

	//GET CURRENT LOCATION ==== VERY VERY IMPORTANT
	
	static String cwd = System.getProperty("user.dir");
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					drugSearch window = new drugSearch();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public drugSearch() {
		initialize();
	}

	public void initialize() {

		//set look and feel so the JButtons don't look crappy
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
			public void windowClosed(WindowEvent e) { //enable buttons 
				rxFillingScreen.patientBtn.setEnabled(true);
				rxFillingScreen.prescriberBtn.setEnabled(true);
				rxFillingScreen.drugBtn.setEnabled(true);

			}
		});
		//doesn't work, try again later.
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
		ImageIcon img = new ImageIcon(cwd + "RxProcessor/rxLogo.jpg"); //set logo
		frame.setIconImage(img.getImage());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		strengthField = new JTextField();

		strengthField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int textPosition = strengthField.getCaretPosition(); // text to uppercase
				strengthField.setText(strengthField.getText().toUpperCase());
				strengthField.setCaretPosition(textPosition);
			}
		});
		strengthField.setBounds(406, 31, 165, 20);
		frame.getContentPane().add(strengthField);
		strengthField.setColumns(10);

		drugNameField = new JTextField();

		drugNameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {// text to uppercase
				int textPosition = drugNameField.getCaretPosition();
				drugNameField.setText(drugNameField.getText().toUpperCase());
				drugNameField.setCaretPosition(textPosition);
			}

		});
		drugNameField.setBounds(107, 31, 165, 20);
		frame.getContentPane().add(drugNameField);
		drugNameField.setColumns(10);

		manufactureField = new JTextField();
		manufactureField.addKeyListener(new KeyAdapter() {

		});
		manufactureField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {// text to uppercase
				int textPosition = manufactureField.getCaretPosition();
				manufactureField.setText(manufactureField.getText().toUpperCase());
				manufactureField.setCaretPosition(textPosition);
			}
		});

		manufactureField.setColumns(10);
		manufactureField.setBounds(107, 93, 165, 20);
		frame.getContentPane().add(manufactureField);

		JLabel lblDrugName = new JLabel("Drug Name");
		lblDrugName.setBounds(21, 34, 76, 14);
		frame.getContentPane().add(lblDrugName);

		JLabel lblStrength = new JLabel("Strength");
		lblStrength.setBounds(333, 34, 63, 14);
		frame.getContentPane().add(lblStrength);

		lblManufacturer = new JLabel("Manufacturer");
		lblManufacturer.setBounds(21, 96, 67, 14);
		frame.getContentPane().add(lblManufacturer);

		upcField = new JTextField();
		upcField.setBounds(107, 62, 165, 20);
		frame.getContentPane().add(upcField);
		upcField.setColumns(10);

		upcField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {// text to uppercase
				int textPosition = upcField.getCaretPosition();
				upcField.setText(upcField.getText().toUpperCase());
				upcField.setCaretPosition(textPosition);
			}
		});

		dinField = new JTextField();
		dinField.setBounds(406, 62, 165, 20);
		frame.getContentPane().add(dinField);
		dinField.setColumns(10);
		dinField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int textPosition = dinField.getCaretPosition();// text to uppercase
				dinField.setText(dinField.getText().toUpperCase());
				dinField.setCaretPosition(textPosition);
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 143, 573, 254);
		frame.getContentPane().add(scrollPane);

		JTable table = new JTable(); //table to display drug search
		scrollPane.setViewportView(table);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		table.setDefaultEditor(Object.class, null);
		model.addColumn("Drug Name");
		model.addColumn("Drug Str");
		model.addColumn("DIN");
		model.addColumn("UPC");
		model.addColumn("MFR");

		//Search for drug based on entered fields
		
		JButton search = new JButton("Search");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				model.setRowCount(0);

				String searchVariables = drugNameField.getText().toString() + "/" + strengthField.getText().toString();

				drugSearch prSeacrh = new drugSearch();
				try {
					prSeacrh.parseFile(cwd + "RxProcessor/drugFiles/drugList.txt", drugNameField.getText().toString(),
							strengthField.getText().toString(), model, dinField.getText().toString(),
							upcField.getText().toString(), manufactureField.getText().toString());
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();

				}
			}
		});

		search.setBounds(505, 401, 89, 23);
		frame.getContentPane().add(search);

		// create drug file
		
		JButton newDrug = new JButton("New");
		newDrug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {

					FileWriter drugFw = new FileWriter(cwd + "RxProcessor/drugFiles/drugList.txt", true);
					BufferedWriter drugBw = new BufferedWriter(drugFw);

					
					
					drugBw.newLine();
					drugBw.write(drugNameField.getText().toString() + "__" + strengthField.getText().toString() + "__"
							+ dinField.getText().toString() + "__" + upcField.getText().toString() + "__"
							+ manufactureField.getText().toString() + "__");
					drugInfoFileName = drugNameField.getText().toString() + "_" + strengthField.getText().toString() + "_"
							+ dinField.getText().toString() + "_" + upcField.getText().toString() + "_"
							+ manufactureField.getText().toString() + "_";
					drugBw.newLine();

					File drugFwInfo = new File(cwd + "RxProcessor/drugFiles/" + drugInfoFileName + ".txt");
//					FileReader ptFwInfo2 = new FileReader(pt9wInfo);
//					BufferedReader ptInfoBr = new BufferedReader(ptFwInfo2);

					drugFileWriter = new FileWriter(drugFwInfo);
					BufferedWriter drugFileWriterBr = new BufferedWriter(drugFileWriter);
					drugFileWriterBr.append("#### ####");

					drugFw.flush();
					drugBw.flush();
					drugFileWriterBr.close();
					drugFileWriter.close();
					drugFw.close();
					drugBw.close();

					// Window window = SwingUtilities.windowForComponent(table);

					// window.setVisible(false);

					search.doClick();

					JOptionPane.showMessageDialog(frame, "Drug Added", "Drug Added",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		newDrug.setBounds(406, 401, 89, 23);
		frame.getContentPane().add(newDrug);

		JLabel lblUpc = new JLabel("UPC");
		lblUpc.setBounds(21, 65, 46, 14);
		frame.getContentPane().add(lblUpc);

		JLabel lblDin = new JLabel("DIN");
		lblDin.setBounds(359, 65, 24, 14);
		frame.getContentPane().add(lblDin);

		//click table values twice to open file
		
		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				if (mouseEvent.getClickCount() == 2) {

					
				
					Window window = SwingUtilities.windowForComponent(table);

					window.setVisible(false);

					int row = table.getSelectedRow();

					 drugNameTable = (Object) model.getValueAt(row, 0);
					 strTable = (Object) model.getValueAt(row, 1);
					 dinTable = (Object) model.getValueAt(row, 2);
					 upcTable = (Object) model.getValueAt(row, 3);
					 mfrTable = (Object) model.getValueAt(row, 4);
					

					if (enterRx.rxEnterScreenDrugSearch == false) {
					
					
					rxFillingScreen.prInfo_1.setVisible(false);
					rxFillingScreen.ptInfo_1.setVisible(false);
					rxFillingScreen.drugInfo_1.setVisible(true);
					rxFillingScreen.patientBtn.setEnabled(true);
					rxFillingScreen.prescriberBtn.setEnabled(true);
					rxFillingScreen.drugBtn.setEnabled(true);
					rxFillingScreen.prInfo_1.repaint();
					rxFillingScreen.lblNameOfDrug.setText(drugNameTable + " " + strTable);
					rxFillingScreen.setTextOfDrugFields(drugNameTable, strTable, dinTable, upcTable, mfrTable);
					
					} else {
						
						Window window2 = SwingUtilities.windowForComponent(table);

						window2.setVisible(false);
						
						enterRx.rxEnterScreenDrugSearch = false;
						enterRx.drugField.setText(drugNameTable + " | " + strTable + " | " + dinTable + " | " + mfrTable);
						
						
						
					}
				}
			}
		};

		table.addMouseListener(mouseListener);

	}

	// the real search function sent from search.doClick()
	
	public void parseFile(String fileName, String drugNameSearch, String strengthSearch, DefaultTableModel model,
			String dinSearch, String upcSeacrh, String manufactureSearch) throws FileNotFoundException {
		try (Scanner scan = new Scanner(new File(fileName))) {
			String[] searchName = null;
			String[] fieldValues = null;
			String drugName = null;
			String strength = null;
			String din = null;
			String upc = null;
			String manufacture = null;
			
			int wordCount = 0;
			while (scan.hasNext()) {
				String line = scan.nextLine().toString().trim();

				if (line.contains(drugNameSearch) && line.contains(strengthSearch) && line.contains(dinSearch)
						&& line.contains(upcSeacrh) && line.contains(manufactureSearch)) {

					searchName = line.toString().split("__"); //pattern for algorithm
					String[] ary = searchName;
					drugName = ary[0];
					strength = ary[1];
					din = ary[2];
					upc = ary[3];
					manufacture = ary[4];
					

					if ((drugName.startsWith(drugNameSearch)) && (strength.startsWith(strengthSearch))
							&& (din.startsWith(dinSearch.toString()))
							&& (upc.startsWith(upcSeacrh.toString())) && (manufacture.startsWith(manufactureSearch.toString()))) {

						wordCount++;

					}
					if (wordCount != 0) {
						model.addRow(ary); //keep added relevant fields to table
					}
				}
			}

			scan.close();
		} catch (IOException e) {
			e.printStackTrace();
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
