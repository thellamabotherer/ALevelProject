package TestGame01;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import TestGame01.Vertex;

import org.lwjgl.util.vector.Vector4f;

import BasicEngine.Window;

public class Triangle {
	
	private Vertex[] vertices;
	private Vector4f colour;

	public Triangle (Vertex vertex1, Vertex vertex2, Vertex vertex3, Vector4f col) {
		
		this.vertices = new Vertex[3];
		
		this.vertices[0] = vertex1;
		this.vertices[1] = vertex2;
		this.vertices[2] = vertex3;
		this.colour = col;
		
	}
	
	public void render (Window window) {
		
		try {
		
		Vector4f greyCol = new Vector4f ();
		greyCol.w -= 0.01;
		greyCol.x -= 0.01;
		greyCol.y -= 0.01;
		greyCol.z -= 0.01;
		
		//window.beginRender();
		//window.polyOffsetFill(1, 1);
		//window.changeColour(greyCol);
		//window.addVertex(vertices[0].getPos());
		//window.addVertex(vertices[1].getPos());
		//window.addVertex(vertices[2].getPos());
		//window.endRender();
		
		// draw slightly smaller triangle in grey to show lines
		
		window.beginLineRender ();
		window.polyOffsetLine(-1, -1);
		window.changeColour(colour);
		window.addVertex(vertices[0].getPos());
		window.addVertex(vertices[1].getPos());
		window.addVertex(vertices[2].getPos());
		window.endRender();
		
		}
		
		catch (Exception e) {
			System.out.println(vertices[0]);
			System.out.println(vertices[1]);
			System.out.println(vertices[2]);
		}

	}
	
	public boolean isAdjacent (Triangle t) { // checks if two vertices are the same (but not all three)
		
try {
	
	System.out.println("\nthis = " + this);
	System.out.println("verts = " + this.getVertices()[0]);
	System.out.println("verts = " + this.getVertices()[1]);
	System.out.println("verts = " + this.getVertices()[2]);
	System.out.println("t = " + t);
	System.out.println("verts = " + t.getVertices()[0]);
	System.out.println("verts = " + t.getVertices()[1]);
	System.out.println("verts = " + t.getVertices()[2]);
	
		int eq = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				
				System.out.println("vertex i " + this.getVertices()[i] + "[" + i + "]");
				System.out.println("vertex j " + t.getVertices()[j] + "[" + j + "]");

				if (this.getVertices()[i].isEqual(t.getVertices()[j])) {
					eq++;
				}
			}
		}
		if (eq == 2) {
			return true;
		}
		return false;
	
}

catch (Exception e) {
	e.printStackTrace();
	
	System.exit(1);
	return false;
	
}
		
	}
	
	public void flip (Triangle t) { // swaps a vertex with an adjacent triangle
		
		if (this.isAdjacent(t)) {
			// find the non-adjacent vertices and the adjacent vertices
			Vertex adjVer1 = null;
			Vertex adjVer2 = null;
			Vertex oppVer1 = null;
			Vertex oppVer2 = null;
			
			boolean[] tFound = {false, false, false};
			
			boolean adjFound = false;
			
			for (int i = 0; i < 3; i++) {
				if (this.vertices[0].isEqual(t.vertices[i])) {
					adjFound = true;
					tFound[i] = true;
					if (!(adjVer1 == null)) {
						adjVer1 = this.vertices[0];
					}else {
						adjVer2 = this.vertices[0];
					}
				}
			}if (!adjFound) {
				oppVer1 = this.vertices[1];
			}adjFound = false;
			
			for (int i = 0; i < 3; i++) {
				if (this.vertices[1].isEqual(t.vertices[i])) {
					adjFound = true;
					tFound[i] = true;
					if (!(adjVer1 == null)) {
						adjVer1 = this.vertices[1];
					}else {
						adjVer2 = this.vertices[1];
					}
				}
			}if (!adjFound) {
				oppVer1 = this.vertices[1];
			}adjFound = false;
			
			for (int i = 0; i < 3; i++) {
				if (this.vertices[2].isEqual(t.vertices[i])) {
					adjFound = true;
					tFound[i] = true;
					if (!(adjVer1 == null)) {
						adjVer1 = this.vertices[2];
					}else {
						adjVer2 = this.vertices[2];
					}
				}
			}if (!adjFound) {
				oppVer1 = this.vertices[2];
			}
			
			for (int i = 0; i < 3; i++) {
				if (!tFound[i]) {
					oppVer2 = t.vertices[i];
				}
			}
			
			t.resetVertices(adjVer1, oppVer1, oppVer2);
			this.resetVertices(adjVer2, oppVer1, oppVer2);
			
		}
		
		
	
		
	}
	
	public Vertex[] getVertices () {
		return this.vertices;
	}
	
	public void resetVertices (Vertex a, Vertex b, Vertex c) {
		this.vertices[0] = a;
		this.vertices[1] = b;
		this.vertices[2] = c;
	}
}
