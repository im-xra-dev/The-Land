package com.weebly.kyslol.Game.player.inventory;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Ammo extends Inventory{
	private int Row, Colom, Ammount, dmg;
	private String name, description;
	private Image img;
	private boolean Selected;

	
	public Ammo(String name, int Ammount, String description){
		String[] n = name.split(" ");
		try {
//			System.out.println(n[0]);

			img = ImageIO.read(InventoryItems.class.getResource(URL + "Ammo/" + n[0].toLowerCase() + ".png"));
		} catch (IOException e) {
			System.out.println("ERROR LOADING " + name.toLowerCase() + ".png");
			e.printStackTrace();
			System.exit(1);
		}
		
		this.Ammount = Ammount;
		this.name = n[0] + " Ammo";
		this.description = description;
		
//		Inventory.Items.add(this);
	}
	
	public void pos(){
//		Inventory.Items.size()/10);
		int i = 0;
		for (int len = 0; len < Inventory.Ammo.size(); len ++){
			if(Inventory.Ammo.get(len).getName().equals(name)){
				i = len + 1;
//				continue;
			}
		}
		Colom = (i % 10)-11;
		if(Colom == -13){
			Colom = -3;
		}
//		System.out.println(i+ " " + Colom);

		Row = (int) (Math.floor((i-1) / 10))+4;
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
		if(Ammount < 1){
			Ammo.remove(this);
		}
	}
	public boolean getSelected(){
		return Selected;
	}
	public void setSelected(boolean i){
		Selected = i;
	}
}
