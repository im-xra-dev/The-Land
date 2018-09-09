package com.weebly.kyslol.Game.player.inventory;

import java.util.ArrayList;

public class GetItems {
	public static ArrayList<Items> newItems = new ArrayList<Items>();
	
	public static void tick(){
//		System.out.println("Tick");
//		System.out.println("----");
		for(int i = 0; i < newItems.size(); i++){
//			System.out.println(5 + ":6:"+i);
//			System.out.println(newItems.get(i).getName());
			newItems.get(i).tick();
		}
	}
	public static void addItem(String name, int amm){
		if(amm == 0)return;
		newItems.add(new Items(name, amm));
	}
}
