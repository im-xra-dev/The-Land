package com.weebly.kyslol.Game.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.imageio.ImageIO;

import com.weebly.kyslol.Game.Loop;
import com.weebly.kyslol.Game.Trophy4.TrophyTracker;
import com.weebly.kyslol.Game.entities.Inputs;
import com.weebly.kyslol.Game.entities.Player;
import com.weebly.kyslol.Game.entities.JobList.Job;
import com.weebly.kyslol.Game.entities.JobList.Jobs;
import com.weebly.kyslol.Game.player.inventory.GetItems;
import com.weebly.kyslol.Game.player.inventory.Inventory;
import com.weebly.kyslol.Game.player.inventory.InventoryItems;

public class JobsGUI extends PersonGUI {
	boolean running = true;
	int id, sel = -1;
	Job job;

	public JobsGUI(int value, Job jobs) {
		super(1000, jobs);

		job = jobs;
		if (Jobs.jobList.contains(job)) {
			jobAccept = true;
		}
		if (Jobs.jobList.size() >= 5) {
			jobAccept = true;
		}

		if (job == null) {// GUI (J key)
			id = 1;
			try {
				job = Jobs.jobList.get(0);
				sel = 0;
			} catch (Exception e) {
				id = 2;// No jobs
			}

		} else {// Person
			if(Loop.job < 120){
				id = 3;
			}else{
				id = 0;
			}
		}

		while (running) {
			tick();
			render();
		}
	}

	void tick() {
		Inputs.tick();

		if (Inputs.esc) {
			running = false;
		}
		
		for(int i = 0; i < Jobs.jobList.size(); i++){
			Jobs.jobList.get(i).tick();
		}
		if(id == 0){
			job.tick();
		}
		
		while (Inputs.esc) {
			Inputs.tick();
		}

		if (id == 0) {
			if (Inputs.mb == 1) {
				if (Loop.region(350, 100, 500, 50, false) && !jobAccept) {
					Jobs.jobList.add(job);
					jobAccept = true;
					Loop.job = 0;
				}
			}
		} else if (id == 1) {
			if (Loop.region(567, 245, (120 * 0), 120, false)) {
				try {
					job = Jobs.jobList.get(0);
					sel = 0;
				} catch (Exception e) {
				}
			}
			if (Loop.region(567, 245, (120 * 1), 120, false)) {
				try {
					job = Jobs.jobList.get(1);
					sel = 1;
				} catch (Exception e) {
				}
			}
			if (Loop.region(567, 245, (120 * 2), 120, false)) {
				try {
					job = Jobs.jobList.get(2);
					sel = 2;
				} catch (Exception e) {
				}
			}
			if (Loop.region(567, 245, (120 * 3), 120, false)) {
				try {
					job = Jobs.jobList.get(3);
					sel = 3;
				} catch (Exception e) {
				}
			}
			if (Loop.region(567, 245, (120 * 4), 120, false)) {
				try {
					job = Jobs.jobList.get(4);
					sel = 4;
				} catch (Exception e) {
				}
			}
			/*
			 * ("/GUIS/Jobs/Done.png")), 50, 500, 100, 50, null);
			 * ("/GUIS/Jobs/Remove.png")), 200, 500, 100, 50, null);
			 * ("/GUIS/Jobs/Complete.png")), 350, 500, 100, 50, null);
			 * 
			 */
			if (Inputs.mb == 1) {
				if(Loop.region(350, 100, 500, 50, false)){
					if(Jobs.jobList.get(sel).getHas() >= Jobs.jobList.get(sel).getAmm()){
						Player.money += Jobs.jobList.get(sel).getMoney();
						
						GetItems.addItem("£", Jobs.jobList.get(sel).getMoney());
						Inventory i = Loop.inv;
						if(Jobs.jobList.get(sel).getType() == 1){
							i.addFood(Jobs.jobList.get(sel).getName(), -Jobs.jobList.get(sel).getAmm(), "", -10);
						}else if(Jobs.jobList.get(sel).getType() == 0){
							i.addItem(Jobs.jobList.get(sel).getName(), -Jobs.jobList.get(sel).getAmm(), "");
						}
						
						if(Jobs.jobList.get(sel).getJobId() == 0){
							TrophyTracker.people += 1;
						}else{
							TrophyTracker.church += 1;
//							System.out.println(TrophyTracker.church);
							
						}
						
						if(TrophyTracker.church == 1){
							TrophyTracker.get(11);
							
						}else if(TrophyTracker.church == 10){
							TrophyTracker.get(12);
							
						}else if(TrophyTracker.people == 5){
							TrophyTracker.get(10);
						}
						
						Jobs.jobList.remove(job);
						if(Jobs.jobList.size() == 0){
							id = 2;
							return;
						}
						if(Jobs.jobList.size()-1 < sel) sel--;
						job = Jobs.jobList.get(sel);

						while(Inputs.mb == 1){
							Inputs.tick();
						}
					}
				}
				if(Loop.region(200, 100, 500, 50, false)){
					Jobs.jobList.remove(job);
					if(Jobs.jobList.size() == 0){
						id = 2;
						return;
					}
					if(Jobs.jobList.size()-1 < sel) sel--;
					job = Jobs.jobList.get(sel);

					while(Inputs.mb == 1){
						Inputs.tick();
					}

				}
				
			}
		}
		if (Inputs.mb == 1) {
			if (Loop.region(50, 100, 500, 50, false)) {
				running = false;
			}
		}

	}

