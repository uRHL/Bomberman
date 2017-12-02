

public class Drop extends Enemy {

	public Drop(Level l) {
		image = "enemy211.png";
		alive = true;

		do {
			xPos = (int)(Math.random()*(Constants.BOARD_SIZE-1)+1);
			yPos = (int)(Math.random()*(Constants.BOARD_SIZE-1)+1);

		} while (l.board[xPos][yPos].available == false || (xPos == 1 && yPos == 1) );

	}

	public void move() {
		//here the code to move the drop 
	}

	public void move(Level l, int playerXpos, int playerYpos) {
		int copyX=xPos, copyY=yPos;
		if (l.board[++copyX][copyY].walkable && playerXpos>xPos) {
			xPos++;
		} else if (l.board[--copyX][copyY].walkable && playerXpos<xPos) {
			xPos--;
		} else if (l.board[copyX][--copyY].walkable && playerYpos<yPos) {
			yPos--;
		} else if (l.board[copyX][++copyY].walkable && playerYpos>yPos) {
			yPos++;
		}


	}

}
