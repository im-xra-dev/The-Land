package com.weebly.kyslol.Game;

import java.util.ArrayList;

public class Doors {
	public static ArrayList<Doors> doors = new ArrayList<Doors>();
	
	private int x, y;
	private String door; //Where it takes you
	
	public Doors(int x, int y, String door){
		this.setX(x);
		this.setY(y);
		this.setDoor(door);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getDoor() {
		return door;
	}

	public void setDoor(String door) {
		this.door = door;
	}
}
