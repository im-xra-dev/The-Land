package com.weebly.kyslol.Game.entities;

import com.weebly.kyslol.Game.Loop;
import com.weebly.kyslol.Game.Trophy4.TrophyTracker;
import com.weebly.kyslol.Game.player.inventory.GetItems;

//TODO .  
// Change Music.

//DONE Satan kills all mobs. attk power: 100.  Health: 1000. spawn chance: 1/1,000,000, hell: 1/100,000. Rewards: 
//£100,000, Mysterious essence, Satans triden thingy. increases chance of satans slaves spawning

public class Satan extends AI{
	private int x = 20, damage = 0, size = 100, health;

//	public int id=0;
	public Satan(){
		Boss = true;
		this.health = 1000;
		this.damage = 100;
//		id = Mobs.Ghosts.size();
//		System.out.println(x);
	}
	
	public void tick(){
		for(int i = 0; i < Mobs.mobs.size(); i++){
			if(!Mobs.mobs.get(i).getTex().equals("/SatansSlave.png")){
				Mobs.mobs.remove(i);
			}
		}
		
		BossAI(this);
		
		if(health<=0){
			Boss = false;
			Player.money += 2000;
			Loop.inv.addItem("Mysterious Essence", 1, "The remains of a powerful mob. Maby I can harness its power.");
			Loop.inv.addWeapon("Triden", 1, "The weapon used by Satan himself!  100 Damage", 100);
			
			if(!TrophyTracker.trophies[13]){
				TrophyTracker.get(13);
			}
			
			GetItems.addItem("£", 100000);
			GetItems.addItem("Mysterious Essence", 1);
			GetItems.addItem("Satan's Triden", 1);
			Mobs.Satan.remove(this);
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
