package de.ikt.prakt.model;

import java.util.LinkedList;
import java.util.List;

import de.ikt.prakt.controller.ProfibusInterface;
import de.ikt.prakt.model.blocks.FB_Input;
import de.ikt.prakt.model.blocks.TB_Pressure;
import de.ikt.prakt.model.blocks.TB_Temperature;

public class Block {
	
	private DirectoryEntry dirEntry;
	private int Block_Object;
	private int Parent_Class;
	private int Class;
	
	private int DD_Reference;
	private int DD_Revision;
	private byte[] Profile;
	private int Profile_Revision;
	private int Execution_Time;
	private int Number_Of_Parameters;
	private int Address_Of_View_1;
	private int Number_Of_Views;
	
	private List<BlockParameter> parameters;
	
	public Block() {
		parameters = new LinkedList<BlockParameter>();
	}
	
	public static Block readBlock(ProfibusInterface pb, DirectoryEntry dirEntry) {
		byte[] data = pb.readDataAcyclic(dirEntry.getAddress(), dirEntry.getSlot(), dirEntry.getIndex());
		
		if(data.length != 20) {
			throw new IllegalArgumentException("wrong Block length");
		}
		
		Block b = new Block();
		b.dirEntry = dirEntry;
		b.Block_Object = data[1];
		b.Parent_Class = data[2];
		b.Class = data[3];
		b.DD_Reference = ProfibusInterface.readUnsigned32(data, 4);
		b.DD_Revision = ProfibusInterface.readUnsigned16(data, 8);
		b.Profile = new byte[] {data[10], data[11]};
		b.Profile_Revision = ProfibusInterface.readUnsigned16(data, 12);
		b.Execution_Time = data[14];
		b.Number_Of_Parameters = ProfibusInterface.readUnsigned16(data, 15);
		b.Address_Of_View_1 = ProfibusInterface.readUnsigned16(data, 17);
		b.Number_Of_Views = data[19];
		
		switch(b.Block_Object) {
		case 1:
			//Physical Block
			break;
		case 2:
			//Function Block
			switch(b.Parent_Class) {
				case 1:
					//Input
					switch(b.Class) {
						case 1:
							//Analog Input
							b.parameters = new FB_Input();
							break;
					}
					break;
			}
			break;
		case 3:
			//Transducer Block
			switch(b.Parent_Class) {
				case 1:
					//Pressure
					b.parameters = new TB_Pressure();
					break;
				case 2:
					//Temperature
					b.parameters = new TB_Temperature();
					break;
			}
			break;
		}
		
		return b;
	}
	
	public byte[] readParameter(ProfibusInterface pb, int paramListIndex) {
		return readParameter(pb, parameters.get(paramListIndex));
	}
	
	public byte[] readParameter(ProfibusInterface pb, BlockParameter param) {
		byte[] data = pb.readDataAcyclic(dirEntry.getAddress(), dirEntry.getSlot(), dirEntry.getIndex() + param.getRelativeIndex());
		//if(!(param.isOptional() && data.length == 0) && data.length != param.getSize())
		//	throw new RuntimeException("Parameterdata has wrong size");
		
		return data;
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
	public byte[] getProfile() {
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

	public List<BlockParameter> getParameters() {
		return parameters;
	}
}
