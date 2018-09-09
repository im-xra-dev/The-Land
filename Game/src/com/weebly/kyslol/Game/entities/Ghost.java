package com.weebly.kyslol.Game.entities;

import com.weebly.kyslol.Game.Loop;
import com.weebly.kyslol.Game.Trophy4.TrophyTracker;
import com.weebly.kyslol.Game.player.inventory.GetItems;
import com.weebly.kyslol.Game.player.inventory.Inventory;

public class Ghost extends AI{
//	private int x = 140, damage = 0, size = 10, health;
//	String Tex = "Ghost.png";
//	public int id=0;
	public Ghost(String tex){
		this.health = 10;
		this.damage = 3;
		Tex = tex;
//		id = Mobs.Ghosts.size();
//		System.out.println(x);
	}
	
	public void tick(){
		AI(this);
		
		if(health<=0){
			TrophyTracker.mobs ++;
			
			int d = random.nextInt(10);
//			System.out.println(d + " " + Player.cashMultipyer + " " + d * Player.cashMultipyer);
			d *= Player.cashMultipyer;
			Player.money += d;
			Mobs.mobs.remove(this);
			int e = random.nextInt(3);
//			while (e == 0){
//				e = random.nextInt(3);
//			}
//			Loop.inv.Items.remove(9);
			Loop.inv.addItem("Ecto-Plasm", e, "This Ecto-Plasm was dropped by a ghost. It might be useful.");
			
			if(Loop.easter){
				Loop.inv.addFood("Easter Egg", e, "You got an easter egg!", 10);
				GetItems.addItem("Easter Egg", e);
				if(random.nextInt(100) == 0){
					Loop.inv.addItem("Easter Bunny Statue", 1, "You got an easter bunny statue!");
					GetItems.addItem("Easter Bunny Statue", 1);
				}
			}
			
//			Loop.inv.addItem("Ecto-Plasm", e, "#bestWeaponEvr!!!!! XD.");
//			Loop.inv.addItem("Robo Lord Spawner Part", 4, "This can apparantly craft a Boss spawner!");
//			Loop.inv.addSpawn("Robo Lord", 1, "Spawns a 'Robo Lord'... use with caution");
//
//			Loop.inv.addSpawn("Mysterious Essence", 1, "The remains of a powerful mob. Maby I can harness its power.");
//			Loop.inv.addSpawn("Satan", e, "#bestWeaponEvr!!!!! XD.");
//			Loop.inv.addSpawn("MiniRoboLord", e, "#bestWeaponEvr!!!!! XD.");
//			Loop.inv.addSpawn("Robo Lord Spawner Part", e, "#bestWeaponEvr!!!!! XD.");
//			Loop.inv.addSpawn("SpriteSheet", e, "#bestWeaponEvr!!!!! XD.");
//			Loop.inv.addSpawn("Thumb", e, "#bestWeaponEvr!!!!! XD.");
//			Loop.inv.addSpawn("tree A2", e, "#bestWeaponEvr!!!!! XD.");
//
//			Loop.inv.addSpawn("2", e, "#bestWeaponEvr!!!!! XD.");
//			Loop.inv.addSpawn("3", e, "#bestWeaponEvr!!!!! XD.");
//			Loop.inv.addSpawn("4", e, "#bestWeaponEvr!!!!! XD.");
////			Loop.inv.addWeapon("5", e, "#bestWeaponEvr!!!!! XD.");
//			Loop.inv.addItem("6", e, "#bestWeaponEvr!!!!! XD.");
//			Loop.inv.addItem("7", e, "#bestWeaponEvr!!!!! XD.");
//			Loop.inv.addItem("8", e, "#bestWeaponEvr!!!!! XD.");
//			Loop.inv.addItem("9", e, "#bestWeaponEvr!!!!! XD.");
//			Loop.inv.addItem("Ghost", e, "#bestWeaponEvr!!!!! XD.");
//			Loop.inv.addItem("Inv", e, "#bestWeaponEvr!!!!! XD.");
			GetItems.addItem("£", d);
			GetItems.addItem("Ecto-Plasm", e);
}
	}
	public int getSize(){
		return size;
	}
	public int getx(){
		return x;
	}
	public int getDamage(){
		return damage;
	}
	public int getHealth(){
		return health;
	}

	public void addHealth(int i) {
		health += i;
		
	}
	public void addX(int i) {
		x += i;
		
	}
	public void addSize(int i) {
		size += i;
		
	}

}
