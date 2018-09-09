package com.weebly.kyslol.Game;

public class Game extends Render{

	public Game(int width, int height) {
		super(width, height);
	}


	private Render3D render = new Render3D(800, 600);
	public static int SpriteSize = 256;
	public static int SpriteSheet = 2560;
	public static int sky = 1;
	public static int floor = 0;
	public static int width = 800, height = 600;
	
	
	public void render() {
//		for (int i =0; i<width*height; i++){
//			pixels[i] =0;
//		}
			
				render.floor(floor, 0, sky, 0); // x,y floor tex, x,y roof tex

				
				
//				draw(render, 0, 0);
			//}
	}
}
