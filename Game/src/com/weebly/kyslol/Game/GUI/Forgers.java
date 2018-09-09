package com.weebly.kyslol.Game.GUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.weebly.kyslol.Game.Loop;
import com.weebly.kyslol.Game.Trophy4.TrophyTracker;
import com.weebly.kyslol.Game.entities.Inputs;
import com.weebly.kyslol.Game.entities.Player;
import com.weebly.kyslol.Game.player.inventory.GetItems;
import com.weebly.kyslol.Game.player.inventory.InventoryItems;

public class Forgers {
	public static int qty = 1, val = 0;
	public static InventoryItems item;

	public static int tick(int forgers) {
		if (forgers == 5) {
			if (qty ==0){
				qty = 1;
			}
			if(item == null){
				if(Loop.inv.Items.size() > 0){
					item = Loop.inv.Items.get(0);
				}else{
					return forgers;
				}
			}
			if(Inputs.mb != -1){
				if(Loop.region(30, 210, 465, 540-465, true)){//Done
					return 3;
				}
				if(Loop.region(340, 420-340, 100,135-100, true)){//Increase
					if(qty != item.getAmm()){
						qty ++;
					}else{
						qty = 1; 
					}
				}
				}
			if(Inputs.mb != -1){
					if(Loop.region(340, 420-340, 270,135-100, true)){//Decrease
						if(qty > 1){
							qty--;
						}else{
							qty = item.getAmm();
						}
					}
					}
			if(qty>item.getAmm()){
				qty = item.getAmm();
			}
			if(Inputs.mb!=-1){
				if(Loop.region(540, 750-540, 280, 360-280, true)){
					Player.money += qty *val;
					TrophyTracker.forge += qty;
					
					item.setAmm(-qty);
					if(qty>item.getAmm()){
						qty=item.getAmm();
					}
					if(item.getAmm() ==0){
						Loop.inv.Items.remove(item);
						qty =1;
					}
					//remove items check not selling too many next
				}
			}
//			File file = new File("res/GUIS/Forgers/Sell/Values.txt");
			try{
//			FileInputStream fis = new FileInputStream(file);
			InputStream is = (Forgers.class.getResourceAsStream("/txt files/Values.txt"));

			byte[] b = new byte[(int) is.available()];
			byte[] name = new byte[item.getName().length()];
//			b = fis.getChannel();
			is.read(b);
			name = item.getName().getBytes();
//			System.out.println(b.toString());
//			System.out.println(item.getName());
			String v = "";
			
			for(int i = 0; i < b.length; i++){
				if(b[i] == name[0]){
					for(int ii = 0; ii < name.length; ii ++){
						if(b[i+ii] != name[ii]){
							continue;
						}
						if(ii + 1 == name.length){
							ii+=3;
							for(int iii = 0; iii < 5; iii ++){
								if(b[iii + ii + i] == 59 || b[iii + ii + i] == 186){
//									continue;
									iii = 200;
								}else{
									v = v + (b[iii + ii + i]-48);
//									System.out.println(b[iii + ii + i]-48);
								}
							}
						}
					}
				}
			}
			
			if(v.equals("")){
				val = 5;
			}else{
				val = Integer.parseInt(v);
			}
			is.close();
			}catch(Exception e){
				System.out.println("ERROR READING Values.txt");
				e.printStackTrace();
				return 3;
			}
		}

		if (forgers == 3) {

			if (Inputs.k) {
				forgers = 0;
			}

			if (Inputs.mb != -1) {

				if (Loop.region(100, 150, 100, 100, true)) {
					forgers = 4;
					return forgers;

				}
				if (Loop.region(300, 150, 100, 100, true)) {
					forgers = 5;
					return forgers;

				}
			}
		}
		if (forgers == 4) {
			if (Inputs.k) {
				forgers = 0;
			}

			if (Inputs.mb != -1) {

				if (Loop.region(275, 150, 100, 100, true)) {
					for (int i = 0; i < Loop.inv.Items.size(); i++) {
						if (Loop.inv.Items.get(i).getName().equalsIgnoreCase("Satan Spawner Part")
								&& Loop.inv.Items.get(i).getAmm() >= 4) {
							Loop.inv.addItem("Satan Spawner Part", -4, "This can apparantly craft a Boss spawner!");
							GetItems.addItem("Satan Spawner", 1);
							GetItems.addItem("Satan Spawner Part", -4);
							Loop.inv.addSpawn("Satan Spawner", 1, "Spawns 'Satan'... use with caution");

							return forgers;
						}
					}
					GetItems.addItem("Not enough items", 9);

				}

				if (Loop.region(100, 150, 100, 100, true)) {
					for (int i = 0; i < Loop.inv.Items.size(); i++) {
						if (Loop.inv.Items.get(i).getName().equalsIgnoreCase("Robo Lord Spawner Part")
								&& Loop.inv.Items.get(i).getAmm() >= 4) {
							Loop.inv.addItem("Robo Lord Spawner Part", -4, "This can apparantly craft a Boss spawner!");
							GetItems.addItem("Robo Lord Spawner", 1);
							GetItems.addItem("Robo Lord Spawner Part", -4);
							Loop.inv.addSpawn("Robo Lord", 1, "Spawns a 'Robo Lord'... use with caution");

							return forgers;
						}
					}
					GetItems.addItem("Not enough items", 9);

				}

				if (Loop.region(0, 100, 0, 50, true)) {
					forgers = 3;
					return forgers;

				}
			}
		}

		return forgers;

	}
}
