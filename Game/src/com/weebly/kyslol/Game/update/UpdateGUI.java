package com.weebly.kyslol.Game.update;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.weebly.kyslol.Game.Loop;
import com.weebly.kyslol.Game.entities.Player;

public class UpdateGUI extends JFrame {
	// world
	// stats
	// inventory
	// pos
	public JPanel window = new JPanel();
	private JButton OK;
	private Rectangle rOK;
	private JTextField folder;
	private JLabel label;
	Properties GameDta = new Properties();
	int row = 30;

	public UpdateGUI() {
		setTitle("Update Available!");
		setSize(400, 200);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		add(window);
		window.setBackground(new Color(0xdfdfdf));
		window.setLayout(null);

		try {
			setIconImage((ImageIO.read(Loop.class.getResource("/Ghost.png"))));
		} catch (Exception e) {
			System.out.println("ERROR LOADING: Ghost.png");
			e.printStackTrace();
			// System.exit(1);
		}
		drawButtons();
		repaint();

	}

	private void drawButtons() {

		folder = new JTextField();
		folder.setBounds(10, 20, 375, 20);
		window.add(folder);

		label = new JLabel("Folder to Download the update to:");
		label.setBounds(10, 0, 800, 15);
		window.add(label);
		
		drawData();
		//lol
		folder.setText("C:\\Users\\" + System.getProperty("user.name") + "\\Desktop\\The Land\\");
		
		OK = new JButton("Download now!");
		rOK = new Rectangle(140, row+80, 120, 40);
		OK.setBounds(rOK);
		window.add(OK);

		OK.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Update.download(folder.getText());
				hide();
				System.exit(0);
			}
		});
		requestFocus();
	}

	private void drawData() {
		String d = "";
		JLabel labl = new JLabel("The Land Version: " + Loop.latest + " is ready to download!");
		labl.setBounds(10, row, 800, row + 15);
		window.add(labl);
		row += 15;

		for (int i = 0; i < Loop.Details.length(); i++) {
			char c = Loop.Details.charAt(i);
			if (c == '{') {
//				g.drawString(d, 10, row);
				JLabel label = new JLabel(d);
				label.setBounds(10, row, 800, row + 15);
				window.add(label);
				d = "";

				while (c == '{') {
					i++;
					row += 15;
					c = Loop.Details.charAt(i);
				}
			}
			if (c == '}') {
				// System.out.println(c);
				continue;
			}
			d = d + Loop.Details.charAt(i);

		}
		setSize(400, row+150);

	}
}
