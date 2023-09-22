package gaming.sprite;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Power extends Sprite {
    Color color;//Why create argument for what purpose  
    String playerName;//and how they support
public Power(int x,Color color,String playerName){
		this.x=x;
		  y=100; 
		  h=50;
		  w=MAX_HEALTH;;
			this.color	=color;
			this.playerName=playerName;
	}
	@Override
	protected BufferedImage defaultImage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void printRect(Graphics pen) {
		pen.setColor(Color.RED);
		pen.fillRect(x, y, w, h);
		pen.setColor(color);//As color is a type which 
		//             can be of any color thus need to take 
		//              it as input in power constructor
		pen.fillRect(x, y, health, h);
		pen.setColor(Color.WHITE);
		pen.setFont(new Font("times",Font.BOLD,30));
		pen.drawString(playerName, x, 200);
	}

}