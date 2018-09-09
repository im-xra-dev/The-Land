package com.weebly.kyslol.Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.weebly.kyslol.Game.GUI.Forgers;
import com.weebly.kyslol.Game.GUI.JobsGUI;
import com.weebly.kyslol.Game.GUI.Qwertybay_com;
import com.weebly.kyslol.Game.GUI.potions.Armour;
import com.weebly.kyslol.Game.GUI.potions.Items;
import com.weebly.kyslol.Game.GUI.potions.Potions;
import com.weebly.kyslol.Game.GUI.potions.Weapons;
import com.weebly.kyslol.Game.Trophy4.TrophyTracker;
import com.weebly.kyslol.Game.entities.AI;
import com.weebly.kyslol.Game.entities.Inputs;
import com.weebly.kyslol.Game.entities.Mobs;
import com.weebly.kyslol.Game.entities.NPC;
import com.weebly.kyslol.Game.entities.Player;
import com.weebly.kyslol.Game.entities.RoboLord;
import com.weebly.kyslol.Game.entities.Satan;
import com.weebly.kyslol.Game.player.inventory.GetItems;
import com.weebly.kyslol.Game.player.potions.Effects;

public class RenderThread implements Runnable {
	Thread thread;
	public static Loop l;
	Graphics g;
	Shop shop = new Shop(this);
	public static int fps = 0;
	
	public static int renderID = 0;

	public RenderThread(Loop loop) {
		l = loop;
		thread = new Thread(this);
		thread.start();
		System.out.println("Running Render Thread");

	}

	@Override
	public void run() {

		while (l.running) {
			// l.render();
			
			if(renderID != 5){
				shop.setShop(3);
			}
			
			try {

				l.bs = l.getBufferStrategy();
				// if (l.bs == null) {
				// l.createBufferStrategy(3);
				// return;
				//
				// }
				g = l.bs.getDrawGraphics();

				render();
				
				g.dispose();
				l.bs.show();
				
				while(NPC.rendering){
				}
				
			} catch (Exception e) {
				System.out.println("An Error Has Occoured.");
				e.printStackTrace();
				l.error = true;
			}
			
			fps ++;
		}
	}

	private void render() {
		if (renderID == 0) {
			l.r.render();
			g.drawImage(l.img, 0, 0, 800, 600, null);

			g.setColor(Color.WHITE);

			g.drawString("POS: " + Player.x, 0, 150);
			g.drawString("Biome: " + Player.biome, 0, 140);
//			g.drawString("FPS: " + l.fps, 0, 130);

			g.setFont(new Font("Cambria", 0, 20));
			g.drawString("© Lavatheif -- Beta V0.2.2 - DEV", 500, 550);
			// TODO Make current version of dev game

			stats();
			if (l.MMdd.equals("1105") || l.MMdd.equals("1231") || l.MMdd.equals("0203")) {
				for (int i = 0; i < FireWork.fireworks.size(); i++) {
					g.setColor(new Color(FireWork.fireworks.get(i).getCol()));
					g.fillRect(FireWork.fireworks.get(i).getX(), FireWork.fireworks.get(i).getY(), 1, 3);
				}
			}
		} else if (renderID == 1) {// inv
			g.drawImage(l.img, 0, 0, 800, 600, null);
			l.DisplayInventory(0);
		} else if (renderID == 2) {// Home
			home();
		} else if (renderID == 3) {// Bank
			g.drawImage(l.img, 0, 0, 800, 600, null);
			bank();
		} else if (renderID == 4) {// Pause
			g.drawImage(l.img, 0, 0, 800, 600, null);
			pause();
		} else if (renderID == 5) {//Shop
			shop.render();
			stats();
		} else if (renderID == 6) {//Trophies
			trophy();
		} else if (renderID == 7) {//Forgers
			g.drawImage(l.img, 0, 0, 800, 600, null);
			forgers();
			stats();

		} else if (renderID == 8) {//Jobs
			JobsGUI GUI = new JobsGUI(0, null);
			while(GUI.isRunning()){
			}
			renderID = 0;
			Loop.pause = 0;

		} else if (renderID == 9) {
			prison();
		}
		
		
		if (l.error) {
			g.setFont(new Font("cambri", 0, 15));
			g.setColor(Color.black);
			g.drawString("An error has occoured.  The game should be fine to continue, but its recomended you save and",
					165, 15);
			g.drawString("reload it.", 165, 30);
		}
	}

