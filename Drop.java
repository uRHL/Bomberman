

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
		int copyX1=xPos, copyX2=xPos,copyX3=xPos,copyX4=xPos; 
		int copyY1=yPos, copyY2=yPos,copyY3=yPos,copyY4=yPos; 
		if (l.board[++copyX1][copyY1].walkable && playerXpos>xPos) {
			xPos++;
		} else if (l.board[--copyX2][copyY2].walkable && playerXpos<xPos) {
			xPos--;
		} else if (l.board[copyX3][--copyY3].walkable && playerYpos<yPos) {
			yPos--;
		} else if (l.board[copyX4][++copyY4].walkable && playerYpos>yPos) {
			yPos++;
		}


	}

}
