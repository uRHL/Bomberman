package sprites;

import structures.Constants;
import structures.Level;

/**
 * Represents a Balloon. This type of enemy moves randomly through the board.
 * The explosion of a bomb will kill them, in addition, if a player touches one,
 * the player will lose health points (hp). Every Balloon has an unique ID
 * identifying them, and a X position and Y position.
 * 
 * This class extends Enemy, which extends Sprite.
 * 
 * @author Ramón Hernández León. Bachelor Degree in Computer Science. UC3M
 * @author Miguel Espinosa Miñano. Bachelor Degree in Computer Science. UC3M
 * @since December, 6, 2017
 * @version 1.1
 */
public class Balloon extends Enemy {
    /**
     * Constructor. Initializes a balloon in a random position (if that position is
     * available)
     * 
     * @param l
     *            Level were the Balloon exists
     * @param id
     *            Unique ID identifying the balloon
     */
    public Balloon(Level l, int id) {
        super(id);
        image = "enemy111.png";
        alive = true;
        ownLevel = l;

        do {
            xPos = (int) (Math.random() * (Constants.BOARD_SIZE - 1) + 1);
            yPos = (int) (Math.random() * (Constants.BOARD_SIZE - 1) + 1);

        } while (ownLevel.board[xPos][yPos].isAvailable() == false || (xPos == 1 && yPos == 1));

    }

    /**
     * Method that moves randomly a Balloon
     */
    @Override
    public void move() {
        int copyX = xPos, copyY = yPos;
        int randomNum = (int) (Math.random() * 4);
        switch (randomNum) {
        case 0: // moves to the right
            if (ownLevel.board[++copyX][copyY].isWalkable()) {
                xPos++;
            }
            break;
        case 1: // moves to the left
            if (ownLevel.board[--copyX][copyY].isWalkable()) {
                xPos--;
            }
            break;
        case 2: // moves upwards
            if (ownLevel.board[copyX][--copyY].isWalkable()) {
                yPos--;
            }
            break;
        case 3: // moves downwards
            if (ownLevel.board[copyX][++copyY].isWalkable()) {
                yPos++;
            }
        }

    }

    /**
     * Override method, it will call the method move() (without arguments)
     */
    @Override
    public void move(String lastAction) {
        move();
    }
}
