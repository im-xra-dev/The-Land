package com.weebly.kyslol.Game.player.potions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.weebly.kyslol.Game.Loop;
import com.weebly.kyslol.Game.entities.Player;

public class Effects {
//	Calendar c = Calendar.getInstance();
	public static ArrayList<Effects> effects = new ArrayList<Effects>();
	String type;
	int strngth, dur, time;
	
	public static void removeAll(){
		for(int i = 0; i < effects.size(); i ++){
			effects.get(i).reset();
		}
	}
//	public Effects(int dur, String type, int strngth){
//		this.type = type;
//		this.strngth = strngth;
//		c.setTime(new Date());
//		c.add(Calendar.SECOND, dur);
////		System.out.println();
//
//		effects.add(this);
//	}
//			Player.exdam = strngth; for dmg potion

	public void tick(){
	}
	public int getDur(){
		return dur;
	}
	public int getTime(){
		return time;
	}
	public String getType(){
		return type;
	}
	public void reset(){
		dur = 0;
	}

}
