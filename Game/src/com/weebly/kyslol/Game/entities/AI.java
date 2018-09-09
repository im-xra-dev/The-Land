package com.weebly.kyslol.Game.entities;

import java.util.Random;

import com.weebly.kyslol.Game.Loop;
import com.weebly.kyslol.Game.Sound.Audio;
import com.weebly.kyslol.Game.player.inventory.GetItems;

public class AI extends Mobs {
	static Random random = new Random();
	protected int x = 140, damage = 0, size = 10, health;
	protected String Tex;
	// public static int x = 140, damage = 0, size = 10, health;

	// public void AI(MiniRoboLord g){
	//
	// if (g.getx()<280){
	// if(g.getx()<160){
	// g.addSize(1);
	// g.addX(1);
	//
	// }else if(g.getx()<180){
	// g.addSize(2);
	// g.addX(2);
	//
	// }else if(g.getx()<200){
	// g.addSize(3);
	// g.addX(3);
	//
	// }else if(g.getx()<220){
	// g.addSize(4);
	// g.addX(4);
	//
	// }else if(g.getx()<240){
	// g.addSize(5);
	// g.addX(5);
	//
	// }else if(g.getx()<160){
	// g.addSize(6);
	// g.addX(6);
	//
	// }else{
	// g.addSize(7);
	// g.addX(7);
	//
	//
	// }
	//
	// } else {
	// if(random.nextInt(10) == 0){
	// Player.health -= g.getDamage();
	// }
	//
	// if(Inputs.mb == 1){
	// if(Inputs.mx > 400 - g.getSize() && Inputs.mx < 400 - g.getSize()+
	// g.getSize() * 2&& Inputs.my > g.getx() && Inputs.my < g.getx() +
	// g.getSize() * 2){
	// g.addHealth(-Player.damage) ;
	// }//400 - gh.getSize(), gh.getx(), gh.getSize() * 2, gh.getSize() * 2
	// }
	// }
	// }

	public void AI(AI g) {

		if (g.getx() < 280) {
			if (g.getx() < 160) {
				g.addSize(1);
				g.addX(1);

			} else if (g.getx() < 180) {
				g.addSize(2);
				g.addX(2);

			} else if (g.getx() < 200) {
				g.addSize(3);
				g.addX(3);

			} else if (g.getx() < 220) {
				g.addSize(4);
				g.addX(4);

			} else if (g.getx() < 240) {
				g.addSize(5);
				g.addX(5);

			} else if (g.getx() < 160) {
				g.addSize(6);
				g.addX(6);

			} else {
				g.addSize(7);
				g.addX(7);

			}

		} else {
			if (random.nextInt(10) == 0) {
				Player.health -= Math.floor(g.getDamage() - ((g.getDamage() / 100.0) * Player.resistance));
				// System.out.println(( ((g.getDamage() / 100.0) *
				// Player.resistance)));
			} // 1000 - ((1000/ 100)*10)

			if (Inputs.mb == 1) {
				if (Inputs.mx > 400 - g.getSize() && Inputs.mx < 400 - g.getSize() + g.getSize() * 2
						&& Inputs.my > g.getx() && Inputs.my < g.getx() + g.getSize() * 2) {
					// g.addHealth(-Player.damage);
					if (Player.weaponType == 1) {
						Audio.Sword.flush();
						Audio.Sword.loop(1);
					}
					if (Player.weaponType == 0) {
						// Audio.Sword.loop(1); //TODO ARM NOISE
					}

					if (Player.weaponType == 2 || Player.weaponType == 3) {
						for (int i = 0; i < Loop.inv.Ammo.size(); i++) {
							if (Loop.inv.Ammo.get(i).getName().equalsIgnoreCase(Player.weapon + " Ammo")) {
								if (Player.weaponType == 3) {
									Audio.Bow.flush();
									Audio.Bow.loop(1);
								} else {
									Audio.Gun.flush();
									Audio.Gun.loop(1);
								}
								Loop.inv.Ammo.get(i).setAmm(-1);
								g.addHealth(-Player.damage);
								return;
							}
						}
						// TODO
						// SAY NO AMMO
						GetItems.addItem("Not enough ammo", 1);

						// PLAY NO AMMO CLICK
					}
					g.addHealth(-Player.damage);

				} // 400 - gh.getSize(), gh.getx(), gh.getSize() * 2,
					// gh.getSize() * 2
			}
		}
	}

	public void BossAI(AI g) {

		if (g.getx() < 130) {
			if (g.getx() < 100) {
				g.addSize(2);
				g.addX(1);

			} else {
				g.addSize(4);
				g.addX(2);
			}

		} else {
			if (random.nextInt(10) == 0) {
				Player.health -= Math.floor(g.getDamage() - ((g.getDamage() / 100.0) * Player.resistance));
				// System.out.println((g.getDamage() - ((g.getDamage() / 100) *
				// Player.resistance)));
			}

			if (Inputs.mb == 1) {
				if (Inputs.mx > 400 - g.getSize() && Inputs.mx < 400 - g.getSize() + g.getSize() * 2
						&& Inputs.my > g.getx() && Inputs.my < g.getx() + g.getSize() * 2) {
					if (Player.weaponType == 1) {
						Audio.Sword.flush();
						Audio.Sword.loop(1);
					}
					if (Player.weaponType == 0) {
						// Audio.Sword.loop(1); //TODO ARM NOISE
					}
					if (Player.weaponType == 2 || Player.weaponType == 3) {
						for (int i = 0; i < Loop.inv.Ammo.size(); i++) {
							if (Loop.inv.Ammo.get(i).getName().equalsIgnoreCase(Player.weapon + " Ammo")) {
								if (Player.weaponType == 3) {
									Audio.Bow.flush();
									Audio.Bow.loop(1);
								} else {
									Audio.Gun.flush();
									Audio.Gun.loop(1);
								}
								Loop.inv.Ammo.get(i).setAmm(-1);
								g.addHealth(-Player.damage);
								return;
							}
						}
						// TODO
						// SAY NO AMMO
						GetItems.addItem("Not enough ammo", 1);
						// PLAY NO AMMO CLICK
						return;
					}
					g.addHealth(-Player.damage);

				} // 400 - gh.getSize(), gh.getx(), gh.getSize() * 2,
					// gh.getSize() * 2
			}
		}
	}

	public int getSize() {
		return size;
	}

	public int getx() {
		return x;
	}

	public int getDamage() {
		return damage;
	}

	public int getHealth() {
		return health;
	}

	public void addHealth(int i) {
		health += i;

	}

	public void addX(int i) {
		x += i;

	}

	public void addSize(int i) {
		size += i;

	}

	public String getTex() {
		return Tex;
	}
}
