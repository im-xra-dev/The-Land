package com.weebly.kyslol.Game.player.inventory;

import java.awt.Image;
import java.util.ArrayList;

import com.weebly.kyslol.Game.Loop;

public class Inventory {
	private static final long serialVersionUID = 1L;

	private int Row, Colom, Ammount;
	private String name, description, type;
	private Image img;
	private boolean Selected;
	String URL = "/GUIS/Inventory/Icons/";

	public Inventory() {

	}

	public static ArrayList<InventoryItems> Items = new ArrayList<InventoryItems>();
	public static ArrayList<Food> Food = new ArrayList<Food>();
	public static ArrayList<Ammo> Ammo = new ArrayList<Ammo>();
	public static ArrayList<Potions> Potions = new ArrayList<Potions>();
	public static ArrayList<Weapons> Weapons = new ArrayList<Weapons>();
	public static ArrayList<Armour> Armour = new ArrayList<Armour>();
	public static ArrayList<Spawners> Spawns = new ArrayList<Spawners>();

	public void addItem(String name, int ammount, String description) {
		if (ammount == 0)
			return;

		for (int i = 0; i < Items.size(); i++) {
			if (Items.get(i).getName().equals(name)) {
				Items.get(i).setAmm(ammount);

				if (Items.get(i).getAmm() == 0) {
					Items.remove(i);
				}
				return;
			}
		}
		Items.add(new InventoryItems(name, ammount, description));
		// System.out.println(Items.size());
	}

	public void addWeapon(String name, int ammount, String description, int dmg) {
		if (ammount == 0)
			return;

		for (int i = 0; i < Weapons.size(); i++) {
			if (Weapons.get(i).getName().equals(name) && Weapons.get(i).getDmg() == dmg) {
				Weapons.get(i).setAmm(ammount);
				if (Weapons.get(i).getAmm() == 0) {
					Weapons.remove(i);
				}
				return;
			}
		}
		Weapons.add(new Weapons(name, ammount, description, dmg));
	}

	public void addArmour(String name, int ammount, String description, int dmg) {
		if (ammount == 0)
			return;

		for (int i = 0; i < Armour.size(); i++) {
			if (Armour.get(i).getName().equals(name) && Armour.get(i).getDmg() == dmg) {
				Armour.get(i).setAmm(ammount);
				if (Armour.get(i).getAmm() == 0) {
					Armour.remove(i);
				}
				return;
			}
		}
		Armour.add(new Armour(name, ammount, description, dmg));
	}

	public void addSpawn(String name, int ammount, String description) {
		if (ammount == 0)
			return;

		for (int i = 0; i < Spawns.size(); i++) {
			if (Spawns.get(i).getName().equals(name)) {
				Spawns.get(i).setAmm(ammount);
				if (Spawns.get(i).getAmm() == 0) {
					Spawns.remove(i);
				}
				return;
			}
		}
		Spawns.add(new Spawners(name, ammount, description));
	}

	public void addAmmo(String name, int ammount, String description) {
		if (ammount == 0)
			return;

		for (int i = 0; i < Ammo.size(); i++) {
			if (Ammo.get(i).getName().equals(name + " Ammo")) {
				Ammo.get(i).setAmm(ammount);
				if (Ammo.get(i).getAmm() == 0) {
					Ammo.remove(i);
				}
				return;
			}
		}
		Ammo.add(new Ammo(name, ammount, description));
	}

	public void addFood(String name, int ammount, String description, int heals) {
		if (ammount == 0)
			return;
		if (heals > -10) {
			for (int i = 0; i < Food.size(); i++) {
				if (Food.get(i).getName().equals(name) && Food.get(i).getDmg() == heals) {
					Food.get(i).setAmm(ammount);
					if (Food.get(i).getAmm() == 0) {
						Food.remove(i);
					}
					return;
				}
			}
		} else {
			for (int i = 0; i < Food.size(); i++) {
				if (Food.get(i).getName().equals(name)) {
					Food.get(i).setAmm(ammount);
					if (Food.get(i).getAmm() == 0) {
						Food.remove(i);
					}
					return;
				}
			}

		}
		Food.add(new Food(name, ammount, description, heals));
	}

	public void addPotion(String name, int ammount, String description, int dmg) {
		if (ammount == 0)
			return;

		for (int i = 0; i < Potions.size(); i++) {
			if (Potions.get(i).getName().equals(name) && Potions.get(i).getDmg() == dmg) {
				Potions.get(i).setAmm(ammount);
				if (Potions.get(i).getAmm() == 0) {
					Potions.remove(i);
				}
				return;
			}
		}
		if (description.contains(" X" + dmg)) {
			Potions.add(new Potions(name, ammount, description, dmg));
		} else {
			Potions.add(new Potions(name, ammount, description + " X" + dmg, dmg));
		}
	}

	public int getAmm() {
		return Ammount;
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

	public boolean getSelected() {
		return Selected;
	}

	public void setSelected(boolean i) {
		Selected = i;
	}

	public void clear() {

		Items.removeAll(Items);
		Food.removeAll(Food);
		Ammo.removeAll(Ammo);
		Potions.removeAll(Potions);
		Weapons.removeAll(Weapons);
		Armour.removeAll(Armour);
		Spawns.removeAll(Spawns);

	}

}
