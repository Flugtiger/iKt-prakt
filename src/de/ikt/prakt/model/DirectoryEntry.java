package de.ikt.prakt.model;

public class DirectoryEntry {
	
	public enum TYPE {
		PBCompListDirEntry,
		TBCompListDirEntry,
		FBCompListDirEntry,
		LOCompListDirEntry,
		PhysicalBlockDirEntry,
		TransducerBlockDirEntry,
		FunctionalBlockDirEntry,
		LinkedObjectDirEntry
	}
	
	private int address;
	private int slot;
	private int index;
	private int numParams;
	private TYPE type;
	
	public static final byte ByteLength = 4;
	
	
	public DirectoryEntry(int devAddress, byte[] entryData, int offset, TYPE t) {
		
		if((entryData.length - offset) < 4)
			throw new IllegalArgumentException("data out of range");
		
		address = devAddress;
		slot = entryData[offset];
		index = entryData[offset+1];
		numParams = (entryData[offset+2] << 8) + entryData[offset+3];
		type = t;
	}
	
	public int getAddress() {
		return address;
	}
	
	public int getSlot() {
		return slot;
	}
	
	public int getIndex() {
		return index;
	}
	
	public int getNumParams() {
		return numParams;
	}
	
	public TYPE getType() {
		return type;
	}
}
