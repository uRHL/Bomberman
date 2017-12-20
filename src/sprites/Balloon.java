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
     * Maximum speed reachable for a player
     */
    private final float BALLOON_MAX_SPEED = 1.0F;
    /**
     * Minimum speed reachable for a player
     */
    private final float BALLOON_MIN_SPEED = 0.1F;
    
    /**
     * @see {@link Enemy#points}
     */
    private final int POINTS_BALLOON = 100;

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
        setMax_speed(BALLOON_MAX_SPEED);
        setMin_speed(BALLOON_MIN_SPEED);
        points = POINTS_BALLOON;

        do {
            xPos = (int) (Math.random() * (Constants.BOARD_SIZE - 1) + 1);
            yPos = (int) (Math.random() * (Constants.BOARD_SIZE - 1) + 1);

        } while (!ownLevel.board[xPos][yPos].isAvailable() || (xPos == 1 && yPos == 1));

        xCoord = xPos;
        yCoord = yPos;
    }

    /**
     * Method that moves in a random direction a Balloon
     */
    @Override
    public void move() {
        double copyX = Math.min((xCoord + 0.4), Constants.BOARD_SIZE);
        double copyY = Math.min((yCoord + 0.8), Constants.BOARD_SIZE);
        // Chooses a random direction
        switch ((int) (Math.random() * 4)) {
        case 0: // moves to the right
            if (ownLevel.board[(int) (copyX + 0.2)][(int) (copyY)].isWalkable()) {
                xCoord = xCoord + 0.2;
                xPos = (int) (xCoord);
            }
            break;
        case 1: // moves to the left
            if (ownLevel.board[(int) (copyX - 0.2)][(int) (copyY)].isWalkable()) {
                xCoord = xCoord - 0.2;
                xPos = (int) (xCoord);
            }
            break;
        case 2: // moves upwards
            if (ownLevel.board[(int) (copyX)][(int) (copyY - 0.2)].isWalkable()) {
                yCoord = yCoord - 0.2;
                yPos = (int) (yCoord);
            }
            break;
        case 3: // moves downwards
            if (ownLevel.board[(int) (copyX)][(int) (copyY + 0.2)].isWalkable()) {
                yCoord = yCoord + 0.2;
                yPos = (int) (yCoord);
            }
        }

    }

    /**
     * Implementation of the abstract method {@link Sprite#move(String)
     * move(String)}. This method has no code because the movements do not depend on
     * 'lastAction', instead the move is done with {@link Balloon#move()}.
     */
    @Override
    public void move(String lastAction) {
        move();
    }

    /**
     * Sets the {@link Sprite#alive alive} to false.
     */
    @Override
    public void killed() {
        alive = false;
        image = "enemy100.png";

    }
}
