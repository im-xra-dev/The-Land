package com.weebly.kyslol.Game.GUI;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import com.weebly.kyslol.Game.GUI.potions.Armour;
import com.weebly.kyslol.Game.GUI.potions.Items;
import com.weebly.kyslol.Game.GUI.potions.Potions;
import com.weebly.kyslol.Game.GUI.potions.Weapons;

public class Qwertybay_com {
	public static ArrayList<Potions> pot = new ArrayList<Potions>();
	public static ArrayList<Items> items = new ArrayList<Items>();
	public static ArrayList<Weapons> weapons = new ArrayList<Weapons>();
	public static ArrayList<Armour> armour = new ArrayList<Armour>();

	public static ArrayList<Potions> sellPot = new ArrayList<Potions>();
	public static ArrayList<Items> sellItems = new ArrayList<Items>();
	public static ArrayList<Weapons> sellWeapons = new ArrayList<Weapons>();
	public static ArrayList<Armour> sellArmour = new ArrayList<Armour>();

	static Random random = new Random();

	public static void _Init_() {
		System.out.println("_Init_");
		pot.removeAll(pot);
		items.removeAll(items);
		armour.removeAll(armour);
		weapons.removeAll(weapons);
		
//		File file = new File(Qwertybay_com.class.getResourceAsStream("res/GUIS/Pc/websites/qwertybay.com/random.txt"));
		try {
			InputStream is = (Qwertybay_com.class.getResourceAsStream("/txt files/random.txt"));
//			System.out.println(is);
			byte[] b = new byte[(int) is.available()];
			byte[] data = new byte[200];
			// b = fis.getChannel();
			is.read(b);
			// name = item.getName().getBytes();
			// System.out.println(b.toString());
			// System.out.println(item.getName());
			String type = "";
			String name = "";
			String chance = "";
			String ammount = "";
			String cost = "";
			String damage = "";
//			System.out.println(":".getBytes()[0]);
//			System.out.println(";".getBytes()[0]);
			for (int i = 0; i < b.length; i++) {
				if (b[i] == 35) {

					i += 2;
					int l = 0;
					for (int e = i; b[e] != 35; e++) {
						data[l] = b[e];
						i++;
						l++;
					}
					i += 2;
					// for (int d = 0; d < data.length; d++) {
					// if(data[d] == 0) continue;
					// type = type + Byte.toString(data[d]);
					//
					// }
					String t = new String(data, "UTF-8");
					data = new byte[200];
					type = "";

					for (int p = 0; p < t.length(); p++) {
						if (t.charAt(p) == 0)
							continue;
						type = type + t.charAt(p);
					}
//					System.out.println(type);
					
					if(type.equalsIgnoreCase("end")){
						continue;
					}
					i++;
					// GET NAME
					for (; b[i-2] != "_".getBytes()[0];) {
						i += 3;
						l = 0;
						for (int e = i; b[e] != 58; e++) {
							if(b[i] == "#".getBytes()[0]){
								i--; continue;
							}
							if (b[i] == 58)
								continue;
							data[l] = b[e];
							i++;
							l++;
						}
						i++;
						t = new String(data, "UTF-8");
						data = new byte[200];
						name = "";

						for (int p = 0; p < t.length(); p++) {
							if (t.charAt(p) == 0)
								continue;
							name = name + t.charAt(p);
						}
//						System.out.println(name);

						// GET CHANCE
						i += 2;
						l = 0;
						for (int e = i; b[e] != 59; e++) {
							if(b[i] == "#".getBytes()[0]){
								i--; continue;
							}
							if (b[i] == 59)
								continue;
							data[l] = b[e];
							i++;
							l++;
						}
						i++;
						t = new String(data, "UTF-8");
						data = new byte[200];
						chance = "";

						for (int p = 0; p < t.length(); p++) {
							if (t.charAt(p) == 0)
								continue;
							chance = chance + t.charAt(p);
						}
//						System.out.println(chance);

						// GET COST
						i += 2;
						l = 0;
						for (int e = i; b[e] != 59; e++) {
							if(b[i] == "#".getBytes()[0]){
								i--; continue;
							}
							if (b[i] == 59)
								continue;
							data[l] = b[e];
							i++;
							l++;
						}
						i++;
						t = new String(data, "UTF-8");
						data = new byte[200];
						cost = "";

						for (int p = 0; p < t.length(); p++) {
							if (t.charAt(p) == 0)
								continue;
							cost = cost + t.charAt(p);
						}
//						System.out.println(cost);

						// GET AMMOUNT
						i += 2;
						l = 0;
						for (int e = i; b[e] != 59; e++) {
//							if(b[i] == "#".getBytes()[0]){
//								i--; continue;
//							}
							if (b[i] == 59)
								continue;
							data[l] = b[e];
							i++;
							l++;
						}
						i++;
						t = new String(data, "UTF-8");
						data = new byte[200];
						ammount = "";

						for (int p = 0; p < t.length(); p++) {
							if (t.charAt(p) == 0)
								continue;
							ammount = ammount + t.charAt(p);
						}
//						System.out.println(ammount);

						// GET DAMAGE
						i += 2;
						l = 0;
						for (int e = i; b[e] != 59 && b[e] != "_".getBytes()[0]; e++) {
							if(b[i] == "#".getBytes()[0]){
								i--; continue;
							}
							if(b[e] == "_".getBytes()[0]){
								continue;
							}

							if (b[i] == 59)
								continue;
							data[l] = b[e];
							i++;
							l++;
						}
						i+=2;
						t = new String(data, "UTF-8");
						data = new byte[200];
						damage = "";

						for (int p = 0; p < t.length(); p++) {
							if (t.charAt(p) == 0)
								continue;
							damage = damage + t.charAt(p);
						}
//						System.out.println(damage);

						if (type.equalsIgnoreCase("potions")) {
							pot.add(new Potions(name, ammount, cost, chance, damage));
						}else
						if (type.equalsIgnoreCase("items")) {
							items.add(new Items(name, ammount, cost, chance, damage));
						}else
						if (type.equalsIgnoreCase("weapons")) {
							weapons.add(new Weapons(name, ammount, cost, chance, damage));
						}else
						if (type.equalsIgnoreCase("armour")) {
							armour.add(new Armour(name, ammount, cost, chance, damage));
						}else{
							System.out.println("ERROR");
						}

					}
				}
			}
			is.close();

		} catch (Exception e) {
			System.out.println("ERROR READING Random.txt");
			e.printStackTrace();
			return;
		}

		updateSelling();
	}

