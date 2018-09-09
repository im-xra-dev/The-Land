package com.weebly.kyslol.Game.entities;

import com.weebly.kyslol.Game.Loop;
import com.weebly.kyslol.Game.Trophy4.TrophyTracker;
import com.weebly.kyslol.Game.player.inventory.GetItems;
import com.weebly.kyslol.Game.player.inventory.Inventory;

public class MiniRoboLord extends AI{

		private int x = 140, damage = 0, size = 10, health;
		private String Tex;
//		public int id=0;
		public MiniRoboLord(String tex){
			this.health = 75;
			this.damage = 10;
			Tex = tex;
		}
		
		public void tick(){
			AI(this);
			
			if(health<=0){
				int d = random.nextInt(20);
				d *= Player.cashMultipyer;
				Player.money += d;
				TrophyTracker.mobs ++;
				
				Mobs.mobs.remove(this);
				int e = random.nextInt(2);
//				while (e == 0){
//					e = random.nextInt(3);
//				}
//				Loop.inv.Items.remove(9);
				Loop.inv.addItem("Robo Lord Spawner Part", e, "This can apparantly craft a Boss spawner!");
				GetItems.addItem("£", d);
				GetItems.addItem("Robo Lord Spawner Part", e);
				if(Loop.easter){
					Loop.inv.addFood("Easter Egg", e, "You got an easter egg!", 10);
					GetItems.addItem("Easter Egg", e);
					if(random.nextInt(100) == 0){
						Loop.inv.addItem("Easter Bunny Statue", 1, "You got an easter bunny statue!");
						GetItems.addItem("Easter Bunny Statue", 1);
					}
				}
	}
		}
		
		public int getSize(){
			return size;
		}
		public int getx(){
			return x;
		}
		public String getTex(){
			return Tex;
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
