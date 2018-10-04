package Engine3d;

public class Instance {
	
	private Window window;
	
	public boolean run () {
		
		
		
	}
	
	public void init(int WIDTH, int HEIGHT, String title) {
		try {
			this.window = new Window (WIDTH, HEIGHT, title);
		}catch (Throwable e) {
			e.printStackTrace();
			System.out.println ("Window could not be generated");
		}
	}

}
