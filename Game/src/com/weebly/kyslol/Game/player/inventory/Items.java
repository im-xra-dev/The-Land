package com.weebly.kyslol.Game.player.inventory;

public class Items {
	private int time, amm;
	private String name;

	public Items(String name, int amm) {
		// System.out.println("100");
		if (name.equals("£")) {
			this.name = name + amm;
		} else if (name.equals("Not enough money") || name.equals("Not enough items") || name.contains("Trophy")
				|| name.equals("Not enough ammo")) {
			this.name = name;
			// System.out.println("2");
		} else {
			this.name = amm + " X " + name;
		}
		time = 0;
	}

	public void tick() {
//		System.out.println(1);
		time++;
		if (time > 50) {
			GetItems.newItems.remove(this);
			return;
		}
//		System.out.println(2);

//		while (GetItems.newItems.size() > 3) {
//			System.out.println(GetItems.newItems.size() > 3);
//			System.out.println(GetItems.newItems.size());
			if (GetItems.newItems.size() > 3 && GetItems.newItems.get(0) == this) {
				GetItems.newItems.remove(this);
				return;
//			}
		}
//		System.out.println(3);

	}

	public String getName() {
		return name;
	}
}
