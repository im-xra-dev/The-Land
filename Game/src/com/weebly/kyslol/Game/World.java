package com.weebly.kyslol.Game;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.weebly.kyslol.Game.GUI.Forgers;

public class World {
	public static ArrayList<Ground> Biomes = new ArrayList<Ground>();
	public static ArrayList<String> buildings = new ArrayList<String>();

	public static int homes = 0;
	String currentBiome = "", nextBiome;

	public World(int size) {
		System.out.println("More Land generating");
		try {
			currentBiome = Biomes.get(Biomes.size() - 1).biome;
		} catch (Exception e) {
			currentBiome = "rock";
			try {
				InputStream buildings = (World.class.getResourceAsStream("/txt files/Buildings.txt"));
				Scanner scan = new Scanner(buildings);
				while (scan.hasNextLine()) {
					this.buildings.add(scan.nextLine());
				}

				// InputStream is =
				// (Forgers.class.getResourceAsStream("/GUIS/txt
				// files/Jobs.txt"));
			} catch (Exception ee) {
				ee.printStackTrace();
			}
			town(70);
		}
		for (int i = 0; i < size; i++) {
			if (Loop.size > 60000) {
				// endOfWorld();
				return;
			}
			// System.out.println(Biomes.size());

			Random random = new Random();
			int biome = random.nextInt(15);
			if (biome < 10) {
				if (random.nextInt(2) == 0) {

					nextBiome = "Green";
					transition();
					currentBiome = "Green";

					greenBiome();
				} else {
					nextBiome = "Desert";
					transition();
					currentBiome = "Desert";

					desert();
				}
			} else if (biome == 10) {
				if (random.nextInt(2) == 0) {
					nextBiome = "Hell";
					transition();
					currentBiome = "Hell";

					hell();
				} else {
					nextBiome = "Swamp";
					transition();
					currentBiome = "Swamp";

					swamp();
				}
			} else if (biome < 15) {
				if (random.nextInt(2) == 0) {

					nextBiome = "Frost";
					transition();
					currentBiome = "Frost";

					frost();
				} else {
					nextBiome = "Rock";
					transition();
					currentBiome = "Rock";

					rock();

				}
			} else {
				System.out.println(15);
			}
		}
	}

	private void town(int size) {
		// System.out.println("Town");
		Random random = new Random();
		Perlin p = new Perlin(1000, "Rock");
		BufferedImage side = p.image;
		p = new Perlin(1000, "Desert");
		BufferedImage path = p.image;

		nextBiome = "rock";
		transition();

		if ((random.nextInt(10) != 0 || (homes == 0))) {
			for (int x = 0; x < size; x++) {
				for (int y = 0; y < 50; y++) {
					if (y > 15 && y < 35) {
						if (random.nextBoolean()) {
							Biomes.add(new Ground(path.getRGB(x, y), "Town", "", 0, 0));
						} else {
							Biomes.add(new Ground(side.getRGB(x, y), "Town", "", 0, 0));
						}
					} else if ((x % 10 == 0 || x == 1) && (y == 5 || y == 45) && x != 0) {
						// System.out.println("true");
						if (homes == 1) {
							Biomes.add(new Ground(side.getRGB(x, y), "Town", "home", 0, 3));
							System.out.println("home");
						} else if (y < 25) {
							Biomes.add(new Ground(side.getRGB(x, y), "Town",
									buildings.get(random.nextInt(buildings.size())), 0, 4));
						} else {
							Biomes.add(new Ground(side.getRGB(x, y), "Town",
									buildings.get(random.nextInt(buildings.size())), 0, 3));
						}
						homes++;

					} else {
						Biomes.add(new Ground(side.getRGB(x, y), "Town", "", 0, 0));
					}
					// System.out.println(x + " " + y + " " + Biomes.size());
				}

			}

		} else {

			for (int x = 0; x < size; x++) {
				for (int y = 0; y < 50; y++) {
					if (y > 15 && y < 35) {
						if (random.nextBoolean()) {
							Biomes.add(new Ground(path.getRGB(x, y), "Abandoned Town", "", 0, 0));
						} else {
							Biomes.add(new Ground(side.getRGB(x, y), "Abandoned Town", "", 0, 0));
						}
					} else if ((x % 10 == 0 || x == 1) && (y == 5 || y == 45) && x != 0) {
						// System.out.println("true");
						if (y < 25) {
							Biomes.add(new Ground(side.getRGB(x, y), "Abandoned Town",
									"Abandoned", 0, 6));
						} else {
							Biomes.add(new Ground(side.getRGB(x, y), "Abandoned Town",
									"Abandoned", 0, 5));
						}
						homes++;

					} else {
						Biomes.add(new Ground(side.getRGB(x, y), "Abandoned Town", "", 0, 0));
					}
				}
			}
		}
		// System.out.println(homes);
		transition();

	}

