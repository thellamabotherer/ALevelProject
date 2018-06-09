package PolygonEngine01;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import BasicEngine.Window;

public class Map {

	private Vertex[] vertices;
	private ArrayList<Polygon> polys;

	public Map() {

		int maxTrianglesY = (int) Math
				.floor((Vars.HEIGHT - (1 * Vars.borderSize))  /  (Math.cos(Math.PI / 6) * Vars.triangleSideLength));
		int maxTrianglesX = (int) Math.floor((Vars.WIDTH - (2 * Vars.borderSize)) / Vars.triangleSideLength);

		float YGap = (float) ((Vars.HEIGHT - (2 * Vars.borderSize))
				% (Math.cos(Math.PI / 6) * Vars.triangleSideLength));
		float XGap = (Vars.WIDTH - (2 * Vars.borderSize)) % (Vars.triangleSideLength);

		float[] XStartOffset = { Vars.borderSize + (XGap / 2),
				Vars.borderSize + (XGap / 2) + (Vars.triangleSideLength / 2) };
		float[] YStartOffset = { Vars.borderSize + (YGap / 2),
				Vars.borderSize + (YGap / 2) + (Vars.triangleSideLength / 2) };

		System.out.println(maxTrianglesX);
		System.out.println(maxTrianglesY);

		ArrayList<ArrayList<Vertex>> vertMap = new ArrayList();

		for (int y = 0; y < maxTrianglesY; y++) {
			vertMap.add(new ArrayList());
			for (int x = 0; x < maxTrianglesX; x++) {

				vertMap.get(y).add(new Vertex(new Vector3f((XStartOffset[y % 2]) + (x * Vars.triangleSideLength),
						(float) ((YStartOffset[y % 2]) + ((Vars.triangleSideLength * Math.cos(Math.PI / 6)) * y))
								- (Vars.triangleSideLength / 2 * (y % 2)),
						0)));

			}
		}

		int arraySize = 0;
		for (int i = 0; i < vertMap.size(); i++) {
			arraySize += vertMap.get(i).size();
		}

		this.vertices = new Vertex[arraySize];
		int verticesAdded = 0;

		ArrayList<ArrayList<Integer>> indexList = new ArrayList();

		for (int i = 0; i < vertMap.size(); i++) {
			indexList.add(new ArrayList());
			for (int j = 0; j < vertMap.get(i).size(); j++) {
				this.vertices[verticesAdded] = vertMap.get(i).get(j);
				verticesAdded++;
				indexList.get(i).add(verticesAdded);
			}
		}
		
		ArrayList<Polygon> triangles = new ArrayList();
		
		for (int y = 0; y < indexList.size() - 1; y++) {
			for (int x = 0; x < indexList.get(y).size() - 1; x++) {
				if (y % 2 == 0) {
					
					ArrayList<Integer> buffer = new ArrayList();
					buffer.add(indexList.get(y).get(x));
					buffer.add(indexList.get(y).get(x + 1));
					buffer.add(indexList.get(y + 1).get(x));
					
					triangles.add(new Polygon(buffer));
				}
				if (y % 2 == 1) {
					
					ArrayList<Integer> buffer = new ArrayList();
					buffer.add(indexList.get(y).get(x));
					buffer.add(indexList.get(y).get(x + 1));
					buffer.add(indexList.get(y + 1).get(x + 1));
					
					triangles.add(new Polygon(buffer));
				}
			}
		}
		
		for (int y = 1; y < indexList.size(); y++) {
			for (int x = 0; x < indexList.get(y).size() - 1; x++) {
				if (y % 2 == 0) {
					
					ArrayList<Integer> buffer = new ArrayList();
					buffer.add(indexList.get(y).get(x));
					buffer.add(indexList.get(y).get(x + 1));
					buffer.add(indexList.get(y - 1).get(x));
					
					triangles.add(new Polygon(buffer));
				}
				if (y % 2 == 1) {
					
					ArrayList<Integer> buffer = new ArrayList();
					buffer.add(indexList.get(y).get(x));
					buffer.add(indexList.get(y).get(x + 1));
					buffer.add(indexList.get(y - 1).get(x + 1));
					
					triangles.add(new Polygon(buffer));
				}
			}
		}
	
		this.polys = triangles;

	}
	
	public void render (Window window) {
		
		for (int i = 0; i < this.polys.size(); i++) {
			
			this.polys.get(i).render(window, this);
		}
	}

	public Vertex getVertex(int pos) {
		System.out.println(pos);
		return this.vertices[pos ];
	}
	
	public void flip (int a, int b ) {
		this.polys.get(a).flip(this.polys.get(b));
	}
	
	public int getSize () {
		return this.polys.size();
	}

}
