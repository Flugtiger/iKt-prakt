package de.ikt.prakt.gui;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.InetAddress;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import de.ikt.prakt.controller.ProfibusInterface;
import de.ikt.prakt.model.Block;
import de.ikt.prakt.model.BlockParameter;
import de.ikt.prakt.model.DeviceDirectory;
import de.ikt.prakt.model.DirectoryEntry;

public class MainWindow {

	private JFrame frame;
	private JTextField textField;
	
	private ProfibusInterface pb;
	private String add;
	private JTable blocksTable;
	private DirectoryEntryTableModel blocksTableModel;
	private Block selectedBlock;
	private JTable paramsTable;
	private BlockParameterTableModel paramsTableModel;
	private JTextField dataTextField;

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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		frame.setResizable(false);
		
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
		btnExit.setBounds(10, 250, 89, 23);
		panel.add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");			
			}
		});
		
		btnClear.setBounds(110, 250, 89, 23);
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
				blocksTableModel.setEntriesList(entries);

				((CardLayout) cardLayout).next(panel_1);
			}
		});
		btnWeiter.setBounds(310, 250, 89, 23);
		panel.add(btnWeiter);
		
		textField = new JTextField();
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
		button.setBounds(10, 250, 89, 23);
		panel_2.add(button);
		
		JLabel lblBlockAuswhlen = new JLabel("Block ausw\u00E4hlen:");
		lblBlockAuswhlen.setBounds(59, 25, 126, 14);
		panel_2.add(lblBlockAuswhlen);
		
		JButton button_3 = new JButton("Weiter");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(blocksTable.getSelectedRows().length == 0) {
					JOptionPane.showMessageDialog(frame, "Bitte zuerst einen Block auswählen!!");
					return;
				}
				
				int blockId = blocksTable.getSelectedRow();
				
				DirectoryEntry entry = blocksTableModel.getEntriesList().get(blockId);
				try {
				selectedBlock = Block.readBlock(pb, entry);
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(frame, exc.getMessage());
				}
				List<BlockParameter> params = selectedBlock.getParameters();
				paramsTableModel.setParams(params);
				
				((CardLayout) cardLayout).next(panel_1);
			}
		});
		button_3.setBounds(310, 250, 89, 23);
		panel_2.add(button_3);
		
		JButton btnZurck = new JButton("Zur\u00FCck");
		btnZurck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) cardLayout).previous(panel_1);
			}
		});
		btnZurck.setBounds(210, 250, 89, 23);
		panel_2.add(btnZurck);
		
		
		blocksTableModel = new DirectoryEntryTableModel(new LinkedList<DirectoryEntry>());
		blocksTable = new JTable(blocksTableModel);
		blocksTable.setFillsViewportHeight(true);
		blocksTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		blocksTable.getColumnModel().getColumn(0).setPreferredWidth(90);
		
		JScrollPane panel_4 = new JScrollPane(blocksTable);
		panel_4.setBounds(20, 60, 400, 150);
		panel_2.add(panel_4);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, "name_1149670838138700");
		panel_3.setLayout(null);
		
		JLabel lblParameterAuswhlen = new JLabel("Parameter ausw\u00E4hlen:");
		lblParameterAuswhlen.setBounds(62, 29, 200, 14);
		panel_3.add(lblParameterAuswhlen);
		
		JButton button_5 = new JButton("Weiter");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(paramsTable.getSelectedRows().length == 0) {
					JOptionPane.showMessageDialog(frame, "Bitte zuerst einen Parameter auswählen!");
					return;
				}
				
				int paramId = paramsTable.getSelectedRow();
				BlockParameter param = paramsTableModel.getParams().get(paramId);
				
				try {
					byte[] data = selectedBlock.readParameter(pb, param);
					dataTextField.setText(selectedBlock.paramToString(pb, param, data));
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(frame, exc.getMessage());
				}
				
				((CardLayout) cardLayout).next(panel_1);
			}
		});
		button_5.setBounds(310, 250, 89, 23);
		panel_3.add(button_5);
		
		JButton button_1 = new JButton("Exit");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button_1.setBounds(10, 250, 89, 23);
		panel_3.add(button_1);
		
		JButton button_6 = new JButton("Zur\u00FCck");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) cardLayout).previous(panel_1);
			}
		});
		button_6.setBounds(210, 250, 89, 23);
		panel_3.add(button_6);
		
		paramsTableModel = new BlockParameterTableModel();
		paramsTable = new JTable(paramsTableModel);
		paramsTable.setFillsViewportHeight(true);
		paramsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		paramsTable.getColumnModel().getColumn(0).setPreferredWidth(90);
		
		JScrollPane panel_5 = new JScrollPane(paramsTable);
		panel_5.setBounds(20, 60, 400, 150);
		panel_3.add(panel_5);
		
		JPanel panel_6 = new JPanel();
		panel_1.add(panel_6, "name_1149234238138700");
		panel_6.setLayout(null);
		
		JButton button_7 = new JButton("Exit");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button_7.setBounds(10, 250, 89, 23);
		panel_6.add(button_7);
		
		JButton button_8 = new JButton("Zur\u00FCck");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) cardLayout).previous(panel_1);
			}
		});
		button_8.setBounds(210, 250, 89, 23);
		panel_6.add(button_8);
		
		JLabel lblData = new JLabel("gelesene Daten:");
		lblData.setBounds(59, 25, 126, 14);
		panel_6.add(lblData);
		
		dataTextField = new JTextField();
		dataTextField.setBounds(20, 60, 400, 20);
		dataTextField.setEditable(false);
		
		panel_6.add(dataTextField);
				
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
}