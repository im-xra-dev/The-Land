package com.weebly.kyslol.Game;

import java.util.ArrayList;
import java.util.Random;

import com.weebly.kyslol.Game.Sound.Audio;

public class FireWork {
	protected int x, y, col;
	public static ArrayList<FireWork> fireworks = new ArrayList<FireWork>();
	protected Random random = new Random();

	public FireWork(){
		
		x = random.nextInt(800);
		y = 150;
		col = 0x191919;
		fireworks.add(this);
		
	}
	
	public void tick(){
		y --;
		
		if(y < 70){
			explode();
			Audio.Button.loop(1);
			fireworks.remove(this);
		}
	}
	
	public void explode(){
		int r = random.nextInt(500);
		for(int i = 0; i < r; i ++){
			new Particle(x, y);
		}
	}
	
	public int getCol() {
		return col;
	}


	public void setCol(int col) {
		this.col = col;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}

}
