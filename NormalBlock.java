
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
		available = true;
		image = null;
	}

	@Override
	public int getField() {
		// TODO Auto-generated method stub
		return 0;
	}
}
