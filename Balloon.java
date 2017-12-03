

public class Balloon extends Enemy {

	public Balloon(Level l) {
		image = "enemy111.png";
		alive = true;

		do {
			xPos = (int)(Math.random()*(Constants.BOARD_SIZE-1)+1);
			yPos = (int)(Math.random()*(Constants.BOARD_SIZE-1)+1);

		} while (l.board[xPos][yPos].available == false || (xPos == 1 && yPos == 1) );

	}


	public void move () {

	}

	public void move(Level l) {
		int copyX=xPos, copyY=yPos;
		int randomNum = (int)(Math.random()*4);
		switch (randomNum) {
		case 0: if (l.board[++copyX][copyY].walkable) {
			xPos++;
		} break;
		case 1:  if (l.board[--copyX][copyY].walkable) {
			xPos--;
		} break;
		case 2: if (l.board[copyX][--copyY].walkable) {
			yPos--;
		} break;
		case 3: if (l.board[copyX][++copyY].walkable) {
			yPos++;
		}
		}

	}
}
