package com.weebly.kyslol.Game.Save;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.weebly.kyslol.Game.Loop;
import com.weebly.kyslol.Game.Trophy4.TrophyTracker;
import com.weebly.kyslol.Game.entities.Player;
import com.weebly.kyslol.Game.player.potions.Effects;

public class Load extends JFrame {
	Properties GameDta = new Properties();

	public JPanel window = new JPanel();
	private JButton OK;
	private Rectangle rOK;
	private JTextField folder;
	private JLabel label;
	int dataInt = 0;
	
	
	public Load() {
		setTitle("Load Game.");
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
		OK = new JButton("OK");
		rOK = new Rectangle(160, 130, 80, 40);
		OK.setBounds(rOK);
		window.add(OK);

		folder = new JTextField();
		folder.setBounds(10, 20, 375, 20);
		window.add(folder);

		label = new JLabel("Name of the game save data:");
		label.setBounds(10, 0, 800, 15);
		window.add(label);

		folder.setText("GameData");
		
		folder.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				loadData("C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Roaming\\Rebel Roosters\\The Land", folder.getText());
			}
		});

		
		OK.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				loadData("", folder.getText());
			}
		});
	}

	public void loadData(String text, String fname) {
//		 System.getProperties().list(System.out);
		if(System.getProperty("os.name").contains("Windows")){
			text = "C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Roaming\\Rebel Roosters\\The Land";
		}else{
			text = System.getProperty("user.home") + System.getProperty("file.separator") + "The Land";
		}
//		System.out.println(text);
		try {
			
			String GameData;
//			if (!(text.charAt(text.length() - 1) == System.getProperty("file.separator"))) {
				GameData = text + System.getProperty("file.separator")+fname+".xml";
//			} else {
//				GameData = text + fname+".xml";
//			}
			
//			System.out.println(GameData);
			InputStream readFile = new FileInputStream(GameData);
			GameDta.loadFromXML(readFile);

			String Stats = GameDta.getProperty("Stats");

			String[] StatsArray = Stats.split("/");
			String[] TrophyArray = new String[0];
			
			for(int i = 0; i < 20; i ++){
				TrophyTracker.trophies[i] = false;

			}
			
			try{
			String Trophy = GameDta.getProperty("Trophy");

			TrophyArray = Trophy.split("/");
			}catch(Exception e){
			}
//			for (int i = 0; i < StatsArray.length; i++) {
//				System.out.println(StatsArray[i]);
//			}
			
			String inv = GameDta.getProperty("Inventory");

			String[] invArray = inv.split("/");
//			for (int i = 0; i < invArray.length; i++) {
//				System.out.println(invArray[i]);
//			}

			try {
				int bank = 0;
				int money = 0;
				int ammo = 0;
				int health = 100;
				int hunger = 100;
				Loop.inv.clear();
				
				for (int data = 0; data < StatsArray.length; data++) {
					
					if (StatsArray[data].contains("[bank(")) {
						String pos = "";
						boolean read = false;
						for (int i = 0; i < StatsArray[data].length(); i++) {
							if (StatsArray[data].charAt(i) == ')') {
								read = false;
							}
							if (read) {
								pos = pos + StatsArray[data].charAt(i);
							}
							if (StatsArray[data].charAt(i) == '(') {
								read = true;
							}

						}

						bank = Integer.parseInt(pos);
					}
					if (StatsArray[data].contains("[health(")) {
						String pos = "";
						boolean read = false;
						for (int i = 0; i < StatsArray[data].length(); i++) {
							if (StatsArray[data].charAt(i) == ')') {
								read = false;
							}
							if (read) {
								pos = pos + StatsArray[data].charAt(i);
							}
							if (StatsArray[data].charAt(i) == '(') {
								read = true;
							}

						}

						health = Integer.parseInt(pos);
					}
					if (StatsArray[data].contains("[money(")) {
						String pos = "";
						boolean read = false;
						for (int i = 0; i < StatsArray[data].length(); i++) {
							if (StatsArray[data].charAt(i) == ')') {
								read = false;
							}
							if (read) {
								pos = pos + StatsArray[data].charAt(i);
							}
							if (StatsArray[data].charAt(i) == '(') {
								read = true;
							}

						}

						money = Integer.parseInt(pos);
					}
					if (StatsArray[data].contains("[hunger(")) {
						String pos = "";
						boolean read = false;
						for (int i = 0; i < StatsArray[data].length(); i++) {
							if (StatsArray[data].charAt(i) == ')') {
								read = false;
							}
							if (read) {
								pos = pos + StatsArray[data].charAt(i);
							}
							if (StatsArray[data].charAt(i) == '(') {
								read = true;
							}

						}

						hunger = Integer.parseInt(pos);
					}
					if (StatsArray[data].contains("[ammo(")) {
						String pos = "";
						boolean read = false;
						for (int i = 0; i < StatsArray[data].length(); i++) {
							if (StatsArray[data].charAt(i) == ')') {
								read = false;
							}
							if (read) {
								pos = pos + StatsArray[data].charAt(i);
							}
							if (StatsArray[data].charAt(i) == '(') {
								read = true;
							}

						}

						ammo = Integer.parseInt(pos);
					}

				}
				
				Player.bank = bank;
				Player.health = health;
				Player.ammo = ammo;
				Player.hunger = hunger;
				Player.money = money;
				
				
				int dist = 0;
				int forge = 0;
				int mobs = 0;
				int people = 0;
				int church = 0;
				
				for (int data = 0; data < TrophyArray.length; data++) {
					
					if (TrophyArray[data].contains("[dist(")) {
						String pos = "";
						boolean read = false;
						for (int i = 0; i < TrophyArray[data].length(); i++) {
							if (TrophyArray[data].charAt(i) == ')') {
								read = false;
							}
							if (read) {
								pos = pos + TrophyArray[data].charAt(i);
							}
							if (TrophyArray[data].charAt(i) == '(') {
								read = true;
							}

						}

						dist = Integer.parseInt(pos);
					}
					if (TrophyArray[data].contains("[mobs(")) {
						String pos = "";
						boolean read = false;
						for (int i = 0; i < TrophyArray[data].length(); i++) {
							if (TrophyArray[data].charAt(i) == ')') {
								read = false;
							}
							if (read) {
								pos = pos + TrophyArray[data].charAt(i);
							}
							if (TrophyArray[data].charAt(i) == '(') {
								read = true;
							}

						}

						mobs = Integer.parseInt(pos);
					}
					if (TrophyArray[data].contains("[forge(")) {
						String pos = "";
						boolean read = false;
						for (int i = 0; i < TrophyArray[data].length(); i++) {
							if (TrophyArray[data].charAt(i) == ')') {
								read = false;
							}
							if (read) {
								pos = pos + TrophyArray[data].charAt(i);
							}
							if (TrophyArray[data].charAt(i) == '(') {
								read = true;
							}

						}

						forge = Integer.parseInt(pos);
					}
					if (TrophyArray[data].contains("[church(")) {
						String pos = "";
						boolean read = false;
						for (int i = 0; i < TrophyArray[data].length(); i++) {
							if (TrophyArray[data].charAt(i) == ')') {
								read = false;
							}
							if (read) {
								pos = pos + TrophyArray[data].charAt(i);
							}
							if (TrophyArray[data].charAt(i) == '(') {
								read = true;
							}

						}

						church = Integer.parseInt(pos);
					}
					if (TrophyArray[data].contains("[people(")) {
						String pos = "";
						boolean read = false;
						for (int i = 0; i < TrophyArray[data].length(); i++) {
							if (TrophyArray[data].charAt(i) == ')') {
								read = false;
							}
							if (read) {
								pos = pos + TrophyArray[data].charAt(i);
							}
							if (TrophyArray[data].charAt(i) == '(') {
								read = true;
							}

						}

						people = Integer.parseInt(pos);
					}
					if (TrophyArray[data].contains("[data(")) {
						String pos = "";
						boolean read = false;
						for (int i = 0; i < TrophyArray[data].length(); i++) {
							if (TrophyArray[data].charAt(i) == ')') {
								read = false;
							}
							if (read) {
								pos = pos + TrophyArray[data].charAt(i);
							}
							if (TrophyArray[data].charAt(i) == '(') {
								read = true;
							}

						}

						TrophyTracker.trophies[dataInt] = Boolean.parseBoolean(pos);
						dataInt ++;
					}
				}
				
				TrophyTracker.dist = dist;
				TrophyTracker.forge = forge;
				TrophyTracker.mobs = mobs;
				TrophyTracker.people = people;
				TrophyTracker.church = church;
				
				
				for (int data = 0; data < invArray.length; data++) {
					
					
//					System.out.println(invArray[data] + data);
					
					if (invArray[data].contains("[items(")) {
						String name = "";
						String amm = "";
						String desc = "";
						boolean read = false;
						int r = 0;
						
						for (int i = 0; i < invArray[data].length(); i++) {
							if (invArray[data].charAt(i) == ')') {
								read = false;
							}
							if (read) {
								if(r == 1){
									name = name + invArray[data].charAt(i);
								}
								if(r == 2){
									amm = amm + invArray[data].charAt(i);
								}
								if(r == 3){
									desc = desc + invArray[data].charAt(i);
								}
							}
							if (invArray[data].charAt(i) == '(') {
								read = true;
								r ++;
							}

						}
						Loop.inv.addItem(name, Integer.parseInt(amm), desc);
					}
					
					if (invArray[data].contains("[ammo(")) {
						String name = "";
						String amm = "";
						String desc = "";
						boolean read = false;
						int r = 0;
						
						for (int i = 0; i < invArray[data].length(); i++) {
							if (invArray[data].charAt(i) == ')') {
								read = false;
							}
							if (read) {
								if(r == 1){
									name = name + invArray[data].charAt(i);
								}
								if(r == 2){
									amm = amm + invArray[data].charAt(i);
								}
								if(r == 3){
									desc = desc + invArray[data].charAt(i);
								}
							}
							if (invArray[data].charAt(i) == '(') {
								read = true;
								r ++;
							}

						}
						Loop.inv.addAmmo(name, Integer.parseInt(amm), desc);
					}
					
					
					if (invArray[data].contains("[potions(")) {
						String name = "";
						String amm = "";
						String desc = "";
						String dmg = "";

						boolean read = false;
						int r = 0;
						
						for (int i = 0; i < invArray[data].length(); i++) {
							if (invArray[data].charAt(i) == ')') {
								read = false;
							}
							if (read) {
								if(r == 1){
									name = name + invArray[data].charAt(i);
								}
								if(r == 2){
									amm = amm + invArray[data].charAt(i);
								}
								if(r == 3){
									desc = desc + invArray[data].charAt(i);
								}
								if(r == 4){
									dmg = dmg + invArray[data].charAt(i);
								}

							}
							if (invArray[data].charAt(i) == '(') {
								read = true;
								r ++;
							}

						}
						Loop.inv.addPotion(name, Integer.parseInt(amm), desc, Integer.parseInt(dmg));
					}
					
					if (invArray[data].contains("[food(")) {
						String name = "";
						String amm = "";
						String desc = "";
						String dmg = "";
						boolean read = false;
						int r = 0;
						
						for (int i = 0; i < invArray[data].length(); i++) {
							if (invArray[data].charAt(i) == ')') {
								read = false;
							}
							if (read) {
								if(r == 1){
									name = name + invArray[data].charAt(i);
								}
								if(r == 2){
									amm = amm + invArray[data].charAt(i);
								}
								if(r == 3){
									desc = desc + invArray[data].charAt(i);
								}
								if(r == 4){
									dmg = dmg + invArray[data].charAt(i);
								}
							}
							if (invArray[data].charAt(i) == '(') {
								read = true;
								r ++;
							}

						}
						Loop.inv.addFood(name, Integer.parseInt(amm), desc, Integer.parseInt(dmg));
					}
					if (invArray[data].contains("[armour(")) {
						String name = "";
						String amm = "";
						String desc = "";
						String dmg = "";
						boolean read = false;
						int r = 0;
						
						for (int i = 0; i < invArray[data].length(); i++) {
							if (invArray[data].charAt(i) == ')') {
								read = false;
							}
							if (read) {
								if(r == 1){
									name = name + invArray[data].charAt(i);
								}
								if(r == 2){
									amm = amm + invArray[data].charAt(i);
								}
								if(r == 3){
									desc = desc + invArray[data].charAt(i);
								}
								if(r == 4){
									dmg = dmg + invArray[data].charAt(i);
								}
							}
							if (invArray[data].charAt(i) == '(') {
								read = true;
								r ++;
							}

						}
						Loop.inv.addArmour(name, Integer.parseInt(amm), desc, Integer.parseInt(dmg));
					}
					if (invArray[data].contains("[spawns(")) {
						String name = "";
						String amm = "";
						String desc = "";
						boolean read = false;
						int r = 0;
						
						for (int i = 0; i < invArray[data].length(); i++) {
							if (invArray[data].charAt(i) == ')') {
								read = false;
							}
							if (read) {
								if(r == 1){
									name = name + invArray[data].charAt(i);
								}
								if(r == 2){
									amm = amm + invArray[data].charAt(i);
								}
								if(r == 3){
									desc = desc + invArray[data].charAt(i);
								}
							}
							if (invArray[data].charAt(i) == '(') {
								read = true;
								r ++;
							}

						}
						Loop.inv.addSpawn(name, Integer.parseInt(amm), desc);
					}
					
					if (invArray[data].contains("[weapons(")) {
						String name = "";
						String amm = "";
						String desc = "";
						String dmg = "";
						boolean read = false;
						int r = 0;
						
						for (int i = 0; i < invArray[data].length(); i++) {
							if (invArray[data].charAt(i) == ')') {
								read = false;
							}
							if (read) {
								if(r == 1){
									name = name + invArray[data].charAt(i);
								}
								if(r == 2){
									amm = amm + invArray[data].charAt(i);
								}
								if(r == 3){
									desc = desc + invArray[data].charAt(i);
								}
								if(r == 4){
									dmg = dmg + invArray[data].charAt(i);
								}

							}
							if (invArray[data].charAt(i) == '(') {
								read = true;
								r ++;
							}

						}
						Loop.inv.addWeapon(name, Integer.parseInt(amm), desc, Integer.parseInt(dmg));
					}
				}
				
			} catch (Exception e) {
				System.out.println("Error Loading Game Data");
				e.printStackTrace();
				// TODO add this when done 
				System.exit(1);
			}
			System.out.println("Done");
			readFile.close();
			Effects.removeAll();
		} catch (FileNotFoundException e1) {
			System.out.println("Error Loading Game File (Not Found)");
			return;

		} catch (IOException e2) {
			System.out.println("Error Loading Game File (IO)");

		}
		
		hide();

	}
}
