package com.weebly.kyslol.Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.weebly.kyslol.Game.GUI.Forgers;
import com.weebly.kyslol.Game.GUI.Qwertybay_com;
import com.weebly.kyslol.Game.GUI.potions.Armour;
import com.weebly.kyslol.Game.GUI.potions.Data;
import com.weebly.kyslol.Game.GUI.potions.Items;
import com.weebly.kyslol.Game.GUI.potions.Potions;
import com.weebly.kyslol.Game.GUI.potions.Weapons;
import com.weebly.kyslol.Game.Save.Load;
import com.weebly.kyslol.Game.Save.Save;
import com.weebly.kyslol.Game.Sound.Audio;
import com.weebly.kyslol.Game.Trophy4.SendTrophyData;
import com.weebly.kyslol.Game.Trophy4.TrophyTracker;
import com.weebly.kyslol.Game.entities.AI;
import com.weebly.kyslol.Game.entities.Inputs;
import com.weebly.kyslol.Game.entities.Mobs;
import com.weebly.kyslol.Game.entities.NPC;
import com.weebly.kyslol.Game.entities.Player;
import com.weebly.kyslol.Game.entities.RoboLord;
import com.weebly.kyslol.Game.entities.Satan;
import com.weebly.kyslol.Game.entities.JobList.Jobs;
import com.weebly.kyslol.Game.player.inventory.GetItems;
import com.weebly.kyslol.Game.player.inventory.Inventory;
import com.weebly.kyslol.Game.player.potions.Effects;
import com.weebly.kyslol.Game.servers.loginsignup.Account;
import com.weebly.kyslol.Game.servers.loginsignup.Login;
import com.weebly.kyslol.Game.update.Update;
import com.weebly.kyslol.Game.update.UpdateGUI;

public class Loop extends Canvas implements Runnable {
	public static String USERNAME, AUTHKEY;
	
	public static boolean easter = false;
	
	private static final long serialVersionUID = 1L;
	static boolean running = false;
	public static BufferedImage img;
	public static Thread thread;
	Render render;
	Game r = new Game(800, 600);
	public int[] pixels = new int[800 * 600];
	public static boolean inet;
	public static int shop = 0, inventory = 0, debug = 0, forgers = 0, bank = 0, pause = 0, home = 0, pc = 0, app = 0,
			type = 0, reset = 0, trophies = 0, times = 0, job = 120;
	public static String page = "wikinedia.com", url, typing = "", folder = "", browser = "browsers/Goggles";
	// public static boolean type = false;
	Data d;

	public static int nextJob = 0;

	public static boolean error = false, selling = false;

	public static int time = 0, dispTime = 0, startTime = 0, fps = 0, ups = 0;
	Mobs m = new Mobs();
	public static Inventory inv;
	public static BufferStrategy bs;
	public static boolean GoodGraph = true, audLoad = false;;
	public static int size = 0;
	public static WaterAnim p = new WaterAnim();

