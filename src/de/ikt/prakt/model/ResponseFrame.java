package de.ikt.prakt.model;

import java.util.Arrays;

public class ResponseFrame {
	
	private byte[] frame;
	
	public ResponseFrame(byte[] packet) {
		this.frame = packet;
	}
	
	public byte[] getData() {
		return Arrays.copyOfRange(frame, 1, frame.length);
	}
	
	public boolean isResponseOf(RequestFrame request) {
		return request.getMarker() == frame[0];
	}
}
