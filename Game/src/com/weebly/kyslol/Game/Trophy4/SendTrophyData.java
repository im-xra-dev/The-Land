package com.weebly.kyslol.Game.Trophy4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

import com.weebly.kyslol.Game.Loop;

public class SendTrophyData {

	private static Socket socket;

	public static int TrophyData(String data) {
		try {
			String host = "localhost";
			int port = 25000;
			InetAddress address = InetAddress.getByName(host);
			socket = new Socket(address, port);

			// Send the message to the server
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(osw);

			String number = data;

			String sendMessage = number + "\n";
			bw.write(sendMessage);
			bw.flush();
//			System.out.println("Message sent to the server : " + sendMessage);

			// Get the return message from the server
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String message = br.readLine();
//			System.out.println("Message received from the server : " + message);
			
			return Integer.parseInt(message);
		} catch (Exception exception) {
//			exception.printStackTrace();
			Loop.inet = false;

			return -1;
		} finally {
			// Closing the socket
			try {
				socket.close();
			} catch (Exception e) {
//				e.printStackTrace();
				Loop.inet = false;
				return -1;

			}
			
		}
	}
}