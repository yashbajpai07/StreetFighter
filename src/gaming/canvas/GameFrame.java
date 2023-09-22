package gaming.canvas;

import java.io.IOException;

import javax.swing.JFrame;

import gaming.utils.GameConstants;

public class GameFrame extends JFrame implements GameConstants {
     public GameFrame() throws IOException {
    	
    	 setTitle(TITLE);
    	//setLocationRelativeTo(null);
    	 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	 //dispose off from memory
    	 setSize(GWIDTH,GHEIGHT);
     Board board=new Board();
     add(board);
    	 setVisible(true);
    	 
    	 
     }
	public static void main(String[] args) {
	try {
		GameFrame gf = new GameFrame();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		

	}

}