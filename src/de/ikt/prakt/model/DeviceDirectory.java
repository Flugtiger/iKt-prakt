package de.ikt.prakt.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.ikt.prakt.controller.ProfibusInterface;


public class DeviceDirectory {
	
	int[] header;
	byte devAddr;
	
	class CompListDirEntry {
		int index;
		int offset;
		int num;
		
		CompListDirEntry(byte[] data, int offset) {
			index = data[offset];
			this.offset = data[offset+1];
			num = (data[offset+2] << 8) + data[offset+3];
		}
	}
	
	DirectoryEntry.TYPE[] listTypes = {
			DirectoryEntry.TYPE.PBCompListDirEntry,
			DirectoryEntry.TYPE.TBCompListDirEntry,
			DirectoryEntry.TYPE.FBCompListDirEntry,
			DirectoryEntry.TYPE.LOCompListDirEntry
	};
	
	DirectoryEntry.TYPE[] blockTypes = {
			DirectoryEntry.TYPE.PhysicalBlockDirEntry,
			DirectoryEntry.TYPE.TransducerBlockDirEntry,
			DirectoryEntry.TYPE.FunctionalBlockDirEntry,
			DirectoryEntry.TYPE.LinkedObjectDirEntry
	};
	
	Map<DirectoryEntry.TYPE, DirectoryEntry.TYPE> entryTypesMap;
	
	public DeviceDirectory(byte devAddr, byte[] headerFrame) {
		this.devAddr = devAddr;
		
		if(headerFrame.length != 12)
			throw new IllegalArgumentException("Headerframe of device directory hast to be 12 Bytes long");
		
		header = new int[6];
		for(int i = 0; i < 6; i++) {
			header[i] = (headerFrame[2*i] << 8) + headerFrame[2*i+1];
		}
		
		entryTypesMap = new HashMap<DirectoryEntry.TYPE,DirectoryEntry.TYPE>();
		for(int i = 0; i < listTypes.length; i++)
			entryTypesMap.put(listTypes[i], blockTypes[i]);
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
	
	public static DeviceDirectory readDevDir(ProfibusInterface pb, byte address) {		
		return new DeviceDirectory(address, pb.readDataAcyclic(address, 1, 0));
	}
	
	public List<DirectoryEntry> readEntrys(ProfibusInterface pb) {
		byte[] entryListData = pb.readDataAcyclic(devAddr, 1, 1);
		
		int numListEntries = getNumCompListDirEntry();
		
		if(entryListData.length < 3*4 || entryListData.length < 4*numListEntries)
			throw new IllegalArgumentException("Länge der Composite List Directory Entries stimmt nicht");
		
		List<DirectoryEntry> compListDirEntries = new LinkedList<DirectoryEntry>();
		for(int i = 0; i < numListEntries; i++) {
			DirectoryEntry listEntry = new DirectoryEntry(devAddr, entryListData, i*4, listTypes[i]);
			compListDirEntries.add(listEntry);
		}
		
		int index = 1;
		int frameEntryOffset = 0;
		byte[] frame = entryListData;
		List<DirectoryEntry> entries = new LinkedList<DirectoryEntry>();
		for(DirectoryEntry compListDirEntry : compListDirEntries) {
			
			// falls der Directory Entry in einem neuen Index liegt lade diesen
			int entryIndex = compListDirEntry.getSlot();
			if(entryIndex > index) {
				frame = pb.readDataAcyclic(devAddr, 1, entryIndex);
				index = entryIndex;
				frameEntryOffset = compListDirEntry.getIndex();
			}
			if(entryIndex != index)
				throw new RuntimeException("CompListDirEntries haben ungültige Reihenfolge");
			
			// lese die Entries
			for( int i = 0; i < compListDirEntry.getNumParams(); i++) {
				int interFrameOffset = DirectoryEntry.ByteLength * (compListDirEntry.getIndex() - frameEntryOffset + i - 1);
				DirectoryEntry entry = new DirectoryEntry(devAddr, frame, interFrameOffset, entryTypesMap.get(compListDirEntry.getType()));
				entries.add(entry);
			}
		}
		
		return entries;		
	}
}
