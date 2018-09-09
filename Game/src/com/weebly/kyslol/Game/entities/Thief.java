package com.weebly.kyslol.Game.entities;

import com.weebly.kyslol.Game.Loop;
import com.weebly.kyslol.Game.Trophy4.TrophyTracker;
import com.weebly.kyslol.Game.player.inventory.GetItems;
import com.weebly.kyslol.Game.player.inventory.Inventory;

public class Thief extends AI{
//	private int x = 140, damage = 0, size = 10, health;
//	private String Tex;
//	public int id=0;
	public Thief(String tex){
		this.health = 30;
		this.damage = 3;
		Tex = tex;
//		id = Mobs.Ghosts.size();
//		System.out.println(x);
	}
	
	public void tick(){
		AI(this);
		
		if(health<=0){
			int d = random.nextInt(50);
			d *= Player.cashMultipyer;
			TrophyTracker.mobs ++;
			int e = random.nextInt(2);
			Player.money += d;
			Mobs.mobs.remove(this);
//			Loop.inv.addItem("Ecto-Plasm", e, "This Ecto-Plasm was dropped by a ghost. It might be useful.");
			if(Loop.easter){
				Loop.inv.addFood("Easter Egg", e, "You got an easter egg!", 10);
				GetItems.addItem("Easter Egg", e);
				if(random.nextInt(100) == 0){
					Loop.inv.addItem("Easter Bunny Statue", 1, "You got an easter bunny statue!");
					GetItems.addItem("Easter Bunny Statue", 1);
				}
			}
			GetItems.addItem("£", d);
//			GetItems.addItem("Ecto-Plasm", e);
}
		if(Player.health <= 0){
			Player.health = 100; //TODO replace with global respawn
			int d = random.nextInt(50);
			Player.money -= d;
//			Mobs.Thief.remove(this);
//			Loop.inv.addItem("Ecto-Plasm", e, "This Ecto-Plasm was dropped by a ghost. It might be useful.");
			
			GetItems.addItem("£", -d);

			
			
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
	public String getTex(){
		return Tex;
	}
}
