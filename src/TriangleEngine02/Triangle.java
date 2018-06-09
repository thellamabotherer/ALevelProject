package TriangleEngine02;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector4f;

import BasicEngine.Window;

public class Triangle {

	private Vertex[] vertices;
	private Vector4f col;

	public Triangle(Vertex v1, Vertex v2, Vertex v3, Vector4f col) {
		this.vertices = new Vertex[3];

		v1.addTriangle(this);
		v2.addTriangle(this);
		v3.addTriangle(this);

		this.vertices[0] = v1;
		this.vertices[1] = v2;
		this.vertices[2] = v3;
		this.col = col;
	}

	public void render(Window window) {
		window.beginLineRender();
		window.polyOffsetLine(-1, -1);
		window.changeColour(this.col);
		window.addVertex(vertices[0].getPos());
		window.addVertex(vertices[1].getPos());
		window.addVertex(vertices[2].getPos());
		window.endRender();
	}

	public boolean isEqual(Triangle t) {
		int eq = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (this.vertices[i].isEqual(t.getVertices()[j])) {
					eq++;
				}
			}
		}
		if (eq == 3) {
			return true;
		}
		return false;
	}

	/*
	 * public int numNeighbors() { ArrayList<Triangle> neighbours = new ArrayList();
	 * for (int i = 0; i < 3; i++) {
	 * neighbours.addAll(this.vertices[i].getTriangles()); } // next, eliminate all
	 * duplicate triangles for (int i = 0; i < neighbours.size(); i++) { for (int j
	 * = 0; j < neighbours.size(); j++) { if (i != j) { //System.out.println("i = "
	 * + i); //System.out.println("j = " + j); if
	 * (neighbours.get(i).isEqual(neighbours.get(j))) { neighbours.remove(i); i -=
	 * 1; if (j > i) { j--; } } } } }System.out.println(neighbours.size()); return
	 * neighbours.size() - 1; }
	 */

	public void flip(Triangle t) {

		if (this.isAdjacent(t)) {

			Vertex shared1 = null;
			Vertex shared2 = null;
			Vertex diff1 = null;
			Vertex diff2 = null;

			boolean thisFound;
			boolean[] vertFound = { false, false, false };

			for (int i = 0; i < 3; i++) {
				thisFound = false;
				for (int j = 0; j < 3; j++) {
					if (this.vertices[i].isEqual(t.getVertices()[j])) {
						vertFound[j] = true;
						thisFound = true;
						if (shared1 == null) {
							shared1 = this.vertices[i];
						} else {
							shared2 = this.vertices[i];
						}
					}
				}
				if (!thisFound) {
					diff1 = this.vertices[i];
				}
			}
			for (int i = 0; i < 3; i++) {
				if (!vertFound[i]) {
					diff2 = t.getVertices()[i];
				}
			}

			// check if new triangles will have more than 7 or fewer than 5 neighbours
			Triangle thisTriangle = new Triangle(diff1, diff2, shared1, new Vector4f(1, 1, 1, 1));
			Triangle otherTriangle = new Triangle(diff1, diff2, shared2, new Vector4f(1, 1, 1, 1));

			if (this.validFlip() && t.validFlip()) {
				this.setVertices(thisTriangle.getVertices());
				t.setVertices(otherTriangle.getVertices());
			}

		}

	}

	public boolean isAdjacent(Triangle t) {
		int eq = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (this.vertices[i].isEqual(t.getVertices()[j])) {
					eq++;
				}
			}
		}
		if (eq == 2) {
			return true;
		}
		return false;
	}

	public Vertex[] getVertices() {
		return vertices;
	}

	public void setVertices(Vertex[] vertices) {
		this.vertices = vertices;
	}

	public boolean validFlip() {
		if ((this.vertices[0].getTriangles().size() > 0) && (this.vertices[0].getTriangles().size() < 12)
				&& (this.vertices[1].getTriangles().size() > 0) && (this.vertices[1].getTriangles().size() < 12)
				&& (this.vertices[2].getTriangles().size() > 0) && (this.vertices[2].getTriangles().size() < 12)) {
			return true;
		}return false;
	}

}
