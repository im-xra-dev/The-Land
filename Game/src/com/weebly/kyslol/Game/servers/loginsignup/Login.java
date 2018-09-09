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

public class Login extends JFrame{
	public static boolean running;
	
	public JPanel window = new JPanel();
	private JButton OK, NO, NEW;
	private Rectangle rOK;
	private JTextField username;
	private JPasswordField password;
	private JLabel label;
	boolean eoc;
	Login log = this;
	
	public Login(boolean eoc){
		running = true;
		this.eoc = eoc;
		setTitle("Log in.");
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
		
		NEW = new JButton("Sign Up");
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

		label = new JLabel("Username:");
		label.setBounds(10, 0, 800, 15);
		window.add(label);
		
		label = new JLabel("Password:");
		label.setBounds(10, 40, 800, 15);
		window.add(label);
		
		label = new JLabel("Dont have an account? ");
		label.setBounds(10, 155, 800, 15);
		window.add(label);
		
		password.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				sendData(username.getText(), password.getText(), 25001);
				
			}
		});
		username.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				sendData(username.getText(), password.getText(), 25001);
			}
		});
		
		OK.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				sendData(username.getText(), password.getText(), 25001);
			}
		});
		NO.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				running = false;
				hide();
			}
		});
		
		NEW.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new SignUp(log, eoc);
				hide();
			}
		});
	}
	
	boolean sendData(String un, String pass, int port){
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
			running = false;
		}else{
			if(port == 25001){
				label = new JLabel("Incorrect username-password");
				label.setBounds(115, 90, 800, 15);
				window.add(label);
				label = new JLabel("combination.");
				label.setBounds(165, 105, 800, 15);
				window.add(label);
				repaint();
			}
		}
		return false;
	}
}