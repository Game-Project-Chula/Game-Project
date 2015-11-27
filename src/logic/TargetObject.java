package logic;

import java.awt.Graphics2D;

public class TargetObject implements IRenderableObject{
	
	protected int x,y,z = 0;
	protected int radius;
	protected boolean isDestroyed = false;
	protected int[] movingParameter;
	protected int movingDuration;
	protected int movingDurationCounter = 0;
	protected int movingType;
	
	public TargetObject(int radius,int movingDuration,int z) {
		this.z = z;
		this.radius = radius;
		this.movingDuration = movingDuration;
		
		movingParameter = new int[8];
		
		
		
		
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
	public void render(Graphics2D arg0) {

		
	}

}
