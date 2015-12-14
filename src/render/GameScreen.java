package render;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.FeatureDescriptor;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import logic.IRenderableObject;
import logic.TargetObject;
import logic.IRenderableHolder;
import logic.InputUtility;;

public class GameScreen extends JPanel{
	
	private IRenderableHolder renderableHolder;
	private JPanel graphics;
	private static boolean isGameOver=false;
	//life should be 4
	private int lifeLeft=4;
	private URL url = getClass().getResource("res/img/friend");
	private File folder = new File("src/res/img/friend");
	private File[] listOfFiles = folder.listFiles();
	private static BufferedImage friendImage;
	
	private AudioClip gameSong; 
	private List<TargetObject> targetObject = new ArrayList<TargetObject>();
	private List<String> targetName = new ArrayList<String>(); 
	private List<BufferedImage> targetPicture = new ArrayList<BufferedImage>();
	private static int friendLeft;
	public static TargetObject testTargetObject;
	//public static TargetObject testTargetObject2  = new TargetObject(0, 0, null);
	
	private static String inputString= "";
	private static boolean killedByTyping = false;

	protected GameScreen() {
		setBackground(new Color(0,102,0));
		setDoubleBuffered(true);
		ClassLoader loader = GameScreen.class.getClassLoader();
		

		
		
		try {
			gameSong = Applet.newAudioClip(loader.getResource("res/se/swanlake.wav").toURI().toURL());
			for (File file : listOfFiles) {
			    if (file.isFile()) {
			    	String friendImageName = file.getName();
			    	friendImage = ImageIO.read(loader.getResourceAsStream("res/img/friend/"+friendImageName));
			    	targetName.add(friendImageName);
			    	targetPicture.add(friendImage);
			    }
			}
			
			
		} catch (Exception e) {}
		if(GameTitle.toGameScreen)gameSong.play();
		
		//Add enemy
		for(int i=0;i<100;i++){
			
			 testTargetObject = new TargetObject(0, targetName.get(0),null);
			 targetObject.add(testTargetObject);
		}
		friendLeft = targetObject.size();
		
		
		graphics = new JPanel(){
			
			@Override	
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setColor(new Color(0,102,0));
				g2.fillRect(0, 0, 1200, 800);
				setFocusable(true);
				requestFocus();
				
					try {
						//When we kill enemy
						if(killedByTyping&&!targetObject.isEmpty()){
							targetObject.remove(0);
							inputString="";
							friendLeft-=1;
							killedByTyping=false;
						}
						
						//When enemy collide us 
						if(475<=targetObject.get(0).getX()
								&& targetObject.get(0).getX()<=525
								&& 450<=targetObject.get(0).getY()&&targetObject.get(0).getY()<=475 
								&&!targetObject.isEmpty()){
							
							targetObject.remove(0);
							inputString="";
							lifeLeft-=1;
							friendLeft-=1;
							if(lifeLeft==0)isGameOver=true;
						}
		
						
						if(/*!isGameOver*/lifeLeft!=0){
							DrawingUtility.drawStatusBar(g2, friendLeft, lifeLeft);
							targetObject.get(0).move();
							DrawingUtility.drawInputString(g2,inputString);
							DrawingUtility.drawFriend(g2, targetObject.get(0).getX()
									,targetObject.get(0).getY() ,"test1",null);
							
						}else{
							DrawingUtility.drawStatusBar(g2, friendLeft, lifeLeft);
							DrawingUtility.drawInputString(g2,"it's You");
						}

						
						Thread.sleep(5);
						GameTitle.startGame.repaint();
					} catch (InterruptedException e) {}
				
				
				try {
					Thread.sleep(13);
				} catch (InterruptedException e) {}
				
			}
			
		};
		graphics.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyPressed(e);
				if(e.getKeyCode()==KeyEvent.VK_ENTER && inputString.equalsIgnoreCase("HELLO1")){
					targetObject.remove(0);
					inputString="";
					friendLeft-=1;
					killedByTyping=false;
				}				if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
					if(inputString.length()!=0)inputString=inputString.substring(0,inputString.length()-1);
				}
				
				if(e.getKeyCode()!=KeyEvent.VK_BACK_SPACE && e.getKeyCode()!=KeyEvent.VK_ENTER
						&& e.getKeyCode()!=KeyEvent.VK_CONTROL && e.getKeyCode()!=KeyEvent.VK_SHIFT){
					char a = e.getKeyChar();
					if(inputString.length()<15)inputString+=a;
				}
			
			}
			
		});
		graphics.setPreferredSize(new Dimension(1200, 800));
		this.add(graphics);
	}
	
	

}
