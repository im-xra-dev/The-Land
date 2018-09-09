package com.weebly.kyslol.Game.entities;

import java.util.Random;

import com.weebly.kyslol.Game.Loop;
import com.weebly.kyslol.Game.RenderThread;
import com.weebly.kyslol.Game.GUI.PersonGUI;
import com.weebly.kyslol.Game.entities.JobList.Job;

public class NPC extends Mobs{
	int x, y, size;
	String tex;
	Random random = new Random();
	public static boolean rendering = false;
	Job job;

	public NPC(int x, int y){
		size = 256;
		this.x = x;
		this.y = y;
		Mobs.people.add(this);
		tex = "/Entities/Mobs/Non-Hostile/Person.png";
		
	}
	
	public void tick(){
		if(RenderThread.renderID != 8 && RenderThread.renderID != 2){
			Mobs.people.remove(this);
			rendering = false;
			Loop.home = 0;

		}
		
		if(Inputs.mb == 1 && Inputs.mx < x + (size/2) && Inputs.mx > x - (size/2) && Inputs.my < y + (size/2) && Inputs.my > y - (size/2)){
			if(Loop.home == 10){ //home
				rendering = true;
				PersonGUI GUI = new PersonGUI(1, job);
				job = GUI.getJob();

				while(GUI.isDisplay()){
				}
//				System.out.println(job.getAmm());

				rendering = false;
//				System.out.println("Can you help me get " + Jobs.jobs.get(random.nextInt(Jobs.jobs.size())));
				//if yes add to job list
				//if no
//				System.out.println("Ok");
			}else if(Loop.home == 11){ //Church
				rendering = true;
				PersonGUI GUI = new PersonGUI(2, job);
				job = GUI.getJob();

				while(GUI.isDisplay()){
				}
//				System.out.println(job.getAmm());
				rendering = false;
				
//				System.out.println("Can you help me get " + Jobs.churchJobs.get(random.nextInt(Jobs.churchJobs.size())));
				//if yes add to job list
				//if no
//				System.out.println("Ok");
			}
//			System.out.println("YES");
		}
		
	}
	
	public int getSize(){
		return size;
	}
	public int getx(){
		return x;
	}
	public String getTex(){
		return tex;
	}
	public int gety(){
		return y;
	}

	public void addX(int i) {
		x += i;
		
	}
	public void addY(int i) {
		y += i;
		
	}
	public void addSize(int i) {
		size += i;
		
	}

}
