package com.weebly.kyslol.Game.player.inventory;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.weebly.kyslol.Game.Sound.Audio;
import com.weebly.kyslol.Game.entities.Mobs;
import com.weebly.kyslol.Game.entities.Player;
import com.weebly.kyslol.Game.entities.Satan;
import com.weebly.kyslol.Game.player.potions.AttractPotion;
import com.weebly.kyslol.Game.player.potions.CashPotion;
import com.weebly.kyslol.Game.player.potions.DamagePotion;
import com.weebly.kyslol.Game.player.potions.Effects;
import com.weebly.kyslol.Game.player.potions.RepelPotion;


public class Potions extends Inventory{
	private int Row, Colom, Ammount, dmg;
	private String name, description;
	private Image img;
	private boolean Selected;

	
	public Potions(String name, int Ammount, String description, int dmg){
//		this.dmg = dmg;
		try {
			img = ImageIO.read(InventoryItems.class.getResource(URL + "Potions/" + name.toLowerCase() + ".png"));
		} catch (IOException e) {
			System.out.println("ERROR LOADING " + name + ".png");
			e.printStackTrace();
			System.exit(1);
		}
		
		this.Ammount = Ammount;
		this.name = name;
		this.description = description;
		this.dmg = dmg;
		
//		Inventory.Items.add(this);
	}
	public void tick(){
		if(Selected){
			//Spawn boss
			Selected = false;
			addPotion(name, -1, description, dmg);
			GetItems.addItem(name, -1);
			
			if(name.equalsIgnoreCase("Healing Potion")){
				Player.health = 100;
			}
			if(name.equalsIgnoreCase("Hunger Potion")){
				Player.hunger = 100;
			}
			if(name.equalsIgnoreCase("Cash Potion")){
				new CashPotion(120, name, dmg);
			}
			if(name.equalsIgnoreCase("Attract Potion")){
				new AttractPotion(120, name, dmg);
			}
			if(name.equalsIgnoreCase("Repel Potion")){
				new RepelPotion(120, name, dmg);
			}
			if(name.equalsIgnoreCase("Damage Potion")){
				new DamagePotion(120, name, dmg);
			}
			Audio.drink.flush();
			Audio.drink.loop(1);;
			
		}
	}
	public void pos(){
//		Inventory.Items.size()/10);
		int i = 0;
		for (int len = 0; len < Inventory.Potions.size(); len ++){
			if(Inventory.Potions.get(len).getName().equals(name)&&Inventory.Potions.get(len).getDmg() == dmg){
				i = len + 1;
//				continue;
			}
		}
		Colom = (i % 10)-13;
		if(Colom == -13){
			Colom = -3;
		}
//		System.out.println(i+ " " + Colom);

		Row = (int) (Math.floor((i-1) / 10))+7;
//		System.out.println(Colom + "_" + Row + " " + i + " " + Inventory.Items.size());
	}
	
	public int getAmm(){
		return Ammount;
	}
	public String getDesc(){
		return description;
	}
	public String getName(){
		return name;
	}
	public Image getImg(){
		return img;
	}
	public int getRow(){
		return Row;
	}
	public int getDmg(){
		return dmg;
	}
	public int getColom(){
		return Colom;
	}
	public void setAmm(int i){
		Ammount += i;
	}
	public boolean getSelected(){
		return Selected;
	}
	public void setSelected(boolean i){
		Selected = i;
	}
}
