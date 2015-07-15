package de.ikt.prakt.controller;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

import de.ikt.prakt.model.RequestFrame;
import de.ikt.prakt.model.ResponseFrame;

public class ProfibusInterface {

	private DatagramSocket sock;
	private DatagramPacket recvPacket;
	private InetAddress ip;
	private int port;

	public ProfibusInterface(InetAddress ip, int port) {

		this.ip = ip;
		this.port = port;
		recvPacket = new DatagramPacket(new byte[1024], 1024);
		try {
			sock = new DatagramSocket();
			sock.setSoTimeout(10000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public byte[] readDataAcyclic(int address, int slot, int index) {
		
		RequestFrame req = new RequestFrame((byte)address, (byte)slot, (byte)index);
		DatagramPacket reqPacket = new DatagramPacket(req.getFrame(), req.getFrame().length, ip, port);
		try {
			sock.send(reqPacket);
			
			sock.receive(recvPacket);
			ResponseFrame resp = new ResponseFrame(Arrays.copyOf(recvPacket.getData(), recvPacket.getLength()));
			
			if(!resp.isResponseOf(req)) {
				throw new RuntimeException("Response passt nicht zum Request");
			}
			
			return resp.getData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new byte[0];
	}
	
	public static int readUnsigned32(byte[] data, int offset) {
		return (data[offset] << 3*8) | (data[++offset] << 2*8) | (data[++offset] << 8) | (data[++offset]);
	}
	
	public static int readUnsigned16(byte[] data, int offset) {
		return (data[offset] << 8) | (data[++offset]);
	}
}
