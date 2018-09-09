package com.weebly.kyslol.Game.servers.loginsignup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

import com.weebly.kyslol.Game.Loop;

public class AccountServer {
	private static Socket socket;
	private static String message = "";
	
	public static String getMessage() {
		return message;
	}

	public AccountServer(String request, String type, String text, String password){
		try {
			String host = "localhost";
			int port = 25003;
			InetAddress address = InetAddress.getByName(host);
			socket = new Socket(address, port);

			// Send the message to the server
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(osw);

			String data = "{AuthKey:\""+Loop.AUTHKEY+"\"`���`AccountName:\""+Loop.USERNAME+"\"`���`request:\""+request+"\"`���`Type:\""+type+"\"`���`string:\""+text+"\"`���`pass:\""+password+"\"}";

			String sendMessage = data + "\n";
			bw.write(sendMessage);
			bw.flush();
//			System.out.println("Message sent to the server : " + sendMessage);

			// Get the return message from the server
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			message = br.readLine();
//			System.out.println("Message received from the server : " + message);
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			// Closing the socket
			try {
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
