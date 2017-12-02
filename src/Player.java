
public class Player {
	int xPos, yPos;
	String image = "bomberman111.png";
	boolean alive = true;
	int health = 100;
	//variables used for changing the images of the player
	private int up=0,down=0,left=0,right=0;
	
	public Player() {
		xPos = 1;
		yPos = 1;
	}

	public void move(String lastAction, Level l) {
		int copyX=xPos, copyY=yPos;
		if (lastAction.equals("right") && l.board[++copyX][copyY].walkable && alive) {
			if (right%5==0) {
				image = "bomberman131.png";  
			} else if ((right+1)%5==0) {
				image = "bomberman132.png";  
			} else if ((right+2)%5==0) {
				image = "bomberman133.png";  
			} else if ((right+3)%5==0) {
				image = "bomberman134.png";  
			} else if ((right)%5==0) {
				image = "bomberman135.png";  
			}
			right++;
			xPos++;
		} else if (lastAction.equals("left") && l.board[--copyX][copyY].walkable && alive) {
			if (left%5==0) {
				image = "bomberman121.png";  
			} else if ((left+1)%5==0) {
				image = "bomberman122.png";  
			} else if ((left+2)%5==0) {
				image = "bomberman123.png";  
			} else if ((left+3)%5==0) {
				image = "bomberman124.png";  
			} else if ((left)%5==0) {
				image = "bomberman125.png";  
			}
			left++;
			xPos--;
		} else if (lastAction.equals("up") && l.board[copyX][--copyY].walkable && alive) {
			if (up%5==0) {
				image = "bomberman101.png";  
			} else if ((up+1)%5==0) {
				image = "bomberman102.png";  
			} else if ((up+2)%5==0) {
				image = "bomberman103.png";  
			} else if ((up+3)%5==0) {
				image = "bomberman104.png";  
			} else if ((up+4)%5==0) {
				image = "bomberman105.png";  
			}
			up++;
			yPos--;
		} else if (lastAction.equals("down") && l.board[copyX][++copyY].walkable && alive) {
			if (down%5==0) {
				image = "bomberman111.png";  
			} else if ((down+1)%5==0) {
				image = "bomberman112.png";  
			} else if ((down+2)%5==0) {
				image = "bomberman113.png";  
			} else if ((down+3)%5==0) {
				image = "bomberman114.png";  
			} else if ((down+4)%5==0) {
				image = "bomberman115.png";  
			}
			down++;
			yPos++;
		}
	}
	
	public void putBomb(String lastAction, Level l, int timer) {
		int copyX = 1, copyY = 1;
		if (lastAction.equals("space") && l.board[xPos][yPos].available && alive) {
			l.board[xPos][yPos] = new Bomb(timer);
			copyX=xPos;
			copyY=yPos;
		}
		if ((l.board[copyX][copyY].getField())+10<timer) {
			l.board[copyX][copyY] = new NormalBlock();
			}
	}

	public void killed() {
		alive = false;
	}

	public int getxPos() {
		return xPos;
	}

	public int getyPos() {
		return yPos;
	}

}
