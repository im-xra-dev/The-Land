package com.weebly.kyslol.Game;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class WaterAnim {
	Random random = new Random();
//	public static BufferedImage Water;
	String Biome;
	int[][] Water = new int[200][50];
	
	public WaterAnim(){
		
		double[][] result = new double[200][50];
		this.Biome = Biome;
		for (int i = 0; i < 200; i++) {
			for (int j = 0; j < 50; j++) {
				double l;
				if (i == 0 && j == 0) {
					l = 0.7;
				} else if (j == 0) {
					l = random.nextDouble();
					while (!(l < result[i - 1][0] + 0.03 && l > result[i - 1][0] - 0.03)) {
						l = random.nextDouble();
						if (l < 0.3) {
							l = 10000;
						}
					}
				} else if (i == 0) {
					l = random.nextDouble();
					while (!(l < result[i][j - 1] + 0.03 && l > result[i][j - 1] - 0.03)) {
						l = random.nextDouble();
						if (l < 0.3) {
							l = 10000;
						}
					}
				} else {
					l = random.nextDouble();
					while (!(l < result[i][j - 1] + 0.03 && l > result[i][j - 1] - 0.03)
							&& !(l < result[i - 1][j] + 0.03 && l > result[i - 1][j] - 0.03)) {
						l = random.nextDouble();
						if (l < 0.3) {
							l = 10000;
						}
					}
				}
				result[i][j] = l;
			}
		}

		water(result);


		
	}
	
	public void update(){
		double[][] result = new double[200][50];

		for (int i = 0; i < 200; i++) {
			for (int j = 0; j < 50; j++) {
				double l;
				if (i == 0 && j == 0) {
					l = 0.7;
				} else if (j == 0) {
					l = random.nextDouble();
					while (!(l < result[i - 1][0] + 0.005 && l > result[i - 1][0] - 0.005)) {
						l = random.nextDouble();
						if (l < 0.3) {
							l = 10000;
						}
					}
				} else if (i == 0) {
					l = random.nextDouble();
					while (!(l < result[i][j - 1] + 0.005 && l > result[i][j - 1] - 0.005)) {
						l = random.nextDouble();
						if (l < 0.3) {
							l = 10000;
						}
					}
				} else {
					l = random.nextDouble();
					while (!(l < result[i][j - 1] + 0.005 && l > result[i][j - 1] - 0.005)
							&& !(l < result[i - 1][j] + 0.005 && l > result[i - 1][j] - 0.005)) {
						l = random.nextDouble();
						if (l < 0.3) {
							l = 10000;
						}
					}
				}
				result[i][j] = l;
			}
		}

		water(result);


		
	}

	
//	public Perlin(int size, String Biome) {
//		double[][] result = new double[size][50];
//		this.Biome = Biome;
//		for (int i = 0; i < size; i++) {
//			for (int j = 0; j < 50; j++) {
//				double l;
//				if (i == 0 && j == 0) {
//					l = 0.7;
//				} else if (j == 0) {
//					l = random.nextDouble();
//					while (!(l < result[i - 1][0] + 0.03 && l > result[i - 1][0] - 0.03)) {
//						l = random.nextDouble();
//						if (l < 0.5) {
//							l = 10000;
//						}
//					}
//				} else if (i == 0) {
//					l = random.nextDouble();
//					while (!(l < result[i][j - 1] + 0.03 && l > result[i][j - 1] - 0.03)) {
//						l = random.nextDouble();
//						if (l < 0.5) {
//							l = 10000;
//						}
//					}
//				} else {
//					l = random.nextDouble();
//					while (!(l < result[i][j - 1] + 0.03 && l > result[i][j - 1] - 0.03)
//							&& !(l < result[i - 1][j] + 0.03 && l > result[i - 1][j] - 0.03)) {
//						l = random.nextDouble();
//						if (l < 0.5) {
//							l = 10000;
//						}
//					}
//				}
//				result[i][j] = l;
//				// System.out.println(l);
//			}
//		}
//
//		land(result);
//
//	}
	
	public void water(double[][] data){
//        Water = new BufferedImage(data.length,data[0].length, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < data[0].length; y++){
          for (int x = 0; x < data.length; x++){
            if (data[x][y]>1){
                data[x][y]=1;
            }
            if (data[x][y]<0.3){
                data[x][y]=0.3; 
            }
            Color col;
         col=new Color(0, (float)data[x][y]/5, (float)data[x][y]); 
            
              
              Water[x][y] = col.getRGB();
          }
        }
	}

}
