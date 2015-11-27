package render;

import java.applet.Applet;
import java.applet.AudioClip;

public class AudioUtility {
	private static AudioClip acShoot;
	
	static{
		ClassLoader loader = GameManager.class.getClassLoader();
		try {
			acShoot = Applet.newAudioClip((loader.getResource("res/se/shoot.wav")).toURI().toURL());
		} catch (Exception e){}
		
	}
}
