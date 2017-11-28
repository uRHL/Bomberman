
public class Player {
	int xPos, yPos;
	String image = "bomberman111.png";
	
	public Player() {
		xPos = 1;
		yPos = 1;
	}

	public void move(String lastAction, Level l) {
		int copyX=xPos, copyY=yPos;
		if (lastAction.equals("right") && l.board[++copyX][copyY].walkable) {
			xPos++;
		} else if (lastAction.equals("left") && l.board[--copyX][copyY].walkable) {
			xPos--;
		} else if (lastAction.equals("up") && l.board[copyX][--copyY].walkable) {
			yPos--;
		} else if (lastAction.equals("down") && l.board[copyX][++copyY].walkable) {
			yPos++;
		}
	}

}
