package gaming.canvas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import gaming.sprite.Ken;
import gaming.sprite.Power;
import gaming.sprite.Ryu;
import gaming.utils.GameConstants;

public class Board extends JPanel implements GameConstants  {
BufferedImage img;	
private Ryu p1;
private Ken p2;
private Power ryuFP;//full power
private Power kenFP;
private Timer timer;
private boolean gameOver;//FALSE by default 

private void gameLoop() {
	// Thread Trigger
	timer = new Timer(200, new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			repaint();//calls paint Component
			if(gameOver)
				timer.stop();
		    p1.fall();
			
		    collision();
		    isGameOver();
			
		}
	});
	timer.start();
	}
private void loadPower() {
	ryuFP =new Power(30,Color.LIGHT_GRAY,"RYU");
	kenFP =new Power(GWIDTH-800,Color.GREEN,"KEN");
}
private void printFP(Graphics g) {
	 ryuFP.printRect(g);
	 kenFP.printRect(g);
}

private boolean isCollide() {
	int xDist=Math.abs( p1.getX()-p2.getX());
	//abs for +ve_int
	int yDist=Math.abs(p1.getY()-p2.getY());
	return xDist<=(W-10) && yDist<=(H-10);
}
private void collision() {
	if(isCollide()) { 
		if(p1.isAttack()&&p2.isAttack()) {
			
		}else {
			if(p1.isAttack()) {
				p2.setCurrentMove(DMG);
				p2.setSpeed(SPEED);
				p2.move();
				kenFP.setHealth();
			}else if(p2.isAttack()) {
				p1.setCurrentMove(DMG);
				p1.setSpeed(SPEED);
				p1.revmove();
				ryuFP.setHealth();
			}
		} 
		p1.setCollide(true);
		p1.setSpeed(0);
		p2.setCollide(true);
		p2.setSpeed(0);
	}
	else {
		p1.setCollide(false);
		p1.setSpeed(SPEED);
		p2.setCollide(false);
		p2.setSpeed(SPEED);
} 
}

private void isGameOver() {
	if (ryuFP.getHealth()<=0) {
		gameOver=true;
		p1.setCurrentMove(GO);
	}else if(kenFP.getHealth()<=0) {
		gameOver=true;
		p2.setCurrentMove(GO);
	}
}
private void printGameOver(Graphics pen) {
	if(gameOver) {
		pen.setColor(Color.ORANGE);
		pen.setFont(new Font("times",Font.BOLD,40));
pen.drawString("GAME OVER",GWIDTH/2-300,GHEIGHT/2-100);
	}
	
}

 public Board() throws IOException {
	 p1= new Ryu();
	 p2= new Ken();
	 
	loadBgImg();
	setFocusable(true);
	bindEvents();
	gameLoop();
	loadPower();
 }
 
 private void bindEvents() {
	 this.addKeyListener(new KeyAdapter()  {
 
		 public void keyReleased(KeyEvent e) {
				
				p1.setSpeed(0);
				p2.setSpeed(0);

				
			}
		 
		 public void keyPressed(KeyEvent e) {
			 //RYU P1
				if(e.getKeyCode() == KeyEvent.VK_A) {
					p1.setCollide(false);
					p1.setSpeed(SPEED);
					p1.revmove();
					//repaint();
				}
				else if(e.getKeyCode() == KeyEvent.VK_D) {
 					// p1.setCollide(false);
					p1.setSpeed(SPEED);
					p1.move();
					//repaint();
				}
				else if(e.getKeyCode() == KeyEvent.VK_R) {
					p1.setCurrentMove(RUN);
					p1.setSpeed(SPEED2);
					p1.move();
					//repaint();
				}
				else if(e.getKeyCode() == KeyEvent.VK_E) {
					p1.setCollide(false);
					p1.setCurrentMove(RUN);
					p1.setSpeed(SPEED2);
					p1.revmove();
					//repaint();
				}
				else if(e.getKeyCode() == KeyEvent.VK_W) {
					p1.setCurrentMove(JUMP);
					//p1.setSpeed(SPEED);
					p1.jump();
					
					
					

					//repaint();
				}
				else if(e.getKeyCode() == KeyEvent.VK_Z) {
					p1.setCurrentMove(KICK);
						//repaint();
				}
				else if(e.getKeyCode() == KeyEvent.VK_Q) {
					p1.setCurrentMove(PUNCH);
				}
				// Ken P2
				else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					p2.setSpeed(SPEED);
					p2.revmove();
					//repaint();
				}
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					p2.setCollide(false);
					p2.setSpeed(SPEED);
					p2.move();
					//repaint();
				}
				else if(e.getKeyCode() == KeyEvent.VK_P) {
					p2.setCurrentMove(PUNCH);
				}
				else if(e.getKeyCode() == KeyEvent.VK_N) {
					p2.setCurrentMove(RUN);
					p2.setSpeed(SPEED2);
					p2.revmove();
				
				}
				else if(e.getKeyCode() == KeyEvent.VK_M) {
					p2.setCollide(false);
					p2.setCurrentMove(RUN);
					p2.setSpeed(SPEED2);
					p2.move();
				
				}
				
			}
	 
	 });
 }
 
 public void paintComponent(Graphics pen) {//painting on frame with Graphics(Brush)
	 super.paintComponent(pen);
	 pen.drawImage(img,0,0,1800,900,null);
	 p1.drawPlayer(pen);
	 p2.drawPlayer(pen);
	 printFP(pen);
	 printGameOver(pen);
 }
 

 private void loadBgImg() {
	 try {
			img=ImageIO.read(Board.class.getResource("bg.jpg"));
		} catch (IOException e) {
			System.out.println("IMAGE not found");
			System.exit(0);
		}
 }
}