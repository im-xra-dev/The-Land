package com.weebly.kyslol.Game.player.potions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.weebly.kyslol.Game.Loop;
import com.weebly.kyslol.Game.entities.Mobs;
import com.weebly.kyslol.Game.entities.Player;

public class AttractPotion extends Effects {

	String type;
	int strength, time, dur;

	public AttractPotion(int dur, String type, int strength) {
		this.type = type;
		this.dur = dur;
		this.strength = strength;
		effects.add(this);
	}
	// Player.exdam = strngth; for dmg potion

	public void tick() {

		if (time > dur) {
			effects.remove(this);
			Mobs.attract = 1;
			return;

		}
		
		Mobs.attract = strength;
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
