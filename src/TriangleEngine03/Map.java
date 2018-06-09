package TriangleEngine03;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import BasicEngine.Window;
import TriangleEngine02.Triangle;

public class Map { // a whole bunch of vertices in an array, every other occurence is just referenced from here
	
	public Vertex[] globalVertices;
	public ArrayList<Polygon> polys;
	
	public Map (int WIDTH, int HEIGHT, int border, int edgeLength) {
		int maxTrianglesY = (int) Math.floor(
				(HEIGHT - (2 * border)) / (Math.cos(Math.PI / 6) * edgeLength));
		int maxTrianglesX = (int) Math.floor((WIDTH - (2 * border)) / edgeLength);
		
		System.out.println(maxTrianglesX);
		System.out.println(maxTrianglesY);
		
		float YGap = (float) ((HEIGHT - (2 * border))
				% (Math.cos(Math.PI / 6) * edgeLength));
		float XGap = (WIDTH - (2 * border)) % (edgeLength);

		float[] XStartOffset = { border + (XGap / 2),
				border + (XGap / 2) + (edgeLength / 2) };
		float[] YStartOffset = { border + (YGap / 2),
				border + (YGap / 2) + (edgeLength / 2) };

		ArrayList<ArrayList<Vertex>> globalVertices = new ArrayList();

		for (int y = 0; y < maxTrianglesY; y++) {
			globalVertices.add(new ArrayList());
			for (int x = 0; x < maxTrianglesX - (y % 2); x++) {

				globalVertices.get(y).add(new Vertex(new Vector3f(
						(XStartOffset[y % 2]) + (x * edgeLength),
						(float) ((YStartOffset[y % 2]) + ((edgeLength * Math.cos(Math.PI / 6)) * y))
								- (edgeLength / 2 * (y % 2)),
						0), new Vector4f (1,1,1,1)));

			}
		}
		
		int arraySize = 0;
		for (int i = 0; i < globalVertices.size(); i++) {
			arraySize += globalVertices.get(i).size();
		}
		
		this.globalVertices = new Vertex[arraySize];
		int verticesAdded = 0;
		
		ArrayList<ArrayList<Integer>> indexList = new ArrayList();
		
		for (int i = 0; i < globalVertices.size(); i++) {
			indexList.add(new ArrayList());
			for (int j = 0; j < globalVertices.get(i).size(); j++) {
				this.globalVertices[verticesAdded] = globalVertices.get(i).get(j);
				verticesAdded ++;
				indexList.get(i).add(verticesAdded);
			}
		}System.out.println(indexList);
		
		ArrayList<Polygon> triangles = new ArrayList();
		
		
		
		for (int y = 0; y < indexList.size() - 1; y++) {
			for (int x = 0; x < indexList.get(y).size() - 1; x++) {
				if (y % 2 == 0) {
					
					int[] buffer = {indexList.get(y).get(x), indexList.get(y).get(x + 1),
							indexList.get(y + 1).get(x)};
					
					triangles.add(new Polygon(buffer));
				}
				if (y % 2 == 1) {
					
					int[] buffer = {indexList.get(y).get(x), indexList.get(y).get(x + 1),
							indexList.get(y + 1).get(x + 1)};
					
					triangles.add(new Polygon(buffer));
				}
			}
		}
		
		for (int y = 1; y < indexList.size(); y++) {
			for (int x = 0; x < indexList.get(y).size() - 1; x++) {
				if (y % 2 == 0) {
					
					int[] buffer = {indexList.get(y).get(x), indexList.get(y).get(x + 1),
							indexList.get(y - 1).get(x)};
					
					triangles.add(new Polygon(buffer));
				}
				if (y % 2 == 1) {
					
					int[] buffer = {indexList.get(y).get(x), indexList.get(y).get(x + 1),
							indexList.get(y - 1).get(x + 1)};
					triangles.add(new Polygon(buffer));
				}
			}
		}
	
		this.polys = triangles;
		
	}
	
	public Vector3f getVertex (int pos) {
		try {
		return this.globalVertices[pos].getPos();
	
		}
	catch (Exception e) {
		e.printStackTrace();
		return this.globalVertices[0].getPos();
	}
	}
	
	public void render (Window window) {
		for (int i = 0; i < this.polys.size(); i++) {
			this.polys.get(i).render(window, this);
		}
	}
}