	private void render() {
		try {
			BufferStrategy bs = Loop.bs;
			if (bs == null) {
				createBufferStrategy(3);
				return;

			}

			Graphics g = bs.getDrawGraphics();

			g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Jobs/Jobs.png")), 0, 0, 800, 600, null);
			g.setColor(Color.black);
			g.setFont(new Font("Stencil", 0, 20));
			if (id != 2 && id != 3) {
				if (job.getJobId() == 1) {
					g.drawString("Church Job", 50, 100);
				} else {
					g.drawString("Persons Job", 50, 100);
				}

				g.drawString(job.getTodo(), 50, 150);
				g.drawString("I'll give you £" + job.getMoney(), 50, 200);
				g.drawString("You have: " + job.getHas() + "/" + job.getAmm(), 50, 250);

				g.drawImage(ImageIO.read(Loop.class.getResource("/Grey.png")), 567, (119 * sel), 245, 120, null);

				for (int i = 0; i < Jobs.jobList.size(); i++) {
					g.drawString(Jobs.jobList.get(i).getHas() + "/" + Jobs.jobList.get(i).getAmm(), 590,
							30 + (120 * i));
					g.drawString("£" + Jobs.jobList.get(i).getMoney(), 590, 60 + (120 * i));
					g.drawString(Jobs.jobList.get(i).getTodo(), 590, 90 + (120 * i));
				}

				if (id == 0) {
					g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Jobs/Accept.png")), 350, 500, 100, 50, null);

					if (jobAccept) {
						g.drawImage(ImageIO.read(Loop.class.getResource("/Grey.png")), 350, 500, 100, 50, null);
					}
					g.drawImage(ImageIO.read(Loop.class.getResource("/Grey.png")), 567, 0, 245, 600, null);
				} else if (id == 1) {
					g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Jobs/Remove.png")), 200, 500, 100, 50, null);
					g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Jobs/Complete.png")), 350, 500, 100, 50,
							null);
					if(!(Jobs.jobList.get(sel).getHas() >= Jobs.jobList.get(sel).getAmm())){
						g.drawImage(ImageIO.read(Loop.class.getResource("/Grey.png")), 350, 500, 100, 50,
								null);

					}
				}
			} else if(id == 2) {
				g.drawString("No Jobs", 50, 100);

			}else{
				g.drawString("You need to wait " + (120 - Loop.job) + " seconds", 50, 100);
				g.drawString("before you can get another job", 50, 130);
				for (int i = 0; i < Jobs.jobList.size(); i++) {
					g.drawString(Jobs.jobList.get(i).getHas() + "/" + Jobs.jobList.get(i).getAmm(), 590,
							30 + (120 * i));
					g.drawString("£" + Jobs.jobList.get(i).getMoney(), 590, 60 + (120 * i));
					g.drawString(Jobs.jobList.get(i).getTodo(), 590, 90 + (120 * i));
				}
				g.drawImage(ImageIO.read(Loop.class.getResource("/Grey.png")), 567, 0, 245, 600, null);

			}
			g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Jobs/Done.png")), 50, 500, 100, 50, null);

			g.dispose();
			bs.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean isRunning() {
		return running;
	}
}
