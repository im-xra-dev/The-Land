package com.weebly.kyslol.Game;

import java.awt.Color;

public class Particle extends FireWork{
	private int x, y, col, ticks = 0;
	private double dir;
	
	public Particle(int x, int y){
		this.x = x;
		this.y = y;
		Color col = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat());
		this.col = col.getRGB();
		dir = random.nextDouble();
		dir *= 10;
		fireworks.add(this);
//		System.out.println("f");
	}
	
	public void tick(){
		ticks ++;
//		System.out.println(x + " " + y);
//		col = 0xff0000;
		y -= dir;
		if(random.nextInt(2) == 0){
			x -= dir/1.5;
		}else{
			x += dir/1.5;

		}
//		System.out.println( y < 0);
		if(ticks > 100){
			fireworks.remove(this);
		}
		if(y < 0){
			fireworks.remove(this);
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
