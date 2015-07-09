package de.ikt.prakt.gui;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.CardLayout;
import java.awt.Button;


public class MainWindow {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 432, 280);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1);
		LayoutManager cardLayout = new CardLayout(0,0);
		panel_1.setLayout(cardLayout);
		
		JPanel panel = new JPanel();
		panel_1.add(panel, "name_1147093491352000");
		panel.setLayout(null);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(191, 202, 89, 23);
		panel.add(btnExit);
		
		JButton btnButton = new JButton("Read");
		btnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		btnButton.setBounds(302, 45, 89, 23);
		panel.add(btnButton);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				
			}
		});
		
		btnClear.setBounds(302, 79, 89, 23);
		panel.add(btnClear);	
		
		JLabel lblLabel = new JLabel("Ger\u00E4teadresse eingeben:");
		lblLabel.setBounds(39, 45, 126, 14);
		panel.add(lblLabel);
		
		JLabel lblNewLabel = new JLabel("label2");
		lblNewLabel.setBounds(39, 70, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("label3");
		lblNewLabel_1.setBounds(39, 95, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("label4");
		lblNewLabel_2.setBounds(39, 120, 46, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("label5");
		lblNewLabel_3.setBounds(39, 145, 46, 14);
		panel.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(175, 42, 105, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(93, 67, 105, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(93, 92, 105, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(93, 117, 105, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(93, 142, 105, 20);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnWeiter = new JButton("Weiter");
		btnWeiter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((CardLayout) cardLayout).next(panel_1);
			}
		});
		btnWeiter.setBounds(302, 202, 89, 23);
		panel.add(btnWeiter);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_1.add(panel_2, "name_1147944818536800");
		
		JButton button = new JButton("Exit");
		button.setBounds(191, 202, 89, 23);
		panel_2.add(button);
		
		JButton button_1 = new JButton("Read");
		button_1.setBounds(302, 45, 89, 23);
		panel_2.add(button_1);
		
		JButton button_2 = new JButton("Clear");
		button_2.setBounds(302, 79, 89, 23);
		panel_2.add(button_2);
		
		JLabel label = new JLabel("Ger\u00E4teadresse eingeben:");
		label.setBounds(39, 45, 126, 14);
		panel_2.add(label);
		
		JLabel label_1 = new JLabel("label2");
		label_1.setBounds(39, 70, 46, 14);
		panel_2.add(label_1);
		
		JLabel label_2 = new JLabel("label3");
		label_2.setBounds(39, 95, 46, 14);
		panel_2.add(label_2);
		
		JLabel label_3 = new JLabel("label4");
		label_3.setBounds(39, 120, 46, 14);
		panel_2.add(label_3);
		
		JLabel label_4 = new JLabel("label5");
		label_4.setBounds(39, 145, 46, 14);
		panel_2.add(label_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(175, 42, 105, 20);
		panel_2.add(textField_5);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		textField_7.setBounds(93, 92, 105, 20);
		panel_2.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setEditable(false);
		textField_8.setColumns(10);
		textField_8.setBounds(93, 117, 105, 20);
		panel_2.add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setEditable(false);
		textField_9.setColumns(10);
		textField_9.setBounds(93, 142, 105, 20);
		panel_2.add(textField_9);
		
		JButton button_3 = new JButton("Weiter");
		button_3.setBounds(302, 202, 89, 23);
		panel_2.add(button_3);
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
}