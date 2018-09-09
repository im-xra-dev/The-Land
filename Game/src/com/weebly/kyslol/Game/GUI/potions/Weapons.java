package com.weebly.kyslol.Game.GUI.potions;

public class Weapons extends Data{
	String name, type;
	int amm_min, amm_max;
	int cost_min, cost_max;
	int chance;
	int dmg_min, dmg_max;
	
	public Weapons(String name, String ammount, String cost, String chance, String damage) {
		this.name = name;
		this.type = type;
		
		String data = "";
		
		for(int i = 0; i < ammount.length(); i ++){
			if(ammount.charAt(i) == '-'){
				amm_min = Integer.parseInt(data);
				data = "";
				i++;
			}
			data = data + ammount.charAt(i);
		}
		amm_max = Integer.parseInt(data);
		data = "";

		for(int i = 0; i < cost.length(); i ++){
			if(cost.charAt(i) == '-'){
				cost_min = Integer.parseInt(data);
				data = "";
				i++;
			}
			data = data + cost.charAt(i);
		}
		cost_max = Integer.parseInt(data);
		data = "";

		for(int i = 0; i < damage.length(); i ++){
			if(damage.charAt(i) == '-'){
				dmg_min = Integer.parseInt(data);
				data = "";
				i++;
			}
			data = data + damage.charAt(i);
		}
		dmg_max = Integer.parseInt(data);
		data = "";
		
		this.chance = Integer.parseInt(chance);
		
//		System.out.println("Added " + name);
	}
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
