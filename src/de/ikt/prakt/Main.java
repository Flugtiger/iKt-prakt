package de.ikt.prakt;

import java.awt.EventQueue;
import java.net.InetAddress;
import java.util.List;

import de.ikt.prakt.controller.ProfibusInterface;
import de.ikt.prakt.gui.MainWindow;
import de.ikt.prakt.model.Block;
import de.ikt.prakt.model.BlockParameter;
import de.ikt.prakt.model.DeviceDirectory;
import de.ikt.prakt.model.DirectoryEntry;

public class Main {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					//window.setVisible(true);
					
					byte devAddr = 7; //2010TD: 6; ABB Temperaturfühler TF12: 7
					
					ProfibusInterface pb = new ProfibusInterface(InetAddress.getByName("141.76.82.170"),12345);
					
					DeviceDirectory dd = DeviceDirectory.readDevDir(pb, devAddr);
					List<DirectoryEntry> entries = dd.readEntrys(pb);
					
					Block block = Block.readBlock(pb, entries.get(3));
					System.out.println("BlockObject: " + block.getBlock_Object());
					System.out.println("ParentClass: " + block.getParent_Class());
					System.out.println("Class: " + block.getBlockClass());
					System.out.println("Parameters:");
					
					for(BlockParameter param : block.getParameters()) {
						System.out.print("\t" + param.getName() + "\n\t");
						byte[] paramData = block.readParameter(pb, param);
						for(byte b : paramData) {
							System.out.print(String.format("%02X ", b));
						}
						System.out.print("\n\n");
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
