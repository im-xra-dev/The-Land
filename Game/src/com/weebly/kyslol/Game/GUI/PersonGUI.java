package com.weebly.kyslol.Game.GUI;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import com.weebly.kyslol.Game.Loop;
import com.weebly.kyslol.Game.RenderThread;
import com.weebly.kyslol.Game.entities.Inputs;
import com.weebly.kyslol.Game.entities.Player;
import com.weebly.kyslol.Game.entities.JobList.Job;
import com.weebly.kyslol.Game.entities.JobList.Jobs;
import com.weebly.kyslol.Game.player.inventory.InventoryItems;

public class PersonGUI extends Canvas {
	private boolean display = true;
	int type;
	int page = 0;
	boolean jobAccept = false;
	Job job;

	public PersonGUI(int value, Job job) {
		this.job = job;
		type = value;
		if (value == 1000)
			return;

		running();
	}

	void running() {
		if (job == null) {
			setJob();
		} else {
			if (Jobs.jobList.contains(job)) {
				jobAccept = true;
			}
			if (Jobs.jobList.size() >= 5) {
				jobAccept = true;
			}

		}
		while (display) {
			render();
			tick();

		}
	}

	private void setJob() {
		Random random = new Random();

		int jobId = random.nextInt(Jobs.jobs.size());
		String j = Jobs.jobs.get(jobId);
		if (Loop.home == 11) {
			jobId = random.nextInt(Jobs.churchJobs.size());
			j = Jobs.churchJobs.get(jobId);
		}

		int money = Integer.parseInt(j.split("£")[1].trim());
		money += ((int) money / 10) - (random.nextInt(((int) money / 10) * 2 + 1));
		String todo = j.split("£")[0];
		String[] arr = todo.split("~");
		todo = "";
		for (int i = 0; i < arr.length; i++) {
			todo += arr[i] + " ";
		}

		String name = arr[2];

		int amm = Integer.parseInt(arr[1]);
		System.out.println(amm + " " + ((int) amm / 10) * 2);
		amm += ((int) amm / 10) - (random.nextInt(((int) amm / 10) * 2 + 1));

		job = new Job(Loop.home - 10, money, amm, todo, name);

		if (Jobs.jobList.size() < 5)
			return;
		jobAccept = true;
	}

	private void render() {
		BufferStrategy bs = Loop.bs;
		if (bs == null) {
			createBufferStrategy(3);
			return;

		}
		Graphics g = bs.getDrawGraphics();

		try {
			if (Loop.home == 10) {
				g.drawImage(ImageIO.read(Loop.class.getResource("/Buildings/Interfaces/10.png")), 0, 0, 800, 600, null);
			} else if (Loop.home == 11) {
				g.drawImage(ImageIO.read(Loop.class.getResource("/Buildings/Interfaces/11.png")), 0, 0, 800, 600, null);
			}

			if (page == 0) {
				g.drawImage(ImageIO.read(Loop.class.getResource("/Grey.png")), 0, 0, 800, 600, null);
				g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/People/Sell Icon.png")), 150, 300, 100, 50,
						null);
				g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/People/Jobs Icon.png")), 550, 300, 100, 50,
						null);

			} else if (page == 1) {
				g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/People/Sell.png")), 0, 0, 800, 600, null);
				g.setFont(new Font("Stencil", 0, 30));
				g.setColor(Color.BLACK);

				g.drawString("x" + qty, 310, 213);
				g.drawString("£" + qty * val, 565, 125);
				g.drawString("£" + Player.money, 420, 520);
				Loop.selling = true;
				RenderThread.l.renderSellInv();

			} else if (page == 2) {
				// g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Jobs/Jobs.png")),
				// 0, 0, 800, 600, null);
				// g.setColor(Color.black);
				// g.setFont(new Font("Stencil", 0, 20));
				//
				// if(Loop.home == 11){
				// g.drawString("Church Job", 50, 100);
				// }else{
				// g.drawString("Persons Job", 50, 100);
				// }
				//
				// g.drawString(job.getTodo(), 50, 150);
				// g.drawString("I'll give you £" + job.getMoney(), 50, 200);
				// g.drawString(job.getAmm() + "/" + job.getAmm(), 50, 250);
				//
				// g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Jobs/Accept.png")),
				// 350, 500, 100, 50, null);
				//
				// if(jobAccept){
				// g.drawImage(ImageIO.read(Loop.class.getResource("/Grey.png")),
				// 350, 500, 100, 50, null);
				// }
				//
				// for(int i = 0; i < Jobs.jobList.size(); i++){
				// g.drawString(Jobs.jobList.get(i).getAmm() +"/"+
				// Jobs.jobList.get(i).getAmm(), 590, 30+(120*i));
				// g.drawString("£" + Jobs.jobList.get(i).getMoney(), 590,
				// 60+(120*i));
				// g.drawString(Jobs.jobList.get(i).getTodo(), 590, 90+(120*i));
				// }
				// g.drawImage(ImageIO.read(Loop.class.getResource("/Grey.png")),
				// 567, 0, 245, 600, null);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		g.dispose();
		bs.show();

	}