	public static String MMdd = new SimpleDateFormat("MMdd").format(Calendar.getInstance().getTime());
	public static String HHmmss = new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());
	public static Calendar c = Calendar.getInstance();
	public static boolean boss = false, cycle = false;
	public static ArrayList<String> wiki = new ArrayList<String>();
	public static int interest = 0;

	public static String version = "Beta V0.2.2", latest, Details; // TODO
																		// Make
																		// current
																		// version
																		// of
																		// dev
																		// game
	static boolean update = false, det = false;

	public static ArrayList<Integer> troph1 = new ArrayList<Integer>();

	protected Loop() {
		img = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
		pixels = (((DataBufferInt) img.getRaster().getDataBuffer()).getData());

		render = new Render(800, 600);
		StartRunning();

	}

	static JFrame frame = new JFrame("The Land -- "+version); // TODO Make
	// current
	// version of
	// dev game 

	public static void main(String[] args) {

		try {
			latest = Update.getLatestVersion();
			inet = true;
			// System.out.println(version.equalsIgnoreCase(latest));
			if (!version.equalsIgnoreCase(latest)) {
				update = true;

				Details = Update.getDetails();
				new UpdateGUI();
			}
		} catch (Exception e) {
			e.printStackTrace();
			inet = false;
		}

		// frame.setName("The Land");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		try {
			frame.setIconImage((ImageIO.read(Loop.class.getResource("/Ghost.png"))));
		} catch (IOException e) {
			System.out.println("ERROR LOADING: Ghost.png");
			e.printStackTrace();
			System.exit(1);
		}
		new Loop();
	}

	public void StartRunning() {
		if (running) {
			return;
		} else {
			running = true;
			thread = new Thread(this);
			thread.start();
			System.out.println("Running");
		}
	}

	@Override
	public void run() {

		frame.setVisible(false);

		if (inet) {
			Login log = new Login(true);

			while (log.running) {
			}
		}
		frame.setVisible(true);

		frame.setSize(801, 601);
		addKeyListener(new Inputs());
		addMouseListener(new Inputs());
		addMouseMotionListener(new Inputs());
		frame.add(this);

		bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);

		}
		new RenderThread(this);

		// Update.download();

		int frames = 0, upss = 0;
		double ups = 0;
		long prevtime = System.nanoTime();
		double spt = 1 / 60.0;
		int tickcount = 0;
		long currenttime, passedTime;
		requestFocus();
		frame.setSize(800, 600);
		while (running) {
			try {
				MMdd = new SimpleDateFormat("MMdd").format(Calendar.getInstance().getTime());
				HHmmss = new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());
				c.setTime(new Date());

				startTime++;
				// System.out.println(startTime);

				currenttime = System.nanoTime();
				passedTime = currenttime - prevtime;
				prevtime = currenttime;
				ups += passedTime / 1000000000.0;
				// System.out.println(ups + " " + spt);

				while (ups > spt) {
					// System.out.println(ups + " " + spt);
					if (tickcount % 60 * 3 == 0) {// 60 *3 == 3 sec. *100
						// (shades) / 2 (the %2) = 5 min cycle (2.5 min darker
						// 2.5 lighter)
						time++;
						if (time % 2 == 0) {
							cycle = true;
						}
					}
					if (tickcount % 60 == 0) {
						// System.out.println(1);
						job ++;
						
						interest++;
						reset++;

						if (reset == 60) {
							Qwertybay_com.updateSelling();
							reset = 0;
						}

						if (interest == 60) {

							Player.bank *= 1.01;
							interest = 0;
						}

						if (pause == 0) {
							for (int i = 0; i < Effects.effects.size(); i++) {
								Effects.effects.get(i).tick();
							}
						}
					}
					if (Inputs.esc) {
						// System.out.println(2);

						if (pause == 0) {
							pause = 3;
							home = 0;
							RenderThread.renderID = 4;

						} else if (pause == 2) {
							pause = 0;
							trophies = 0;
							bank = 0;
							forgers = 0;
							shop = 0;
							home = 0;
							inventory = 0;
							// if(pc == 0){
							// }
							pc = 0;
							// page = "goggles.com";
							app = 0;
							RenderThread.renderID = 0;

						} else {
							pause = 0;
							RenderThread.renderID = 0;

						}
						while (Inputs.esc) {
							Inputs.tick();
						}
					}

					ups -= spt;
					tickcount++;

					nextJob--;

					// System.out.println(frames + "FPS ");

					if (Inputs.mb != -1) {
						// System.out.println(3);

						for (int i = 0; i < Doors.doors.size(); i++) {
							if (Doors.doors.get(i).getX() == Inputs.mx && Doors.doors.get(i).getY() == Inputs.my) {
								System.out.println(Doors.doors.get(i).getDoor());
							}
						}
					}
					if (audLoad) {
						// System.out.println(4);
						music();
					}

					if (tickcount % 10 == 0) {
						//
						// System.out.println(5);

						dispTime++;
						// System.out.println(5 +":"+ 1);

						tick();
						// System.out.println(5 +":"+ 2);

						upss++;
						p.update();
						// System.out.println(5 +":"+ 3);

						for (int i = 0; i < inv.Spawns.size(); i++) {
							inv.Spawns.get(i).tick();
						}
						// System.out.println(5 +":"+ 4);

						// System.out.println(MMdd);
						if (MMdd.equals("1105") || MMdd.equals("1231") || MMdd.equals("0203")) {
							for (int i = 0; i < FireWork.fireworks.size(); i++) {
								FireWork.fireworks.get(i).tick();
							}
							Random random = new Random();

							if (random.nextInt(5) == 0) {
								new FireWork();
							}
						}
						// System.out.println(5 +":"+ 5);

						Inputs.tick();
						// System.out.println(5 +":"+ 6);

						GetItems.tick();
						// System.out.println(5 +":"+ 7);

						menu();
						// System.out.println(5 +":"+ 8);

						if (pause == 0 && home == 0) { // IF
														// PLAYING
														// NO
							Player.tick();
							m.tick();
						}
						// System.out.println(5 +":"+ 9);

					}

					if (tickcount % 60 == 0) {
						// System.out.println(6);

						// tickcount = 0;
						prevtime += 1000;
						fps = frames;
						this.ups = upss;

						System.out.println(RenderThread.fps + " " + upss);

						upss = 0;
						RenderThread.fps = 0;
						// render();
						// frames++;

					}
					if (!audLoad) {
						// render();
						// render();
						// render();
						// System.out.println(7);

						Audio.LoadAudio();
						audLoad = true;

						Qwertybay_com._Init_();

						new World(2);
						new Jobs();
						inv = new Inventory();
						TrophyTracker._Init_();
						_Init_();
					}
					// if (tickcount % 20 == 0) {
					// render();
					// frames++;
					// }
				}
			} catch (Exception e) {
				System.out.println("An Error Has Occoured.");
				e.printStackTrace();
				error = true;
			}

			// render();
			// frames++;
		}

		System.out.println("Stopping all threads");
		System.exit(1);

	}

	private void _Init_() {	
		try {
			InputStream in = Loop.class.getResourceAsStream("/txt files/Sites.txt");
			Scanner scanner = new Scanner(in);
			while(scanner.hasNextLine()){
				wiki.add(scanner.nextLine());
			}
			scanner.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void tick() {
		
		easter();
		
		if (trophies == 0) {
			times = 0;
		}

		trophies();

		// trophies =1;
		//// Player.money =100;

		// new SendTrophyData("1000_");

		// if (Inputs.N_4) {
		// Qwertybay_com._Init_();
		// }

		for (int i = 0; i < Mobs.people.size(); i++) {
			Mobs.people.get(i).tick();
		}

		if (pause >= 3) {

			if (Inputs.mb != -1) {
				if (region(350, 100, 100, 50, true)) {
					// Audio.Button.loop(2);
					new Save();
				}
				if (region(350, 100, 200, 50, true)) {
					// Audio.Button.loop(2);
					new Load();
				}
				if (region(350, 100, 300, 50, true)) {
					// Audio.Button.loop(2);
					new Account();
				}
			}
		}
		if (bank == 4) {
			if (Inputs.mb != -1) {

				if (region(0, 100, 0, 50, true)) {
					bank = 3;
					return;
				}

				if (region(100, 100, 200, 50, true)) {
					if (Player.money >= 5) {
						Player.money -= 5;
						Player.bank += 5;
					}
				}
				if (region(220, 100, 200, 50, true)) {
					if (Player.money >= 10) {
						Player.money -= 10;
						Player.bank += 10;
					}
				}
				if (region(340, 100, 200, 50, true)) {
					if (Player.money >= 20) {
						Player.money -= 20;
						Player.bank += 20;
					}
				}
				if (region(460, 100, 200, 50, true)) {
					if (Player.money >= 50) {
						Player.money -= 50;
						Player.bank += 50;
					}
				}
				if (region(580, 100, 200, 50, true)) {
					Player.bank += Player.money;
					Player.money = 0;
				}

			}

		}
		if (bank == 5) {
			if (Inputs.mb != -1) {

				if (region(0, 100, 0, 50, true)) {
					bank = 3;
					return;
				}

				if (region(100, 100, 200, 50, true)) {
					if (Player.bank >= 5) {
						Player.money += 5;
						Player.bank -= 5;
					}
				}
				if (region(220, 100, 200, 50, true)) {
					if (Player.bank >= 10) {
						Player.money += 10;
						Player.bank -= 10;
					}
				}
				if (region(340, 100, 200, 50, true)) {
					if (Player.bank >= 20) {
						Player.money += 20;
						Player.bank -= 20;
					}
				}
				if (region(460, 100, 200, 50, true)) {
					if (Player.bank >= 50) {
						Player.money += 50;
						Player.bank -= 50;
					}
				}
				if (region(580, 100, 200, 50, true)) {
					Player.money += Player.bank;
					Player.bank = 0;
				}

			}

		}
		if (bank == 3) {
			if (Inputs.mb != -1) {
				if (region(100, 150, 200, 100, true)) {
					bank = 4;
				}
				if (region(550, 150, 200, 100, true)) {
					bank = 5;
				}
			}
		}

		forgers = Forgers.tick(forgers);
		if (forgers != 0) {
			selling = true;
		} else {
			selling = false;
		}
		// if (Inputs.N_4) {
		// if (debug == 0) {
		// debug = 1;
		// } else if (debug != 0 && debug != 1) {
		// debug = 2;
		// }
		// } else if (!Inputs.N_4 && debug == 1) {
		// debug = 3;
		// pause = 2;
		// } else if (!Inputs.N_4 && debug == 2) {
		// debug = 0;
		// pause = 0;
		// }

		if (debug == 3) {

			if (Inputs.mb != -1) {

				if (Inputs.mx > 100 && Inputs.mx < 200 && Inputs.my > 100 && Inputs.my < 150) {
					inventory = 3;
					debug = 0;
				}
				if (Inputs.mx > 250 && Inputs.mx < 350 && Inputs.my > 100 && Inputs.my < 150) {
					shop = 3;
					debug = 0;
					RenderThread.renderID = 5;
				}
				if (Inputs.mx > 400 && Inputs.mx < 500 && Inputs.my > 100 && Inputs.my < 150) {
					Player.money += 100;
					// debug = 0;
				}
				if (Inputs.mx > 550 && Inputs.mx < 650 && Inputs.my > 100 && Inputs.my < 150) {
					forgers = 3;
					debug = 0;
				}
			}

		}

		if (shop == 3) {
			// System.out.println(Inputs.mb + " " + Inputs.my);

			if (Inputs.mb != -1) {

				if (Inputs.mx > 475 && Inputs.mx < 700 && Inputs.my > 100 && Inputs.my < 250) {
					while (Inputs.mb != -1) {
						Inputs.tick();
					}
					if (Inputs.mx > 475 && Inputs.mx < 700 && Inputs.my > 100 && Inputs.my < 250) {

						shop = 5;
					}
				}

				if (Inputs.mx > 100 && Inputs.mx < 325 && Inputs.my > 100 && Inputs.my < 250) {
					while (Inputs.mb != -1) {
						Inputs.tick();
					}
					if (Inputs.mx > 100 && Inputs.mx < 325 && Inputs.my > 100 && Inputs.my < 250) {

						shop = 4;
					}
				}
				if (Inputs.mx > 100 && Inputs.mx < 325 && Inputs.my > 350 && Inputs.my < 500) {
					while (Inputs.mb != -1) {
						Inputs.tick();
					}
					if (Inputs.mx > 100 && Inputs.mx < 325 && Inputs.my > 350 && Inputs.my < 500) {

						shop = 6;
					}
				}
				if (Inputs.mx > 475 && Inputs.mx < 700 && Inputs.my > 350 && Inputs.my < 500) {
					while (Inputs.mb != -1) {
						Inputs.tick();
					}
					if (Inputs.mx > 475 && Inputs.mx < 700 && Inputs.my > 350 && Inputs.my < 500) {

						shop = 7;
					}
				}
			}
		}

		if (shop == 4) {
			// System.out.println(Inputs.mb + " " + Inputs.my);

			if (Inputs.mb != -1) { // 25, 100, 150, 100
				if (Inputs.mx > 0 && Inputs.mx < 100 && Inputs.my > 0 && Inputs.my < 50) {
					while (Inputs.mb != -1) {
						Inputs.tick();
					}
					if (Inputs.mx > 0 && Inputs.mx < 100 && Inputs.my > 0 && Inputs.my < 50) {
						shop = 3;
					}
				}
				if (region(25, 150, 250, 100, true)) {
					buy("Pistol", 1, "Imma kill da evil mobz ez now! 35 damage", 35, 200, "Weapon");
				}
				if (region(225, 150, 250, 100, true)) {
					buy("Shot-Gun", 1, "I'm ready for you satan! 50 damage", 50, 500, "Weapon");
				}
				if (region(425, 150, 250, 100, true)) {
					buy("AK-47", 1, "I wish i had 47 of them! 45 damage", 45, 400, "Weapon");
				}
				if (region(625, 150, 250, 100, true)) {
					buy("Bow", 1, "'For the old fashion people who dont like guns' 20 damage", 20, 150, "Weapon");
				}

				if (region(25, 150, 400, 100, true)) {
					buy("Pistol", 100, "Ammo for a pistol", 0, 20, "Ammo");
				}
				if (region(225, 150, 400, 100, true)) {
					buy("Shot-Gun", 100, "Ammo for a shot-gun", 0, 20, "Ammo");
				}
				if (region(425, 150, 400, 100, true)) {
					buy("AK-47", 100, "Ammo for an AK-47", 0, 20, "Ammo");
				}
				if (region(625, 150, 400, 100, true)) {
					buy("Bow", 100, "Ammo For a bow", 0, 20, "Ammo");
				}

				if (Inputs.mx > 25 && Inputs.mx < 175 && Inputs.my > 100 && Inputs.my < 200) {
					while (Inputs.mb != -1) {
						Inputs.tick();
					}
					if (Inputs.mx > 25 && Inputs.mx < 175 && Inputs.my > 100 && Inputs.my < 200) {
						if (Player.money >= 20) {
							Player.money -= 20;
							Loop.inv.addWeapon("Wood Sword", 1,
									"This sword is better than nothing... I guess.  3 Damage", 3);
							GetItems.addItem("Wooden Sword", 1);
							GetItems.addItem("£", -20);

							TrophyTracker.get(5);
						} else if (Player.bank >= 20) {
							Player.bank -= 20;
							Loop.inv.addWeapon("Wood Sword", 1,
									"This sword is better than nothing... I guess.  3 Damage", 3);
							GetItems.addItem("Wooden Sword", 1);
							GetItems.addItem("£", -20);

							TrophyTracker.get(5);
						} else {
							GetItems.addItem("Not enough money", 9);
						}
					}
				}
				if (Inputs.mx > 225 && Inputs.mx < 375 && Inputs.my > 100 && Inputs.my < 200) {
					while (Inputs.mb != -1) {
						Inputs.tick();
					}
					if (Inputs.mx > 225 && Inputs.mx < 375 && Inputs.my > 100 && Inputs.my < 200) {
						if (Player.money >= 70) {
							Player.money -= 70;
							Loop.inv.addWeapon("Steel Sword", 1, "'Stainless steel'.  5 Damage", 5);
							GetItems.addItem("Steel Sword", 1);
							GetItems.addItem("£", -70);
						} else if (Player.bank >= 70) {
							Player.bank -= 70;
							Loop.inv.addWeapon("Steel Sword", 1, "'Stainless steel'.  5 Damage", 5);
							GetItems.addItem("Steel Sword", 1);
							GetItems.addItem("£", -70);

						} else {
							GetItems.addItem("Not enough money", 9);
						}
					}
				}
				if (Inputs.mx > 425 && Inputs.mx < 575 && Inputs.my > 100 && Inputs.my < 200) {
					while (Inputs.mb != -1) {
						Inputs.tick();
					}
					if (Inputs.mx > 425 && Inputs.mx < 575 && Inputs.my > 100 && Inputs.my < 200) {
						if (Player.money >= 100) {
							Player.money -= 100;
							Loop.inv.addWeapon("Iron Sword", 1, "Atleast i can kill ghosts in one hit now.  10 Damage",
									10);
							GetItems.addItem("Iron Sword", 1);
							GetItems.addItem("£", -100);

						} else if (Player.bank >= 100) {
							Player.bank -= 100;
							Loop.inv.addWeapon("Iron Sword", 1, "Atleast i can kill ghosts in one hit now.  10 Damage",
									10);
							GetItems.addItem("Iron Sword", 1);
							GetItems.addItem("£", -100);

						} else {
							GetItems.addItem("Not enough money", 9);
						}
					}
				}
				if (Inputs.mx > 625 && Inputs.mx < 775 && Inputs.my > 100 && Inputs.my < 200) {
					while (Inputs.mb != -1) {
						Inputs.tick();
					}
					if (Inputs.mx > 625 && Inputs.mx < 775 && Inputs.my > 100 && Inputs.my < 200) {
						if (Player.money >= 120) {
							Player.money -= 120;
							Loop.inv.addWeapon("Gold Sword", 1, "Forged from the kings golden crown.  15 Damage", 15);
							GetItems.addItem("Gold Sword", 1);
							GetItems.addItem("£", -120);

						} else if (Player.bank >= 120) {
							Player.bank -= 120;
							Loop.inv.addWeapon("Gold Sword", 1, "Forged from the kings golden crown.  15 Damage", 15);
							GetItems.addItem("Gold Sword", 1);
							GetItems.addItem("£", -120);

						} else {
							GetItems.addItem("Not enough money", 9);
						}
					}
				}
			}
		}

		if (shop == 5) {
			if (Inputs.mb != -1) {
				if (region(25, 150, 250, 100, true)) {
					buy("5%", 1, "Reduces damage by 5%", 5, 100, "Armour");
				}
				if (region(225, 150, 250, 100, true)) {
					buy("10%", 1, "Reduces damage by 10%", 10, 300, "Armour");

				}
				if (region(425, 150, 250, 100, true)) {
					buy("20%", 1, "Reduces damage by 20%", 20, 500, "Armour");

				}
				if (region(625, 150, 250, 100, true)) {
					buy("50%", 1, "Reduces damage by 50%", 50, 1000, "Armour");

				}
			}
			// System.out.println(Inputs.mb + " " + Inputs.my);

			if (Inputs.mb != -1) {
				if (Inputs.mx > 0 && Inputs.mx < 100 && Inputs.my > 0 && Inputs.my < 50) {
					while (Inputs.mb != -1) {
						Inputs.tick();
					}
					if (Inputs.mx > 0 && Inputs.mx < 100 && Inputs.my > 0 && Inputs.my < 50) {
						shop = 3;
						RenderThread.renderID = 5;

					}
				}
			}
		}

		if (shop == 6) {
			// System.out.println(Inputs.mb + " " + Inputs.my);

			if (Inputs.mb != -1) {
				if (region(25, 150, 250, 100, true)) {
					buy("Meat", 1, "Heals 10 hunger", 10, 10, "Food");
				}
				if (region(225, 150, 250, 100, true)) {
					buy("Veg", 1, "Heals 10 hunger", 10, 10, "Food");

				}
				if (region(425, 150, 250, 100, true)) {
					buy("Sweets", 1, "Heals 1 hunger", 1, 1, "Food");

				}

				if (region(625, 150, 250, 100, true)) {
					buy("Fruit", 1, "Heals 5 hunger", 5, 5, "Food");

				}

				if (Inputs.mx > 0 && Inputs.mx < 100 && Inputs.my > 0 && Inputs.my < 50) {
					while (Inputs.mb != -1) {
						Inputs.tick();
					}
					if (Inputs.mx > 0 && Inputs.mx < 100 && Inputs.my > 0 && Inputs.my < 50) {
						shop = 3;

					}
				}
			}
		}
		if (shop == 7) {
			if (Inputs.mb != -1) {

				if (region(25, 150, 100, 100, true)) {
					buy("Healing Potion", 1, "Restores all health instantly!", 0, 100, "Potion");

				}
				if (region(225, 150, 100, 100, true)) {
					buy("Hunger Potion", 1, "Restores all hunger for people too lazy to eat.", 0, 100, "Potion");

				}
				if (region(225, 150, 250, 100, true)) {
					buy("Cash Potion", 1, "Maby use this with the attract potion", 4, 200, "Potion");

				}
				if (region(425, 150, 100, 100, true)) {
					buy("Damage Potion", 1, "Deal more damage to all mobs.", 10, 150, "Potion");

				}
				if (region(625, 150, 100, 100, true)) {
					buy("Repel Potion", 1, "Mob repellant! Have a peaceful day!", 0, 200, "Potion");

				}
				if (region(25, 150, 250, 100, true)) {
					buy("Attract Potion", 1, "For the days you just want a bit more walking cash.", 3, 200, "Potion");

				}
				if (Inputs.mx > 0 && Inputs.mx < 100 && Inputs.my > 0 && Inputs.my < 50) {
					while (Inputs.mb != -1) {
						Inputs.tick();
					}
					if (Inputs.mx > 0 && Inputs.mx < 100 && Inputs.my > 0 && Inputs.my < 50) {
						shop = 3;
					}
				}
			}
		}

		if (pc == 3) {
			// if up for few secs
			if (startTime >= 12) {
				pc = 4;
			} else {
				startTime++;
				// System.out.println(startTime);
			}
		}

		if (pc == 4) {
			if (app == 0) { // Desktop icons
				if (Inputs.mb != -1) {
					if (region(0, 75, 0, 75, false)) {
						app = 1;
					}
					if (region(0, 75, 75, 75, false)) {
						app = 2;
					}
				}
			}
		}
		if (page.contains(".com")) {
			folder = "websites/";
		
		} else {
			folder = "search/";
		}

		if ((app == 1 || app == 2) && pc != 0) {
			// System.out.println(pc);
			if (type != 0) {
				if (typing != "") {
					if (typing.charAt(typing.length() - 1) == '¬') {
						// System.out.println("lol");
						char[] arr = new char[Loop.typing.length() - 1];
						arr = Loop.typing.toCharArray();
						Loop.typing = "";

						for (int i = 0; i < arr.length - 1; i++) {
							Loop.typing = Loop.typing + arr[i];
						}
						page = typing;

						typing = "";
						type = 0;
					}
				}
			}
		} else {
			type = 0;
		}
		if ((app == 1 || app == 2) && pc != 0) {

			if (Inputs.mb != -1) {
				if (region(760, 50, 0, 30, false)) {
					app = 0;
				}
				if (region(427, 106, 41, 31, false)) {
					page = "ping.com";
				}
				if (region(532, 106, 41, 31, false)) {
					page = "woohoo.com";
				}
				if (region(638, 106, 41, 31, false)) {
					page = "quackquackgo.com";
				}
				if (region(762, 20, 46, 23, false)) {
					page = "goggles.com";
				}
				if (region(9, 300 - 9, 44, 69 - 47, false)) {
					type = 1;
					typing = "";
					// System.out.println(9);
				}

				if (region(231, 356, 564 - 231, 394 - 356, false) && page.toLowerCase().equals("goggles.com")) {
					type = 2;
				}
				if (region(216, 532 - 216, 219, 262 - 219, false) && page.toLowerCase().equals("ping.com")) {
					type = 3;
				}
				if (region(115, 286 - 115, 112, 135 - 112, false) && page.toLowerCase().equals("woohoo.com")) {
					type = 4;
				}
				if (region(220, 539 - 220, 338, 368 - 338, false) && page.toLowerCase().equals("quackquackgo.com")) {
					type = 5;
				}

				if (page.toLowerCase().equals("goggles.com")) {
					// System.out.println("22");

					browser = "browsers/Goggles";
				}
				if (page.toLowerCase().equals("ping.com")) {
					browser = "browsers/Ping";
				}
				if (page.toLowerCase().equals("woohoo.com")) {
					browser = "browsers/Woohoo";
				}
				if (page.toLowerCase().equals("quackquackgo.com")) {
					// System.out.println("22");
					browser = "browsers/Quackquackgo";
				}

				if (page.toLowerCase().equals("shirts.ibxtoycat.com")) {
					if (region(430, 210, 400, 45, false)) {
						buy("Toycat Shirt", 1, "The legendary toycat shirt! Reduces damage by 2%", 2, 10, "Armour");
						if (!TrophyTracker.trophies[16]) {
							TrophyTracker.get(16);
						}
					}
				}
				if (page.toLowerCase().equals("bank.com")) {
					if (region(75, 240 - 75, 150, 220 - 150, false)) {
						page = "bank.com/Deposit";
					}
					if (region(560, (240 - 75), 150, 220 - 150, false)) {
						page = "bank.com/Withdraw";
					}
				}
				if (page.toLowerCase().equals("bank.com/deposit")) {
					if (region(5, 82, 85, 110, false)) {
						page = "bank.com";
					}

					if (region(60, 180 - 60, 150, 215 - 150, false)) {
						Player.money -= 5;
						Player.bank += 5;
						if (Player.money < 0) {
							Player.money += 5;
							Player.bank -= 5;
						}
					}
					if (region(60 + (134 * 1), 180 - 60, 150, 215 - 150, false)) {
						Player.money -= 10;
						Player.bank += 10;
						if (Player.money < 0) {
							Player.money += 10;
							Player.bank -= 10;
						}
					}
					if (region(60 + (134 * 2), 180 - 60, 150, 215 - 150, false)) {
						Player.money -= 20;
						Player.bank += 20;
						if (Player.money < 0) {
							Player.money += 20;
							Player.bank -= 20;
						}
					}
					if (region(60 + (134 * 3), 180 - 60, 150, 215 - 150, false)) {
						Player.money -= 50;
						Player.bank += 50;
						if (Player.money < 0) {
							Player.money += 50;
							Player.bank -= 50;
						}
					}
					if (region(60 + (134 * 4), 180 - 60, 150, 215 - 150, false)) {
						Player.bank += Player.money;
						Player.money = 0;
					}
				}

				if (page.toLowerCase().equals("bank.com/withdraw")) {
					if (region(5, 82, 85, 110, false)) {
						page = "bank.com";
					}

					if (region(60, 180 - 60, 150, 215 - 150, false)) {
						Player.money += 5;
						Player.bank -= 5;
						if (Player.money < 0) {
							Player.money -= 5;
							Player.bank += 5;
						}
					}
					if (region(60 + (134 * 1), 180 - 60, 150, 215 - 150, false)) {
						Player.money += 10;
						Player.bank -= 10;
						if (Player.money < 0) {
							Player.money -= 10;
							Player.bank += 10;
						}
					}
					if (region(60 + (134 * 2), 180 - 60, 150, 215 - 150, false)) {
						Player.money += 20;
						Player.bank -= 20;
						if (Player.money < 0) {
							Player.money -= 20;
							Player.bank += 20;
						}
					}
					if (region(60 + (134 * 3), 180 - 60, 150, 215 - 150, false)) {
						Player.bank -= 50;
						Player.money += 50;
						if (Player.money < 0) {
							Player.bank += 50;
							Player.money -= 50;
						}
					}
					if (region(60 + (134 * 4), 180 - 60, 150, 215 - 150, false)) {
						Player.money += Player.bank;
						Player.bank = 0;
					}
				}
				if (page.toLowerCase().contains("qwertybay.com")) {
					if (page.toLowerCase().equals("qwertybay.com/potions")) {
						for(int y = 0; y < 3; y++){
							for(int x = 0; x < 3; x++){
								if (region((250*x) + 35,256 - 35, 110 + (130*y), 195 - 110, false)) {
									d = Qwertybay_com.sellPot.get(x+y*3);
									page = "qwertybay.com/buy";
									return;
								}

							}
						}
						if (region(290, 505 - 290, 480, 450 - 335, false)) {
							page = "Qwertybay.com";
							return;
						}

					}

					if (page.toLowerCase().equals("qwertybay.com/items")) {
						for(int y = 0; y < 3; y++){
							for(int x = 0; x < 3; x++){
								if (region((250*x) + 35,256 - 35, 110 + (130*y), 195 - 110, false)) {
									d = Qwertybay_com.sellItems.get(x+y*3);
									page = "qwertybay.com/buy";
									return;
								}

							}
						}
						if (region(290, 505 - 290, 480, 450 - 335, false)) {
							page = "Qwertybay.com";
							return;
						}

					}

					if (page.toLowerCase().equals("qwertybay.com/weapons")) {
						for(int y = 0; y < 3; y++){
							for(int x = 0; x < 3; x++){
								if (region((250*x) + 35,256 - 35, 110 + (130*y), 195 - 110, false)) {
									d = Qwertybay_com.sellWeapons.get(x+y*3);
									page = "qwertybay.com/buy";
									return;
								}

							}
						}
						if (region(290, 505 - 290, 480, 450 - 335, false)) {
							page = "Qwertybay.com";
							return;
						}

					}

					if (page.toLowerCase().equals("qwertybay.com/armour")) {
						for(int y = 0; y < 3; y++){
							for(int x = 0; x < 3; x++){
								if (region((250*x) + 35,256 - 35, 110 + (130*y), 195 - 110, false)) {
									d = Qwertybay_com.sellArmour.get(x+y*3);
									page = "qwertybay.com/buy";
									return;
								}

							}
						}
						if (region(290, 505 - 290, 480, 450 - 335, false)) {
							page = "Qwertybay.com";
						}
					}

					if (page.toLowerCase().equals("qwertybay.com/buy")) {
						System.out.println(d.getName().equalsIgnoreCase("Sold"));
						if (region(290, 505 - 290, 480, 450 - 335, false) || d.getName().equalsIgnoreCase("Sold")) {
							if (d instanceof Potions) {
								page = "Qwertybay.com/potions";
							} else if (d instanceof Items) {
								page = "Qwertybay.com/items";
							} else if (d instanceof Weapons) {
								page = "Qwertybay.com/Weapons";
							} else if (d instanceof Armour) {
								page = "Qwertybay.com/Armour";
							} else {
								page = "Qwertybay.com";
							}
							return;
						}

						if (region(290, 505 - 290, 370, 450 - 370, false)) {
							if (d instanceof Potions) {
								buy(d.getName(), d.getMaxAmm(),
										"Second hand potion bought from qwertybay x" + d.getMaxDmg(), d.getMaxDmg(),
										d.getMaxCost(), "potion");
								page = "Qwertybay.com/potions";
							}
							if (d instanceof Items) {
								buy(d.getName(), d.getMaxAmm(), "Second hand Item bought from qwertybay", d.getMaxDmg(),
										d.getMaxCost(), "item");
								page = "Qwertybay.com/items";
							}
							if (d instanceof Weapons) {
								buy(d.getName(), d.getMaxAmm(), "Second hand " + d.getName()
										+ " bought from qwertybay.  " + d.getMaxDmg() + " Damage", d.getMaxDmg(),
										d.getMaxCost(), "weapon");
								page = "Qwertybay.com/Weapons";
							}
							if (d instanceof Armour) {
								buy(d.getName(), d.getMaxAmm(),
										"Second hand Armour bought from qwertybay. reduces damage by " + d.getMaxDmg()
												+ "%",
										d.getMaxDmg(), d.getMaxCost(), "armour");
								page = "Qwertybay.com/Armour";
							}
							d.setName("Sold");

						}

					}

					if (page.toLowerCase().equals("qwertybay.com")) {
						if (region(35, 256 - 35, 110, 195 - 110, false)) {
							page = "qwertybay.com/Items";
						}
						if (region((250) + 35, (250) + 256 - 35, 110, 195 - 110, false)) {
							page = "qwertybay.com/Potions";
						}
						if (region((2 * 250) + 35, (2 * 250) + 256 - 35, 110, 195 - 110, false)) {
							page = "qwertybay.com/Weapons";
						}
						if (region((250) + 35, (250) + 256 - 35, 110 + (130), 195 - 110 + (130), false)) {
							page = "qwertybay.com/Armour";
						}
					}

				}
			}
		}

	}

	private void easter() {
		int y = new Date().getYear() + 1900;
		int a = y % 19;
		int b = (11 * a + 5) % 30;
		int c;
		
		if(b==0||(b==1&&a>10)){
			c = b+1;
		}else{
			c = b;
		}
		
		int month;
		if(c <= 19){
			month = 3;
		}else{
			month = 2;
		}

		int day = ((50-c) % 31);

		Date d = new Date(y-1900, month, day);
		
		if(!(d.getDay() == 0)){
			d.setDate(d.getDate() + (7 - d.getDay()));
		}
		
		if(d.getMonth() == new Date().getMonth() && d.getDate() == new Date().getDate()){
			easter = true;
		}else{
			easter = false;
		}
	}

	private void trophies() {
		if (Player.money >= 100 && !TrophyTracker.trophies[1]) {
			TrophyTracker.get(1);
			// System.out.println("trophy");
		}
		if (Player.money >= 1000 && !TrophyTracker.trophies[2]) {
			TrophyTracker.get(2);
			// System.out.println("trophy");
		}
		if (Player.money >= 10000 && !TrophyTracker.trophies[3]) {
			TrophyTracker.get(3);
			// System.out.println("trophy");
		}
		if (Player.money >= 1000000 && !TrophyTracker.trophies[4]) {
			TrophyTracker.get(4);
			// System.out.println("trophy");
		}
		if (TrophyTracker.dist >= 100 && !TrophyTracker.trophies[0]) {
			TrophyTracker.get(0);
		}
		if (TrophyTracker.mobs >= 10 && !TrophyTracker.trophies[6]) {
			TrophyTracker.get(6);
		}
		if (Player.bank != 0 && !TrophyTracker.trophies[15]) {
			TrophyTracker.get(15);
		}
		if (TrophyTracker.forge >= 100 && !TrophyTracker.trophies[17]) {
			TrophyTracker.get(17);
		}

	}

	private void music() {
		if (!boss) {
			Audio.music_Main.loop(Audio.music_Main.LOOP_CONTINUOUSLY);
			Audio.music_Boss.stop();

		} else {
			if (Audio.music_Main.isActive()) {
				Audio.music_Boss.setFramePosition(0);
			}
			Audio.music_Boss.loop(Audio.music_Boss.LOOP_CONTINUOUSLY);
			;
			Audio.music_Main.stop();
			;
			// Audio.music_Boss.setFramePosition(0);

		}
	}

	public void render() {

		bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;

		}
		Graphics g = bs.getDrawGraphics();

		if (audLoad) {
			if (shop == 0) {

				if (pause == 0) {
					r.render();
				}

				// GoodGraph = true;
				//// if(!GoodGraph){
				// for (int x = 0; x < 800; x++) {
				// for (int y = 0; y < 600; y++) {
				// img.setRGB(x, y, r.pixels[x + y * 800]);
				// }
				// }
				//// System.out.println(img.getRGB(100, 10));
				//
				//
				// for (int x = 0; x < 800; x++) {
				// for (int y = 0; y < 600; y++) {
				//
				//// if(pixels[x+y*800] == 0x00ffffff){
				////// Color c = new Color(0, 0, 0, 0);
				////// img.isAlphaPremultiplied();
				//// img.setRGB(x, y, 0x00ffffff);
				//// }else{
				// img.setRGB(x, y, pixels[x+y*800]);
				//// System.out.println(pixels[x+y*800]);
				//
				//// }
				//
				// }
				// }

				g.drawImage(img, 0, 0, 800, 600, null);
				// System.out.println(img.getRGB(100, 10));

				// }else{
				//
				// for (int x = 0; x < 800; x++) {
				// for (int y = 0; y < 600; y++) {
				//
				// img.setRGB(x, y, pixels[x+y*800]);
				// }
				// }
				// g.drawImage(img, 0, 0, 800, 600, null);
				//
				// }

				g.setColor(Color.WHITE);

				g.drawString("POS: " + Player.x, 0, 150);
				g.drawString("Biome: " + Player.biome, 0, 140);
				g.drawString("FPS: " + fps, 0, 130);

				g.setFont(new Font("Cambria", 0, 20));
				g.drawString("© Lavatheif -- Beta V0.2 - DEV", 500, 550);
				// // TODO
				// Make
				// current
				// version
				// of
				// dev
				// game

				// GAME STUFF

				g.setColor(new Color(0x000000));
				g.fillRect(0, 0, 160, 90);

				// g.fillRect(250, 0, 320, GetItems.newItems.size() * 30);

				g.setColor(new Color(0x0000ff));

				g.setFont(new Font("Stencil", 0, 20));
				g.drawString("Health: " + Player.health + "%", 0, 20);
				g.drawString("Hunger: " + Player.hunger + "%", 0, 40);
				g.drawString("Ammo: " + Player.ammo + "", 0, 80);
				if (Player.money < 1000000) {
					g.drawString("Money: £" + Player.money, 0, 60);
				} else {
					g.drawString("Money: £" + Math.floor(Player.money / 100000) / 10 + "M", 0, 60);
				}

				for (int i = 0; i < Effects.effects.size(); i++) {
					g.setColor(new Color(0xffffff));
					g.setFont(new Font("Stencil", 0, 15));
					g.drawString(
							Effects.effects.get(i).getType() + ": "
									+ (Effects.effects.get(i).getDur() - Effects.effects.get(i).getTime()),
							630, i * 15 + 15);

				}
				// for (int i = 0; i < GetItems.newItems.size(); i++) {
				// g.drawString(GetItems.newItems.get(i).getName(), 260, i * 30
				// +
				// 23);
				// }

				// System.out.println(r);
				if (Inputs.m || dispTime < 50) {
					if (Inputs.m)
						dispTime = 0;
					g.setColor(new Color(0x191919));
					g.fillRect(0, 350, 350, 250);
					g.setColor(new Color(0x0000ff));

					g.drawString("m -- Opens this help menu.", 0, 370);
					g.drawString("k -- Opens shop.", 0, 390);
					g.drawString("h -- Go home.", 0, 410);
					// g.drawString("4 -- Opens Debug Inventory.", 0, 570);t --
					// Opens Trophy Interface.
					g.drawString("t -- Opens Trophy Interface.", 0, 430);

					g.drawString("I -- Opens Inventory.", 0, 450);
					g.drawString("ESC -- Pause/ exit menu.", 0, 470);
					g.drawString("Click on doors to go inside.", 0, 490);

				}
			}

			if (MMdd.equals("1105") || MMdd.equals("1231") || MMdd.equals("0203")) {
				for (int i = 0; i < FireWork.fireworks.size(); i++) {
					g.setColor(new Color(FireWork.fireworks.get(i).getCol()));
					g.fillRect(FireWork.fireworks.get(i).getX(), FireWork.fireworks.get(i).getY(), 1, 3);
				}
			}

			if (pause >= 3) {
				try {
					g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Pause/Pause.png")), 0, 0, 800, 600, null);
					g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Pause/Menu Icons/Save.png")), 350, 100, 100,
							50, null);
					g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Pause/Menu Icons/Load.png")), 350, 200, 100,
							50, null);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bank == 4) {

				try {
					g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Bank/Bank.png")), 0, 0, 800, 600, null);
					g.drawImage(ImageIO.read(Loop.class.getResource("/back.png")), 0, 0, 100, 50, null);

					g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Bank/values/£5.png")), 100, 200, 100, 50,
							null);
					g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Bank/values/£10.png")), 220, 200, 100, 50,
							null);
					g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Bank/values/£20.png")), 340, 200, 100, 50,
							null);
					g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Bank/values/£50.png")), 460, 200, 100, 50,
							null);
					g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Bank/values/all.png")), 580, 200, 100, 50,
							null);

					g.setFont(new Font("Stencil", 0, 40));
					g.setColor(new Color(0x000000));

					g.drawString("Bank: £" + Player.bank, 280, 500);
					g.drawString("Person: £" + Player.money, 250, 550);

				} catch (IOException e) {
					System.out.println("ERROR LOADING Bank images");
					e.printStackTrace();
				}

				// if (Inputs.mb != -1) {
				//
				// if (region(0, 100, 0, 50)) {
				// bank = 3;
				// return;
				// }
				//
				// if (region(100, 100, 200, 50)) {
				// if (Player.money >= 5) {
				// Player.money -= 5;
				// Player.bank += 5;
				// }
				// }
				// if (region(220, 100, 200, 50)) {
				// if (Player.money >= 10) {
				// Player.money -= 10;
				// Player.bank += 10;
				// }
				// }
				// if (region(340, 100, 200, 50)) {
				// if (Player.money >= 20) {
				// Player.money -= 20;
				// Player.bank += 20;
				// }
				// }
				// if (region(460, 100, 200, 50)) {
				// if (Player.money >= 50) {
				// Player.money -= 50;
				// Player.bank += 50;
				// }
				// }
				// if (region(580, 100, 200, 50)) {
				// Player.bank += Player.money;
				// Player.money = 0;
				// }
				//
				// }

			}
			// trophies = 0;

			if (trophies != 0) {
				// System.out.println(times);
				try {
					g.setFont(new Font("Cambri", 0, 12));
					g.setColor(Color.WHITE);
					g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/trophies/Screen " + trophies + ".png")), 0,
							0, 800, 600, null);

					times++;
					if (times < 2) {
						g.drawImage(ImageIO.read(Loop.class.getResource("/Loading 2.png")), 0, 0, 800, 600, null);
					} else if (times < 4) {
						getData();
					} else {

						for (int s = 0; s < 4; s++) {
							for (int i = 0; i < 5; i++) {
								// SendTrophyData.TrophyData(i + 1+"");
								int amm = troph1.get(((s * 5) + i));

								// if(inet){
								// amm = SendTrophyData.TrophyData("The Land/" +
								// ((s*5)+i + 1) + "_");
								// }

								if (amm != -1) {
									g.drawString(amm + " People Have This!", 40 + (200 * s), 80 + (i * 120));
								} else {
									g.drawString("Cant connect to servers.", 40 + (200 * s), 80 + (i * 120));
								}
							}
						}
					}
					times++;
				} catch (Exception e) {
					System.out.println("Error loading trophies: " + trophies);
				}
			}
			if (bank == 5) {
				try {
					g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Bank/Bank.png")), 0, 0, 800, 600, null);
					g.drawImage(ImageIO.read(Loop.class.getResource("/back.png")), 0, 0, 100, 50, null);

					g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Bank/values/£5.png")), 100, 200, 100, 50,
							null);
					g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Bank/values/£10.png")), 220, 200, 100, 50,
							null);
					g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Bank/values/£20.png")), 340, 200, 100, 50,
							null);
					g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Bank/values/£50.png")), 460, 200, 100, 50,
							null);
					g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Bank/values/all.png")), 580, 200, 100, 50,
							null);

					g.setFont(new Font("Stencil", 0, 40));
					g.setColor(new Color(0x000000));

					g.drawString("Bank: £" + Player.bank, 280, 500);
					g.drawString("Person: £" + Player.money, 250, 550);

				} catch (IOException e) {
					System.out.println("ERROR LOADING Bank images");
					e.printStackTrace();
				}

				// if (Inputs.mb != -1) {
				//
				// if (region(0, 100, 0, 50)) {
				// bank = 3;
				// return;
				// }
				//
				// if (region(100, 100, 200, 50)) {
				// if (Player.bank >= 5) {
				// Player.money += 5;
				// Player.bank -= 5;
				// }
				// }
				// if (region(220, 100, 200, 50)) {
				// if (Player.bank >= 10) {
				// Player.money += 10;
				// Player.bank -= 10;
				// }
				// }
				// if (region(340, 100, 200, 50)) {
				// if (Player.bank >= 20) {
				// Player.money += 20;
				// Player.bank -= 20;
				// }
				// }
				// if (region(460, 100, 200, 50)) {
				// if (Player.bank >= 50) {
				// Player.money += 50;
				// Player.bank -= 50;
				// }
				// }
				// if (region(580, 100, 200, 50)) {
				// Player.money += Player.bank;
				// Player.bank = 0;
				// }
				//
				// }

			}
			if (bank == 3) {
				try {
					g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Bank/Bank.png")), 0, 0, 800, 600, null);
					g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Bank/Menu Icons/Deposit.png")), 100, 200,
							150, 100, null);
					g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Bank/Menu Icons/Withdraw.png")), 550, 200,
							150, 100, null);

					g.setFont(new Font("Stencil", 0, 40));
					g.setColor(new Color(0x000000));

					g.drawString("Bank: £" + Player.bank, 280, 500);
					g.drawString("Person: £" + Player.money, 250, 550);

				} catch (IOException e) {
					System.out.println("ERROR LOADING Bank images");
					e.printStackTrace();
				}
				// if (Inputs.mb != -1) {F
				// if (region(100, 150, 200, 100)) {
				// bank = 4;
				// }
				// if (region(550, 150, 200, 100)) {
				// bank = 5;
				// }
				// }
			}

			// System.out.println(shop);

			if (forgers == 3) {

				if (Inputs.k) {
					forgers = 0;
				}
				try {
					g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Forgers/Forgers.png")), 0, 0, 800, 600,
							null);
					g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Forgers/Menu Icons/BossSpawners.png")), 100,
							100, 150, 100, null);
					g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Forgers/Menu Icons/Sell.png")), 300, 100,
							150, 100, null);

				} catch (IOException e) {
					System.out.println("ERROR LOADING Forgers images");
					e.printStackTrace();
				}

				// if (Inputs.mb != -1) {
				//
				// if (region(100, 150, 100, 100)) {
				// forgers = 4;
				// return;
				//
				// }
				// }
			}
			if (forgers == 5) {
				// if (Inputs.N_2) {
				// forgers = 0;
				// }
				try {
					g.setFont(new Font("Stencil", 0, 30));
					g.setColor(Color.BLACK);

					g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Forgers/Sell/Sell.png")), 0, 0, 800, 600,
							null);
					g.drawString("x" + Forgers.qty, 310, 213);
					g.drawString("£" + Forgers.qty * Forgers.val, 565, 125);
					g.drawString("£" + Player.money, 420, 520);
					renderSellInv();

				} catch (IOException e) {
					System.out.println("ERROR LOADING Forgers images");
					e.printStackTrace();
				}
			}
			if (forgers == 4) {
				// if (Inputs.N_2) {
				// forgers = 0;
				// }
				try {
					g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Forgers/Forgers.png")), 0, 0, 800, 600,
							null);
					g.drawImage(ImageIO.read(Loop.class.getResource("/Back.png")), 0, 0, 100, 50, null);// Forgers_RoboLordSpawner.png
					g.drawImage(
							ImageIO.read(
									Loop.class.getResource("/GUIS/Forgers/Boss Spawners/Forgers_RoboLordSpawner.png")),
							100, 100, 150, 100, null);
					g.drawImage(
							ImageIO.read(
									Loop.class.getResource("/GUIS/Forgers/Boss Spawners/Forgers_SatanSpawner.png")),
							275, 100, 150, 100, null);

				} catch (IOException e) {
					System.out.println("ERROR LOADING Forgers images");
					e.printStackTrace();
				}

				// if (Inputs.mb != -1) {
				//
				// if (region(275, 150, 100, 100)) {
				// for (int i = 0; i < inv.Items.size(); i++) {
				// if (inv.Items.get(i).getName().equalsIgnoreCase("Satan
				// Spawner
				// Part")
				// && inv.Items.get(i).getAmm() >= 4) {
				// inv.addItem("Satan Spawner Part", -4, "This can apparantly
				// craft
				// a Boss spawner!");
				// GetItems.addItem("Satan Spawner", 1);
				// GetItems.addItem("Satan Spawner Part", -4);
				// inv.addSpawn("Satan Spawner", 1, "Spawns 'Satan'... use with
				// caution");
				//
				// return;
				// }
				// }
				// GetItems.addItem("Not enough items", 9);
				//
				// }
				//
				// if (region(100, 150, 100, 100)) {
				// for (int i = 0; i < inv.Items.size(); i++) {
				// if (inv.Items.get(i).getName().equalsIgnoreCase("Robo Lord
				// Spawner Part")
				// && inv.Items.get(i).getAmm() >= 4) {
				// inv.addItem("Robo Lord Spawner Part", -4, "This can
				// apparantly
				// craft a Boss spawner!");
				// GetItems.addItem("Robo Lord Spawner", 1);
				// GetItems.addItem("Robo Lord Spawner Part", -4);
				// inv.addSpawn("Robo Lord", 1, "Spawns a 'Robo Lord'... use
				// with
				// caution");
				//
				// return;
				// }
				// }
				// GetItems.addItem("Not enough items", 9);
				//
				// }
				//
				// if (region(0, 100, 0, 50)) {
				// forgers = 3;
				// return;
				//
				// }
				// }
			}

			// if (Inputs.N_4) {
			// if (debug == 0) {
			// debug = 1;
			// } else if (debug != 0 && debug != 1) {
			// debug = 2;
			// }
			// } else if (!Inputs.N_4 && debug == 1) {
			// debug = 3;
			// pause = 2;
			// } else if (!Inputs.N_4 && debug == 2) {
			// debug = 0;
			// pause = 0;
			// }

			if (debug == 3) {
				g.setColor(new Color(0x000000));
				g.fillRect(0, 0, 800, 600);
				g.setColor(new Color(0x0000ff));

				g.fillRoundRect(100, 100, 100, 50, 20, 20);
				g.fillRoundRect(250, 100, 100, 50, 20, 20);
				g.fillRoundRect(400, 100, 100, 50, 20, 20);
				g.fillRoundRect(550, 100, 100, 50, 20, 20);

				g.setFont(new Font("Cambri", 0, 10));
				g.setColor(new Color(0xffffff));

				g.drawString("Inventory", 100, 125);
				g.drawString("Shop", 250, 125);
				g.drawString("+£100", 400, 125);
				g.drawString("Forgers", 550, 125);

				// if (Inputs.mb != -1) {
				//
				// if (Inputs.mx > 100 && Inputs.mx < 200 && Inputs.my > 100 &&
				// Inputs.my < 150) {
				// inventory = 3;
				// debug = 0;
				// }
				// if (Inputs.mx > 250 && Inputs.mx < 350 && Inputs.my > 100 &&
				// Inputs.my < 150) {
				// shop = 3;
				// debug = 0;
				// }
				// if (Inputs.mx > 400 && Inputs.mx < 500 && Inputs.my > 100 &&
				// Inputs.my < 150) {
				// Player.money += 100;
				// // debug = 0;
				// }
				// if (Inputs.mx > 550 && Inputs.mx < 650 && Inputs.my > 100 &&
				// Inputs.my < 150) {
				// forgers = 3;
				// debug = 0;
				// }
				// }

			}

			if (shop == 3) {
				g.setColor(new Color(0x000000));
				g.fillRect(0, 0, 800, 600);
				g.setColor(new Color(0x0000ff));

				g.fillRoundRect(100, 100, 150 + 75, 150, 20, 20);
				g.fillRoundRect(475, 100, 150 + 75, 150, 20, 20);
				g.fillRoundRect(100, 350, 150 + 75, 150, 20, 20);
				g.fillRoundRect(475, 350, 150 + 75, 150, 20, 20);
				g.setColor(new Color(0x000000));
				g.setFont(new Font("Stencil", 0, 30));

				g.drawString("Weapons", 140, 180);
				g.drawString("Armour", 520, 180);
				g.drawString("Consumables", 110, 435);
				g.drawString("Potions", 520, 435);
				// System.out.println(Inputs.mb + " " + Inputs.my);

				// if (Inputs.mb != -1) {
				//
				// if (Inputs.mx > 475 && Inputs.mx < 700 && Inputs.my > 100 &&
				// Inputs.my < 250) {
				// while (Inputs.mb != -1) {
				// Inputs.tick();
				// }
				// if (Inputs.mx > 475 && Inputs.mx < 700 && Inputs.my > 100 &&
				// Inputs.my < 250) {
				//
				// shop = 5;
				// }
				// }
				//
				// if (Inputs.mx > 100 && Inputs.mx < 325 && Inputs.my > 100 &&
				// Inputs.my < 250) {
				// while (Inputs.mb != -1) {
				// Inputs.tick();
				// }
				// if (Inputs.mx > 100 && Inputs.mx < 325 && Inputs.my > 100 &&
				// Inputs.my < 250) {
				//
				// shop = 4;
				// }
				// }
				// if (Inputs.mx > 100 && Inputs.mx < 325 && Inputs.my > 350 &&
				// Inputs.my < 500) {
				// while (Inputs.mb != -1) {
				// Inputs.tick();
				// }
				// if (Inputs.mx > 100 && Inputs.mx < 325 && Inputs.my > 350 &&
				// Inputs.my < 500) {
				//
				// shop = 6;
				// }
				// }
				// if (Inputs.mx > 475 && Inputs.mx < 700 && Inputs.my > 350 &&
				// Inputs.my < 500) {
				// while (Inputs.mb != -1) {
				// Inputs.tick();
				// }
				// if (Inputs.mx > 475 && Inputs.mx < 700 && Inputs.my > 350 &&
				// Inputs.my < 500) {
				//
				// shop = 7;
				// }
				// }
				// }
			}

			if (shop == 4) {

				try {
					g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Shop/Weapons.png")), 0, 0, 800, 600, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
				// System.out.println(true);
				// g.setColor(new Color(0x000000));
				// g.fillRect(0, 0, 800, 600);
				// g.setColor(new Color(0x0000ff));
				//
				// g.fillRoundRect(0, 0, 100, 50, 20, 20);
				//
				// g.fillRoundRect(25, 100, 150, 100, 20, 20);
				// g.fillRoundRect(225, 100, 150, 100, 20, 20);
				// g.fillRoundRect(425, 100, 150, 100, 20, 20);
				// g.fillRoundRect(625, 100, 150, 100, 20, 20);
				//
				// g.fillRoundRect(25, 250, 150, 100, 20, 20);
				// g.fillRoundRect(225, 250, 150, 100, 20, 20);
				// g.fillRoundRect(425, 250, 150, 100, 20, 20);
				// g.fillRoundRect(625, 250, 150, 100, 20, 20);
				//
				// g.fillRoundRect(25, 400, 150, 100, 20, 20);
				// g.fillRoundRect(225, 400, 150, 100, 20, 20);
				// g.fillRoundRect(425, 400, 150, 100, 20, 20);
				// g.fillRoundRect(625, 400, 150, 100, 20, 20);
				//
				// g.setColor(new Color(0x000000));
				// g.setFont(new Font("Stencil", 0, 30));
				//
				// g.drawString("Back", 10, 35);
				// g.drawString(" Wood", 35, 135);
				// g.drawString(" Sword", 35, 160);
				// g.drawString(" Steel", 235, 135);
				// g.drawString(" Sword", 235, 160);
				// g.drawString(" Iron", 435, 135);
				// g.drawString(" Sword", 435, 160);
				// g.drawString(" Gold", 635, 135);
				// g.drawString(" Sword", 635, 160);
				// // g.drawString("Wood" , 35, 135);
				//
				// g.drawString(" Pistol", 35, 285);
				// g.drawString("Shot-Gun", 230, 285);
				// g.drawString(" AK-47", 435, 285);
				// g.drawString(" Bow", 635, 285);
				//
				// g.setFont(new Font("Stencil", 0, 20));
				//
				// g.drawString("Pistol Ammo x 100", 35, 435);
				// (or
				// // atleast fit in the
				// // area)
				// g.drawString("Shot gun Ammo x 100", 235, 435);
				// g.drawString("AK-47 Ammo x 100", 435, 435);
				// g.drawString("Arrows x 100", 635, 435);
				//
				// g.drawString("£20", 75, 180);
				// g.drawString("£70", 275, 180);
				// g.drawString("£100", 475, 180);
				// g.drawString("£120", 675, 180);
				//
				// g.drawString("£150", 75, 305);
				// g.drawString("£190", 275, 305);
				// g.drawString("Coming soon!", 435, 305);
				// g.drawString("Coming soon!", 635, 305);

				// System.out.println(Inputs.mb + " " + Inputs.my);

				// if (Inputs.mb != -1) { // 25, 100, 150, 100
				// if (Inputs.mx > 0 && Inputs.mx < 100 && Inputs.my > 0 &&
				// Inputs.my < 50) {
				// while (Inputs.mb != -1) {
				// Inputs.tick();
				// }
				// if (Inputs.mx > 0 && Inputs.mx < 100 && Inputs.my > 0 &&
				// Inputs.my < 50) {
				// shop = 3;
				// }
				// }
				// if (Inputs.mx > 25 && Inputs.mx < 175 && Inputs.my > 100 &&
				// Inputs.my < 200) {
				// while (Inputs.mb != -1) {
				// Inputs.tick();
				// }
				// if (Inputs.mx > 25 && Inputs.mx < 175 && Inputs.my > 100 &&
				// Inputs.my < 200) {
				// if (Player.money >= 20) {
				// Player.money -= 20;
				// Loop.inv.addWeapon("Wood Sword", 1,
				// "This sword is better than nothing... I guess. 3 Damage", 3);
				// GetItems.addItem("Wooden Sword", 1);
				// GetItems.addItem("£", -20);
				//
				// } else if (Player.bank >= 20) {
				// Player.bank -= 20;
				// Loop.inv.addWeapon("Wood Sword", 1,
				// "This sword is better than nothing... I guess. 3 Damage", 3);
				// GetItems.addItem("Wooden Sword", 1);
				// GetItems.addItem("£", -20);
				//
				// } else {
				// GetItems.addItem("Not enough money", 9);
				// }
				// }
				// }
				// if (Inputs.mx > 225 && Inputs.mx < 375 && Inputs.my > 100 &&
				// Inputs.my < 200) {
				// while (Inputs.mb != -1) {
				// Inputs.tick();
				// }
				// if (Inputs.mx > 225 && Inputs.mx < 375 && Inputs.my > 100 &&
				// Inputs.my < 200) {
				// if (Player.money >= 70) {
				// Player.money -= 70;
				// Loop.inv.addWeapon("Steel Sword", 1, "'Stainless steel'. 5
				// Damage", 5);
				// GetItems.addItem("Steel Sword", 1);
				// GetItems.addItem("£", -70);
				// } else if (Player.bank >= 70) {
				// Player.bank -= 70;
				// Loop.inv.addWeapon("Steel Sword", 1, "'Stainless steel'. 5
				// Damage", 5);
				// GetItems.addItem("Steel Sword", 1);
				// GetItems.addItem("£", -70);
				//
				// } else {
				// GetItems.addItem("Not enough money", 9);
				// }
				// }
				// }
				// if (Inputs.mx > 425 && Inputs.mx < 575 && Inputs.my > 100 &&
				// Inputs.my < 200) {
				// while (Inputs.mb != -1) {
				// Inputs.tick();
				// }
				// if (Inputs.mx > 425 && Inputs.mx < 575 && Inputs.my > 100 &&
				// Inputs.my < 200) {
				// if (Player.money >= 150) {
				// Player.money -= 150;
				// Loop.inv.addWeapon("Iron Sword", 1, "Atleast i can kill
				// ghosts in
				// one hit now. 10 Damage",
				// 10);
				// GetItems.addItem("Iron Sword", 1);
				// GetItems.addItem("£", -150);
				//
				// } else if (Player.bank >= 150) {
				// Player.bank -= 150;
				// Loop.inv.addWeapon("Iron Sword", 1, "Atleast i can kill
				// ghosts in
				// one hit now. 10 Damage",
				// 10);
				// GetItems.addItem("Iron Sword", 1);
				// GetItems.addItem("£", -150);
				//
				// } else {
				// GetItems.addItem("Not enough money", 9);
				// }
				// }
				// }
				// if (Inputs.mx > 625 && Inputs.mx < 775 && Inputs.my > 100 &&
				// Inputs.my < 200) {
				// while (Inputs.mb != -1) {
				// Inputs.tick();
				// }
				// if (Inputs.mx > 625 && Inputs.mx < 775 && Inputs.my > 100 &&
				// Inputs.my < 200) {
				// if (Player.money >= 200) {
				// Player.money -= 200;
				// Loop.inv.addWeapon("Gold Sword", 1, "Forged from the kings
				// golden
				// crown. 15 Damage", 15);
				// GetItems.addItem("Gold Sword", 1);
				// GetItems.addItem("£", -200);
				//
				// } else if (Player.bank >= 200) {
				// Player.bank -= 200;
				// Loop.inv.addWeapon("Gold Sword", 1, "Forged from the kings
				// golden
				// crown. 15 Damage", 15);
				// GetItems.addItem("Gold Sword", 1);
				// GetItems.addItem("£", -200);
				//
				// } else {
				// GetItems.addItem("Not enough money", 9);
				// }
				// }
				// }
				// }
			}

			if (shop == 5) {
				g.setColor(new Color(0x000000));
				g.fillRect(0, 0, 800, 600);
				g.setColor(new Color(0x0000ff));

				g.fillRoundRect(0, 0, 100, 50, 20, 20);

				g.fillRoundRect(25, 250, 150, 100, 20, 20);
				g.fillRoundRect(225, 250, 150, 100, 20, 20);
				g.fillRoundRect(425, 250, 150, 100, 20, 20);
				g.fillRoundRect(625, 250, 150, 100, 20, 20);

				g.setColor(new Color(0x000000));
				g.setFont(new Font("Stencil", 0, 30));

				g.drawString("Back", 10, 35);

				g.drawString("      5%", 35, 285);
				g.drawString("     10%", 235, 285);
				g.drawString("     20%", 435, 285);
				g.drawString("     50%", 635, 285);

				g.setFont(new Font("Stencil", 0, 25));

				g.drawString("Resistance", 27, 320);
				g.drawString("Resistance", 227, 320);
				g.drawString("Resistance", 427, 320);
				g.drawString("Resistance", 627, 320);

				g.setFont(new Font("Stencil", 0, 20));

				g.drawString("£100", 70, 345);
				g.drawString("£300", 270, 345);
				g.drawString("£500", 470, 345);
				g.drawString("£1000", 670, 345);

				// System.out.println(Inputs.mb + " " + Inputs.my);

				// if (Inputs.mb != -1) {
				// if (region(25, 150, 250, 100)) {
				// buy("5%", 1, "Reduces damage by 5%", 5, 100, "Armour");
				// }
				// if (region(225, 150, 250, 100)) {
				// buy("10%", 1, "Reduces damage by 10%", 10, 300, "Armour");
				//
				// }
				// if (region(425, 150, 250, 100)) {
				// buy("20%", 1, "Reduces damage by 20%", 20, 500, "Armour");
				//
				// }
				// if (region(625, 150, 250, 100)) {
				// buy("50%", 1, "Reduces damage by 50%", 50, 1000, "Armour");
				//
				// }
				// }
				// // System.out.println(Inputs.mb + " " + Inputs.my);
				//
				// if (Inputs.mb != -1) {
				// if (Inputs.mx > 0 && Inputs.mx < 100 && Inputs.my > 0 &&
				// Inputs.my < 50) {
				// while (Inputs.mb != -1) {
				// Inputs.tick();
				// }
				// if (Inputs.mx > 0 && Inputs.mx < 100 && Inputs.my > 0 &&
				// Inputs.my < 50) {
				// shop = 3;
				// }
				// }
				// }
			}

			if (shop == 6) {
				g.setColor(new Color(0x000000));
				g.fillRect(0, 0, 800, 600);
				g.setColor(new Color(0x0000ff));

				g.fillRoundRect(0, 0, 100, 50, 20, 20);

				g.fillRoundRect(25, 250, 150, 100, 20, 20);
				g.fillRoundRect(225, 250, 150, 100, 20, 20);
				g.fillRoundRect(425, 250, 150, 100, 20, 20);
				g.fillRoundRect(625, 250, 150, 100, 20, 20);

				g.setColor(new Color(0x000000));
				g.setFont(new Font("Stencil", 0, 30));

				g.drawString("Back", 10, 35);

				g.drawString("   Meat", 35, 285);
				g.drawString("    Veg", 235, 285);
				g.drawString(" Sweets", 435, 285);
				g.drawString("   Fruit", 635, 285);

				g.setFont(new Font("Stencil", 0, 20));

				g.drawString("£10", 80, 305);
				g.drawString("£10", 280, 305);
				g.drawString("£1", 485, 305);
				g.drawString("£5", 685, 305);

				// System.out.println(Inputs.mb + " " + Inputs.my);

				// if (Inputs.mb != -1) {
				// if (region(25, 150, 250, 100)) {
				// buy("Meat", 1, "Heals 10 hunger", 10, 10, "Food");
				// }
				// if (region(225, 150, 250, 100)) {
				// buy("Veg", 1, "Heals 10 hunger", 10, 10, "Food");
				//
				// }
				// if (region(425, 150, 250, 100)) {
				// buy("Sweets", 1, "Heals 1 hunger", 1, 1, "Food");
				//
				// }
				// if (region(625, 150, 250, 100)) {
				// buy("Fruit", 1, "Heals 5 hunger", 5, 5, "Food");
				//
				// }
				//
				// if (Inputs.mx > 0 && Inputs.mx < 100 && Inputs.my > 0 &&
				// Inputs.my < 50) {
				// while (Inputs.mb != -1) {
				// Inputs.tick();
				// }
				// if (Inputs.mx > 0 && Inputs.mx < 100 && Inputs.my > 0 &&
				// Inputs.my < 50) {
				// shop = 3;
				// }
				// }
				// }
			}
			if (shop == 7) {
				g.setColor(new Color(0x000000));
				g.fillRect(0, 0, 800, 600);
				g.setColor(new Color(0x0000ff));

				g.fillRoundRect(0, 0, 100, 50, 20, 20);

				g.fillRoundRect(25, 100, 150, 100, 20, 20);
				g.fillRoundRect(225, 100, 150, 100, 20, 20);
				g.fillRoundRect(425, 100, 150, 100, 20, 20);
				g.fillRoundRect(625, 100, 150, 100, 20, 20);

				g.fillRoundRect(25, 250, 150, 100, 20, 20);
				g.fillRoundRect(225, 250, 150, 100, 20, 20);
				g.fillRoundRect(425, 250, 150, 100, 20, 20);
				g.fillRoundRect(625, 250, 150, 100, 20, 20);

				g.fillRoundRect(25, 400, 150, 100, 20, 20);
				g.fillRoundRect(225, 400, 150, 100, 20, 20);
				g.fillRoundRect(425, 400, 150, 100, 20, 20);
				g.fillRoundRect(625, 400, 150, 100, 20, 20);

				g.setColor(new Color(0x000000));
				g.setFont(new Font("Stencil", 0, 30));

				g.drawString("Back", 10, 35);
				g.drawString(" Health", 35, 135);
				g.drawString("  Potion", 30, 160);
				g.drawString(" Hunger", 235, 135);
				g.drawString("  Potion", 230, 160);
				g.drawString(" Damage", 435, 135);
				g.drawString("  Potion", 430, 160);
				g.drawString("  Repel", 635, 135);
				g.drawString("  Potion", 630, 160);
				// g.drawString("Wood" , 35, 135);

				g.drawString("Attract", 35, 285);
				g.drawString("  Potion", 30, 310);

				g.drawString("   Cash", 235, 285);
				g.drawString("  Potion", 230, 310);

				g.setFont(new Font("Stencil", 0, 20));

				g.drawString("£100", 75, 180);
				g.drawString("£100", 275, 180);
				g.drawString("£150", 475, 180);
				g.drawString("£200", 675, 180);

				g.drawString("£200", 75, 330);
				g.drawString("£200", 275, 330);
				// g.drawString("£150", 475, 330);
				// g.drawString("£200", 675, 330);

				// g.drawString(" AK-47", 435, 285);
				// g.drawString(" Bow", 635, 285);
				//
				//
				//
				// g.drawString(" Ammo x 1", 35, 435);
				// g.drawString(" Ammo x 10", 235, 435);
				// g.drawString(" Ammo x 100", 435, 435);
				// g.drawString("Ammo x 1000", 635, 435);

				// System.out.println(Inputs.mb + " " + Inputs.my);

				// if (Inputs.mb != -1) {
				//
				// if (region(25, 150, 100, 100)) {
				// buy("Healing Potion", 1, "Restores all health", 0, 100,
				// "Potion");
				//
				// }
				// if (region(225, 150, 100, 100)) {
				// buy("Hunger Potion", 1, "Restores all hunger", 0, 100,
				// "Potion");
				//
				// }
				// if (region(225, 150, 250, 100)) {
				// buy("Cash Potion", 1, "Get double cash from all mobs", 0,
				// 200,
				// "Potion");
				//
				// }
				// if (Inputs.mx > 0 && Inputs.mx < 100 && Inputs.my > 0 &&
				// Inputs.my < 50) {
				// while (Inputs.mb != -1) {
				// Inputs.tick();
				// }
				// if (Inputs.mx > 0 && Inputs.mx < 100 && Inputs.my > 0 &&
				// Inputs.my < 50) {
				// shop = 3;
				// }
				// }
				// }
			}
			if (home == 3) {
				try {
					g.drawImage(ImageIO.read(Loop.class.getResource("/Buildings/Interfaces/Home.png")), 0, 0, 800, 600,
							null);
				} catch (IOException e) {
					System.out.println("ERROR LOADING Home.png");
					e.printStackTrace();
				}

			}

			if (home == 10) {
				if (Mobs.people.size() == 0) {
					Mobs.people.add(new NPC(245, 350));
					Mobs.people.add(new NPC(550, 350));
				}
				try {
					g.drawImage(ImageIO.read(Loop.class.getResource("/Buildings/Interfaces/10.png")), 0, 0, 800, 600,
							null);
				} catch (IOException e) {
					System.out.println("ERROR LOADING Home.png");
					e.printStackTrace();
				}

			}

			if (home == 11) {
				if (Mobs.people.size() == 0) {
					Mobs.people.add(new NPC(245, 350));
					Mobs.people.add(new NPC(550, 350));
				}

				try {
					g.drawImage(ImageIO.read(Loop.class.getResource("/Buildings/Interfaces/11.png")), 0, 0, 800, 600,
							null);
				} catch (IOException e) {
					System.out.println("ERROR LOADING Home.png");
					e.printStackTrace();
				}

			}

			if (home >= 3 || pause == 0) {
				try {
					int w = 200;
					int w2 = 300;
					int y = 200;
					int y2 = 300;

					if (800 - Inputs.mx > 300) {
						w = 800 - 300;
					} else {
						w = Inputs.mx;
						w2 = 800 - Inputs.mx;
					}
					if (600 - Inputs.my > 300) {
						y = 600 - 300;
					} else {
						y = Inputs.my;
						y2 = 600 - Inputs.my;
					}
					if (800 - Inputs.mx < 200 && 600 - Inputs.my < 200) {
						y = 600 - 300;
						w = 800 - 300;
						w2 = 300;
						y2 = 300;
					}
					g.drawImage(ImageIO.read(Loop.class.getResource("/Arms/" + Player.weapon.toLowerCase() + ".png")),
							w, y, w2, y2, null);
				} catch (Exception e2) {
					e2.printStackTrace();
					System.out.println("/Arms/" + Player.weapon.toLowerCase() + ".png");
				}
			}
			if (pc == 3) {
				try {
					g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Pc/Startup.png")), 0, 0, 800, 600, null);

					// if up for few secs
					// if (time % 30 == 0) {
					// pc = 4;
					// }

				} catch (IOException e) {
					e.printStackTrace();
				}

			}

			if (pc == 4) {
				try {
					g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Pc/Desktop.png")), 0, 0, 800, 573, null);

					// if (app == 0) { // Desktop icons
					// if (Inputs.mb != -1) {
					// if (region(0, 75, 0, 75)) {
					// app = 1;
					// }
					// if (region(0, 75, 75, 75)) {
					// app = 2;
					// }
					// }
					// }
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			if (app == 1) {
				try {
					g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Pc/ChromeGoggles.png")), 0, 0, 800, 573,
							null);
				} catch (Exception e) {
				}
			}
			if (app == 2) {
				try {
					g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Pc/WaterFox.png")), 0, 0, 800, 573, null);
				} catch (Exception e) {
				}
			}
			if (page.contains(".com")) {
				// folder = "websites/";
			} else {
				// folder = "search/";
				if (app == 1 || app == 2) {
					try {
						g.drawImage(
								ImageIO.read(Loop.class.getResource(
										"/GUIS/Pc/" + folder.toLowerCase() + browser.toLowerCase() + ".png")),
								0, 0, 800, 573, null);
					} catch (Exception e) {
						// System.out.println("s");
					}
				}
			}

			if (app == 1 || app == 2) {

				boolean Error = false;

				try {
					g.drawImage(
							ImageIO.read(Loop.class
									.getResource("/GUIS/Pc/" + folder.toLowerCase() + page.toLowerCase() + ".png")),
							0, 0, 800, 573, null);

					g.setColor(Color.BLACK);
					g.setFont(new Font("Stencil", 0, 20));

					g.drawString(page, 20, 60);
					// syso
					if (!page.contains(".com")) {
						if (Inputs.mb != -1) {
							if (region(27, 691 - 27, 171, 307 - 171, false)) {
								page = page + ".com";
							}
						}

					}
				} catch (Exception e) {
					// page = "";
					if (page.contains("/")) {
						String pge = "";

						for (int i = 0; page.charAt(i) != '/'; i++) {
							pge = pge + page.charAt(i);
						}

						try {
							g.drawImage(
									ImageIO.read(Loop.class.getResource(
											"/GUIS/Pc/" + folder.toLowerCase() + pge.toLowerCase() + ".png")),
									0, 0, 800, 573, null);

							g.drawImage(
									ImageIO.read(Loop.class
											.getResource("/GUIS/Pc/" + folder.toLowerCase() + "404 page.png")),
									0, 0, 800, 573, null);
							g.setColor(Color.BLACK);
							g.setFont(new Font("Stencil", 0, 20));

							g.drawString(page, 20, 60);

						} catch (Exception e1) {
							Error = true;
						}

					} else {
						Error = true;

					}
					// System.out.println("100");

				}
				if (Error) {
					try {
						g.drawImage(
								ImageIO.read(
										Loop.class.getResource("/GUIS/Pc/" + folder.toLowerCase() + "no results.png")),
								0, 0, 800, 573, null);

						g.setColor(Color.BLACK);
						g.setFont(new Font("Stencil", 0, 20));

						g.drawString(page, 20, 60);

					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				// System.out.println("100");

				// type = 1;
				if (type == 1) {
					// System.out.println("100");
					g.setColor(Color.white);
					g.fillRect(9, 44, 300 - 9, 69 - 47);

					g.setColor(Color.BLACK);
					g.setFont(new Font("Stencil", 0, 20));

					g.drawString(typing, 20, 60);
				}
				// 216, 532 - 216, 219, 262 - 219
				if (type == 2) {
					// g.setColor(Color.white);
					// g.fillRect(231, 340, 564 - 231, 394 - 360);

					g.setColor(Color.BLACK);
					g.setFont(new Font("Stencil", 0, 20));

					g.drawString(typing, 240, 363);
				}
				if (type == 3) {
					// g.setColor(Color.white);
					// g.fillRect(216, 219, 532 - 216, 262 - 219);

					g.setColor(Color.BLACK);
					g.setFont(new Font("Stencil", 0, 20));

					g.drawString(typing, 225, 240);
				}
				if (type == 4) {
					g.setColor(Color.BLACK);
					g.setFont(new Font("Stencil", 0, 20));

					g.drawString(typing, 120, 125);
				}
				if (type == 5) {
					g.setColor(Color.BLACK);
					g.setFont(new Font("Stencil", 0, 20));

					g.drawString(typing, 230, 345);
				}

				// if (type != 0) {
				// if (typing != "") {
				// if (typing.charAt(typing.length() - 1) == '¬') {
				// // System.out.println("lol");
				// char[] arr = new char[Loop.typing.length() - 1];
				// arr = Loop.typing.toCharArray();
				// Loop.typing = "";
				//
				// for (int i = 0; i < arr.length - 1; i++) {
				// Loop.typing = Loop.typing + arr[i];
				// }
				// page = typing;
				//
				// typing = "";
				// type = 0;
				// }
				// }
				// }

				if (page.toLowerCase().contains("bank.com")) {
					g.drawString("PERSON: £" + Player.money, 250, 317);
					g.drawString("BANK: £" + Player.bank, 250, 347);
				}

				if (page.toLowerCase().contains("qwertybay.com")) {
					g.setFont(new Font("Stencil", 0, 25));

					if (page.toLowerCase().equals("qwertybay.com")) {
					}
					if (page.toLowerCase().equals("qwertybay.com/potions")) {
						for (int j = 0; j < 3; j++) {
							for (int i = 0; i < 3; i++) {
								g.drawString(Qwertybay_com.sellPot.get(i + (j * 3)).getName(), 60 + (i * 240),
										155 + (120 * j));
							}
						}
					}
					if (page.toLowerCase().equals("qwertybay.com/items")) {
						for (int j = 0; j < 3; j++) {
							for (int i = 0; i < 3; i++) {
								g.drawString(Qwertybay_com.sellItems.get(i + (j * 3)).getName(), 60 + (i * 240),
										155 + (120 * j));
							}
						}

					}
					if (page.toLowerCase().equals("qwertybay.com/weapons")) {
						for (int j = 0; j < 3; j++) {
							for (int i = 0; i < 3; i++) {
								g.drawString(Qwertybay_com.sellWeapons.get(i + (j * 3)).getName(), 60 + (i * 240),
										155 + (120 * j));
							}
						}

					}
					if (page.toLowerCase().equals("qwertybay.com/armour")) {
						for (int j = 0; j < 3; j++) {
							for (int i = 0; i < 3; i++) {
								g.drawString(Qwertybay_com.sellArmour.get(i + (j * 3)).getName(), 60 + (i * 240),
										155 + (120 * j));
							}
						}

					}
					if (page.toLowerCase().equals("qwertybay.com/buy")) {
						g.setFont(new Font("Stencil", 0, 30));
						g.drawString(d.getName(), 54, 143);

						g.setFont(new Font("Stencil", 0, 20));
						g.drawString(d.getMaxAmm() + " x " + d.getName(), 54, 163);

						if (d instanceof Potions) {
							g.drawString("Has a x" + d.getMaxDmg() + " Strength of the effect", 54, 183);
						}
						if (d instanceof Items) {
							g.drawString("Collected by a brave warrior!", 54, 183);
						}
						if (d instanceof Armour) {
							g.drawString("Has a %" + d.getMaxDmg() + " Resistance", 54, 183);
						}
						if (d instanceof Weapons) {
							g.drawString("Deals " + d.getMaxDmg() + " Damage", 54, 183);
						}

						g.drawString("Costs £" + d.getMaxCost(), 54, 203);
						g.setFont(new Font("Stencil", 0, 20));
						g.drawString("Remember, second hand items may be better or", 54, 303);
						g.drawString("worse than how it is sold normaly ~ Qwertybay team", 54, 323);

					}
				}

			} else {
				type = 0;
			}
			// if (Inputs.mb != -1) {
			// if (region(760, 50, 0, 30)) {
			// app = 0;
			// }
			// if (region(427, 106, 41, 31)) {
			// page = "ping.com";
			// }
			// if (region(532, 106, 41, 31)) {
			// page = "woohoo.com";
			// }
			// if (region(638, 106, 41, 31)) {
			// page = "quackquackgo.com";
			// }
			// if (region(762, 20, 46, 23)) {
			// page = "goggles.com";
			// }
			// if (region(9, 300 - 9, 44, 69 - 47)) {
			// type = 1;
			// typing = "";
			// // System.out.println(9);
			// }
			//
			// if (region(231, 356, 564 - 231, 394 - 356) &&
			// page.toLowerCase().equals("goggles.com")) {
			// type = 2;
			// }
			// if (region(216, 532 - 216, 219, 262 - 219) &&
			// page.toLowerCase().equals("ping.com")) {
			// type = 3;
			// }
			// if (region(115, 286 - 115, 112, 135 - 112) &&
			// page.toLowerCase().equals("woohoo.com")) {
			// type = 4;
			// }
			// if (region(220, 539 - 220, 338, 368 - 338) &&
			// page.toLowerCase().equals("quackquackgo.com")) {
			// type = 5;
			// }
			//
			// if (page.toLowerCase().equals("goggles.com")) {
			// // System.out.println("22");
			//
			// browser = "browsers/Goggles";
			// }
			// if (page.toLowerCase().equals("ping.com")) {
			// browser = "browsers/Ping";
			// }
			// if (page.toLowerCase().equals("woohoo.com")) {
			// browser = "browsers/Woohoo";
			// }
			// if (page.toLowerCase().equals("quackquackgo.com")) {
			// // System.out.println("22");
			// browser = "browsers/Quackquackgo";
			// }

			// if (region(0, 50, 550, 50)) {
			// System.out.println("yt");
			// ytv = true;
			// url = "";
			// YouTube.addYT(url);
			// }

			// }
			// END
			if ((pause == 0 || inventory != 0) && home == 0) {
				// Mobs.Ghosts.removeAll(Mobs.Ghosts);
				AI gh;
				for (int i = Mobs.mobs.size() - 1; i >= 0; i--) {
					gh = Mobs.mobs.get(i);
					// System.out.println(Mobs.Ghosts.size() + " " + i + gh.id);
					// System.out.println(Mobs.mobs.get(i).getTex());
					// Mobs.mobs.remove(i);
					try {
						g.drawImage(ImageIO.read(Loop.class.getResource(Mobs.mobs.get(i).getTex())), 400 - gh.getSize(),
								gh.getx(), gh.getSize() * 2, gh.getSize() * 2, null);
					} catch (IOException e) {
						System.out.println("ERROR LOADING " + Mobs.mobs.get(i).getTex() + "!");
						e.printStackTrace();
						System.exit(1);
					}
				}

				Satan sa;
				for (int i = Mobs.Satan.size() - 1; i >= 0; i--) {
					sa = Mobs.Satan.get(i);
					// System.out.println(Mobs.Ghosts.size() + " " + i + gh.id);

					try {
						g.drawImage(ImageIO.read(Loop.class.getResource("/Entities/Mobs/Enemies/Satan.png")),
								400 - sa.getSize(), sa.getx() - 100, sa.getSize() * 2, sa.getSize() * 2, null);
					} catch (IOException e) {
						System.out.println("ERROR LOADING Satan.png!");
						e.printStackTrace();
						System.exit(1);
					}
				}

				RoboLord ro;
				for (int i = Mobs.RoboLord.size() - 1; i >= 0; i--) {
					ro = Mobs.RoboLord.get(i);
					// System.out.println(Mobs.Ghosts.size() + " " + i + gh.id);

					try {
						g.drawImage(ImageIO.read(Loop.class.getResource("/Entities/Mobs/Enemies/RoboLord.png")),
								400 - ro.getSize(), ro.getx() - 100, ro.getSize() * 2, ro.getSize() * 2, null);
					} catch (IOException e) {
						System.out.println("ERROR LOADING RoboLord.png!");
						e.printStackTrace();
						System.exit(1);
					}
				}
				// update = true;
				// System.out.println(update);
			}

			NPC n;
			for (int i = Mobs.people.size() - 1; i >= 0; i--) {
				n = Mobs.people.get(i);
				// System.out.println(Mobs.Ghosts.size() + " " + i + gh.id);
				// System.out.println(n.getTex());
				// Mobs.mobs.remove(i);
				try {
					g.drawImage(ImageIO.read(Loop.class.getResource(n.getTex())), n.getx() - (n.getSize() / 2),
							n.gety() - (n.getSize() / 2), n.getSize(), n.getSize(), null);
				} catch (IOException e) {
					System.out.println("ERROR LOADING " + Mobs.people.get(i).getTex() + "!");
					e.printStackTrace();
					System.exit(1);
				}
			}

			if (inventory == 3) {
				// inventory ++;
				DisplayInventory(0);
			}

			g.setColor(new Color(0x000000));
			g.fillRect(250, 0, 320, GetItems.newItems.size() * 30);
			g.setColor(new Color(0x0000ff));
			g.setFont(new Font("Stencil", 0, 20));
			for (int i = 0; i < GetItems.newItems.size(); i++) {
				g.drawString(GetItems.newItems.get(i).getName(), 260, i * 30 + 23);
			}
		}
		if (!audLoad) {
			try {
				g.drawImage(ImageIO.read(Loop.class.getResource("/Loading.png")), 0, 0, 800, 600, null);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		g.dispose();
		// g.im
		bs.show();

	}

	public void getData() {
		inet = true;

		troph1.removeAll(troph1);

		for (int s = 0; s < 4; s++) {
			for (int i = 0; i < 5; i++) {
				int amm = -1;
				if (inet) {
					amm = SendTrophyData.TrophyData("" + ((s * 5) + i + 1) + "_");
				}
				troph1.add(amm);

			}
		}

	}

	public void renderSellInv() {
		bs = this.getBufferStrategy();

		Graphics g = bs.getDrawGraphics();

		for (int i = 0; i < inv.Items.size(); i++) {
			inv.Items.get(i).pos();
			renderInvItems(inv.Items.get(i));
		}
		for (int i = 0; i < inv.Items.size(); i++) {
			inv.Items.get(i).pos();
			renderInvItemsText(inv.Items.get(i));
		}
		for (int i = 0; i < inv.Items.size(); i++) {
			if (inv.Items.get(i).getSelected()) {
				Forgers.item = inv.Items.get(i);
				return;
			}
		}
		if (inv.Items.size() != 0) {
			Forgers.item = inv.Items.get(0);
			inv.Items.get(0).setSelected(true);
		} else {
			forgers = 3;
		}

	}

	private void buy(String name, int amm, String desc, int dam, int cost, String type) {
		// Player.money +=10000;
		if (Player.bank >= cost) {
			if (type.equalsIgnoreCase("Ammo")) {
				GetItems.addItem(name + " Ammo", amm);
			} else {
				GetItems.addItem(name, amm);
			}
			GetItems.addItem("£", -cost);
			Player.bank -= cost;
			if (type.equalsIgnoreCase("Food")) {
				inv.addFood(name, amm, desc, dam);
			}
			if (type.equalsIgnoreCase("Item")) {
				inv.addItem(name, amm, desc);
			}
			if (type.equalsIgnoreCase("Weapon")) {
				inv.addWeapon(name, amm, desc, dam);
				if (name.equalsIgnoreCase("Pistol")) {
					TrophyTracker.get(8);
				}
			}
			if (type.equalsIgnoreCase("Armour")) {
				inv.addArmour(name, amm, desc, dam);
				TrophyTracker.get(7);

			}
			if (type.equalsIgnoreCase("Ammo")) {
				inv.addAmmo(name, amm, desc);

			}
			if (type.equalsIgnoreCase("Potion")) {
				inv.addPotion(name, amm, desc, dam);
			}
			if (type.equalsIgnoreCase("Spawner")) {
				inv.addSpawn(name, amm, desc);
			}

		} else if (Player.money >= cost) {
			if (type.equalsIgnoreCase("Ammo")) {
				GetItems.addItem(name + " Ammo", amm);
			} else {
				GetItems.addItem(name, amm);
			}
			GetItems.addItem("£", -cost);
			Player.money -= cost;
			if (type.equalsIgnoreCase("Food")) {
				inv.addFood(name, amm, desc, dam);
			}
			if (type.equalsIgnoreCase("Item")) {
				inv.addItem(name, amm, desc);
			}
			if (type.equalsIgnoreCase("Weapon")) {
				inv.addWeapon(name, amm, desc, dam);
				if (name.equalsIgnoreCase("Pistol")) {
					TrophyTracker.get(8);
				}
			}
			if (type.equalsIgnoreCase("Armour")) {
				inv.addArmour(name, amm, desc, dam);
				TrophyTracker.get(7);

			}
			if (type.equalsIgnoreCase("Ammo")) {
				inv.addAmmo(name, amm, desc);

			}
			if (type.equalsIgnoreCase("Potion")) {
				inv.addPotion(name, amm, desc, dam);
			}
			if (type.equalsIgnoreCase("Spawner")) {
				inv.addSpawn(name, amm, desc);
			}

		} else {
			GetItems.addItem("Not enough money", -10);
		}

	}

	public void DisplayInventory(int scroll) {
		renderInv();
	}

	public void renderInv() {
		// bs = this.getBufferStrategy();
		// if (bs == null) {
		// createBufferStrategy(3);
		// return;
		//
		// }
		bs = this.getBufferStrategy();

		Graphics g = bs.getDrawGraphics();
		try {
			g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Inventory/Inv.png")), 0, 0, 800, 600, null);
		} catch (IOException e) {
			System.out.println("ERROR LOADING inv.png!");
			e.printStackTrace();
			System.exit(1);
		}
		// inv.Items.removeAll(inv.Items);

		// g.dispose();
		// // g.im
		// bs.show();

		for (int i = 0; i < inv.Items.size(); i++) {
			inv.Items.get(i).pos();
			renderInvItems(inv.Items.get(i));
			inv.Items.get(i).tick();
		}

		for (int i = 0; i < inv.Weapons.size(); i++) {
			// System.out.println(1);
			inv.Weapons.get(i).pos();
			renderInvItems(inv.Weapons.get(i));
		}
		for (int i = 0; i < inv.Spawns.size(); i++) {
			inv.Spawns.get(i).pos();
			renderInvItems(inv.Spawns.get(i));
		}
		for (int i = 0; i < inv.Food.size(); i++) {
			inv.Food.get(i).pos();
			inv.Food.get(i).tick();
			renderInvItems(inv.Food.get(i));
		}
		for (int i = 0; i < inv.Potions.size(); i++) {
			inv.Potions.get(i).pos();
			renderInvItems(inv.Potions.get(i));
			inv.Potions.get(i).tick();

		}
		for (int i = 0; i < inv.Armour.size(); i++) {
			inv.Armour.get(i).pos();
			// inv.Armour.get(i).tick();
			renderInvItems(inv.Armour.get(i));
		}
		for (int i = 0; i < inv.Ammo.size(); i++) {
			inv.Ammo.get(i).pos();
			// inv.Armour.get(i).tick();
			renderInvItems(inv.Ammo.get(i));
		}

		for (int i = 0; i < inv.Ammo.size(); i++) {
			inv.Ammo.get(i).pos();
			renderInvItemsText(inv.Ammo.get(i));
		}
		for (int i = 0; i < inv.Armour.size(); i++) {
			inv.Armour.get(i).pos();
			renderInvItemsText(inv.Armour.get(i));
		}
		for (int i = 0; i < inv.Potions.size(); i++) {
			inv.Potions.get(i).pos();
			renderInvItemsText(inv.Potions.get(i));
		}

		for (int i = 0; i < inv.Food.size(); i++) {
			inv.Food.get(i).pos();
			renderInvItemsText(inv.Food.get(i));
		}
		for (int i = 0; i < inv.Spawns.size(); i++) {
			inv.Spawns.get(i).pos();
			renderInvItemsText(inv.Spawns.get(i));
		}
		for (int i = 0; i < inv.Items.size(); i++) {
			inv.Items.get(i).pos();
			renderInvItemsText(inv.Items.get(i));
		}
		for (int i = 0; i < inv.Weapons.size(); i++) {
			inv.Weapons.get(i).pos();
			renderInvItemsText(inv.Weapons.get(i));
		}

	}

	public void renderInvItems(Inventory e) {
		// bs = this.getBufferStrategy();
		// if (bs == null) {
		// createBufferStrategy(3);
		// return;
		//
		// }
		Graphics g = bs.getDrawGraphics();

		try {

			int c = (e.getColom() - 1) * 20;
			int r = 0;

			if (e.getColom() == 0) {
				c = 180;
			}
			if (c > 180) {
				c -= 9;
			}
			if (c < 0) {
				c -= 1;
			}
			if (selling) {
				c -= 262;
				r = -69;
			}
			// System.out.println(e.getColom() + " " + e.getRow());
			// if (Inputs.mb == 1 && inv.Items.contains(e) && forgers == 0) {
			// for (int i = 0; i < inv.Items.size(); i++) {
			// inv.Items.get(i).setSelected(false);
			// }
			// }
			// e.setSelected(true);
			if (e.getSelected()) {
				g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Inventory/Icons/Selected.png")), 305 + c,
						(155 + (e.getRow() * 20) + r), 20, 20, null);
			}
			g.drawImage(e.getImg(), 305 + c, (155 + (e.getRow() * 20) + r), 20, 20, null);
			// System.out.println(e.Colom + " " + e.Row);

		} catch (Exception e1) {
			System.out.println("ERROR LOADING " + e.getName() + ".png");
			System.exit(1);
		}

		// g.dispose();
		// bs.show();
	}

	public void renderInvItemsText(Inventory e) {
		Graphics g = bs.getDrawGraphics();

		int c = (e.getColom() - 1) * 20;
		int r = 0;

		if (e.getColom() == 0) {
			c = 180;
		}
		if (c > 180) {
			c -= 9;
		}
		if (selling) {
			c -= 262;
			r = -69;
		}

		if (Inputs.mx > 305 + c && Inputs.mx < 325 + c && Inputs.my > (155 + (e.getRow() * 20)) + r
				&& Inputs.my < (155 + (e.getRow() * 20) + 20) + r) {
			g.setFont(new Font("Stencil", 0, 20));
			g.setColor(new Color(0, 0, 0));

			g.drawString(e.getAmm() + " x " + e.getName(), Inputs.mx, Inputs.my);
			g.drawString(e.getDesc(), 5, 15);

			if (Inputs.mb == 1 && inv.Weapons.contains(e)) {
				for (int i = 0; i < inv.Weapons.size(); i++) {
					inv.Weapons.get(i).setSelected(false);
				}

				e.setSelected(true);
			}
			if (Inputs.mb == 1 && inv.Items.contains(e)) {
				for (int i = 0; i < inv.Items.size(); i++) {
					inv.Items.get(i).setSelected(false);
				}

				e.setSelected(true);
			}
			if (Inputs.mb == 1 && inv.Spawns.contains(e)) {
				for (int i = 0; i < inv.Spawns.size(); i++) {
					inv.Spawns.get(i).setSelected(false);
				}

				e.setSelected(true);
			}
			if (Inputs.mb == 1 && inv.Armour.contains(e)) {
				for (int i = 0; i < inv.Armour.size(); i++) {
					inv.Armour.get(i).setSelected(false);
				}

				e.setSelected(true);
			}
			if (Inputs.mb == 1 && inv.Food.contains(e)) {
				for (int i = 0; i < inv.Food.size(); i++) {
					inv.Food.get(i).setSelected(false);
				}

				e.setSelected(true);
			}
			if (Inputs.mb == 1 && inv.Potions.contains(e)) {
				for (int i = 0; i < inv.Potions.size(); i++) {
					inv.Potions.get(i).setSelected(false);
				}

				region(0, 0, 0, 0, false);

				if (Inputs.mx > 305 + c && Inputs.mx < 325 + c && Inputs.my > 155 + (e.getRow() * 20)
						&& Inputs.my < 155 + (e.getRow() * 20) + 20) {
					e.setSelected(true);
				}
			}

		}

	}

	public static boolean region(int x, int x1, int y, int y1, boolean Aud) {

		if (Inputs.mx > x && Inputs.mx < x + x1 && Inputs.my > y && Inputs.my < y + y1) {
			if (Aud) {
				Audio.Button.flush();
				Audio.Button.loop(1);
			}
			while (Inputs.mb != -1) {
				Inputs.tick();


			}
		} else if (x == 0 && x1 == 0 && y == 0 && y1 == 0) {
			while (Inputs.mb != -1) {
				Inputs.tick();
			}

		}
		if (Inputs.mx > x && Inputs.mx < x + x1 && Inputs.my > y && Inputs.my < y + y1) {
			if (Aud) {
				Audio.Button.flush();
				Audio.Button.loop(1);
			}

		}

		return Inputs.mx > x && Inputs.mx < x + x1 && Inputs.my > y && Inputs.my < y + y1;
	}

	public void menu() {
		if (pause == 0 || pc < 3) {
			if (Inputs.mb != -1) {
				if (home == 3 && region(100, 150, 350, 100, false)) {
					pc = 3;
					pause = 2;
					startTime = 0;
				}
			}
		}
		if (Inputs.j && pause < 3) {
			if(RenderThread.renderID == 0){
				RenderThread.renderID = 8;
				pause = 2;
			}else{
				RenderThread.renderID = 0;
				pause = 0;
			}
			while (Inputs.j) {
				Inputs.tick();
			}
		}

		if (Inputs.h && pause < 3) {
			if (home == 0) {
				RenderThread.renderID = 2;
				home = 3;
				// if (pause == 2) {
				pause = 2;
				// }
				bank = 0;
				forgers = 0;
				shop = 0;
				inventory = 0;

			} else {
				if (pc != 0)
					return;

				home = 0;
				pause = 0;
				RenderThread.renderID = 0;

			}
			while (Inputs.h) {
				Inputs.tick();
			}
		}

		if (pause == 0 || inventory != 0) {
			if (Inputs.I) {
				if (pc != 0)
					return;

				if (pause == 0) {
					inventory = 3;
					pause = 2;
					RenderThread.renderID = 1;
				} else {
					inventory = 0;
					pause = 0;
					RenderThread.renderID = 0;

				}
				while (Inputs.I) {
					Inputs.tick();
				}

			}
		}

		if (Inputs.t && trophies == 0) {
			if (pc != 0)
				return;

			pause = 2;
			trophies = 1;
			RenderThread.renderID = 6;
			while (Inputs.t) {
				Inputs.tick();
			}
		} else if (Inputs.t) {
			if (pc != 0)
				return;

			pause = 0;
			RenderThread.renderID = 0;
			trophies = 0;
			while (Inputs.t) {
				Inputs.tick();
			}
		}

		if (pause == 0 || shop != 0) {
			if (Inputs.k) {
				if (pc != 0)
					return;

				while (Inputs.k) {
					Inputs.tick();
				}

				if (pause == 0) {
					shop = 3;
					pause = 2;
					RenderThread.renderID = 5;

				} else {

					shop = 0;
					pause = 0;
					RenderThread.renderID = 0;

				}
			}
		}
	}

	public static void Door(String type) {
		while(Inputs.mb!=-1){
			Inputs.tick();
		}
		if (type.equalsIgnoreCase("forgers")) {
			forgers = 3;
			pause = 2;
			RenderThread.renderID = 7;
		} else if (type.equalsIgnoreCase("bank")) {
			bank = 3;
			pause = 2;
			RenderThread.renderID = 3;
		} else if (type.equalsIgnoreCase("house")) {
			home = 10;
			pause = 2;
			RenderThread.renderID = 2;
		} else if (type.equalsIgnoreCase("church")) {
			home = 11;
			pause = 2;
			RenderThread.renderID = 2;
		} else if (type.equalsIgnoreCase("home")) {
			home = 3;
			pause = 2;
			RenderThread.renderID = 2;
		} else if (type.equalsIgnoreCase("Abandoned")) {
			home = 12;
			RenderThread.renderID = 2;
			pause = 2;
		} else if (type.equalsIgnoreCase("Prison")) {
			RenderThread.renderID = 9;
			pause = 2;
		}

	}
}