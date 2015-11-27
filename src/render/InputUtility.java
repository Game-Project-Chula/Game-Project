package render;

public class InputUtility {

	private static boolean[] keyPressed = new boolean[256];
	private static boolean[] keyTriggered = new boolean[256];

	public static void setKeyPressed(int key,boolean pressed) {
		if(!(0<key && key<=255))return;
		InputUtility.keyPressed[key] = pressed;
	}
	
	public static void setKeyTriggered(int key,boolean pressed) {
		if(!(0<key && key<=255))return;
		InputUtility.keyTriggered[key] = pressed;
	}

	public static boolean getKeyPressed(int key) {
		if(0>key || key>255)return false;
		return keyPressed[key];
	}

	public static boolean getKeyTriggered(int key) {
		if(0>key || key>255)return false;
		return keyTriggered[key];
	}
	
	public static void postUpdate() {
		// TODO Auto-generated method stub
	
		for(int k=0;k<256;k++){
			setKeyTriggered(k, false);
		}
	}


}