	private void greenBiome() {
		Random random = new Random();
		int length = random.nextInt(100) * 2;
		Perlin p = new Perlin(length, currentBiome);
		BufferedImage image = p.image;

		for (int x = 0; x < length; x++) {
			for (int y = 4; y != 3; y++) {
				if (y >= 50) {
					y = 0;
				}
				if (random.nextInt(5000) == 0) {
					river("Green");
				} else if (random.nextInt(7000) == 0) {
					for (int i = y; Biomes.size() % 50 != 1; i++) {
						if (i >= 50)
							i = 0;
						biomeBlock("Green", image.getRGB(x, i)); // TODO TEST
					}

					town(random.nextInt(100) + 50);
				}
				// else {
				// float col = 0;
				// while (col < 0.5) {
				// col = random.nextFloat();
				// }
				int col = image.getRGB(x, y);

				Color c = new Color(col);

				// System.out.println(0x0000ff + 0x0000ff == 0x00ff00);
				if (random.nextInt(1000) == 100) {
					// c.getRGB()
					Biomes.add(new Ground(c.getRGB(), "Green", "Tree", 0, 0));
					// } else if (random.nextInt(2000) == 100) {
					// // c.getRGB()
					// Biomes.add(new Ground(c.getRGB(), "Green", "Bank", 0,
					// 1));
					//
				} else {
					Biomes.add(new Ground(c.getRGB(), "Green", "", 0, 0));
				}
				// }
			}
			Loop.size++;
			;

			if (Loop.size > 60000) {
				endOfWorld();
				return;
			}
		}
	}

