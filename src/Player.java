
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
	
	public void putBomb(String lastAction, Level l, int timer) {
		int copyX = 1, copyY = 1;
		if (lastAction.equals("space") && l.board[xPos][yPos].available == true) {
			l.board[xPos][yPos] = new Bomb(timer);
			copyX=xPos;
			copyY=yPos;
		}
		if ((l.board[copyX][copyY].getField())+10<timer) {
			l.board[copyX][copyY] = new NormalBlock();
			}
	}


	public int getxPos() {
		return xPos;
	}

	public int getyPos() {
		return yPos;
	}

}
