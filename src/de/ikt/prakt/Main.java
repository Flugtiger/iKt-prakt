package de.ikt.prakt;

import java.awt.EventQueue;
import java.net.InetAddress;
import java.util.List;

import de.ikt.prakt.controller.ProfibusInterface;
import de.ikt.prakt.gui.MainWindow;
import de.ikt.prakt.model.DeviceDirectory;
import de.ikt.prakt.model.DirectoryEntry;

public class Main {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.setVisible(true);
					
					byte devAddr = 6; //2010TD: 6; ABB Temperaturfühler TF12: 7
					
					ProfibusInterface pb = new ProfibusInterface(InetAddress.getByName("141.76.82.170"),12345);
					
					DeviceDirectory dd = DeviceDirectory.readDevDir(pb, devAddr);
					List<DirectoryEntry> entries = dd.readEntrys(pb);
					
					for(DirectoryEntry entry : entries) {
						System.out.println(entry.getType() + ":");
						System.out.println("\t"+entry.getSlot()+"/"+entry.getIndex());
						System.out.println("\t"+entry.getNumParams());
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
