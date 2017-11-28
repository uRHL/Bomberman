
public class BrickBlock extends Block {
	boolean availableBomb = false, destroyed = false;
	Bonus bonus;

	public BrickBlock() {
		image = "bricks.gif";
		walkable = false;
		if ((int)(Math.random()*2)==0) {
			bonus= new Bonus();
		}
	}
}
