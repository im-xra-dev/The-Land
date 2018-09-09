package com.weebly.kyslol.Game.entities;

import java.util.Random;

import com.weebly.kyslol.Game.Loop;
import com.weebly.kyslol.Game.RenderThread;
import com.weebly.kyslol.Game.Trophy4.TrophyTracker;
import com.weebly.kyslol.Game.player.inventory.GetItems;

public class Player {
	public static int x = -550;
	public static String biome = "";
	static Random random = new Random();
	public static int money = 50, bank = 0, health = 100, hunger = 100, damage, ammo = 0;
	public static String weapon = "arm";
	public static int resistance = 0, exdam = 0, weaponType = 0;
	public static int cashMultipyer = 1;
	public static int respawn = -550;
	public static void tick(){
//		money +=1000;
//		x = 0;
//		Loop.time = 199;
		
		damage = 1 + exdam;
		resistance = 0;
		weapon = "arm";
		weaponType = 0;
		
		if(biome.equalsIgnoreCase("ocien")){
			x-=1;
			if(!TrophyTracker.trophies[18]){
				TrophyTracker.get(18);
			}
		}
//		x = -450;
//		System.out.println(x);
//		hunger = 89;
		if(biome.equalsIgnoreCase("town")){
			Mobs.repel = true;
		}else{
			Mobs.repel = false;
		}
		
		ammo = 0;

		
		if(Loop.time % 10000 == 0){
			bank += (int) (bank/100)*5;
		}
		
		if(health <= 0){
			health = 100;
			x = respawn;
			Loop.home = 3;
			RenderThread.renderID = 2;
			Mobs.mobs.removeAll(Mobs.mobs);
			Mobs.Satan.removeAll(Mobs.Satan);
			Mobs.RoboLord.removeAll(Mobs.RoboLord);
			Mobs.Boss = false;
		}
		
//		money = 1100000;
//		Loop.inv.addItem("Ecto-plasm", 30, "Dropped by a ghost");
//		Loop.inv.addItem("mysterious essence", 3, "Strong");
//		Loop.inv.addSpawn("Satan Spawner", 3, "");
//		Loop.inv.addWeapon("Triden", 1, "", 100);
		
		if(hunger < 90 && Loop.inv.Food.size() != 0){
			hunger += Loop.inv.Food.get(0).getDmg();
			GetItems.addItem(Loop.inv.Food.get(0).getName() + " Consumed" , 1);

			Loop.inv.addFood(Loop.inv.Food.get(0).getName(), -1, "", Loop.inv.Food.get(0).getDmg());
		}
		
//		biome = w
		
		for(int i = 0; i < Loop.inv.Weapons.size(); i++){
			
			if(Loop.inv.Weapons.get(i).getSelected()){
				damage = Loop.inv.Weapons.get(i).getDmg() + exdam;
				weapon = Loop.inv.Weapons.get(i).getName();
				continue;
			}

		}
		
		for (int i = 0; i < Loop.inv.Ammo.size(); i++) {
//			System.out.println(Loop.inv.Ammo.get(i).getName() + "__" + weapon + " Ammo");

			if (Loop.inv.Ammo.get(i).getName().equalsIgnoreCase(weapon + " Ammo")) {
				ammo = Loop.inv.Ammo.get(i).getAmm();
				continue;
				
			}
		}

		
		if(weapon.contains("Sword")|| weapon.equalsIgnoreCase("Triden")){
			weaponType = 1;
		}else if(weapon.contains("Bow")){
			weaponType = 3;
		}else if(weapon.contains("arm")){
			weaponType = 0;
		}else{
			weaponType = 2;
		}

		for(int i = 0; i < Loop.inv.Armour.size(); i++){
			
			if(Loop.inv.Armour.get(i).getSelected()){
				resistance = Loop.inv.Armour.get(i).getDmg();
//				System.out.println(resistance); 
				continue;
				
			}

		}

		
		if(random.nextInt(100) == 0){
			if(hunger == 0){
				health -= random.nextInt(3);
			}else{
				hunger -= random.nextInt(3);
				if(hunger < 0){
					hunger = 0;
				}
			}
		}
		
		
		if(Inputs.w == true){
			x++;
			TrophyTracker.dist++;
		}
		if(Inputs.s == true && x > -550){
			x--;
			TrophyTracker.dist++;
		}
		
	}
}
