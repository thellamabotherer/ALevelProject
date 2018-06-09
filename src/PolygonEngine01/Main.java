package PolygonEngine01;

import java.util.Random;

import BasicEngine.Instance;
import BasicEngine.Window;
import TriangleEngine02.VarHandler;

public class Main {
	

	public static Instance instance = new Instance();
	public static Window window = new Window(VarHandler.WIDTH, VarHandler.HEIGHT, "PolygonEngine01");

	
	
	public static void main (String args [] ) {
		Map map = new Map ();
		Random rand = new Random();
		
		for (int i = 0; i < 100000; i++) {
			map.flip(rand.nextInt(map.getSize()), rand.nextInt(map.getSize()));
		}
		
		
		while (instance.run(60)) {
			map.render(window);
		}
		
	}
	
	

}
