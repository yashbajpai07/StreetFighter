package gaming.sprite;

import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ryu extends Sprite  {
 private BufferedImage walkImages[]=new BufferedImage[6]; 
 private BufferedImage kickImages[]=new BufferedImage[6];
 private BufferedImage punchImages[]=new BufferedImage[6];
 private BufferedImage runImages[]=new BufferedImage[6];
 private BufferedImage dmgEffectImages[]=new BufferedImage[6];

   public Ryu()  {
	   x=500;
	   h=H;
		w=W;
	   y=FLOOR-h;
	   
	   try {
		img=ImageIO.read(Ryu.class.getResource("ryu.png"));
	} catch (IOException e) {
		System.out.println("IMAGE not found");
	}
   loadWalkImages();
   loadKickImages();
   loadpunchImages();
   loadrunImages();
   loadDmgEffectImages();
   }
   
  
   public void jump() {
	 //  blockInput();
	   if(isJump==0) {
		   h=150;
		   w=90;
		   isJump=1;
	   force=-20;
	   y=y+force;
	  
	   }
   }

public void fall() {
	
	   if(y>(FLOOR-H)) {
		   isJump=0;
		   h=H;
		   w=W;
		   return;}
	   y=y+force;
	    force =force+GRAVITY;
	    if(force==22) {
			   currentMove=WALK; 
		   }
	    
   }
private BufferedImage gameover() {
	   return img.getSubimage(617,1003,105,153);
}
   private BufferedImage jumpImg() {
	   return img.getSubimage(217, 1039, 90,111);
   }
   private void  loadDmgEffectImages() {
		dmgEffectImages[0]=img.getSubimage(637,675,98,150);
		dmgEffectImages[1]=img.getSubimage(735,675,117,150);
		dmgEffectImages[2]=img.getSubimage(852,675,117,150);
		dmgEffectImages[3]=img.getSubimage(969,675,95,150);
		dmgEffectImages[4]=img.getSubimage(1076,675,89,150);
		dmgEffectImages[5]=img.getSubimage(1184,675,88,150);
	}
   
   private void  loadWalkImages() {
	   walkImages[0]=img.getSubimage(14,6,94,151);
	   walkImages[1]=img.getSubimage(120,8,92,151);
	   walkImages[2]=img.getSubimage(228,4,88,156);
	   walkImages[3]=img.getSubimage(331,0,89,159);
	   walkImages[4]=img.getSubimage(440,3,86,157);
	   walkImages[5]=img.getSubimage(532,3,91,155);
   }
   private void  loadKickImages() {
	   kickImages[0]=img.getSubimage(109,372,112,154);
	   kickImages[1]=img.getSubimage(219,378,106,150);
	   kickImages[2]=img.getSubimage(409,360,142,167);
	   kickImages[3]=img.getSubimage(683,372,136,153);
	   kickImages[4]=img.getSubimage(819,376,93,150);
	   kickImages[5]=img.getSubimage(924,376,76,150);
   }

   private void  loadpunchImages() {
	   punchImages[0]=img.getSubimage(18,188,97,154);
	   punchImages[1]=img.getSubimage(120,187,90,153);
	   punchImages[2]=img.getSubimage(215,191,110,150);
	   punchImages[3]=img.getSubimage(331,189,139,153);
	   punchImages[4]=img.getSubimage(472,191,132,150);	
	   punchImages[5]=img.getSubimage(631,186,89,152);	   

   }
   private void  loadrunImages() {
	   runImages[0]=img.getSubimage(503,842,112,155);
	   runImages[1]=img.getSubimage(636,848,102,150);
	   runImages[2]=img.getSubimage(739,851,82,144);
	   runImages[3]=img.getSubimage(819,851,116,144);
	   runImages[4]=img.getSubimage(944,849,118,144);
	   runImages[5]=img.getSubimage(1060,856,96,139);


	   
   }
//HOW PRINTWALK TYPE FUNCTION RUN
   private BufferedImage printWalk() {//HOw this part works 
	   isAttack=false;                //how img++ able to call the 
	   if(imageIndex>5) {//             same function again
			imageIndex=0;
			
		}
		BufferedImage img= walkImages[imageIndex];
		imageIndex++;
		return img;
   }
   private BufferedImage printDamage() {
	   if(imageIndex>5) {
			imageIndex=0;
			
			currentMove=WALK; 
		}
		BufferedImage img= dmgEffectImages[imageIndex];
		imageIndex++;
		x=x-20;
		return img;
}
   
   private BufferedImage printRun() {
	   isAttack=false;
	   if(imageIndex>5) {
			imageIndex=0;
			currentMove=WALK; 
		}
		BufferedImage img= runImages[imageIndex];
		imageIndex++;
		return img;
   }
  
   private BufferedImage printKick() {
	   if(imageIndex>5) {
			imageIndex=0;
			currentMove=WALK; 
			isAttack=false;
		}
	   isAttack=true;
		BufferedImage img= kickImages[imageIndex];
		//System.out.println("kick");
		imageIndex++;
		return img;
   }
   private BufferedImage printPunch() {
	   if(imageIndex>5) {
			imageIndex=0;
			currentMove=WALK; 
			isAttack=false;
		}
	   isAttack=true;
		BufferedImage img= punchImages[imageIndex];
		imageIndex++;
		//System.out.println("punch");
		return img;
   }

   
@Override
public BufferedImage defaultImage() {
	if(currentMove==KICK )
         return printKick();
	else if (currentMove==PUNCH )
		   return printPunch();
	else if (currentMove==RUN)
		   return printRun();
	else if (currentMove==JUMP)
		   return jumpImg();
	else if (currentMove==DMG)
		   return printDamage();
	else if (currentMove==GO)
		   return gameover();

	else
		return printWalk();
 
}
}