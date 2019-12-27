package RxProcessor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

public class ScreenExp {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScreenExp window = new ScreenExp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ScreenExp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		frame.setSize(screenSize.width,screenSize.height - 50);
		
		frame.getContentPane().setLayout(null);
		
		Button button = new Button("New button");
		button.setBounds(10, 10, 70, 22);
		frame.getContentPane().add(button);
		
		Button button_1 = new Button("New button");
		button_1.setBounds(90, 10, 70, 22);
		frame.getContentPane().add(button_1);
		
		Button button_2 = new Button("New button");
		button_2.setBounds(172, 10, 70, 22);
		frame.getContentPane().add(button_2);
		
		String ptName = new String("");
		ptName = "Patient Search";
	}
}
