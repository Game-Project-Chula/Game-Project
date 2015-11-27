package logic;

import java.awt.Graphics2D;

public abstract interface IRenderableObject {
	public abstract boolean isVisible();
	public abstract int getZ();
	 public abstract void render(Graphics2D arg0);
}
