package PolygonEngine01;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector3f;

import BasicEngine.Window;

public class Polygon {
	
	private ArrayList<Integer> vertices;
	// might need a colour attribute soon
	
	public Polygon (ArrayList<Integer> verts) {
		this.vertices = verts;
	}
	
	public void render (Window window, Map map) {

		window.beginLineRender();
		
		for (int i = 0; i < this.vertices.size(); i++) {
			
			try {
			
			window.addVertex(map.getVertex(this.vertices.get(i)).getPos());
		
			} catch (Exception e) {
				window.addVertex(new Vector3f (0,0,0));
			}
			
			
			}
			
			
			
		
		window.endRender();
	}
	
	public void flip (Polygon p) { // swap vertices of two triangles
		
		if (this.isAdjacent(p) && this.getSize() == 3 && p.getSize() == 3 && !this.isEqual(p)) {
			
			int shared1 = -1;
			int shared2 = -1;
			int opp1 = -1;
			int opp2 = -1;
			boolean vFound;
			boolean[] Vs = {false, false, false};
			
			for (int i = 0; i < 3; i++) {
				vFound = false;
				for (int j = 0; j < 3; j++) {
					if (this.vertices.get(i) == p.getVertices().get(j)) {
						if (shared1 == -1) {
							shared1 = this.vertices.get(i);
						}else {
							shared2 = this.vertices.get(i);
						}Vs[j] = true;
					}
					if (!vFound) {
						opp1 = this.vertices.get(i);
					}
				}
			}
			
			System.out.println("4 verts = " + shared1 + " " + shared2 + " " + opp1 + " " + opp2);
			
			
			for (int j = 0; j < 3; j++) {
				if (!Vs[j]) {
					opp2 = p.getVertices().get(j);
				}
			}
			
			ArrayList<Integer> buffer = new ArrayList();
			buffer.add(opp1);
			buffer.add(shared1);
			buffer.add(opp2);
			this.setVertices(buffer);
			buffer = new ArrayList();
			buffer.add(opp1);
			buffer.add(shared2);
			buffer.add(opp2);
			p.setVertices(buffer);
			
			//TODO
			/*
			 * Check num neigbours on new triangles
			 * Don't o the flip if it fewer than 5 or more than 7
			 */
		}
		
	}
	
	public void setVertices(ArrayList<Integer> vertices) {
		this.vertices = vertices;
	}

	public ArrayList<Integer> getVertices () {
		return this.vertices;
	}public boolean isEqual (Polygon p) {
		int eq = 0;
		if (this.vertices.size() != p.getVertices().size()) {
			return false;
		}
		for (int i = 0; i < this.vertices.size(); i++) {
			for (int j = 0; j < p.getVertices().size(); j++) {
				if (this.vertices.get(i) == p.getVertices().get(j)) {
					eq++;
				}
			}
		}if (eq == this.vertices.size()) {
			return true;
		}return false;
	}public boolean isAdjacent (Polygon p) {
		int eq = 0;
		for (int i = 0; i < this.vertices.size(); i++) {
			for (int j = 0; j < p.getVertices().size(); j++) {
				if (this.vertices.get(i) == p.getVertices().get(j)) {
					eq++;
				}
			}
		}if (eq > 1) {
			return true;
		}return false;
	}public int getSize () {
		return this.vertices.size();
	}

}
