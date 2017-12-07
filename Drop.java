package sprites;

import structures.*;

public class Drop extends Enemy {

    public Drop(Level l, int id) {
        super(id);
        image = "enemy211.png";
        alive = true;
        ownLevel = l;

        do {
            xPos = (int) (Math.random() * (Constants.BOARD_SIZE - 1) + 1);
            yPos = (int) (Math.random() * (Constants.BOARD_SIZE - 1) + 1);

        } while (!ownLevel.board[xPos][yPos].isAvailable() || (xPos == 1 && yPos == 1));
    }

    public void move() {
        int copyX1 = xPos, copyX2 = xPos, copyX3 = xPos, copyX4 = xPos;
        int copyY1 = yPos, copyY2 = yPos, copyY3 = yPos, copyY4 = yPos;
        int playerXpos = ownLevel.sprites[0][0].getxPos();
        int playerYpos = ownLevel.sprites[0][0].getyPos();
        if (ownLevel.board[++copyX1][copyY1].isWalkable() && playerXpos > xPos) {
            xPos++;
        } else if (ownLevel.board[--copyX2][copyY2].isWalkable() && playerXpos < xPos) {
            xPos--;
        } else if (ownLevel.board[copyX3][--copyY3].isWalkable() && playerYpos < yPos) {
            yPos--;
        } else if (ownLevel.board[copyX4][++copyY4].isWalkable() && playerYpos > yPos) {
            yPos++;
        }
    }

    @Override
    public void move(String lastAction) {
        // TODO Auto-generated method stub

    }

}
