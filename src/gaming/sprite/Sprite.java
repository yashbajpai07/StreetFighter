package gaming.sprite;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gaming.utils.GameConstants;

public abstract class Sprite implements GameConstants {
//img=image || drawPlayer=printPLayer
	 protected int x;
	 protected int y;
	 protected int h;
	 protected int w;
	 protected int speed;
	 protected BufferedImage img;
	 protected boolean isAttack;
	 protected int health;
	 public Sprite() {
		 health=MAX_HEALTH;
	 }
	 
	public int getHealth() {
		return health;
	}

	public void setHealth() {
		
		this.health = (int)(this.health- (MAX_HEALTH *.1));
		//System.out.println(health);
	}

	//getter and setter used to in here to convert 
		 //protected stuff which can only be used by child 
		 //directly for more over public usage
	 public boolean isAttack() {
		return isAttack;
	}
	public void setAttack(boolean isAttack) {
		this.isAttack = isAttack;
	}
	
	 public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	protected abstract BufferedImage defaultImage();
	 protected int imageIndex;
	 protected int currentMove;
	 protected int force;
	 protected int isJump=0;
	
	 protected boolean isCollide;//default==false
	 
	 public boolean isCollide() {
		return isCollide;
	}
	public void setCollide(boolean isCollide) {
		this.isCollide = isCollide;
	}
	public int getCurrentMove() {
		return currentMove;
	}
	public void setCurrentMove(int currentMove) {
		this.currentMove = currentMove;
	}
	public int getSpeed() {
		 return speed;
	 }
	 public void setSpeed(int speed) {
		 this.speed=speed;
	 }
	 public void move() {
		 if(!isCollide)
		 x=x+speed;
	 }
	 public void revmove() {
		 if(!isCollide)
		 x=x-speed;
	 }
	
	 public void drawPlayer(Graphics pen) {
		   pen.drawImage(defaultImage(),x,y,w,h,null);
	   }
		 
	  
		
	   
}