	public static int qty = 1, val = 0;
	public static InventoryItems item;

	void tick() {
		item = Forgers.item;
		// System.out.println(page);

		Inputs.tick();

		if (Inputs.esc) {
			while (Inputs.esc) {
				Inputs.tick();
			}
			display = false;
		}

		if (page == 0) {
			// page = 2;
			// Sell Icon.png")), 150, 300, 100, 50, null);
			// Jobs Icon.png")), 550, 300, 100, 50, null);
			if (Inputs.mb == 1) {
				if (Loop.region(150, 100, 300, 50, false)) {
					page = 1;
				}
				if (Loop.region(550, 100, 300, 50, false)) {
					page = 2;
				}
			}

		} else if (page == 1) {
			if (qty == 0) {
				qty = 1;
			}
			if (item == null) {
				if (Loop.inv.Items.size() > 0) {
					item = Loop.inv.Items.get(0);
				} else {
					page = 0;
					return;
				}
			}
			if (Inputs.mb != -1) {
				if (Loop.region(30, 210, 465, 540 - 465, true)) {// Done
					page = 0;

				}
				if (Loop.region(340, 420 - 340, 100, 135 - 100, true)) {// Increase
					if (qty != item.getAmm()) {
						qty++;
					} else {
						qty = 1;
					}
				}
			}
			if (Inputs.mb != -1) {
				if (Loop.region(340, 420 - 340, 270, 135 - 100, true)) {// Decrease
					if (qty > 1) {
						qty--;
					} else {
						qty = item.getAmm();
					}
				}
			}
			if (qty > item.getAmm()) {
				qty = item.getAmm();
			}
			if (Inputs.mb != -1) {
				if (Loop.region(540, 750 - 540, 280, 360 - 280, true)) {
					Player.money += qty * val;
					item.setAmm(-qty);
					if (qty > item.getAmm()) {
						qty = item.getAmm();
					}
					if (item.getAmm() == 0) {
						Loop.inv.Items.remove(item);
						qty = 1;
					}
					// remove items check not selling too many next
				}
			}
			// File file = new File("res/GUIS/Forgers/Sell/Values.txt");
			try {
				// FileInputStream fis = new FileInputStream(file);
				InputStream is = (Forgers.class.getResourceAsStream("/txt files/Values.txt"));

				byte[] b = new byte[(int) is.available()];
				byte[] name = new byte[item.getName().length()];
				// b = fis.getChannel();
				is.read(b);
				name = item.getName().getBytes();
				// System.out.println(b.toString());
				// System.out.println(item.getName());
				String v = "";

				for (int i = 0; i < b.length; i++) {
					if (b[i] == name[0]) {
						for (int ii = 0; ii < name.length; ii++) {
							if (b[i + ii] != name[ii]) {
								continue;
							}
							if (ii + 1 == name.length) {
								ii += 3;
								for (int iii = 0; iii < 5; iii++) {
									if (b[iii + ii + i] == 59 || b[iii + ii + i] == 186) {
										// continue;
										iii = 200;
									} else {
										v = v + (b[iii + ii + i] - 48);
										// System.out.println(b[iii + ii +
										// i]-48);
									}
								}
							}
						}
					}
				}

				if (v.equals("")) {
					val = 5;
				} else {
					val = Integer.parseInt(v);
				}
				is.close();
			} catch (Exception e) {
				System.out.println("ERROR READING Values.txt");
				e.printStackTrace();
			}
		} else if (page == 2) {
			JobsGUI GUI = new JobsGUI(type, job);
			while (GUI.isRunning()) {
			}

			page = 0;

			// if(Inputs.mb == 1){
			// if(Loop.region(350, 100, 500, 50, false) && !jobAccept){
			// Jobs.jobList.add(job);
			// jobAccept = true;
			// }
			// }
		}
	}

	public boolean isDisplay() {
		return display;
	}

	public void setDisplay(boolean display) {
		this.display = display;
	}

	public Job getJob() {
		return job;
	}

}
