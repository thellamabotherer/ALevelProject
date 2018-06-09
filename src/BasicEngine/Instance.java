package BasicEngine;

import org.lwjgl.opengl.Display;

public class Instance {

	public Window start () {

		Window window = null;
		try {
			window = new Window (1080, 720, "Basic Engine Auto Window");
		}catch (Throwable e) {
			e.printStackTrace();
			System.out.println("Winow did not generate");
		}
		
		return window;
		
	}public void stop () {
		
		Display.destroy();
		System.exit(0);;
		
	}public boolean run (int fps) {
		
		if (Display.isCloseRequested()) {
			return false;
		}
		
		Display.sync(fps);;
		Display.update();
		
		return true;
		
		}
		
		
	private void render () { // tbh, I'm probably going to do most of the rendering in the game package
		
		
	}

}
