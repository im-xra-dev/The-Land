package com.weebly.kyslol.Game.entities.JobList;

import com.weebly.kyslol.Game.player.inventory.Inventory;

public class Job {
	int jobId, money, amm, has = 0, type;
	String todo, name;

	public Job(int jobId, int money, int amm, String todo, String name) {
		this.jobId = jobId;
		this.money = money;
		this.amm = amm;
		this.todo = todo;
		this.name = name;
	}

	public void tick() {
		for (int i = 0; i < Inventory.Items.size(); i++) {
//			System.out.println(name + " " + Inventory.Items.get(i).getName());
			if (Inventory.Items.get(i).getName().equalsIgnoreCase(name)) {
				has = Inventory.Items.get(i).getAmm();
				type = 0;
			}
		}
		for (int i = 0; i < Inventory.Food.size(); i++) {
//			System.out.println(Inventory.Food.get(i).getName() + " " + name);
			if (Inventory.Food.get(i).getName().equalsIgnoreCase(name)) {
				has = Inventory.Food.get(i).getAmm();
				type = 1;
			}
		}
	}

	public int getJobId() {
		return jobId;
	}

	public int getMoney() {
		return money;
	}

	public int getAmm() {
		return amm;
	}

	public int getHas() {
		return has;
	}
	
	public int getType() {
		return type;
	}

	public String getTodo() {
		return todo;
	}
	
	public String getName() {
		return name;
	}
}
