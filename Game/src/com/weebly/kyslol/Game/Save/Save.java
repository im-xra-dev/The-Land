package com.weebly.kyslol.Game.Save;

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
import com.weebly.kyslol.Game.Trophy4.TrophyTracker;
import com.weebly.kyslol.Game.entities.Player;

public class Save extends JFrame {
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

	public Save() {
		setTitle("Save Game.");
		setSize(400, 200);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		add(window);
		window.setBackground(new Color(0xDFDFDF));
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
		OK = new JButton("OK");
		rOK = new Rectangle(160, 130, 80, 40);
		OK.setBounds(rOK);
		window.add(OK);

		folder = new JTextField();
		folder.setBounds(10, 20, 375, 20);
		window.add(folder);

		label = new JLabel("Name for game save data: (If it already exists it will be overwritten)");
		label.setBounds(10, 0, 800, 15);
		window.add(label);

		folder.setText("GameData");

		folder.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				saveGame("C:\\Users\\" + System.getProperty("user.name")
						+ "\\AppData\\Roaming\\Rebel Roosters\\The Land", folder.getText());
			}
		});

		OK.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				saveGame("C:\\Users\\" + System.getProperty("user.name")
						+ "\\AppData\\Roaming\\Rebel Roosters\\The Land", folder.getText());
			}
		});
	}

	private void saveGame(String text, String name) {
		if(System.getProperty("os.name").contains("Windows")){
			text = "C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Roaming\\Rebel Roosters\\The Land";
		}else{
			text = System.getProperty("user.home") + System.getProperty("file.separator") + "The Land";
		}

		try {
			(new File(text)).mkdirs();
		} catch (Exception e) {
			return;
		}

		// world
		// stats
		// pos
		// inventory

		// System.out.println(World.Biomes.size());
		//
		String world = "";
		//
		// for (int i = 0; i < World.Biomes.size(); i ++){
		// world = world + World.Biomes.get(i);
		// }
		//
		// world = world + "}";
		//
		world = "";
		world = world + "/[bank(" + Player.bank + ")]";
		world = world + "/[money(" + Player.money + ")]";
		world = world + "/[hunger(" + Player.hunger + ")]";
		world = world + "/[ammo(" + Player.ammo + ")]";
		world = world + "/[health(" + Player.health + ")]";
		// world = world + "";

		sve(text, name, "Stats", world);
		world = "";

		for (int i = 0; i < Loop.inv.Items.size(); i++) {
			world = world + "/[items(" + Loop.inv.Items.get(i).getName() + ")(" + Loop.inv.Items.get(i).getAmm() + ")("
					+ Loop.inv.Items.get(i).getDesc() + ")]";
		}
		for (int i = 0; i < Loop.inv.Potions.size(); i++) {
			world = world + "/[potions(" + Loop.inv.Potions.get(i).getName() + ")(" + Loop.inv.Potions.get(i).getAmm()
					+ ")(" + Loop.inv.Potions.get(i).getDesc() + ")(" + Loop.inv.Potions.get(i).getDmg() + ")]";
		}
		for (int i = 0; i < Loop.inv.Food.size(); i++) {
			world = world + "/[food(" + Loop.inv.Food.get(i).getName() + ")(" + Loop.inv.Food.get(i).getAmm() + ")("
					+ Loop.inv.Food.get(i).getDesc() + ")(" + Loop.inv.Food.get(i).getDmg() + ")]";
		}
		for (int i = 0; i < Loop.inv.Spawns.size(); i++) {
			world = world + "/[spawns(" + Loop.inv.Spawns.get(i).getName() + ")(" + Loop.inv.Spawns.get(i).getAmm()
					+ ")(" + Loop.inv.Spawns.get(i).getDesc() + ")]";
		}
		for (int i = 0; i < Loop.inv.Weapons.size(); i++) {
			world = world + "/[weapons(" + Loop.inv.Weapons.get(i).getName() + ")(" + Loop.inv.Weapons.get(i).getAmm()
					+ ")(" + Loop.inv.Weapons.get(i).getDesc() + ")(" + Loop.inv.Weapons.get(i).getDmg() + ")]";
		}
		for (int i = 0; i < Loop.inv.Armour.size(); i++) {
			world = world + "/[armour(" + Loop.inv.Armour.get(i).getName() + ")(" + Loop.inv.Armour.get(i).getAmm()
					+ ")(" + Loop.inv.Armour.get(i).getDesc() + ")(" + Loop.inv.Armour.get(i).getDmg() + ")]";
		}
		for (int i = 0; i < Loop.inv.Ammo.size(); i++) {
			world = world + "/[ammo(" + Loop.inv.Ammo.get(i).getName() + ")(" + Loop.inv.Ammo.get(i).getAmm() + ")("
					+ Loop.inv.Ammo.get(i).getDesc() + ")]";
		}

		sve(text, name, "Inventory", world);
		world = "";

		world = "";
		world = world + "/[dist(" + TrophyTracker.dist + ")]";
		world = world + "/[forge(" + TrophyTracker.forge + ")]";
		world = world + "/[mobs(" + TrophyTracker.mobs + ")]";
		world = world + "/[people(" + TrophyTracker.people + ")]";
		world = world + "/[church(" + TrophyTracker.church + ")]";

		for (int i = 0; i < TrophyTracker.trophies.length; i++) {
			world = world + "/[data(" + TrophyTracker.trophies[i] + ")]";
		}

		sve(text, name, "Trophy", world);

		// System.out.println(world);
		hide();
		// show();

	}

	public void sve(String text, String name, String key, String world) {

		String GameData;
		GameData = text + System.getProperty("file.separator")+name+".xml";

//		if (!(text.charAt(text.length() - 1) == '\\')) {
//			GameData = text + "\\" + name + ".xml";
//		} else {
//			GameData = text + name + ".xml";
//		}

		try {
			File file = new File(GameData);
			boolean exist = file.exists();
			if (!exist) {
				file.createNewFile();
			}
			OutputStream write = new FileOutputStream(GameData);

			GameDta.setProperty(key, world);
			GameDta.storeToXML(write, "");

		} catch (Exception e) {
			return;
		}

	}
	// System.out.println(world);

}