	private void river(String Biome) {
		Random random = new Random();
		Perlin p = new Perlin(3, "Desert");
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 50; y++) {
				Color c = new Color(p.image.getRGB(x, y));

				// System.out.println(0x0000ff + 0x0000ff == 0x00ff00);
				Biomes.add(new Ground(c.getRGB(), "River in " + Biome, "", -10, -10));
			}
			Loop.size += 1;
			;
			if (Loop.size > 60000) {
				endOfWorld();
				return;
			}

		}
		int i = random.nextInt(150);
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 50; y++) {
				// float col = 0;
				// while (col < 0.5) {
				// col = random.nextFloat();
				// }
				Color c = new Color(Loop.p.Water[x][y]);

				// System.out.println(0x0000ff + 0x0000ff == 0x00ff00);
				Biomes.add(new Ground(c.getRGB(), "River in " + Biome, "water", -10, -10));
			}
			Loop.size++;
			if (Loop.size > 60000) {
				endOfWorld();
				return;
			}

		}
		p = new Perlin(3, "Desert");
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 50; y++) {
				Color c = new Color(p.image.getRGB(x, y));

				// System.out.println(0x0000ff + 0x0000ff == 0x00ff00);
				Biomes.add(new Ground(c.getRGB(), "River in " + Biome, "", 0, 0));
			}
			Loop.size += 1;
			;
			if (Loop.size > 60000) {
				endOfWorld();
				return;
			}

		}
	}

	private void swamp() {
		Random random = new Random();
		int length = random.nextInt(100);
		Perlin p = new Perlin(length, currentBiome);

		for (int x = 0; x < length; x++) {
			for (int y = 0; y < 50; y++) {
				// random.nextInt(0x00ff00);
				// int col = random.nextInt(0x00ff00);
				// if(col < 0x0000ff){
				// col = (col >> 8) & 0xff;
				// }
				// float col = 1;
				// while (col > 0.5) {
				// col = random.nextFloat();
				// }
				int col = p.image.getRGB(x, y);

				Color c = new Color(col);

				if (random.nextInt(2000) == 100) {
					// c.getRGB()
					Biomes.add(new Ground(c.getRGB(), "Swamp", "Forgers", 0, 2));

				} else if (random.nextInt(2000) == 100) {
					// c.getRGB()
					Biomes.add(new Ground(c.getRGB(), "Swamp", "Prison", 0, 7));

				} else {
					Biomes.add(new Ground(c.getRGB(), "Swamp", "", 0, 0));
				}
			}
			Loop.size++;
			if (Loop.size > 60000) {
				endOfWorld();
				return;
			}

		}
	}

	private void desert() {
		Random random = new Random();
		int length = random.nextInt(100) * 2;
		Perlin p = new Perlin(length, currentBiome);
		for (int x = 0; x < length; x++) {
			for (int y = 4; y != 3; y++) {
				if (y >= 50) {
					y = 0;
				}
				// random.nextInt(0x00ff00);
				// int col = random.nextInt(0x00ff00);
				// if(col < 0x0000ff){
				// col = (col >> 8) & 0xff;
				// }
				// float col = random.nextFloat();
				// while (col < 0.5) {
				// col = random.nextFloat();
				// }

				int col = p.image.getRGB(x, y);
				Color c = new Color(col);

				// Color c = new Color(col, col, 0);

				// System.out.println(0x0000ff + 0x0000ff == 0x00ff00);
				if (random.nextInt(7000) == 100) {
					Biomes.add(new Ground(c.getRGB(), "Desert", "Forgers", 0, 2));

				} else if (random.nextInt(7000) == 100) {
					// c.getRGB()
					Biomes.add(new Ground(c.getRGB(), "Desert", "Bank", 0, 1));
				} else {
					Biomes.add(new Ground(c.getRGB(), "Desert", "", 0, 0));
				}
			}
			Loop.size++;
			if (Loop.size > 60000) {
				endOfWorld();
				return;
			}
		}

	}

	private void hell() {
		Random random = new Random();
		int length = random.nextInt(60);
		Perlin p = new Perlin(length, currentBiome);
		for (int x = 0; x < length; x++) {
			for (int y = 0; y < 50; y++) {
				// random.nextInt(0x00ff00);
				Color c = new Color(p.image.getRGB(x, y));
				// c.brighter();

				Biomes.add(new Ground(c.getRGB(), "Hell", "", 0, 0));
			}
			Loop.size++;
			if (Loop.size > 60000) {
				endOfWorld();
				return;
			}

		}
	}

	private void frost() {
		Random random = new Random();
		int length = random.nextInt(60);
		Perlin p = new Perlin(length, currentBiome);
		for (int x = 0; x < length; x++) {
			for (int y = 0; y < 50; y++) {
				// random.nextInt(0x00ff00);
				Color c = new Color(p.image.getRGB(x, y));
				// c.brighter();

				Biomes.add(new Ground(c.getRGB(), "Frost", "", 0, 0));
			}
			Loop.size++;
			if (Loop.size > 60000) {
				endOfWorld();
				return;
			}

		}
	}

	private void rock() {
		Random random = new Random();
		int length = random.nextInt(60);
		Perlin p = new Perlin(length, currentBiome);
		for (int x = 0; x < length; x++) {
			for (int y = 0; y < 50; y++) {
				// random.nextInt(0x00ff00);
				Color c = new Color(p.image.getRGB(x, y));
				// c.brighter();

				Biomes.add(new Ground(c.getRGB(), "Rock", "", 0, 0));
			}
			Loop.size++;
			if (Loop.size > 60000) {
				endOfWorld();
				return;
			}

		}
	}

	public void transition() {

		if (nextBiome == currentBiome)
			return;

		Random random = new Random();

		int half = random.nextInt(4) + 2;

		Perlin n = new Perlin(half * 2, nextBiome);
		BufferedImage image = n.image;

		Perlin c = new Perlin(half * 2, currentBiome);

		for (int i = 0; i < half; i++) {
			for (int x = 0; x < 50; x++) {
				if (random.nextInt(10) < 4) {
					biomeBlock(image.getRGB(i, x));
				} else {
					biomeBlock(c.image.getRGB(i, x));
				}
			}
			Loop.size++;
			if (Loop.size > 60000) {
				endOfWorld();
				return;
			}

		}

		for (int i = 0; i < half; i++) {
			for (int x = 0; x < 50; x++) {
				if (random.nextInt(10) < 4) {
					biomeBlock(c.image.getRGB(i + half, x));
				} else {
					biomeBlock(image.getRGB(i + half, x));
				}
			}
			Loop.size++;
			if (Loop.size > 60000) {
				endOfWorld();
				return;
			}

		}

	}

	public void biomeBlock(int col) {
		Biomes.add(new Ground(col, "Transition, " + currentBiome + " To " + nextBiome, "", 0, 0));
	}

	public void biomeBlock(String biome, int col) {

		// Random random = new Random();

		// if (biome.equalsIgnoreCase("Hell")) {
		Biomes.add(new Ground(col, biome, "", 0, 0));

		// }
		//
		// if (biome.equalsIgnoreCase("Desert")) {
		//// float col = random.nextFloat();
		//// while (col < 0.5) {
		//// col = random.nextFloat();
		//// }
		//// Color c = new Color(col, col, 0);
		// Biomes.add(new Ground(col, "Transition, " + currentBiome + " To " +
		// nextBiome, "", 0, 0));
		// }
		//
		// if (biome.equalsIgnoreCase("Green")) {
		//// float col = 0;
		//// while (col < 0.5) {
		//// col = random.nextFloat();
		//// }
		//// Color c = new Color(0, col, 0);
		// Biomes.add(new Ground(col, "Transition, " + currentBiome + " To " +
		// nextBiome, "", 0, 0));
		// }
		//
		// if (biome.equalsIgnoreCase("Swamp")) {
		//// float col = 1;
		//// while (col > 0.5) {
		//// col = random.nextFloat();
		//// }
		//// Color c = new Color(0, col, 0);
		// Biomes.add(new Ground(col, "Transition, " + currentBiome + " To " +
		// nextBiome, "", 0, 0));
		// }
	}

	public void endOfWorld() {

		Random random = new Random();

		// if(random.nextBoolean()){
		// return;
		//
		// }else{
		// return;
		//
		// }
		Perlin p = new Perlin(5, "Desert");
		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 50; y++) {
				Color c = new Color(p.image.getRGB(x, y));

				// System.out.println(0x0000ff + 0x0000ff == 0x00ff00);
				Biomes.add(new Ground(c.getRGB(), "Beach", "", 0, 0));
			}
			Loop.size += 1;
			;

		}
		// WaterAnim p = new WaterAnim(100, "Desert");

		for (int x = 0; x < 100; x++) {
			for (int y = 0; y < 50; y++) {
				Color c = new Color(Loop.p.Water[x][y]);

				// System.out.println(0x0000ff + 0x0000ff == 0x00ff00);
				Biomes.add(new Ground(c.getRGB(), "Ocien", "water", -10, -10));
			}
			Loop.size += 1;
			;

		}

	}

}
