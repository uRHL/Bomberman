
public class Bomb extends Block{
	
	private int range;
	private int initialTime;
	
	
	public Bomb(int initialTime) {
		image = "bomb1.gif"; 
		walkable = true;
		available = false;
		this.initialTime=initialTime;
	}

	
	public int getField() {
		return initialTime;
	}
	
	

}
