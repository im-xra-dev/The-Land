package com.weebly.kyslol.Game.Trophy4;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import com.weebly.kyslol.Game.GUI.Forgers;
import com.weebly.kyslol.Game.player.inventory.GetItems;

public class TrophyTracker {
	public static boolean[] trophies = new boolean[20];
	public static ArrayList<String> names = new ArrayList<String>();
	
	public static int dist = 0, mobs = 0, forge = 0, people = 0, church = 0;
	
	public static void _Init_() {
		for (int i = 0; i < trophies.length; i++) {
			trophies[i] = false;
		}
		InputStream j = (Forgers.class.getResourceAsStream("/txt files/Trophies.txt"));
		Scanner scan = new Scanner(j);
		while (scan.hasNextLine()) {
			names.add(scan.nextLine());
		}
	}

	public static void get(int i) {
		if(!trophies[i]){
			SendTrophyData.TrophyData("" +(i+1));
			trophies[i] = true;
			GetItems.addItem("You Earned A Trophy!", 1);
			GetItems.addItem(names.get(i), 1);
		}
		for(int ii = 0; ii < 19; ii ++){
			if(!trophies[ii]){
				return;
			}
		}
		if(!trophies[19]){
			SendTrophyData.TrophyData("" +(20));
			trophies[19] = true;
			GetItems.addItem("You Earned A Trophy!", 1);
			GetItems.addItem(names.get(19), 1);
		}
	}
}
