package de.ikt.prakt.model;


public class RequestFrame {
	
	private byte[] frame;
	
	
	public RequestFrame(byte addr, byte slot, byte index) {
		this();
		setTargetAddress(addr);
		setSlot(slot);
		setIndex(index);
	}
	
	public RequestFrame() {
		frame = new byte[4];
		
		//setze zufälligen marker
		frame[0] = (byte)(Math.random() * 255);
	}
	
	public byte getMarker() {
		return frame[0];
	}
	
	public byte[] getFrame() {
		return frame;
	}
	
	public void setTargetAddress(byte addr) {
		frame[1] = addr;
	}
	
	public void setSlot(byte slot) {
		frame[2] = slot;
	}
	
	public void setIndex(byte index) {
		frame[3] = index;
	}

}
