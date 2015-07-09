package de.ikt.prakt.model;

import de.ikt.prakt.controller.ProfibusInterface;

public class Block {
	private int Block_Object;
	private int Parent_Class;
	private int Class;
	
	private int DD_Reference;
	private int DD_Revision;
	private int Profile;
	private int Profile_Revision;
	private int Execution_Time;
	private int Number_Of_Parameters;
	private int Address_Of_View_1;
	private int Number_Of_Views;
	
	public static Block readBlock(ProfibusInterface pb, DirectoryEntry dirEntry) {
		byte[] data = pb.readDataAcyclic(dirEntry.getAddress(), dirEntry.getSlot(), dirEntry.getIndex());
		return new Block();
	}
	
	public int getBlock_Object() {
		return Block_Object;
	}
	public int getParent_Class() {
		return Parent_Class;
	}
	public int getBlockClass() {
		return Class;
	}
	public int getDD_Reference() {
		return DD_Reference;
	}
	public int getDD_Revision() {
		return DD_Revision;
	}
	public int getProfile() {
		return Profile;
	}
	public int getProfile_Revision() {
		return Profile_Revision;
	}
	public int getExecution_Time() {
		return Execution_Time;
	}
	public int getNumber_Of_Parameters() {
		return Number_Of_Parameters;
	}
	public int getAddress_Of_View_1() {
		return Address_Of_View_1;
	}
	public int getNumber_Of_Views() {
		return Number_Of_Views;
	}
}
