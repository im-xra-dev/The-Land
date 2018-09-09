package com.weebly.kyslol.Game.servers.loginsignup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

public class Encrypt {
	public static String UserName, Password, log;
	public static double Data = 1;
	public static Properties property = new Properties();
	private static Socket socket;
	static int port = 25001;
	public String message = "";

	public Encrypt(String un, String pass, int port){
//		System.out.println("Login(25001) or sign up(25002)?: ");
		UserName = un.toLowerCase();

		Password = pass;

		log = "";
		if (Password.length() > UserName.length()) {
			for (int i = 0; i < Password.length(); i++) {
				try {
					log = log + UserName.charAt(i);
				} catch (Exception e) {
					log = log + "_";
				}
				log = log + Password.charAt(i);
			}
		} else {
			for (int i = 0; i < UserName.length(); i++) {
				try {
					log = log + Password.charAt(i);
				} catch (Exception e) {
					log = log + "_";
				}
				log = log + UserName.charAt(i);
			}
		}
		// System.out.println(log);
		String hash = "";
		for (int i = 0; i < log.length(); i++) {
			// char chars = log.charAt(i);
			try {
				int n = log.codePointAt(i);
				// System.out.println(n);
				n = (int) Math.pow(n, i + 1);
				// System.out.println(n);

				Data = Math.abs((log.codePointAt(i) * n) / ((Math.floorMod(i, (int) Math.pow(i + 1, i))) + 1));
				Data *= 37;
				Data -= Math.sqrt(i);
				Data /= Math.sin(Data / 99) + 1;
				if(i < log.length()-1){
					Data *= log.codePointAt(i + 1);
				}else{
					Data *= log.codePointAt(i - 1);
				}
				
				Data = Math.pow(Math.PI * Math.sqrt(Data), (Math.cos(i*957)/99)*191);
				
				hash = hash + Data;
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			// System.out.println(hash);

			// System.out.println(log.length() + " " + i);
		}
//		System.out.println("\n");

		// System.out.println(hash);
		if (hash.contains(".")) {

			String[] store = hash.split("\\.");
			// System.out.println(hash + " " + store.length);

			hash = "";

			for (int i = 0; i < store.length; i++) {
				hash = hash + store[i];
			}
		}

		if (hash.contains("E")) {
			String[] store = hash.split("E");
			// System.out.println(hash + " E");

			hash = "";

			for (int i = 0; i < store.length; i++) {
				hash = hash + store[i];
			}
		}

		// System.out.println(hash);
		if(port == 0){
			message = hash;
			return;
		}
		try {
			String host = "35.167.63.112"; // "localhost";//
			// int port = 25002;// 1 is log in 2 is sign up
			InetAddress address = InetAddress.getByName(host);
			socket = new Socket(address, port);

			// Send the message to the server
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(osw);

			String name_pass = UserName + "_" + hash;

			String sendMessage = name_pass + "\n";
			bw.write(sendMessage);
			bw.flush();
//			System.out.println("Message sent to the server : " + sendMessage);

			// Get the return message from the server
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String message = br.readLine();
			
			this.message = message;
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
