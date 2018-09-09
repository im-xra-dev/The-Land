package com.weebly.kyslol.Game;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Shop {
	int shop = 3;
	RenderThread r;
	
	public Shop(RenderThread r){
		this.r = r;
	}
	
	public void render(){
		shop = Loop.shop;
		
		if (shop == 3) {
			r.g.setColor(new Color(0x000000));
			r.g.fillRect(0, 0, 800, 600);
			r.g.setColor(new Color(0x0000ff));

			r.g.fillRoundRect(100, 100, 150 + 75, 150, 20, 20);
			r.g.fillRoundRect(475, 100, 150 + 75, 150, 20, 20);
			r.g.fillRoundRect(100, 350, 150 + 75, 150, 20, 20);
			r.g.fillRoundRect(475, 350, 150 + 75, 150, 20, 20);
			r.g.setColor(new Color(0x000000));
			r.g.setFont(new Font("Stencil", 0, 30));

			r.g.drawString("Weapons", 140, 180);
			r.g.drawString("Armour", 520, 180);
			r.g.drawString("Consumables", 110, 435);
			r.g.drawString("Potions", 520, 435);
		}

		if (shop == 4) {

			try {
				r.g.drawImage(ImageIO.read(Loop.class.getResource("/GUIS/Shop/Weapons.png")), 0, 0, 800, 600, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (shop == 5) {
			r.g.setColor(new Color(0x000000));
			r.g.fillRect(0, 0, 800, 600);
			r.g.setColor(new Color(0x0000ff));

			r.g.fillRoundRect(0, 0, 100, 50, 20, 20);

			r.g.fillRoundRect(25, 250, 150, 100, 20, 20);
			r.g.fillRoundRect(225, 250, 150, 100, 20, 20);
			r.g.fillRoundRect(425, 250, 150, 100, 20, 20);
			r.g.fillRoundRect(625, 250, 150, 100, 20, 20);

			r.g.setColor(new Color(0x000000));
			r.g.setFont(new Font("Stencil", 0, 30));

			r.g.drawString("Back", 10, 35);

			r.g.drawString("      5%", 35, 285);
			r.g.drawString("     10%", 235, 285);
			r.g.drawString("     20%", 435, 285);
			r.g.drawString("     50%", 635, 285);

			r.g.setFont(new Font("Stencil", 0, 25));

			r.g.drawString("Resistance", 27, 320);
			r.g.drawString("Resistance", 227, 320);
			r.g.drawString("Resistance", 427, 320);
			r.g.drawString("Resistance", 627, 320);

			r.g.setFont(new Font("Stencil", 0, 20));

			r.g.drawString("£100", 70, 345);
			r.g.drawString("£300", 270, 345);
			r.g.drawString("£500", 470, 345);
			r.g.drawString("£1000", 670, 345);
		}

		if (shop == 6) {
			r.g.setColor(new Color(0x000000));
			r.g.fillRect(0, 0, 800, 600);
			r.g.setColor(new Color(0x0000ff));

			r.g.fillRoundRect(0, 0, 100, 50, 20, 20);

			r.g.fillRoundRect(25, 250, 150, 100, 20, 20);
			r.g.fillRoundRect(225, 250, 150, 100, 20, 20);
			r.g.fillRoundRect(425, 250, 150, 100, 20, 20);
			r.g.fillRoundRect(625, 250, 150, 100, 20, 20);

			r.g.setColor(new Color(0x000000));
			r.g.setFont(new Font("Stencil", 0, 30));

			r.g.drawString("Back", 10, 35);

			r.g.drawString("   Meat", 35, 285);
			r.g.drawString("    Veg", 235, 285);
			r.g.drawString(" Sweets", 435, 285);
			r.g.drawString("   Fruit", 635, 285);

			r.g.setFont(new Font("Stencil", 0, 20));

			r.g.drawString("£10", 80, 305);
			r.g.drawString("£10", 280, 305);
			r.g.drawString("£1", 485, 305);
			r.g.drawString("£5", 685, 305);
		}
		if (shop == 7) {
			r.g.setColor(new Color(0x000000));
			r.g.fillRect(0, 0, 800, 600);
			r.g.setColor(new Color(0x0000ff));

			r.g.fillRoundRect(0, 0, 100, 50, 20, 20);

			r.g.fillRoundRect(25, 100, 150, 100, 20, 20);
			r.g.fillRoundRect(225, 100, 150, 100, 20, 20);
			r.g.fillRoundRect(425, 100, 150, 100, 20, 20);
			r.g.fillRoundRect(625, 100, 150, 100, 20, 20);

			r.g.fillRoundRect(25, 250, 150, 100, 20, 20);
			r.g.fillRoundRect(225, 250, 150, 100, 20, 20);
			r.g.fillRoundRect(425, 250, 150, 100, 20, 20);
			r.g.fillRoundRect(625, 250, 150, 100, 20, 20);

			r.g.fillRoundRect(25, 400, 150, 100, 20, 20);
			r.g.fillRoundRect(225, 400, 150, 100, 20, 20);
			r.g.fillRoundRect(425, 400, 150, 100, 20, 20);
			r.g.fillRoundRect(625, 400, 150, 100, 20, 20);

			r.g.setColor(new Color(0x000000));
			r.g.setFont(new Font("Stencil", 0, 30));

			r.g.drawString("Back", 10, 35);
			r.g.drawString(" Health", 35, 135);
			r.g.drawString("  Potion", 30, 160);
			r.g.drawString(" Hunger", 235, 135);
			r.g.drawString("  Potion", 230, 160);
			r.g.drawString(" Damage", 435, 135);
			r.g.drawString("  Potion", 430, 160);
			r.g.drawString("  Repel", 635, 135);
			r.g.drawString("  Potion", 630, 160);
			// g.drawString("Wood" , 35, 135);

			r.g.drawString("Attract", 35, 285);
			r.g.drawString("  Potion", 30, 310);

			r.g.drawString("   Cash", 235, 285);
			r.g.drawString("  Potion", 230, 310);

			r.g.setFont(new Font("Stencil", 0, 20));

			r.g.drawString("£100", 75, 180);
			r.g.drawString("£100", 275, 180);
			r.g.drawString("£150", 475, 180);
			r.g.drawString("£200", 675, 180);

			r.g.drawString("£200", 75, 330);
			r.g.drawString("£200", 275, 330);
		}}

	public void setShop(int i) {
		shop = i;
	}
}