	private void prison() {
		try {
			g.drawImage(ImageIO.read(Loop.class.getResource("/Buildings/Interfaces/Prison.png")), 0, 0, 800, 600, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void forgers() {
		if (l.forgers == 3) {

//			if (Inputs.k) {
//				l.forgers = 0;
//			}
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
		}
		if (l.forgers == 5) {
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
				l.renderSellInv();

			} catch (IOException e) {
				System.out.println("ERROR LOADING Forgers images");
				e.printStackTrace();
			}
		}
		if (l.forgers == 4) {
			// if (Inputs.N_2) {
			// forgers = 0;
			// }
			try {
				g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Forgers/Forgers.png")), 0, 0, 800, 600,
						null);
				g.drawImage(ImageIO.read(Loop.class.getResource("/back.png")), 0, 0, 100, 50, null);// Forgers_RoboLordSpawner.png
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
		}
		
	}

	private void trophy() {
		try {
			g.setFont(new Font("Cambri", 0, 12));
			g.setColor(Color.WHITE);
			g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/trophies/Screen " + l.trophies + ".png")), 0,
					0, 800, 600, null);

			l.times++;
			if (l.times < 5) {
				g.drawImage(ImageIO.read(Loop.class.getResource("/Loading 2.png")), 0, 0, 800, 600, null);
			} else if (l.times < 6) {
				l.getData();
			} else {

				for (int s = 0; s < 4; s++) {
					for (int i = 0; i < 5; i++) {
						// SendTrophyData.TrophyData(i + 1+"");
						int amm = l.troph1.get(((s * 5) + i));

						// if(inet){
						// amm = SendTrophyData.TrophyData("The Land/" +
						// ((s*5)+i + 1) + "_");
						// }
						if(TrophyTracker.trophies[((s * 5) + i)]){
							g.setColor(Color.GREEN);
//							g.fillRect(40 + (200 * s), 0 + (i * 120), 50, 10);//(amm + " People Have This!", 40 + (200 * s), 80 + (i * 120));
						}else{
							g.setColor(Color.RED);
//							g.fillRect(40 + (200 * s), 0 + (i * 120), 50, 10);//(amm + " People Have This!", 40 + (200 * s), 80 + (i * 120));
						}

						if (amm != -1) {
							g.drawString(amm + " People Have This!", 40 + (200 * s), 80 + (i * 120));
						} else {
							g.drawString("Cant connect to servers.", 40 + (200 * s), 80 + (i * 120));
						}
					}
				}
			}
			l.times++;
		} catch (Exception e) {
			System.out.println("Error loading trophies: " + l.trophies);
		}
	}

	private void stats() {
		if(renderID != 0){
			g.setColor(new Color(0x000000));
			g.fillRect(640, 0, 160, 90);

			g.setColor(new Color(0x0000ff));

			g.setFont(new Font("Stencil", 0, 20));
			g.drawString("Health: " + Player.health + "%", 640, 20);
			g.drawString("Hunger: " + Player.hunger + "%", 640, 40);
			g.drawString("Ammo: " + Player.ammo + "", 640, 80);
			if (Player.money < 1000000) {
				g.drawString("Money: £" + Player.money, 640, 60);
			} else {
				g.drawString("Money: £" + Math.floor(Player.money / 100000) / 10 + "M", 640, 60);
			}
			int size = GetItems.newItems.size();
			if(size > 3){
				size = 3;
			}
			g.setColor(new Color(0x000000));
			g.fillRect(250, 0, 320, size * 30);
			g.setColor(new Color(0x0000ff));
			g.setFont(new Font("Stencil", 0, 20));
			int j = 0;
			for (int i = GetItems.newItems.size() - 3; i < GetItems.newItems.size(); i++) {
				if(i < 0){
					i = 0;
				}
				if(GetItems.newItems.size() == 0){
					continue;
				}

				g.drawString(GetItems.newItems.get(i).getName(), 260, j * 30 + 23);
				j++;
			}
			
			return;
		}
		g.setColor(new Color(0x000000));
		g.fillRect(0, 0, 160, 90);

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
			g.drawString(Effects.effects.get(i).getType() + ": "
					+ (Effects.effects.get(i).getDur() - Effects.effects.get(i).getTime()), 630, i * 15 + 15);

		}
		
		int size = GetItems.newItems.size();
		if(size > 3){
			size = 3;
		}
		g.setColor(new Color(0x000000));
		g.fillRect(250, 0, 320, size * 30);
		g.setColor(new Color(0x0000ff));
		g.setFont(new Font("Stencil", 0, 20));
		int j = 0;
		for (int i = GetItems.newItems.size() - 3; i < GetItems.newItems.size(); i++) {
			if(i < 0){
				i = 0;
			}
			if(GetItems.newItems.size() == 0){
				continue;
			}

			g.drawString(GetItems.newItems.get(i).getName(), 260, j * 30 + 23);
			j++;
		}

		
		if (Inputs.m || l.dispTime < 50) {
			if (Inputs.m)
				l.dispTime = 0;
			g.setColor(new Color(0x191919));
			g.fillRect(0, 350, 350, 250);
			g.setColor(new Color(0x0000ff));

			g.drawString("m -- Opens this help menu.", 0, 370);
			g.drawString("k -- Opens shop.", 0, 390);
			g.drawString("h -- Go home.", 0, 410);
			g.drawString("t -- Opens Trophy Interface.", 0, 430);
			g.drawString("j -- Opens Job Interface.", 0, 450);

			g.drawString("I -- Opens Inventory.", 0, 470);
			g.drawString("ESC -- Pause/ exit menu.", 0, 490);
			g.drawString("Click on doors to go inside.", 0, 510);

		}

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
			g.drawImage(ImageIO.read(Loop.class.getResource("/Arms/" + Player.weapon.toLowerCase() + ".png")), w, y, w2,
					y2, null);
		} catch (Exception e2) {
			e2.printStackTrace();
			System.out.println("/Arms/" + Player.weapon.toLowerCase() + ".png");
		}

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
	}

	private void pause() {
		try {
			g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Pause/Pause.png")), 0, 0, 800, 600, null);
			g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Pause/Menu Icons/Save.png")), 350, 100, 100, 50,
					null);
			g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Pause/Menu Icons/Load.png")), 350, 200, 100, 50,
					null);
			g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Pause/Menu Icons/Account.png")), 350, 300, 100, 50,
					null);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void bank() {
		if (l.bank == 3) {
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
		}else{
		try {
			g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Bank/Bank.png")), 0, 0, 800, 600, null);
			g.drawImage(ImageIO.read(Loop.class.getResource("/back.png")), 0, 0, 100, 50, null);

			g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Bank/values/£5.png")), 100, 200, 100, 50, null);
			g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Bank/values/£10.png")), 220, 200, 100, 50, null);
			g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Bank/values/£20.png")), 340, 200, 100, 50, null);
			g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Bank/values/£50.png")), 460, 200, 100, 50, null);
			g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Bank/values/all.png")), 580, 200, 100, 50, null);

			g.setFont(new Font("Stencil", 0, 40));
			g.setColor(new Color(0x000000));

			g.drawString("Bank: £" + Player.bank, 280, 500);
			g.drawString("Person: £" + Player.money, 250, 550);

		} catch (IOException e) {
			System.out.println("ERROR LOADING Bank images");
			e.printStackTrace();
		}}

	}

	private void home() {
		if (l.home == 10) {
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
		else
		if (l.home == 11) {
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
		else if (l.home == 12) {//Abandoned
			try {
				g.drawImage(ImageIO.read(Loop.class.getResource("/Buildings/Interfaces/12.png")), 0, 0, 800, 600,
						null);
			} catch (IOException e) {
				System.out.println("ERROR LOADING Home.png");
				e.printStackTrace();
			}

		}
		else{
		try {
			g.drawImage(ImageIO.read(Loop.class.getResource("/Buildings/Interfaces/Home.png")), 0, 0, 800, 600, null);
		} catch (IOException e) {
			System.out.println("ERROR LOADING Home.png");
			e.printStackTrace();
		}
		if (l.pc == 3) {
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

		if (l.pc == 4) {
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
		if (l.app == 1) {
			try {
				g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Pc/ChromeGoggles.png")), 0, 0, 800, 573, null);
			} catch (Exception e) {
			}
		}
		if (l.app == 2) {
			try {
				g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Pc/WaterFox.png")), 0, 0, 800, 573, null);
			} catch (Exception e) {
			}
		}
		if (l.page.contains(".com")) {
			// folder = "websites/";
		} else {
			// folder = "search/";
			if (l.app == 1 || l.app == 2) {
				try {
					g.drawImage(
							ImageIO.read(Loop.class.getResource(
									"/GUIS/Pc/" + l.folder.toLowerCase() + l.browser.toLowerCase() + ".png")),
							0, 0, 800, 573, null);
				} catch (Exception e) {
					// System.out.println("s");
				}
			}
		}

		if (l.app == 1 || l.app == 2) {

			boolean Error = false;

			try {
				g.drawImage(
						ImageIO.read(Loop.class
								.getResource("/GUIS/Pc/" + l.folder.toLowerCase() + l.page.toLowerCase() + ".png")),
						0, 0, 800, 573, null);

				g.setColor(Color.BLACK);
				g.setFont(new Font("Stencil", 0, 20));

				g.drawString(l.page, 20, 60);
				// syso
				if (!l.page.contains(".com")) {
					if (Inputs.mb != -1) {
						if (l.region(27, 691 - 27, 171, 307 - 171, false)) {
							l.page = l.page + ".com";
						}
					}

				}
			} catch (Exception e) {
				// page = "";
				if (l.page.contains("/")) {
					String pge = "";

					for (int i = 0; l.page.charAt(i) != '/'; i++) {
						pge = pge + l.page.charAt(i);
					}

					try {
						g.drawImage(
								ImageIO.read(Loop.class.getResource(
										"/GUIS/Pc/" + l.folder.toLowerCase() + pge.toLowerCase() + ".png")),
								0, 0, 800, 573, null);

						g.drawImage(
								ImageIO.read(
										Loop.class.getResource("/GUIS/Pc/" + l.folder.toLowerCase() + "404 page.png")),
								0, 0, 800, 573, null);
						g.setColor(Color.BLACK);
						g.setFont(new Font("Stencil", 0, 20));

						g.drawString(l.page, 20, 60);

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
									Loop.class.getResource("/GUIS/Pc/" + l.folder.toLowerCase() + "no results.png")),
							0, 0, 800, 573, null);

					g.setColor(Color.BLACK);
					g.setFont(new Font("Stencil", 0, 20));

					g.drawString(l.page, 20, 60);

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}else if(l.page.contains(".com")){
				if(l.wiki.contains(l.page.toLowerCase())){
					TrophyTracker.get(14);
				}
			}
			// System.out.println("100");

			// type = 1;
			if (l.type == 1) {
				// System.out.println("100");
				g.setColor(Color.white);
				g.fillRect(9, 44, 300 - 9, 69 - 47);

				g.setColor(Color.BLACK);
				g.setFont(new Font("Stencil", 0, 20));

				g.drawString(l.typing, 20, 60);
			}
			// 216, 532 - 216, 219, 262 - 219
			if (l.type == 2) {
				// g.setColor(Color.white);
				// g.fillRect(231, 340, 564 - 231, 394 - 360);

				g.setColor(Color.BLACK);
				g.setFont(new Font("Stencil", 0, 20));

				g.drawString(l.typing, 240, 363);
			}
			if (l.type == 3) {
				// g.setColor(Color.white);
				// g.fillRect(216, 219, 532 - 216, 262 - 219);

				g.setColor(Color.BLACK);
				g.setFont(new Font("Stencil", 0, 20));

				g.drawString(l.typing, 225, 240);
			}
			if (l.type == 4) {
				g.setColor(Color.BLACK);
				g.setFont(new Font("Stencil", 0, 20));

				g.drawString(l.typing, 120, 125);
			}
			if (l.type == 5) {
				g.setColor(Color.BLACK);
				g.setFont(new Font("Stencil", 0, 20));

				g.drawString(l.typing, 230, 345);
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

			if (l.page.toLowerCase().contains("bank.com")) {
				g.drawString("PERSON: £" + Player.money, 250, 317);
				g.drawString("BANK: £" + Player.bank, 250, 347);
			}

			if (l.page.toLowerCase().contains("qwertybay.com")) {
				g.setFont(new Font("Stencil", 0, 25));

				if (l.page.toLowerCase().equals("qwertybay.com")) {
				}
				if (l.page.toLowerCase().equals("qwertybay.com/potions")) {
					for (int j = 0; j < 3; j++) {
						for (int i = 0; i < 3; i++) {
							g.drawString(Qwertybay_com.sellPot.get(i + (j * 3)).getName(), 60 + (i * 240),
									155 + (120 * j));
						}
					}
				}
				if (l.page.toLowerCase().equals("qwertybay.com/items")) {
					for (int j = 0; j < 3; j++) {
						for (int i = 0; i < 3; i++) {
							g.drawString(Qwertybay_com.sellItems.get(i + (j * 3)).getName(), 60 + (i * 240),
									155 + (120 * j));
						}
					}

				}
				if (l.page.toLowerCase().equals("qwertybay.com/weapons")) {
					for (int j = 0; j < 3; j++) {
						for (int i = 0; i < 3; i++) {
							g.drawString(Qwertybay_com.sellWeapons.get(i + (j * 3)).getName(), 60 + (i * 240),
									155 + (120 * j));
						}
					}

				}
				if (l.page.toLowerCase().equals("qwertybay.com/armour")) {
					for (int j = 0; j < 3; j++) {
						for (int i = 0; i < 3; i++) {
							g.drawString(Qwertybay_com.sellArmour.get(i + (j * 3)).getName(), 60 + (i * 240),
									155 + (120 * j));
						}
					}

				}
				if (l.page.toLowerCase().equals("qwertybay.com/buy")) {
					g.setFont(new Font("Stencil", 0, 30));
					g.drawString(l.d.getName(), 54, 143);

					g.setFont(new Font("Stencil", 0, 20));
					g.drawString(l.d.getMaxAmm() + " x " + l.d.getName(), 54, 163);

					if (l.d instanceof Potions) {
						g.drawString("Has a x" + l.d.getMaxDmg() + " Strength of the effect", 54, 183);
					}
					if (l.d instanceof Items) {
						g.drawString("Collected by a brave warrior!", 54, 183);
					}
					if (l.d instanceof Armour) {
						g.drawString("Has a %" + l.d.getMaxDmg() + " Resistance", 54, 183);
					}
					if (l.d instanceof Weapons) {
						g.drawString("Deals " + l.d.getMaxDmg() + " Damage", 54, 183);
					}

					g.drawString("Costs £" + l.d.getMaxCost(), 54, 203);
					g.setFont(new Font("Stencil", 0, 20));
					g.drawString("Remember, second hand items may be better or", 54, 303);
					g.drawString("worse than how it is sold normaly ~ Qwertybay team", 54, 323);

				}
			}

		} else {
			l.type = 0;
		}

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
	}
}
