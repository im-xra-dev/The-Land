package com.weebly.kyslol.Game;

public class Render {
	public final int width;
	public final int height;
	public final int[] pixels;
//	public final int[] pxl;

	public Render(int width, int height) {
//		return;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
//		pxl = new int[width * height];
	}

	public void draw(Render render, int xOffs, int yOffs) {
		for (int i =0; i<width*height; i++){
			pixels[i] =0;
		}
		
		for (int y = 0; y < render.height; y++) {
			int yPix = y + yOffs;
			if (yPix < 0 || yPix >= height) {
				continue;
			}
			for (int x = 0; x < render.width; x++) {
				int xPix = x + xOffs;
				if (xPix < 0 || xPix >= width) {
					continue;
				}

				int rend = render.pixels[x + y * render.width];
				if (rend != 0) {
					pixels[xPix + yPix * width] = rend;
				}
			}
		}

	}

}

