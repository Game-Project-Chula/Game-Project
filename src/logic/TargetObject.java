package logic;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import render.DrawingUtility;

public class TargetObject implements IRenderableObject{
	
	protected int x,y,z = 0;
	private String name;
	public boolean isDestroyed = false;
	protected int[] movingParameter;
	protected int movingDuration;
	protected int movingDurationCounter = 0;
	protected int movingType;
	//private Random randomGen;
	private int randomChannel;
	private static int easyMove = 1;
	private static int mediumMove = 2;
	private static int hardMove = 3;
	//it will have a fileReader for friend's name
	
	public TargetObject(int movingDuration,String name,BufferedImage image) {
		this.name = name;
		this.movingDuration = movingDuration;
		movingParameter = new int[8];
		//we assume that the size of the target is circle with 100 px radius.
		Random randomGen = new Random();
		randomChannel = randomGen.nextInt(5)+1;
		if(randomChannel==1){
			this.x = 0;
			this.y = 450;
		}else if(randomChannel==2){
			this.x = 0;
			this.y =0;
		}else if(randomChannel==3){
			this.x =500;
			this.y= 0;
		}else if(randomChannel==4){
			this.x = 1000;
			this.y = 0;
		}else{
			this.x = 1000;
			this.y = 450;
		}
		
		//why switch can't use??? I spent a lot of times fixed it.
		
	}
	
	public void move(){
		if(isDestroyed) return;
		if(/*selectLevel*/true){
			
	
			if(randomChannel==1){
				this.x +=easyMove;
			}else if(randomChannel==2){
				this.x += easyMove;
				this.y +=easyMove;
			}else if(randomChannel==3){
				this.y+= easyMove;
			}else if(randomChannel==4){
				this.x -=easyMove;
				this.y += easyMove;
			}else{
				this.x -=easyMove;
			}
		}
		
	}
	
	@Override
	public boolean isVisible() {
		if(!isDestroyed) return true;
		else return false;
	}

	@Override
	public int getZ() {
		return z;
	}

	@Override
	public void render(Graphics2D g2) {
	
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public String getName(){
		return this.name;
	}
	
}
