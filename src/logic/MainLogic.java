package logic;

import java.util.ArrayList;
import java.util.List;

import render.GameAnimation;
import render.GameBackground;
import logic.IGameLogic;
import logic.IRenderableHolder;

public class MainLogic implements IRenderableHolder,IGameLogic{
	
	private GameBackground background;
	//No player status
	private List<TargetObject> onScreenObject = new ArrayList<TargetObject>();
	private List<GameAnimation> onScreenAnimation = new ArrayList<GameAnimation>();
	
	
	
	public MainLogic(){
		
	}

	@Override
	public synchronized void onStart() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public synchronized void onExit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logicUpdate() {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public synchronized List<IRenderableObject> getSortedRenderableObject() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
