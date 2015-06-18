package de.ikt.prakt.controller;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

import de.ikt.prakt.model.RequestFrame;
import de.ikt.prakt.model.ResponseFrame;

public class ProfibusInterface {

	private DatagramSocket sock;
	private InetAddress ip;

	public ProfibusInterface(InetAddress ip) {

		this.ip = ip;
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
			DatagramPacket receive = new DatagramPacket(new byte[256], 256);
			sock.receive(receive);
			ResponseFrame resp = new ResponseFrame(receive.getData());
			return resp.getData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new byte[0];
	}
}
