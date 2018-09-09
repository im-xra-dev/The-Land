package com.weebly.kyslol.Game.entities.JobList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import com.weebly.kyslol.Game.GUI.Forgers;

public class Jobs {
	public static ArrayList<String> jobs = new ArrayList<String>();
	public static ArrayList<Job> jobList = new ArrayList<Job>();
	public static ArrayList<String> churchJobs = new ArrayList<String>();

	public Jobs(){
		InputStream j = (Forgers.class.getResourceAsStream("/txt files/Jobs.txt"));
		Scanner scan = new Scanner(j);
		while(scan.hasNextLine()){
			String i = scan.nextLine();
			String[] data = i.split(" ");
			String type = "";
			
			for(int l = 0; l < data.length - 1; l++){
				type = type + data[l] + " ";
			}

			if(i.contains("'Job'")){
				jobs.add(type);
			}
			if(i.contains("'Church_Job'")){
				churchJobs.add(type);
			}

		}
//		System.out.println(jobs.get(0) + "   " + churchJobs.get(0));

	}
}
