package com.weebly.kyslol.Game.GUI.potions;

public class Data{
	String name, type;
	int amm_min, amm_max;
	int cost_min, cost_max;
	int chance;
	int dmg_min, dmg_max;
	
//	public Data(String name, String ammount, String cost, String chance, String damage) {
//		
//	}
	public int getMinAmm(){
		return amm_min;
	}
	public int getMaxAmm(){
		return amm_max;
	}
	public int getMincost(){
		return cost_min;
	}
	public int getMaxCost(){
		return cost_max;
	}
	public int getMinDmg(){
		return dmg_min;
	}
	public int getMaxDmg(){
		return dmg_max;
	}
	public int getChance(){
		return chance;
	}
	public String getName(){
		return name;
	}
	public String getType(){
		return type;
	}
	public void setName(String name){
		this.name = name;
	}

}
