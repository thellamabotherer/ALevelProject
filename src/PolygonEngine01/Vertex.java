package PolygonEngine01;

import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public class Vertex {

	private Vector3f pos;
	private Vector4f col;

	public Vertex(Vector3f pos) { // add colour arg later, black+white only for now
		this.pos = pos;
	}

	public Vector3f getPos() {
		return this.pos;
	}

}
