package com.weebly.kyslol.Game.player.inventory;

import java.awt.Image;
import java.util.Random;

import javax.imageio.ImageIO;

import com.weebly.kyslol.Game.Loop;
import com.weebly.kyslol.Game.Trophy4.TrophyTracker;
import com.weebly.kyslol.Game.entities.Player;

public class InventoryItems extends Inventory {
	private int Row, Colom, Ammount;
	private String name, description;
	private Image img;
	private boolean Selected;

	public InventoryItems(String name, int Ammount, String description) {
		try {
			img = ImageIO.read(InventoryItems.class.getResource(URL + "Items/" + name.toLowerCase() + ".png"));
		} catch (Exception e) {
			System.out.println("ERROR LOADING " + name + ".png");
			e.printStackTrace();
			System.exit(1);
		}

		this.Ammount = Ammount;
		this.name = name;
		this.description = description;

		// Inventory.Items.add(this);
	}

	public void tick() {
//		Selected = true;

		if(Loop.selling) return;
		
		if (Selected) {
			if (name.equalsIgnoreCase("Golden Staff")) {
				Selected = false;
				
				Ammount -= 1;
				if(Ammount <= 0){
					Items.remove(this);
				}
				
				Random random = new Random();
				int ran = random.nextInt(3);
				
				if(!TrophyTracker.trophies[9]){
					TrophyTracker.get(9);
				}
				
				if (ran == 0) {
					if (random.nextBoolean()) {
						Player.health = 100;
						GetItems.addItem("Health!", Player.health);
					} else {
						Player.health = Player.health / 2;
						GetItems.addItem("Health", -Player.health);
					}
				} else if (ran == 1) {
					if (random.nextBoolean()) {
						Player.hunger = 100;
						GetItems.addItem("Hunger!", Player.hunger);
					} else {
						Player.hunger = Player.hunger;
						GetItems.addItem("Hunger!", -Player.hunger / 2);
					}
				} else if (ran == 2) {
					Player.money += 100;
					GetItems.addItem("£", 100);
				}
			}
		}
	}

	public void pos() {
		// Inventory.Items.size()/10);
		int i = 0;
		for (int len = 0; len < Inventory.Items.size(); len++) {
			if (Inventory.Items.get(len).getName().equals(name)) {
				i = len + 1;
				// continue;
			}
		}

		Colom = i % 10;
		Row = (int) Math.floor((i - 1) / 10);
		// System.out.println(Colom + "_" + Row + " " + i + " " +
		// Inventory.Items.size());
	}

	public int getAmm() {
		return Ammount;
	}

	public boolean getSelected() {
		return Selected;
	}

	public String getDesc() {
		return description;
	}

	public String getName() {
		return name;
	}

	public Image getImg() {
		return img;
	}

	public int getRow() {
		return Row;
	}

	public int getColom() {
		return Colom;
	}

	public void setAmm(int i) {
		Ammount += i;
	}
	public void setSelected(boolean i) {
		Selected = i;
	}
}
