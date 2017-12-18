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
        xCoord = xPos;
        yCoord = yPos;
    }

    public void move() {
        double copyX1 = Math.min((xCoord + 0.4), Constants.BOARD_SIZE), copyX2 = Math.min((xCoord + 0.4), Constants.BOARD_SIZE), copyX3 = Math.min((xCoord + 0.4), Constants.BOARD_SIZE), copyX4 = Math.min((xCoord + 0.4), Constants.BOARD_SIZE);
        double copyY1 = Math.min((yCoord + 0.8), Constants.BOARD_SIZE), copyY2 = Math.min((yCoord + 0.8), Constants.BOARD_SIZE), copyY3 = Math.min((yCoord + 0.8), Constants.BOARD_SIZE), copyY4 = Math.min((yCoord + 0.8), Constants.BOARD_SIZE);
        int playerXpos = ownLevel.sprites[0][0].getxPos();
        int playerYpos = ownLevel.sprites[0][0].getyPos();
        if (ownLevel.board[(int)(copyX1+0.2)][(int)(copyY1)].isWalkable() && playerXpos > xPos) {
            xCoord = xCoord + 0.2;
            xPos = (int) (xCoord);
        } else if (ownLevel.board[(int)(copyX2-0.2)][(int)(copyY2)].isWalkable() && playerXpos < xPos) {
            xCoord = xCoord - 0.2;
            xPos = (int) (xCoord);
        } else if (ownLevel.board[(int)(copyX3)][(int)(copyY3-0.2)].isWalkable() && playerYpos < yPos) {
            yCoord = yCoord - 0.2;
            yPos = (int) (yCoord);
        } else if (ownLevel.board[(int)(copyX4)][(int)(copyY4-0.2)].isWalkable() && playerYpos > yPos) {
            yCoord = yCoord + 0.2;
            yPos = (int) (yCoord);
        }
    }

    @Override
    public void move(String lastAction) {
        // TODO Auto-generated method stub

    }

}
