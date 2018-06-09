package TriangleEngine03;

import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public class Vertex {

	private Vector3f position;
	private Vector4f colour;
	
	public Vertex (Vector3f pos, Vector4f col) {
		this.position = pos;
		this.colour = col;
	}
	
	public Vector3f getPos () {
		return this.position;
	}
	
}
