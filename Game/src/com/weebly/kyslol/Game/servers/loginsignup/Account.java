package com.weebly.kyslol.Game.servers.loginsignup;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.weebly.kyslol.Game.Loop;
import com.weebly.kyslol.Game.Trophy4.TrophyTracker;
import com.weebly.kyslol.Game.entities.Player;
import com.weebly.kyslol.Game.player.potions.Effects;

public class Account extends JFrame {
	Properties GameDta = new Properties();

	public JPanel window = new JPanel();
	private JButton OK;
	private Rectangle rOK;
	private JTextField email, banned, username;
	private JCheckBox secure;
	private JPasswordField password, oldPassword;

	private JLabel label;
	int dataInt = 0;
	
	
	public Account() {
		
		if(Loop.USERNAME == null){
			new Login(false);
			return;
		}
		
		setTitle("Account Settings.");
		setSize(400, 200);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		add(window);
		window.setBackground(new Color(0xDFDFDF));
		window.setLayout(null);

		try {
			setIconImage((ImageIO.read(Loop.class.getResource("/Ghost.png"))));
		} catch (IOException e) {
			System.out.println("ERROR LOADING: Ghost.png");
			e.printStackTrace();
			// System.exit(1);
		}
		drawButtons();
		repaint();

	}

	private void drawButtons() {
		String[] data = new AccountServer("true", "Email¬¦¬Secure", "", "").getMessage().split("¬¦¬");
		
		OK = new JButton("OK");
		rOK = new Rectangle(160, 130, 80, 40);
		OK.setBounds(rOK);
		window.add(OK);

		username = new JTextField();
		username.setBounds(80, 0, 310, 20);
		username.setEditable(false);
		username.setText(Loop.USERNAME);
//		email.setOpaque(false);
		window.add(username);

		label = new JLabel("Username");
		label.setBounds(0, 0, 190, 20);
		window.add(label);

		
		email = new JTextField();
		email.setBounds(50, 20, 340, 20);
		email.setEditable(true);
		email.setText(data[0]);
//		email.setOpaque(false);
		window.add(email);

		label = new JLabel("Email:");
		label.setBounds(0, 20, 190, 20);
		window.add(label);

		password = new JPasswordField();
		password.setBounds(120, 40, 270, 20);
		password.setEchoChar('*');
		password.setEditable(true);
		password.setText("");
//		email.setOpaque(false);
		window.add(password);

		label = new JLabel("New password:");
		label.setBounds(0, 40, 190, 20);
		window.add(label);

		secure = new JCheckBox();
		secure.setBounds(140, 60, 20, 20);
		secure.setOpaque(false);
		boolean sec;
		try{
			sec = Boolean.parseBoolean(data[1]);
		}catch(Exception e){
			sec = false;
		}
		secure.setSelected(sec);
		window.add(secure);

		label = new JLabel("Secure your account:");
		label.setBounds(0, 60, 190, 20);
		window.add(label);
		
		oldPassword = new JPasswordField();
		oldPassword.setText("");

		if(sec){  //TODO: UPDATE WITH FIX FOR NOT SAVING DATA IN UNSECURE ACCOUNTS
			oldPassword.setBounds(120, 100, 270, 20);
			oldPassword.setEchoChar('*');
			oldPassword.setEditable(true);
			oldPassword.setText("");
//			email.setOpaque(false);
			window.add(oldPassword);

			label = new JLabel("Current password:");
			label.setBounds(0, 100, 190, 20);
			window.add(label);
		}
		
		OK.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String message = "";
				
				String pass = new Encrypt(Loop.USERNAME, oldPassword.getText(), 0).message;
				if(password.getPassword().length == 0){
					message = new AccountServer("false", "Email¬¦¬Secure", email.getText() + "¬¦¬" + secure.isSelected(), pass).getMessage();
				}else{
					message = new AccountServer("false", "Email¬¦¬Secure¬¦¬Password", email.getText() + "¬¦¬" + secure.isSelected() + "¬¦¬" + new Encrypt(Loop.USERNAME, password.getText(), 0).message, pass).getMessage();
				}
				if(message.contains("Exception")){
					label = new JLabel(message.split(" ")[0]);
					label.setBounds(0, 140, 160, 20);
					window.add(label);
					repaint();

				}else{
					hide();
				}
			}
		});
	}
}
