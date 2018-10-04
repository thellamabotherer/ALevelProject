package TriangleEngine03;

import org.lwjgl.util.vector.Vector4f;

import BasicEngine.Window;

public class Polygon {

	private int[] vertices; // an index on a large global array of vectors
	private int sides;
	private Vector4f colour = new Vector4f (1,1,1,1);
	
	public Polygon (int[] verts) {
		
		this.vertices = verts;
		
		// sort out the vector4f for the colours later
	}
	
	public void render(Window window, Map map) {
		window.beginTriangleRender();
		window.polyOffsetLine(-1, -1);
		window.changeColour(this.colour);
		
		for (int i = 0; i < this.vertices.length; i++) {
			window.addVertex(map.getVertex(this.vertices[i]));
		}

		window.endRender();
	}
	
}