	public static void updateSelling() {
		sellPot.removeAll(sellPot);
		sellItems.removeAll(sellItems);
		sellArmour.removeAll(sellArmour);
		sellWeapons.removeAll(sellWeapons);
//		System.out.println("Updating");
		for (int i = 0; sellPot.size() < 9; i++) {
			if(i >= pot.size()){
				i = 0;		
			}
			if (pot.get(i).getChance() > random.nextInt(100)) {
				int amm = random.nextInt(pot.get(i).getMaxAmm() - pot.get(i).getMinAmm()) + pot.get(i).getMinAmm();
				int dmg = random.nextInt(pot.get(i).getMaxDmg() - pot.get(i).getMinDmg()) + pot.get(i).getMinDmg();
				int cost = random.nextInt(pot.get(i).getMaxCost() - pot.get(i).getMincost()) + pot.get(i).getMincost();
				String name = pot.get(i).getName();
				sellPot.add(new Potions(name, "" + amm, "" + cost, "" + random.nextInt(), "" + dmg)); 				// System.out.println(cost);
			}
		}
		for (int i = 0; sellItems.size() < 9; i++) {
			if(i >= items.size()){
				i = 0;		
			}
			if (items.get(i).getChance() > random.nextInt(100)) {
				int amm = random.nextInt(items.get(i).getMaxAmm() - items.get(i).getMinAmm()) + items.get(i).getMinAmm();
				int dmg = random.nextInt(items.get(i).getMaxDmg() - items.get(i).getMinDmg()) + items.get(i).getMinDmg();
				int cost = random.nextInt(items.get(i).getMaxCost() - items.get(i).getMincost()) + items.get(i).getMincost();
				String name = items.get(i).getName();
				sellItems.add(new Items(name, "" + amm, "" + cost, "" + random.nextInt(), "" + dmg)); 				// System.out.println(cost);
			}
		}
		for (int i = 0; sellArmour.size() < 9; i++) {
			if(i >= armour.size()){
				i = 0;		
			}
			if (armour.get(i).getChance() > random.nextInt(100)) {
				int amm = random.nextInt(armour.get(i).getMaxAmm() - armour.get(i).getMinAmm()) + armour.get(i).getMinAmm();
				int dmg = random.nextInt(armour.get(i).getMaxDmg() - armour.get(i).getMinDmg()) + armour.get(i).getMinDmg();
				int cost = random.nextInt(armour.get(i).getMaxCost() - armour.get(i).getMincost()) + armour.get(i).getMincost();
				String name = armour.get(i).getName();
				sellArmour.add(new Armour(name, "" + amm, "" + cost, "" + random.nextInt(), "" + dmg)); 	
				// System.out.println(cost);
			}
		}
		for (int i = 0; sellWeapons.size() < 9; i++) {
			if(i >= weapons.size()){
				i = 0;		
			}
			if (weapons.get(i).getChance() > random.nextInt(100)) {
				int amm = random.nextInt(weapons.get(i).getMaxAmm() - weapons.get(i).getMinAmm()) + weapons.get(i).getMinAmm();
				int dmg = random.nextInt(weapons.get(i).getMaxDmg() - weapons.get(i).getMinDmg()) + weapons.get(i).getMinDmg();
				int cost = random.nextInt(weapons.get(i).getMaxCost() - weapons.get(i).getMincost()) + weapons.get(i).getMincost();
				String name = weapons.get(i).getName();
				sellWeapons.add(new Weapons(name, "" + amm, "" + cost, "" + random.nextInt(), "" + dmg)); 				// System.out.println(cost);
			}
		}
	}
}
