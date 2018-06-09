package TriangleEngine02;

import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import BasicEngine.Instance;
import BasicEngine.Window;

public class Main {
	
	public static Instance instance = new Instance();
	public static Window window = new Window(VarHandler.WIDTH, VarHandler.HEIGHT, "Triangles 2, electric boogaloo");

	public static int WIDTH = Display.getWidth();
	public static int HEIGHT = Display.getHeight();

	public static void main(String args[]) {

		/*
		 * Set up the game engine
		 * 
		 * Generate a grid of vertices at the correct locations
		 * 
		 * Generate triangles to fit these vertices and add them to a list
		 * 
		 * Run game loop to render the list of triangles
		 * 
		 * try and flip two random adjacent triangles each frame
		 * 
		 * Close down engine
		 */

		Random rand = new Random();
		
		int maxTrianglesY = (int) Math.floor(
				(HEIGHT - (2 * VarHandler.borderSize)) / (Math.cos(Math.PI / 6) * VarHandler.triangleSideLength));
		int maxTrianglesX = (int) Math.floor((WIDTH - (2 * VarHandler.borderSize)) / VarHandler.triangleSideLength);

		float YGap = (float) ((HEIGHT - (2 * VarHandler.borderSize))
				% (Math.cos(Math.PI / 6) * VarHandler.triangleSideLength));
		float XGap = (WIDTH - (2 * VarHandler.borderSize)) % (VarHandler.triangleSideLength);

		float[] XStartOffset = { VarHandler.borderSize + (XGap / 2),
				VarHandler.borderSize + (XGap / 2) + (VarHandler.triangleSideLength / 2) };
		float[] YStartOffset = { VarHandler.borderSize + (YGap / 2),
				VarHandler.borderSize + (YGap / 2) + (VarHandler.triangleSideLength / 2) };

		ArrayList<ArrayList<Vertex>> globalVertices = new ArrayList();

		for (int y = 0; y < maxTrianglesY; y++) {
			globalVertices.add(new ArrayList());
			for (int x = 0; x < maxTrianglesX; x++) {

				globalVertices.get(y).add(new Vertex(new Vector3f(
						(XStartOffset[y % 2]) + (x * VarHandler.triangleSideLength),
						(float) ((YStartOffset[y % 2]) + ((VarHandler.triangleSideLength * Math.cos(Math.PI / 6)) * y))
								- (VarHandler.triangleSideLength / 2 * (y % 2)),
						0)));

			}
		}

		ArrayList<Triangle> triangles = new ArrayList();

		for (int y = 0; y < globalVertices.size() - 1; y++) {
			for (int x = 0; x < globalVertices.get(y).size() - 1; x++) {
				if (y % 2 == 0) {
					triangles.add(new Triangle(globalVertices.get(y).get(x), globalVertices.get(y).get(x + 1),
							globalVertices.get(y + 1).get(x), new Vector4f(1, 1, 1, 1)));
				}
				if (y % 2 == 1) {
					triangles.add(new Triangle(globalVertices.get(y).get(x), globalVertices.get(y).get(x + 1),
							globalVertices.get(y + 1).get(x + 1), new Vector4f(1, 1, 1, 1)));
				}
			}
		}
		
		for (int y = 1; y < globalVertices.size(); y++) {
			for (int x = 0; x < globalVertices.get(y).size() - 1; x++) {
				if (y % 2 == 0) {
					triangles.add(new Triangle(globalVertices.get(y).get(x), globalVertices.get(y).get(x + 1),
							globalVertices.get(y - 1).get(x), new Vector4f(1, 1, 1, 1)));
				}
				if (y % 2 == 1) {
					triangles.add(new Triangle(globalVertices.get(y).get(x), globalVertices.get(y).get(x + 1),
							globalVertices.get(y - 1).get(x + 1), new Vector4f(1, 1, 1, 1)));
				}
			}
		}

		for (int i = 0; i < 1000000; i++) {
			triangles.get(rand.nextInt(triangles.size())).flip(triangles.get(rand.nextInt(triangles.size())));
		}
		
		System.out.println(triangles.size());
		
		while (instance.run(VarHandler.fps)) {			
			
			for (int i = 0; i < triangles.size(); i++) {
				triangles.get(i).render(window);
			}

		}

	}

}
