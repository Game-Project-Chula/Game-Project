package render;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import logic.IRenderableObject;
import logic.IRenderableHolder;

public class GameScreen extends JPanel{
	
	private IRenderableHolder renderableHolder;

	protected GameScreen(IRenderableHolder holder) {
		renderableHolder = holder;
		addListener();
		setDoubleBuffered(true);
	}
	
	private void addListener(){
		
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		Graphics2D g2 = (Graphics2D) g; 
		
		Dimension dim = getSize();
		g2.clearRect(0, 0, (int)dim.getWidth(), (int)dim.getHeight());
		
		for(IRenderableObject renderable : renderableHolder.getSortedRenderableObject()){
			renderable.render(g2);
		}
		
		
	}
	
	

}
