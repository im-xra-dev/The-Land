package com.weebly.kyslol.Game;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Perlin {
	Random random = new Random();
	public static BufferedImage image;
	String Biome;
	
	
	public Perlin(int size, String Biome) {
		if(size == 0){
			size ++;
		}
		double[][] result = new double[size][50];
		this.Biome = Biome;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < 50; j++) {
				double l;
				if (i == 0 && j == 0) {
					l = 0.7;
				} else if (j == 0) {
					l = random.nextDouble();
					while (!(l < result[i - 1][0] + 0.03 && l > result[i - 1][0] - 0.03)) {
						l = random.nextDouble();
						if (l < 0.5) {
							l = 10000;
						}
					}
				} else if (i == 0) {
					l = random.nextDouble();
					while (!(l < result[i][j - 1] + 0.03 && l > result[i][j - 1] - 0.03)) {
						l = random.nextDouble();
						if (l < 0.5) {
							l = 10000;
						}
					}
				} else {
					l = random.nextDouble();
					while (!(l < result[i][j - 1] + 0.03 && l > result[i][j - 1] - 0.03)
							&& !(l < result[i - 1][j] + 0.03 && l > result[i - 1][j] - 0.03)) {
						l = random.nextDouble();
						if (l < 0.5) {
							l = 10000;
						}
					}
				}
				result[i][j] = l;
				// System.out.println(l);
			}
		}

		land(result);

	}

	public void land(double[][] data){
        image = new BufferedImage(data.length,data[0].length, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < data[0].length; y++){
          for (int x = 0; x < data.length; x++){
            if (data[x][y]>1){
                data[x][y]=1;
            }
            if (data[x][y]<0.3){
                data[x][y]=0.3; 
            }
            Color col;
            if(Biome.equalsIgnoreCase("Swamp")){
            	if(data[x][y] < 0.55){
//            		if(data[x][y] < 0.55){
                    	col=new Color((float)data[x][y]/2, (float)data[x][y]/4, 0); 

//                	}else{
//                    	col=new Color((float)data[x][y]/2, (float)data[x][y]/2, 0); 
//                	}
            	}else{
            		col=new Color(0, (float)data[x][y]/4, 0); 
            	}
            }else if(Biome.equalsIgnoreCase("Green")){
            	if(data[x][y] < 0.55){
            		int ran = random.nextInt(4);
            		if(ran == 0){
                		col=new Color(0x8CA1FF); 
            		} else if(ran == 1){
                		col=new Color(0xffffff); 
            		} else if(ran == 2){
                		col=new Color(0xFF4E42); 
            		} else if(ran == 3) {
                		col=new Color(0xFF49DD); 
            		}else{
            			col = new Color(0xFFE554);
            		}
            	}else{
            		col=new Color(0, (float)data[x][y], 0); 
            	}
            		
            }else if(Biome.equalsIgnoreCase("Desert")){
            	col=new Color((float)data[x][y], (float)data[x][y], 0); 
            	
            }else if(Biome.equalsIgnoreCase("Frost")){
            	col=new Color((float)data[x][y], (float)data[x][y], (float)data[x][y]); 
            	
            }else if(Biome.equalsIgnoreCase("Rock")){
            	col=new Color((float)data[x][y]/2, (float)data[x][y]/2, (float)data[x][y]/2); 
            	
            }else if(Biome.equalsIgnoreCase("Hell")){
            	
            	col=new Color((float)data[x][y], 0, 0); 
            }else{
            	col = new Color(0, 0, 0);
            }
            
              
              image.setRGB(x, y, col.getRGB());
          }
        }
//        try {
//            File outputfile = new File("saved.png");
//            outputfile.createNewFile();
//
//            ImageIO.write(image, "png", outputfile);
//        } catch (IOException e) {
//        	e.printStackTrace();
//        }

	}
	
//	public void water(double[][] data){
//        Water = new BufferedImage(data.length,data[0].length, BufferedImage.TYPE_INT_RGB);
//
//        for (int y = 0; y < data[0].length; y++){
//          for (int x = 0; x < data.length; x++){
//            if (data[x][y]>1){
//                data[x][y]=1;
//            }
//            if (data[x][y]<0.3){
//                data[x][y]=0.3; 
//            }
//            Color col;
//         col=new Color(0, (float)data[x][y], (float)data[x][y]); 
//            
//              
//              Water.setRGB(x, y, col.getRGB());
//          }
//        }
//	}

}
