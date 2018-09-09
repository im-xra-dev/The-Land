package com.weebly.kyslol.Game.Sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Audio {
	
	//Player
	public static Clip Damage, Eat;
	
	//weapons
	public static Clip Gun, Sword, Bow;
	
	//W41k1ng
	public static Clip water, grass, hell;
	
	//Music
	public static Clip music_Main, music_Boss, music_Hell;

	//Misc
	public static Clip Button, drink;

	public static void LoadAudio(){
		Button = setAudio("/sound/GUI/Button.wav");

		drink = setAudio("/sound/GUI/Potion/Drink.wav");

		music_Main = setAudio("/sound/Music/music_Main.wav");
		music_Boss = setAudio("/sound/Music/music_Boss.wav");
//		music_Hell = setAudio("/sound/music/music_Hell.wav");
		
//		Damage = setAudio("/sound/Damage.wav");
//		Eat = setAudio("/sound/Eat.wav");
//		
		Gun = setAudio("/sound/Weapons/Gun.wav");
		Sword = setAudio("/sound/Weapons/Sword.wav");
		Bow = setAudio("/sound/Weapons/Bow.wav");
//		Bow.setLoopPoints(0, 10);
		
//		water = setAudio("/sound/water.wav");
//		grass = setAudio("/sound/grass.wav");
//		hell = setAudio("/sound/hell.wav");
	}
	
	
	public static Clip setAudio(String Sound) {
//		InputStream sound = Audio.class.getResourceAsStream(Sound);
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Audio.class.getResource(Sound));
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        return clip;
	    } catch(Exception ex) {
	        System.out.println("Error Loading " + Sound);
	        ex.printStackTrace();
			System.exit(1);
			return null;
	    }
	}

	
	
}
