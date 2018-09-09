package com.weebly.kyslol.Game;

import java.awt.Color;
import java.awt.Graphics;

import com.weebly.kyslol.Game.entities.Inputs;
import com.weebly.kyslol.Game.entities.Player;

public class Render3D extends Render{
	public double[] zBuffer;
	Color skyColour = new Color(0, 100, 150);
	
	int treeCalc = 0;
	public Render3D(int width, int height) {
		super(width, height);
		zBuffer = new double[width * height];

	}
	public void floor(int floor, int i, int sky, int j) {
		
		for (int ii =0; ii<width*height; ii++){
			pixels[ii] =0;
		}
		int wx = Player.x;
		
//		for (int i = 0; i < width; i++) {
//			zBufferwall[i] = 0;
//		}
//		Doors.doors.removeAll(Doors.doors);
//		Loop.time = 1000;
		try{
		if(Loop.cycle && Loop.time < 200){
		skyColour = new Color(0, skyColour.getGreen() - 1, skyColour.getBlue() - 1);
//		Loop.time++;
		Loop.cycle = false;
//		System.out.println(Loop.time);
//		System.out.println("ye");
//		skyColour = new Color(0, 100, 150);
//			Color c = new Color(0, 0, 1/2);
////			System.out.println(skyColour.getRGB());
//
//			c = new Color(0, 0, skyColour.getBlue() + 1/10);
//			System.out.println(skyColour.getRGB() + " :" + c.getRGB());
		}else if(Loop.time > 400){
			Loop.time = 0;
		} else if(Loop.cycle && Loop.time > 200){
			skyColour = new Color(0, skyColour.getGreen() + 1, skyColour.getBlue() + 1);
			Loop.cycle = false;

////			System.out.println(Loop.time + "B" );
			Loop.time++;

		}
		}catch(Exception e){
		}
//		for(int layer = 0; layer < 2; layer ++){

		treeCalc = 0;
		boolean yLoop = false;
		double forward = 0;
		double right = 0;
		double up = 0;
//		Graphics g = Loop.bs.getDrawGraphics();
			// / 80);//
//		int playerX = (int) ((right / 14.5) - (3));
//		int playerZ = (int) ((forward / 14.5) - (3)); // //Math.sin(game.time
		// System.out.println(right); // %1000.0 / 80);//
		int floorpos = 15;
		int toppos = 8;
		int count =800*600;
		int loops = 0;
//		int mod = 64;//
		int size = 0; 
		int row = 0;
		for (int y = 0; y <height; y++) {
			
			if (y > height / 4) {
				row ++;
			}
			double yDepth = ((y - height / 2.0) / height);

			double z = (floorpos + up) / yDepth;
			if (yDepth < 0) {

				z = (toppos - up) / -yDepth;
			}
			for (int x = 0; x < width; x++) {
				int yy = 0;//(int) (y + (Player.rotatey));
//				if (yy >= height) {
//					yy = height - 1;
//				}
//				if (yy < 0) {
//					yy = 0;
//				}
				
//				System.out.println("working");
				
//				double xDepth = (x - width / 2.0) / height;
//				xDepth *= z ;
//				double xx = 0;// xDepth * cos + z * sin;
//				double yyy = 0;//-(z * cos - xDepth * sin);
//				int xPix = (int) ((xDepth - right) * 256) - 128;
//				int yPix = (int) ((-xDepth + forward) * 256) - 128;

//				if (x + y * width > x + yy * width) {
//					pixels[x + y * width] = 100;//Texture.floorSprites.pixels[((x & 255) + FloorSheetX * 256) + ((yPix & 255) + FloorSheetY * 256) * 512]; // ((xPix
//
//				}
				
//				zBuffer[x + y * width] = z;

//				pixels[x + y * width] = 0x00ff00;
				
				if (y > height / 4) {
//					System.out.println("floor");
					boolean str = false;
					for (int ii = 0; ii < 16; ii++){
						for (int iii = 0; iii < size; iii++){
						if(x+y*width >= 480000) continue;
//						zBuffer[x + y * width] = z;
						try{
//							System.out.println(" ");

//							if(layer == 1){
//								if(pixels[x + y * width] == 0) {
//								System.out.println("layer == 1");
//							boolean GoodGraph = false;
							
//							if(Loop.GoodGraph){
//							g.setColor(new Color(World.Biomes.get((int) (Player.x * 50 + Math.floor(count/16))).colour));
//							
//							if(ii>(size - iii) /2 -1&&ii<(size - iii) *2 +1 && iii<size/2){
//								pixels[x + y * width] = World.Biomes.get((int) (Player.x * 50 + Math.floor(count/16))).colour;
//							}else
//							if(ii<(size - iii) *2 -1&&ii>(size - iii) /2 +1 && iii>size/2){
//								pixels[x + y * width] = World.Biomes.get((int) (Player.x * 50 + Math.floor(count/16))).colour;
//							}else
//							if(iii == size/2){
//								pixels[x + y * width] = World.Biomes.get((int) (Player.x * 50 + Math.floor(count/16))).colour;
//							}
//							
							if((World.Biomes.get((int) (wx * 50 + Math.floor(count/16))).Structure == "water")){
//
//								if(iii * ii > size * 2 ||( ii < 1 && !(iii <= 0))){
//									pixels[x + y * width] = World.Biomes.get((int) (Player.x * 50 + Math.floor(count/16))).colour;
//
//								}else{
//									pixels[x + y * width] = World.Biomes.get((int) (Player.x * 50 + Math.floor(count/16))+1).colour;
//								}
//
								
								if(iii * ii > size * 2 ||( ii < 1 && !(iii <= 0))){
									pixels[x + y * width] = Loop.p.Water[(int)y/size][(int)x/16];//Loop.p.Water[(int)x/16][(int)y/size]; //((int) (Player.x * 50 + Math.floor(count/16))).colour;
									Loop.img.setRGB(x, y, Loop.p.Water[(int)y/size][(int)x/16]);//Loop.p.Water[(int)x/16][(int)y/size]; //((int) (Player.x * 50 + Math.floor(count/16))).colour;

								}else{
									Loop.img.setRGB(x, y, Loop.p.Water[((int)y/size)+1][(int)x/16]);//Loop.p.Water[(int)x/16][(int)y/size]; //((int) (Player.x * 50 + Math.floor(count/16))).colour;
									pixels[x + y * width] = Loop.p.Water[((int)y/size)+1][(int)x/16];;//Loop.p.Water[(int)x/16][(int)y/size]; //pixels[x + y * width] = World.Biomes.get((int) (Player.x * 50 + Math.floor(count/16))+1).colour;
								}
							}else{
								if(iii * ii > size * 2 ||( ii < 1 && !(iii <= 0))){
									pixels[x + y * width] = World.Biomes.get((int) (wx * 50 + Math.floor(count/16))).colour;
									Loop.img.setRGB(x, y, World.Biomes.get((int) (wx * 50 + Math.floor(count/16))).colour);
								}else{
									Loop.img.setRGB(x, y, World.Biomes.get((int) (wx * 50 + Math.floor(count/16))+1).colour);

									pixels[x + y * width] = World.Biomes.get((int) (wx * 50 + Math.floor(count/16))+1).colour;
								}
							}
//							g.fillRoundRect(x, y, 20, size + 4, 20, 20);
//							}else{
//								Loop.img.setRGB(x, y, World.Biomes.get((int) (Player.x * 50 + Math.floor(count/16))).colour);
//
////								pixels[x + y * width] = World.Biomes.get((int) (Player.x * 50 + Math.floor(count/16))).colour;
//							}
//						pixels[x + y * width] = ;;//Texture.floorSprites.pixels[((xPix & 255) + FloorSheetX * 256) + ((yPix & 255) + FloorSheetY * 256) * 512]; // ((xPix
//							System.out.println(false);
//								}
//							}else{
//							System.out.println(true);
							if(!str && !(World.Biomes.get((int) (wx * 50 + Math.floor(count/16))).Structure == "") ){
//							if(World.Biomes.get((int) (wx * 50 + Math.floor(count/16))).Structure.equalsIgnoreCase("right")){
////								System.out.println("yeah");
//							}
							treesEtc(x, y, row, size, World.Biomes.get((int) (wx * 50 + Math.floor(count/16))).Structurex, World.Biomes.get((int) (wx * 50 + Math.floor(count/16))).Structure);
							str = true;
						}
//							}
						

						
						}catch (Exception e){
//							e.printStackTrace();
							new World(2);
						}
						
						

						
						y++;
						if(y == height){
							Player.biome = World.Biomes.get((int) (wx * 50 + Math.floor(count/16))).biome;
						} 
						//						System.out.println(x + y * width);
					}
					y -= size;
					x++;
					count--;


					}
					yLoop = true;
					x-=16;
//					System.out.println(count);


				} else {//if (y < 600 / 2) {
//					System.out.println("roof");
//					skyColour.darker();
					
//					skyColour.
//					zBuffer[x + y * width] = z;
					if(pixels[x+y*width] == 0){
					if (Loop.MMdd.equals("1105") || Loop.MMdd.equals("1231") || Loop.MMdd.equals("0203")) {
						Loop.img.setRGB(x, y, new Color(0, 0, 50).getRGB());//LoadSprites.SpriteSheet.pixels[((xPix & 255) + 0 * 256) + ((yPix & 255) + 0 * 256) * 256];
					}else{
						Loop.img.setRGB(x, y, skyColour.getRGB());//LoadSprites.SpriteSheet.pixels[((xPix & 255) + 0 * 256) + ((yPix & 255) + 0 * 256) * 256];
					}
					}
					//
					// &15)
					// *16)
					// |
					// ((yPix&15)*16);

				}
				
//				if(x<105 &&x > 90&& y < 415&&  y > 400){
//					treesEtc(x, y);
//				}
				if(yLoop){
					x+=15;
				}
				}
			if(yLoop){
				y+=size-1;
				loops ++;
				
//				if (y>height/4*3){
//					size +=2;
//
//				}else{
				if(size<12){
					if(loops%3 == 0)
						size ++;
				}else{
					if(loops%2 == 0)
						size +=2;

				}
				
				
				
//				}
//				if(!(mod < 1)){
////					if(loops % mod < loops /10){
////						mod -= 1;
////					}
//				}
			}
			
			
			
		}
//		}
//		count =800*600;
//		loops = 0;
//		mod = 64;
//		size = 0; 
//		for (int y = 0; y <height; y++) {
//			double yDepth = ((y - height / 2.0) / height);
//
//			double z = (floorpos + up) / yDepth;
//			if (yDepth < 0) {
//
//				z = (toppos - up) / -yDepth;
//			}
//			for (int x = 0; x < width; x++) {
//				int yy = 0;//(int) (y + (Player.rotatey));
////				if (yy >= height) {
////					yy = height - 1;
////				}
////				if (yy < 0) {
////					yy = 0;
////				}
//				
////				System.out.println("working");
//				
//				double xDepth = (x - width / 2.0) / height;
//				xDepth *= z ;
//				double xx = 0;// xDepth * cos + z * sin;
//				double yyy = 0;//-(z * cos - xDepth * sin);
//				int xPix = (int) ((xDepth - right) * 256) - 128;
//				int yPix = (int) ((-xDepth + forward) * 256) - 128;
//
////				if (x + y * width > x + yy * width) {
////					pixels[x + y * width] = 100;//Texture.floorSprites.pixels[((x & 255) + FloorSheetX * 256) + ((yPix & 255) + FloorSheetY * 256) * 512]; // ((xPix
////
////				}
//				
//				zBuffer[x + y * width] = z;
//
////				pixels[x + y * width] = 0x00ff00;
//				if (y > height / 4) {
////					System.out.println("floor");
//					for (int ii = 0; ii < 16; ii++){
//						for (int iii = 0; iii < size; iii++){
//						if(x+y*width >= 480000) return;
//						zBuffer[x + y * width] = z;
//						try{
//						if(!(World.Biomes.get((int) (Player.x * 50 + Math.floor(count/16))).Structure == "") && y == 164 && x == 50){//Texture.floorSprites.pixels[((xPix & 255) + FloorSheetX * 256) + ((yPix & 255) + FloorSheetY * 256) * 512]; // ((xPix
//							treesEtc(x, y);
//						
//						}
//						}catch (Exception e){
//							return;
////							e.printStackTrace();
////							new World(2);
//						}
//						
//						y++;
//						
////						if(y == height){
//////							Player.biome = World.Biomes.get((int) (Player.x * 50 + Math.floor(count/16))).biome;
////						}
////						System.out.println(x + y * width);
//					}
//					y -= size;
//					x++;
//					count--;
//
//
//					}
//					yLoop = true;
//					x-=16;
////					System.out.println(count);
//
//
//				}
//				
//				
//				if(yLoop){
//					x+=15;
//				}
//				}
//			if(yLoop){
//				y+=size-1;
//				loops ++;
//				
////				if (y>height/4*3){
////					size +=2;
////
////				}else{
//				if(size<12){
//					if(loops%3 == 0)
//						size ++;
//				}else{
//					if(loops%2 == 0)
//						size +=2;
//
//				}
//				
//				
//				
////				}
////				if(!(mod < 1)){
//////					if(loops % mod < loops /10){
//////						mod -= 1;
//////					}
////				}
//			}
//			
//			
//			
//		}

		
	}
	
