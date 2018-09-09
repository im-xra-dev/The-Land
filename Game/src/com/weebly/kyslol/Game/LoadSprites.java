package com.weebly.kyslol.Game;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class LoadSprites {
	
	
	public static Render SpriteSheet = loadBitmap("/Buildings/Structures/SpriteSheet.png");

	
	
	public static Render loadBitmap(String fileName){
		try{
			BufferedImage image = ImageIO.read(LoadSprites.class.getResource(fileName));
			int width = image.getWidth();
			int height = image.getHeight();
			Render result = new Render(width, height);
			
			image.getRGB(0, 0, width, height, result.pixels, 0, width);
			return result;
		
		} catch (Exception e) {
			System.out.println("crash loading " + fileName);
			return null;
		}
	}
	

	
}
