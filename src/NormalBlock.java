
public class NormalBlock extends Block {
	private short red=178, green=255, blue=102;
	
	public short getRed() {
		return red;
	}

	public short getGreen() {
		return green;
	}

	public short getBlue() {
		return blue;
	}

	public NormalBlock() {
		walkable=true;
	}
}
