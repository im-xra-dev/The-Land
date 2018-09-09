package com.weebly.kyslol.Game.player.inventory;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.weebly.kyslol.Game.entities.Mobs;
import com.weebly.kyslol.Game.entities.RoboLord;
import com.weebly.kyslol.Game.entities.Satan;


public class Food extends Inventory{
	private int Row, Colom, Ammount, dmg;
	private String name, description;
	private Image img;
	private boolean Selected;

	
	public Food(String name, int Ammount, String description, int heals){
		this.dmg = heals;
		try {
			img = ImageIO.read(InventoryItems.class.getResource(URL + "Food/" + name.toLowerCase() + ".png"));
		} catch (IOException e) {
			System.out.println("ERROR LOADING " + name + ".png");
			e.printStackTrace();
			System.exit(1);
		}
		
		this.Ammount = Ammount;
		this.name = name;
		this.description = description;
		
//		Inventory.Items.add(this);
	}
	public void tick(){
		if(Selected ){
			//Slot 1
			Selected = false;
//			System.out.println("HI");
			Inventory.Food.remove(this);
			Inventory.Food.add(0, this);
		}
	}
	public void pos(){
//		Inventory.Items.size()/10);
		int i = 0;
		for (int len = 0; len < Inventory.Food.size(); len ++){
			if(Inventory.Food.get(len).getName().equals(name)){
				i = len + 1;
//				continue;
			}
		}
		Colom = (i % 10)-11;
		if(Colom == -13){
			Colom = -3;
		}
//		System.out.println(i+ " " + Colom);

		Row = (int) (Math.floor((i-1) / 10));
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