	public void treesEtc(int x, int y, int count, int size, int spry, String type){
//		System.out.println("www"); hi    
		if(spry < 0) return;
		
		treeCalc ++;
		if(treeCalc % 2 == 1) return;
//		int count = 0;
//		for (; y < height; y++){
//			for (; x < width; x++){
//				if(World.Biomes.get((int) (Player.x * 50 + Math.floor(count/16))).biome == "")
		int xPix = 0;
				for (int xx = x-256; xx < x+256 ; xx ++){    
					xPix++;
					int yPix = 0;
// 100 = 15000/150        15000/200  15000 / y
					for (double yy = 0; yy < height; yy += (y / 256.00)){//size){//Math.ceil(256 / count)){
						yPix+=2;
//						try{    5 = 250/50    10 = 250 /25
						double change;
						change = Math.abs(-yy);
						if(yy<0){
							yy=0;
							yPix = 512-(512 / count);// 256-((y));//(int)change;
						}
//						yPix = (int)change;
						 change =  Math.abs(xx);

							if(xx<0){
								xx=0;
								xPix = (int)change;

							}
							if(xx>=800){
								return;

							}
						if(xx+yy*width > width * height)
							continue;
						if(yPix>512 ){
							continue;
						}
//								for(int blockx = xx; blockx < xx+1; blockx++){			
//									for(int blocky = yy; blocky < yy+1; blocky++){
										if(!(LoadSprites.SpriteSheet.pixels[((xPix & 511) + spry * 512) + ((yPix & 511) + 0 * 512) * 5120] == 0xffff00ff)){
//											if(blockx+blocky*width > width * height)
//												return;
//											System.out.println(blockx + " " + blocky);
											
											
											for(int i = 0; i < 3; i++){
//												for(int ii = 0; ii < Doors.doors.size(); ii ++){
//													if(Doors.doors.get(ii).getX() == xx && Doors.doors.get(ii).getY() == (int)yy + i){
//														Doors.doors.remove(ii);
//														ii--;
//													}
//												}
												if(Inputs.mb != -1 && Loop.pause == 0){
													if(LoadSprites.SpriteSheet.pixels[((xPix & 511) + spry * 512) + ((yPix & 511) + 0 * 512) * 5120] == 0xff492313){
														if(Inputs.mx == xx && Inputs.my == (int)yy + i){
															Loop.Door(type);
														}
													}
												}
												
												pixels[xx + ((int)yy+i) * width] =  LoadSprites.SpriteSheet.pixels[((xPix & 511) + spry * 512) + ((yPix & 511) + 0 * 512) * 5120];
												Loop.img.setRGB(xx, ((int)yy + i), LoadSprites.SpriteSheet.pixels[((xPix & 511) + spry * 512) + ((yPix & 511) + 0 * 512) * 5120]);
											}
//											System.out.println(xx + " " + yy + " " );
								}}
							}
//					} catch (Exception e){
//						e.printStackTrace();
					}
					
				
//				count ++;
//			}
//		}
//	}
}
