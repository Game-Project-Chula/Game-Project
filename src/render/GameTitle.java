package render;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.spi.AudioFileReader;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ProgressMonitorInputStream;
import javax.swing.border.Border;

public class GameTitle extends JPanel {

	private static AudioClip titleSong;
	private static AudioClip titleButton;
	private static BufferedImage titleBG;
	private JPanel graphics;
	public static JFrame startGame;
	private static GameTitle gameTitle;
	private static GameScreen gameScreen;
	private static Color pressColor = new Color(255, 0, 0);
	private static Color pressShadowColor = new Color(0, 0, 0);
	private static int alpha = 255;
	private static boolean toSelectCharacters = false;
	private static boolean newGame = false;
	private JLabel newGameButton;
	private static RescaleOp rescaleOp;
	private static String scriptCG1 = "≥ «‘»«–®ÿÃ“";
	public static boolean toGameScreen = false;
	

	public static void main(String[] args) {
		startGame = new JFrame();
		gameTitle = new GameTitle();
		gameScreen = new GameScreen();

		
		startGame.add(gameTitle);
		
		startGame.setTitle("Remember me Kill me");
		startGame.setResizable(false);
		startGame.pack();
		startGame.setVisible(true);
		startGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		while(!newGame){
			try {
				Thread.sleep(13);
				startGame.repaint();
			} catch (InterruptedException e) {}
		}
		while(toGameScreen){
			try {
				Thread.sleep(13);
				startGame.repaint();
			} catch (InterruptedException e) {}
			
		}
	}

	public GameTitle() {

		setBackground(Color.BLACK);

		setDoubleBuffered(true);
		ClassLoader loader = GameTitle.class.getClassLoader();
		try {
			titleButton = Applet.newAudioClip(loader.getResource("res/se/titleButton.wav").toURI().toURL());
			titleSong = Applet.newAudioClip(loader.getResource("res/se/swanlake.wav").toURI().toURL());
			titleBG = ImageIO.read(loader.getResourceAsStream("res/img/editedChulaEngBG.jpg"));

		} catch (Exception e) {}
		titleSong.play();

		newGameButton = new JLabel("New Game");
		newGameButton.setFont(new Font("HELVETICA", Font.BOLD, 30));
		newGameButton.setForeground(new Color(255,0,0,alpha));
		newGameButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				// New Game HERE!!s
				synchronized(titleButton){
					titleButton.play();
				}
				newGame = true;
				GameManager.newGame();
			}

		});
		

		graphics = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				//float opacity = 0.5f;
				//g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
				if(newGame){
					
					try {
						rescaleOp = new RescaleOp(1.0f, -2f, null);
						rescaleOp.filter(titleBG, titleBG);// Source and destination are the same.
						
						newGameButton.setForeground(new Color(255,0,0,alpha));
						alpha -= 1;
						if (alpha <= 0)alpha=0;
						if(alpha==0){
							
							startGame.remove(gameTitle);
							newGame=false;
							toGameScreen=true;
						}
						Thread.sleep(13);
						startGame.repaint();
					} catch (InterruptedException e) {}
					
					
				}
				if(toGameScreen){
					startGame.add(gameScreen);
					startGame.setTitle("Remember me Kill me");
					startGame.setResizable(false);
					startGame.pack();
					startGame.setVisible(true);
					startGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						try {
							Thread.sleep(10);
							startGame.repaint();
						} catch (InterruptedException e) {}
					
				}
				g2.drawImage(titleBG, 0, 0, null);
				//opacity =1f;
				//g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
				if (!toSelectCharacters) {
					pressColor = new Color(255, 0, 0, alpha);
					pressShadowColor = new Color(0, 0, 0, alpha);
					alpha -= 1;
					if (alpha == 0)
						alpha = 255;
					
					
				} else {
					pressColor = new Color(255, 0, 0, 0);
					pressShadowColor = new Color(0, 0, 0, 0);
				}
					
				g2.setFont(new Font("SANS-SERIF", Font.BOLD, 80));
				g2.setColor(pressShadowColor);
				g2.drawString("PRESS ANY KEY", 307, 207);
				g2.setFont(new Font("LATIN", Font.BOLD, 80));
				g2.setColor(pressColor);
				g2.drawString("PRESS ANY KEY", 300, 200);
				//It is character select scene, it is optional, deadline is coming!.
				if(toSelectCharacters){
					
					
				}
			}
		};
		graphics.setPreferredSize(new Dimension(1200, 800));

		add(graphics);
		addListener();

	}

	private void addListener() {
		
		
		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				toSelectCharacters = true;
				graphics.add(newGameButton);
				gameTitle.revalidate();
				startGame.validate();
				startGame.repaint();
				titleSong.stop();
				
				
			}
			
			
			
		});
		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				super.mouseClicked(arg0);
				toSelectCharacters = true;
				graphics.add(newGameButton);

				gameTitle.revalidate();
				startGame.validate();
				startGame.repaint();
				titleSong.stop();
			}
			
		});

	}
}
