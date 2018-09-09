package com.weebly.kyslol.Game.entities;

import java.util.ArrayList;
import java.util.Random;

import com.weebly.kyslol.Game.Loop;

public class Mobs {
	static Random random = new Random();
	public static boolean Boss = false, repel = false;
	public static int attract = 1;
	public static ArrayList<AI> mobs = new ArrayList<AI>();
	public static ArrayList<NPC> people = new ArrayList<NPC>();

	// public static ArrayList<Ghost> Ghosts = new ArrayList<Ghost>();
	public static ArrayList<Satan> Satan = new ArrayList<Satan>();
	public static ArrayList<RoboLord> RoboLord = new ArrayList<RoboLord>();
	// public static ArrayList<Thief> Thief = new ArrayList<Thief>();
	// public static ArrayList<MiniRoboLord> MiniRoboLord = new
	// ArrayList<MiniRoboLord>();

	// @SuppressWarnings("static-access")
	public void tick() {
		Loop.boss = Boss;

		for (int i = 0; i < mobs.size(); i++) {
			mobs.get(i).tick();
			// System.out.println(i);
		}
		for (int i = 0; i < Satan.size(); i++) {
			Satan.get(i).tick();
			// System.out.println(i);
		}
		for (int i = 0; i < RoboLord.size(); i++) {
			RoboLord.get(i).tick();
			// System.out.println(i);
		}
		

		if (!repel) {

			if (random.nextInt(300 / attract) == 0) {
				mobs.add(new Ghost("/Entities/Mobs/Enemies/Ghost.png"));
			}

			// for (int i = 0; i < MiniRoboLord.size(); i++) {
			// MiniRoboLord.get(i).tick();
			// }

			if (random.nextInt(3000 / attract) == 0) {
				mobs.add(new MiniRoboLord("/Entities/Mobs/Enemies/MiniRoboLord.png"));
			}

			if (random.nextInt(300 / attract) == 0 && Player.biome.equalsIgnoreCase("Swamp")) {
				mobs.add(new Thief("/Entities/Mobs/Enemies/Thief.png"));
			}

			// for (int i = 0; i < Satan.size(); i++) {
			// Satan.get(i).tick();
			// }

			if (random.nextInt(100 / attract) == 0 && Satan.size() != 0) {
				mobs.add(new SatanSlave("/Entities/Mobs/Enemies/SatanSlave.png"));
			}

			if (Player.biome.equalsIgnoreCase("Hell")) {
				if (random.nextInt(100000 / attract) == 0 && !Boss) {
					Satan.add(new Satan());
				}

				if (random.nextInt(1000 / attract) == 0) {
					mobs.add(new SatanSlave("/Entities/Mobs/Enemies/SatanSlave.png"));
				}

			} else {
//				if (random.nextInt(1000000 / attract) == 0 && !Boss) {
//					Satan.add(new Satan());
//				}

				if (random.nextInt(3000 / attract) == 0) {
					mobs.add(new SatanSlave("/Entities/Mobs/Enemies/SatanSlave.png"));
				}

			}

		}
	}

}
