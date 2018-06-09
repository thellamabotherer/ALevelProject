package TestGame01;

import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import java.util.ArrayList;
import java.util.Random;

import BasicEngine.Instance;
import BasicEngine.Window;

public class Main {

	public static final int fps = 60;

	public static void main(String args[]) {

		Instance instance = new Instance();
		Window window = instance.start();

		//Vertex v1 = new Vertex(new Vector3f(100, 100, 0));
		//Vertex v2 = new Vertex(new Vector3f(200, 100, 0));
		//Vertex v3 = new Vertex(new Vector3f(100, 200, 0));
		//Triangle testTriangle0 = new Triangle(v1, v2, v3, new Vector4f(1, 1, 1, 1));

		//v1 = new Vertex(new Vector3f(500, 500, 0));
		//v2 = new Vertex(new Vector3f(400, 500, 0));
		//v3 = new Vertex(new Vector3f(500, 400, 0));
		//Triangle testTriangle1 = new Triangle(v1, v2, v3, new Vector4f(1, 1, 1, 1));

		ArrayList<Triangle> triangles = new ArrayList();
		// triangles.add(testTriangle0);
		// triangles.add(testTriangle1);

		// ------------------ isometric map creation test 1 -----------------------

		ArrayList<ArrayList<Vertex>> isometricMap = new ArrayList();
		for (int i = 0; i < 12; i++) {
			isometricMap.add(new ArrayList());
		}

		for (int i = 0; i < 12; i++) {
			// no offset line
			if (i % 2 == 0) {
				for (int j = 0; j < 18; j++) {
					isometricMap.get(i).add(new Vertex(new Vector3f((90 + (j * 50)), (60 + i * 50), 0)));
				}
			} // offset line
			else {
				for (int j = 0; j < 17; j++) {
					isometricMap.get(i).add(new Vertex(new Vector3f((115 + (j * 50)), (60 + i * 50), 0)));
				}
			}
		}

		// bottom line

		for (int i = 0; i < 11 - 1; i++) {
			triangles.add(new Triangle(isometricMap.get(0).get(i), isometricMap.get(0).get(i + 1),
					isometricMap.get(1).get(i), new Vector4f(1, 1, 1, 1)));
		}

		// middle lines

		for (int i = 1; i < 11 - 1; i++) {
			if (i % 2 == 1) {
				for (int j = 0; j < isometricMap.get(i).size() - 2; j++) {
					triangles.add(new Triangle(isometricMap.get(i).get(j), isometricMap.get(i).get(j + 1),
				isometricMap.get(i + 1).get(j + 1), new Vector4f(1, 1, 1, 1)));
				}
			} else {
				for (int j = 0; j < 11 - 2; j++) {
					triangles.add(new Triangle(isometricMap.get(i).get(j), isometricMap.get(i).get(j + 1),
							isometricMap.get(i + 1).get(j), new Vector4f(1, 1, 1, 1)));
				}
			}
		}
		
		for (int i = 1; i < 11 - 1; i++) {
			if (i % 2 == 1) {
				for (int j = 0; j < isometricMap.get(i).size() - 2; j++) {
					triangles.add(new Triangle(isometricMap.get(i).get(j), isometricMap.get(i).get(j + 1),
				isometricMap.get(i - 1).get(j + 1), new Vector4f(1, 1, 1, 1)));
				}
			} else {
				for (int j = 0; j < 11 - 2; j++) {
					triangles.add(new Triangle(isometricMap.get(i).get(j), isometricMap.get(i).get(j + 1),
							isometricMap.get(i - 1).get(j), new Vector4f(1, 1, 1, 1)));
				}
			}
		}
		
		

		// top line

		// ------------------------------------------------------------------------

		Random rand = new Random();
		boolean truth = true;
		
		while (instance.getRunning()) {
			for (int i = 0; i < triangles.size(); i++) {
				triangles.get(rand.nextInt(triangles.size()-1)).flip(triangles.get(rand.nextInt(triangles.size()-1)));
				triangles.get(i).render(window);
			}
			instance.run(fps);
		}
		instance.stop();

		System.exit(0);

	}

}
