
public abstract class Block {
	String image;
	boolean walkable;
	boolean available;

	public String getImage() {
		return image;
	}
	public abstract int getField();
		
	
}
