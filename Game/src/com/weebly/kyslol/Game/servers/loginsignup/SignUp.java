package com.weebly.kyslol.Game.servers.loginsignup;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.weebly.kyslol.Game.Loop;

public class SignUp extends JFrame{
	Login login;
	
	public JPanel window = new JPanel();
	private JButton OK, NO, NEW;
	private Rectangle rOK;
	private JTextField username;
	private JPasswordField password;
	private JLabel label, error;

	public SignUp(Login log, boolean eoc){
		login = log;
		
		setTitle("Sign Up!");
		setSize(400, 200);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		add(window);
		window.setBackground(new Color(0xDFDFDF));
		window.setLayout(null);
		if(eoc){
			setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
		
		try {
			setIconImage((ImageIO.read(Loop.class.getResource("/Ghost.png"))));
		} catch (Exception e) {
			System.out.println("ERROR LOADING: Ghost.png");
			e.printStackTrace();
			System.exit(1);
		}
		drawButtons();
		repaint();

	}

	private void drawButtons() {
		OK = new JButton("OK");
		rOK = new Rectangle(30, 90, 80, 40);
		OK.setBounds(rOK);
		window.add(OK);
		
		NO = new JButton("Cancel");
		rOK = new Rectangle(290, 90, 80, 40);
		NO.setBounds(rOK);
		window.add(NO);
		
		NEW = new JButton("Log In");
		rOK = new Rectangle(160, 150, 80, 20);
		NEW.setBounds(rOK);
		window.add(NEW);
		
		username = new JTextField();
		username.setBounds(10, 20, 375, 20);
		window.add(username);
		
		password = new JPasswordField();
		password.setBounds(10, 60, 375, 20);
		password.setEchoChar('*');
		window.add(password);

		label = new JLabel("Username:  (min 4 chars)");
		label.setBounds(10, 0, 800, 15);
		window.add(label);
		
		label = new JLabel("Password:  (min 6 chars)");
		label.setBounds(10, 40, 800, 15);
		window.add(label);
		
		label = new JLabel("Already got an account? ");
		label.setBounds(10, 155, 800, 15);
		window.add(label);
		
		password.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(username.getText().length() >= 4 && password.getText().length() >= 6){
					sendData(username.getText(), password.getText(), 25002);
				}
				
			}
		});
		username.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(username.getText().length() >= 4 && password.getText().length() >= 6){
					sendData(username.getText(), password.getText(), 25002);
				}
			}
		});
		
		OK.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(username.getText().length() >= 4 && password.getText().length() >= 6){
					sendData(username.getText(), password.getText(), 25002);
				}
			}
		});
		NO.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				login.running = false;
				hide();
			}
		});
		
		NEW.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				login.show();
				hide();
			}
		});
	}
	
	boolean sendData(String un, String pass, int port){
//		if(un.contains("_")){
//			if(error != null) error.removeAll();
//			error = new JLabel("Usernames cant currently");
//			error.setBounds(115, 90, 800, 15);
//			window.add(error);
//			
//			error = new JLabel("contain underscores...");
//			error.setBounds(115, 105, 800, 15);
//			window.add(error);
//
//			repaint();
//			return false;
//		}
		char[] p = password.getPassword();
		pass = "";
		
		for(int i = 0; i < p.length; i ++){
			pass = pass + p[i];
		}


		Encrypt en = new Encrypt(un, pass, port);
		
		String correct = (en.message);
		if(correct.contains("true")){
			Loop.USERNAME = username.getText();
			Loop.AUTHKEY = en.message.split("_")[1];

			hide();
			login.running = false;
		}else{
			if(error != null) error.remove(error);
			
				error = new JLabel("      Username taken, sorry!");
				error.setBounds(115, 90, 800, 15);
				window.add(error);
				repaint();
		}
		return false;
	}
}