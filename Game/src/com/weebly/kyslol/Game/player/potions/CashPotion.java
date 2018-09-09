package com.weebly.kyslol.Game.player.potions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.weebly.kyslol.Game.Loop;
import com.weebly.kyslol.Game.entities.Player;

public class CashPotion extends Effects {

	String type;
	int strngth, time, dur;

	public CashPotion(int dur, String type, int strngth) {
		this.type = type;
		this.dur = dur;
		this.strngth = strngth;
		effects.add(this);
	}
	// Player.exdam = strngth; for dmg potion

	public void tick() {

		if (time >= dur) {
			effects.remove(this);
			Player.cashMultipyer = 1;
			return;
			// System.out.println(type);

		}
		Player.cashMultipyer = strngth;
		time ++;
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
