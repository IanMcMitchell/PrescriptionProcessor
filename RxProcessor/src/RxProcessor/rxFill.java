package RxProcessor;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ColorUIResource;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Comparator;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.RootPaneContainer;
import javax.swing.JLayeredPane;
import javax.swing.JInternalFrame;

public class rxFill extends JFrame {

	protected static rxFill rxFrame;
	private JFrame frame;

	public static JPanel panel = new JPanel();
	public static JPanel ptInfo_1 = new JPanel();
	public JButton drugBtn = new JButton();
	
	static String cwd = System.getProperty("user.dir");
	
	public JTextField lNameFill;
	public JTextField fNameFill;
	public JTextField dobFill;
	public JButton newRx;

	public rxFill() {

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
		frame.getContentPane().setBackground(new Color(220, 225, 200));

		java.net.URL url = ClassLoader.getSystemResource(cwd + "RxProcessor/rxLogo.jpg");
		frame.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(10, 11, 331, 33);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JButton patientBtn = new JButton("Patient Search");
		patientBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PtSearch ptFrame = new PtSearch();
				ptFrame.setVisible(true);
				
			}
		});
		patientBtn.setBounds(5, 5, 103, 23);
		panel.add(patientBtn);

		frame.setBounds(100, 100, 804, 524);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		ptInfo_1 = new JPanel();

		ptInfo_1.setBounds(0, 43, 768, 431);
		ptInfo_1.setBackground(new Color(220, 225, 200));
		ptInfo_1.setLayout(new FlowLayout());

		JButton newRx = new JButton("New Rx");
		newRx.setBounds(10, 397, 89, 23);
		ptInfo_1.add(newRx);

		JLabel lblNameOfPt = new JLabel("Name of PT");
		lblNameOfPt.setBounds(10, 11, 141, 14);
		ptInfo_1.add(lblNameOfPt);

		JTextField lNameFill = new JTextField();
		lNameFill.setBounds(152, 36, 141, 20);
		ptInfo_1.add(lNameFill);
		lNameFill.setColumns(10);

		JTextField fNameFill = new JTextField();
		fNameFill.setBounds(408, 36, 141, 20);
		ptInfo_1.add(fNameFill);
		fNameFill.setColumns(10);

		JTextField dobFill = new JTextField();
		dobFill.setBounds(672, 36, 86, 20);
		ptInfo_1.add(dobFill);
		dobFill.setColumns(10);

		drugBtn = new JButton("Drug Search");
		drugBtn.setBounds(113, 5, 91, 23);
		panel.add(drugBtn);

		JButton prescriberBtn = new JButton("Prescriber Search");
		prescriberBtn.setBounds(209, 5, 117, 23);
		panel.add(prescriberBtn);

		ImageIcon img = new ImageIcon(cwd + "RxProcessor/rxLogo.jpg");
		frame.setIconImage(img.getImage());

		revalidate();
	}

	public static void main(String[] args) {

		rxFill frame = new rxFill();
		frame.setVisible(true);

	}

	public void setVisible(boolean b) {
		frame.setVisible(true);

	}


}
