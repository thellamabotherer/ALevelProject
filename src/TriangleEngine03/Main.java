package TriangleEngine03;

import BasicEngine.Instance;
import BasicEngine.Window;
import TriangleEngine02.VarHandler;

public class Main {
	
	public static final int WIDTH = 1080;
	public static final int HEIGHT = 760;
	public static final int BORDER = 30;
	public static final int SIDELEN = 20;
	
	public static Instance instance = new Instance();
	public static Window window = new Window(VarHandler.WIDTH, VarHandler.HEIGHT, "Triangles 3, the quickening");
	
	public static void main (String args []) {
		Map map = new Map(WIDTH, HEIGHT, BORDER, SIDELEN);
		
		
		while (instance.run(60)) {
			map.render(window);
		}
	}

	
	
}
