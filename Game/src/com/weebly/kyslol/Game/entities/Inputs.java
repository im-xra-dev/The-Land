package com.weebly.kyslol.Game.entities;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.weebly.kyslol.Game.Loop;

public class Inputs implements KeyListener, FocusListener, MouseListener, MouseMotionListener {

	public static boolean[] key = new boolean[65535];
	public static boolean w, s, m, k, I, N_4, h, esc, t, j;
	public static int my, mx, mb = -1; 
	
	
	public static void tick(){
//		System.out.println(w);
		I = key[KeyEvent.VK_I];
		N_4 = key[KeyEvent.VK_4];
		h = key[KeyEvent.VK_H];
		w = key[KeyEvent.VK_W];
		s = key[KeyEvent.VK_S];
		m = key[KeyEvent.VK_M];
		k = key[KeyEvent.VK_K];
		t = key[KeyEvent.VK_T];
		j = key[KeyEvent.VK_J];
		esc = key[KeyEvent.VK_ESCAPE];
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if(Loop.type != 0){
//			System.out.println(e.getKeyCode());
			if(e.getKeyCode() == 10){
//				System.out.println("enter");
				Loop.typing = Loop.typing + "¬";
			}else if(e.getKeyCode() == 8){
				char[] arr = new char[Loop.typing.length()-1];
				arr = Loop.typing.toCharArray();
				Loop.typing = "";
				
				for(int i = 0; i < arr.length-1; i++){
					Loop.typing = Loop.typing + arr[i];
				}
//				Loop.typing = Loop.typing + "¬";
			}else{
				Loop.typing = Loop.typing + e.getKeyChar();
			}

		}

		
//		System.out.println(keyCode);
		if (keyCode > 0 && keyCode < key.length){
			key[keyCode] = true;
		}		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode > 0 && keyCode < key.length){
			key[keyCode] = false;
		}		
	}
	
	
	@Override
	public void mouseDragged(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
//		mb = arg0.getButton();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mb = e.getButton();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		mb = -1;
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		
	}



	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	
}
