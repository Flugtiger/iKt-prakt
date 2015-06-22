package de.ikt.prakt.controller;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;

import de.ikt.prakt.model.RequestFrame;
import de.ikt.prakt.model.ResponseFrame;

public class ProfibusInterface {

	private DatagramSocket sock;
	private DatagramPacket recvPacket;
	private InetAddress ip;

	public ProfibusInterface(InetAddress ip) {

		this.ip = ip;
		recvPacket = new DatagramPacket(new byte[1024], 1024);
		try {
			sock = new DatagramSocket();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public byte[] readDataAcyclic(byte address, byte slot, byte index) {
		
		RequestFrame req = new RequestFrame(address, slot, index);
		DatagramPacket reqPacket = new DatagramPacket(req.getFrame(), req.getFrame().length, ip, 12345);
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
}
