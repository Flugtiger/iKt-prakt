package de.ikt.prakt.gui;

import java.awt.EventQueue;
import java.net.InetAddress;

import javax.swing.JFrame;

import de.ikt.prakt.controller.ProfibusInterface;

public class MainWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
					
					ProfibusInterface pb = new ProfibusInterface(InetAddress.getByName("141.76.82.170"));
					byte[] data = pb.readDataAcyclic((byte)6, (byte)0, (byte)1);
					
					for(int i = 0; i < data.length; i++) {
						System.out.println(data[i]);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
