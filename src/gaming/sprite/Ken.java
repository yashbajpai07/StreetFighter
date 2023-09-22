package gaming.sprite;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import gaming.utils.GameConstants;

public class Ken extends Sprite  {
	private BufferedImage walkImages[]=new BufferedImage[6]; 
	 private BufferedImage dmgEffectImages[]=new BufferedImage[6];
	 private BufferedImage punchImages[]=new BufferedImage[6];
	  private BufferedImage runImages[]=new BufferedImage[6];
public Ken() {
	x=GWIDTH-800;
	h=H;
	w=W;
	y=FLOOR-h;
	 try {
			img=ImageIO.read(Ryu.class.getResource("ken.png"));
		} catch (IOException e) {
			System.out.println("IMAGE not found");
		}
	   loadWalkImages();
	 //  loadKickImages();
	   loadpunchImages();
	   loadrunImages();
	   loadDmgEffectImages();
}
private BufferedImage gameover() {
	   return img.getSubimage(428,543,156,130);
}
private void  loadWalkImages() {
	   walkImages[0]=img.getSubimage(0,5,89,158);
	   walkImages[1]=img.getSubimage(88,5,90,158);
	   walkImages[2]=img.getSubimage(264,0,90,161);
	   walkImages[3]=img.getSubimage(352,3,92,158);
	   walkImages[4]=img.getSubimage(440,2,92,159);
	   walkImages[5]=img.getSubimage(529,2,88,159);
}
private void  loadDmgEffectImages() {
	dmgEffectImages[0]=img.getSubimage(5,463,123,136);
	dmgEffectImages[1]=img.getSubimage(128,463,166,109);
	dmgEffectImages[2]=img.getSubimage(294,463,133,129);
	dmgEffectImages[3]=img.getSubimage(294,463,133,129);
	dmgEffectImages[4]=img.getSubimage(294,463,133,129);
	dmgEffectImages[5]=img.getSubimage(924,376,76,150);
}

private void  loadpunchImages() {
	   punchImages[0]=img.getSubimage(615,11,113,148);
	   punchImages[1]=img.getSubimage(728,11,135,148);
	   punchImages[2]=img.getSubimage(863,11,113,148);
	   punchImages[3]=img.getSubimage(104,163,129,153);
	   punchImages[4]=img.getSubimage(233,163,151,152);	
	   punchImages[5]=img.getSubimage(604,463,107,143);	   

}
private void  loadrunImages() {
	   runImages[0]=img.getSubimage(0,314,138,149);
	   runImages[1]=img.getSubimage(138,314,131,149);
	   runImages[2]=img.getSubimage(269,314,103,149);
	   runImages[3]=img.getSubimage(372,314,142,149);
	   runImages[4]=img.getSubimage(514,317,138,146);
	   runImages[5]=img.getSubimage(652,319,109,144);


	   
}

private BufferedImage printWalk() {
	isAttack=false;
	   if(imageIndex>5) {
			imageIndex=0;
			
		}
		BufferedImage img= walkImages[imageIndex];
		imageIndex++;
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
private BufferedImage printDamage() {
	   if(imageIndex>5) {
			imageIndex=0;
			
			currentMove=WALK; 
		}
		BufferedImage img= dmgEffectImages[imageIndex];
		imageIndex++;
		x=x+50;
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
		return img;
}


@Override
public BufferedImage defaultImage() {
if(currentMove==RUN )
      return printRun();
else if (currentMove==DMG)
		   return printDamage();
else if (currentMove==PUNCH )
		   return printPunch();
else if (currentMove==GO)
	   return gameover();
	else
		return printWalk();

	
}

}