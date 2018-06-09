package TestGame01;

import org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.vector.Vector3f;

public class Vertex {

	public Vector3f pos = new Vector3f (-1, -1, -1);

	public Vertex(Vector3f pos) {
		this.pos = pos;
	}

	public Vector3f getPos() {
		//try {
			return pos;
		//} catch (Exception e) {
		//	return (new Vector3f(-1, -1, -1));
		//}
	}

	public void setPos(Vector3f pos) {
		this.pos = pos;
	}

	public boolean isEqual(Vertex v) {
		try {
			
			if ((this.pos.x == v.getPos().x) && (this.pos.y == v.getPos().y) && (this.pos.z == v.getPos().z)) {
				// System.out.println("equal vertex");
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			
			System.out.print(this.pos.x);
			System.out.print(this.pos.y);
			System.out.println(this.pos.z);
			
			System.out.println(v);
			
			System.out.print(v.getPos().x);
			System.out.print(v.getPos().y);
			System.out.println(v.getPos().z);
			
			return false;
		}
	}

}
