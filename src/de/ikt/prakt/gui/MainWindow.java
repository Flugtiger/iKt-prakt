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

import de.ikt.prakt.controller.ProfibusInterface;
import de.ikt.prakt.model.Block;
import de.ikt.prakt.model.DeviceDirectory;
import de.ikt.prakt.model.DirectoryEntry;

import java.awt.CardLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.InetAddress;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;

public class MainWindow {

	private JFrame frame;
	private JTextField textField;
	
	private ProfibusInterface pb;
	private String add;
	private JTable table;
	private TableModel tableModel;


	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
		try{
			pb = new ProfibusInterface(InetAddress.getByName("141.76.82.170"),12345);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 432, 280);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		final JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1);
		final LayoutManager cardLayout = new CardLayout(0,0);
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
		btnExit.setBounds(10, 200, 89, 23);
		panel.add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");			
			}
		});
		
		btnClear.setBounds(110, 200, 89, 23);
		panel.add(btnClear);	
		
		JLabel lblLabel = new JLabel("Ger\u00E4teadresse eingeben:");
		lblLabel.setBounds(10, 53, 202, 14);
		panel.add(lblLabel);
		
		final JButton btnWeiter = new JButton("Weiter");
		btnWeiter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				add = textField.getText();
				
				byte devAddr = Byte.parseByte(add);
				DeviceDirectory dd = DeviceDirectory.readDevDir(pb, devAddr);
				List<DirectoryEntry> entries = dd.readEntrys(pb);
				

				((CardLayout) cardLayout).next(panel_1);
			}
		});
		btnWeiter.setBounds(310, 200, 89, 23);
		panel.add(btnWeiter);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textField.getText().equals("")){
					btnWeiter.setEnabled(true);			
				}
				else {
					btnWeiter.setEnabled(false);
				}
			}
		});
		textField.setBounds(210, 50, 105, 20);
		panel.add(textField);
		textField.setColumns(10);
		

		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_1.add(panel_2, "name_1147944818536800");
		
		JButton button = new JButton("Exit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button.setBounds(10, 200, 89, 23);
		panel_2.add(button);
		
		JLabel lblBlockAuswhlen = new JLabel("Block ausw\u00E4hlen:");
		lblBlockAuswhlen.setBounds(59, 25, 126, 14);
		panel_2.add(lblBlockAuswhlen);
		
		JButton button_3 = new JButton("Weiter");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) cardLayout).next(panel_1);
			}
		});
		button_3.setBounds(310, 200, 89, 23);
		panel_2.add(button_3);
		
		JButton btnZurck = new JButton("Zur\u00FCck");
		btnZurck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) cardLayout).previous(panel_1);
			}
		});
		btnZurck.setBounds(210, 200, 89, 23);
		panel_2.add(btnZurck);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(196, 27, 89, 81);
		panel_2.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		


		DefaultTableModel tableModel = new DefaultTableModel(1,0);
		table = new JTable(tableModel);

		
		panel_4.add(table, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, "name_1149670838138700");
		panel_3.setLayout(null);
		
		JLabel lblParameterAuswhlen = new JLabel("Parameter ausw\u00E4hlen:");
		lblParameterAuswhlen.setBounds(62, 29, 122, 14);
		panel_3.add(lblParameterAuswhlen);
		
		JButton button_5 = new JButton("Weiter");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_5.setBounds(310, 200, 89, 23);
		panel_3.add(button_5);
		
		JButton button_1 = new JButton("Exit");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button_1.setBounds(10, 200, 89, 23);
		panel_3.add(button_1);
		
		JButton button_6 = new JButton("Zur\u00FCck");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) cardLayout).previous(panel_1);
			}
		});
		button_6.setBounds(210, 200, 89, 23);
		panel_3.add(button_6);
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
}