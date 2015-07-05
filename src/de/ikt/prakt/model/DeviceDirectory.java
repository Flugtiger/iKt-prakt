package de.ikt.prakt.model;

import de.ikt.prakt.controller.ProfibusInterface;


public class DeviceDirectory {
	
	int[] header;
	
	public DeviceDirectory(byte[] headerFrame) {
		
		if(headerFrame.length != 12)
			throw new IllegalArgumentException("Headerframe of device directory hast to be 12 Bytes long");
		
		header = new int[6];
		for(int i = 0; i < 6; i++) {
			header[i] = (headerFrame[2*i] << 8) + headerFrame[2*i+1];
		}
	}
	
	public static DeviceDirectory readDevDir(ProfibusInterface pb, byte address) {
		return new DeviceDirectory(pb.readDataAcyclic(address, 1, 0));
	}
	
	public int getDirId() {
		return header[0];
	}
	
	public int getRevNumber() {
		return header[1];
	}
	
	public int getNumDirObj() {
		return header[2];
	}
	
	public int getNumDirEntry() {
		return header[3];
	}
	
	public int getFirstCompListDirEntry() {
		return header[4];
	}
	
	public int getNumCompListDirEntry() {
		return header[5];
	}
}
